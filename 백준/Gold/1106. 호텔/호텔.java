import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] costs = new int[N + 1][2];
        int[][] dp = new int[N + 1][C + 101];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            costs[i] = new int[]{cost, customer};
            int cnt = 1;
            for (int j = customer; customer * cnt <= C + 100; j += customer) {
                dp[i][j] = cost * cnt++;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C + 100; j++) {
                if (dp[i - 1][j] != 0 && dp[i][j] != 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                else if(dp[i - 1][j] != 0) dp[i][j] = dp[i - 1][j];

                if (j < costs[i][1]) continue;

                if (dp[i][j] != 0) {
                    if (dp[i - 1][j - costs[i][1]] != 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - costs[i][1]] + costs[i][0]);
                    }
                    if (dp[i][j - costs[i][1]] != 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - costs[i][1]] + costs[i][0]);
                    }
                } else {
                    if (dp[i - 1][j - costs[i][1]] != 0) {
                        dp[i][j] = dp[i - 1][j - costs[i][1]] + costs[i][0];
                    }
                    if (dp[i][j - costs[i][1]] != 0) {
                        dp[i][j] = dp[i][j - costs[i][1]] + costs[i][0];
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = C; i <= C + 100; i++) {
            if(dp[N][i] == 0) continue;
            min = Math.min(min, dp[N][i]);
        }
        System.out.println(min);
    }
}