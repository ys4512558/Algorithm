import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int N = 19;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 1};
    static int[] dx = {1, 1, 1, 0};
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = 0;
        Loop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    isVisited = new boolean[N][N];
                    res = dfs(i, j, k, map[i][j]);
                    if (res == 5) {
                        sb.append(map[i][j]).append("\n");
                        sb.append(k == 0 ? (i + 5) + " " + (j - 3) : (i + 1) + " " + (j + 1));
                        break Loop;
                    }
                }
            }
        }
        sb.append(res == 5 ? "" : 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int row, int col, int direction, int color) {
        if (row < 0 || row >= N || col < 0 || col >= N || map[row][col] != color || isVisited[row][col]) {
            return 0;
        }
        isVisited[row][col] = true;
        int res = dfs(row + dy[direction], col + dx[direction], direction, color)
                + dfs(row - dy[direction], col - dx[direction], direction, color);
        return res + 1;
    }
}