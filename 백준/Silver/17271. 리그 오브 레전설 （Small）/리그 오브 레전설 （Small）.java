import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        dp[1] = 1;
        if (M <= N) {
            dp[M] = 1;
        }
        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i] + dp[i - 1]) % MOD;
            if (M < i) {
                dp[i] = (dp[i] + dp[i - M]) % MOD;
            }
        }
        System.out.println(dp[N]);
    }

}