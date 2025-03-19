import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isv;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        dp = new long[21];
        dp[2] = 1;
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            isv = new boolean[N];            
            // dfs(0, N);
            long count = memo(N);
            sb.append(count).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static long memo(int n) {
        if(n == 1) return 0;
        if(dp[n] != 0) return dp[n];
        return dp[n] = (n - 1) * (memo(n - 1) + memo(n - 2));
    }
    
    // public static void dfs(int depth, int N) {
        // if(depth == N) {
            // count++;
            // return;
        // }
        
        // for(int i = 0; i < N; i++) {
            // if(depth == i || isv[i]) continue;
            // isv[i] = true;
            // dfs(depth + 1, N);
            // isv[i] = false;
        // }
    // } 
}
