import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 1000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        
        long[] dp = new long[MAX];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
        } 
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");   
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
