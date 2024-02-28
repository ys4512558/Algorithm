import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int ROW = 0;
    static final int DIAG = 1;
    static final int COL = 2;

    static int N;
    static int[][] map; 
    static long[][][] dp;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new long[3][N + 1][N + 1];
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp[COL][1][2] = 1;
        
        for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if(map[i][j] == 1) continue;
				dp[ROW][i][j] = dp[ROW][i-1][j] + dp[DIAG][i-1][j];
				dp[COL][i][j] = dp[COL][i][j-1] + dp[DIAG][i][j-1];
				if(map[i-1][j] == 1 || map[i][j-1] == 1) continue;
				dp[DIAG][i][j] = dp[COL][i - 1][j - 1] + dp[ROW][i - 1][j - 1] + dp[DIAG][i-1][j-1];	
			}
		}

        long sum = dp[ROW][N][N] + dp[COL][N][N] + dp[DIAG][N][N];
        StringBuilder sb = new StringBuilder();
        sb.append(sum);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}