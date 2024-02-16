import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = input[j] - '0';
            }
        }

        bfs(map);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void bfs(int[][] map) {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        boolean[][] isv = new boolean[N][M];
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, 1));
        isv[0][0] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int x = p.row;
            int y = p.col;
            int b = p.breadth;

            for (int i = 0; i < 4; i++) {
                int row = x + dx[i];
                int col = y + dy[i];
                if(row < 0 || row >= N || col < 0 || col >= M || isv[row][col] || map[row][col] == 0) continue;
                if (row == N - 1 && col == M - 1) {
                    sb.append(b + 1);
                    return;
                }
                queue.offer(new Point(row, col, b + 1));
                isv[row][col] = true;
            }
        }
    }
}

class Point{
    int row;
    int col;
    int breadth;

    public Point(int row, int col, int breadth) {
        this.row = row;
        this.col = col;
        this.breadth = breadth;
    }
}