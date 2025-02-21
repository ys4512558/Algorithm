import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coins = new int[N];
            for(int j = 0; j < N; j++) {
                coins[j] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            sb.append(knapsack(N, M, coins));
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
    
    public static int knapsack(int N, int M, int[] coins){
        int[][] dp = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j < coins[i - 1]) continue;
                if(j == coins[i - 1]) dp[i][j]++;
                if(dp[i][j - coins[i - 1]] != 0) dp[i][j] += dp[i][j - coins[i - 1]];
            }
        }
        return dp[N][M];
    }
}
