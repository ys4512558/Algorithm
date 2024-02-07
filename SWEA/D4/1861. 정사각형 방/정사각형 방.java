import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static int[][] map;
    static int max, num;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            solve();
            sb.append(num).append(" ").append(max).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        num = 0;
        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int res = bfs(i, j);
                if (max < res) {
                    num = map[i][j];
                    max = res;
                } else if (max == res) {
                    num = Math.min(num, map[i][j]);
                }
            }
        }
    }

    private static int bfs(int row, int col) {
        boolean[][] isVisited = new boolean[N][N];
        Queue<Point> queue = new ArrayDeque<>();
        isVisited[row][col] = true;
        queue.offer(new Point(row, col));
        int cnt = 1;
        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int k = 0; k < 4; k++) {
                int x = p.row + dx[k];
                int y = p.col + dy[k];

                if (x < 0 || x >= N || y < 0 || y >= N || isVisited[x][y]) {
                    continue;
                }
                if (map[x][y] == map[p.row][p.col] + 1) {
                    isVisited[x][y] = true;
                    queue.offer(new Point(x, y));
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
class Point{
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}