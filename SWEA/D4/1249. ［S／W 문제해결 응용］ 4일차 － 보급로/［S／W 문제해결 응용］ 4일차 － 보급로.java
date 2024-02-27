import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ");
            sb.append(solve1249()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static long solve1249() throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][] res = new long[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
                res[i][j] = -1;
            }
        }



        return bfs1249(map, res);
    }

    private static long bfs1249(int[][] map, long[][] res) {
        class Point{
            int x, y;
            long score;
            public Point(int x, int y, long score) {
                this.x = x;
                this.y = y;
                this.score = score;
            }
        }
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        int N = map.length;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = cur.y + dy[i];
                int col = cur.x + dx[i];
                if (row < 0 || row >= N || col < 0 || col >= N) {
                    continue;
                }
                if (res[row][col] != -1 && res[row][col] <= cur.score + map[row][col]) {
                    continue;
                }
                res[row][col] = cur.score + map[row][col];
                queue.add(new Point(col, row, res[row][col]));
            }
        }
        return res[N - 1][N - 1];
    }
}