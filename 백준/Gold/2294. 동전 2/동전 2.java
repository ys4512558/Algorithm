import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[100001];
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
            dp[coin] = 1;
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 0; j < N; j++) {
                if(i <= coins[j] || dp[i - coins[j]] == 0) continue;
                dp[i] = dp[i] == 0 ? dp[i - coins[j]] + 1 : Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        System.out.println(dp[K] == 0 ? -1 : dp[K]);
    }
}