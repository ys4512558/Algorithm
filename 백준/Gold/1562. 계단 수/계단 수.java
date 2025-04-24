import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int MOD = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][][] dp = new int[N + 1][10][1 << 10];
        for (int i = 1; i < 10; i++) {
            int bit = 1 << i;
            dp[1][i][bit] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1 << 10; k++) {
                    int visit = k | (1 << j);
                    if (j == 0) {
                        dp[i][j][visit] += dp[i - 1][j + 1][k];
                    } else if (j == 9) {
                        dp[i][j][visit] += dp[i - 1][j - 1][k];
                    } else {
                        dp[i][j][visit] += ((dp[i - 1][j - 1][k] % MOD) + (dp[i - 1][j + 1][k] % MOD)) % MOD;
                    }
                    dp[i][j][visit] %= MOD;
                }
            }
        }

        long count = 0;
        for (int i = 0; i < 10; i++) {
            count = ((count % MOD) + (dp[N][i][(1 << 10) - 1] % MOD)) % MOD;
        }
        System.out.println(count);
    }
}
