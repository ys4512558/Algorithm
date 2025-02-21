import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[W + 1][2];
        int max = 0;
        for(int i = 1; i <= T; i++) {
            int N = Integer.parseInt(br.readLine()) - 1;
            for(int j = 0; j <= Math.min(W, i); j++) {
                if(j == 0) {
                    dp[0][0] += N == 0 ? 1 : 0;   
                    max = Math.max(max, dp[0][0]);
                    continue;
                }
                dp[j][N] = Math.max(dp[j][N], dp[j - 1][1 - N]) + 1;
                // dp[j][1 - N] = Math.max(dp[j][1 - N], dp[j - 1][N]);
                max = Math.max(max, dp[j][N]);
            }
        }
        System.out.println(max);
    }
}
