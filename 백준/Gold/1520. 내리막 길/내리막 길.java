import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, dp;
    static int N, M, cnt = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        sb.append(dfs(0, 0));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int x, int y) {
        if(x == N - 1 && y == M - 1){
            return 1;
        }

        if(dp[x][y] != -1){
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int r = x + dx[i];
            int c = y + dy[i];

            if(r < 0 || r >= N || c < 0 || c >= M || map[r][c] >= map[x][y]) continue;
            dp[x][y] += dfs(r, c);
        }
        return dp[x][y];
    }
}