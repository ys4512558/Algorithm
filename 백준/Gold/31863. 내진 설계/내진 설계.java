import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[][] isv;
    static int N, M;
    static int total, broken;
    static Position start;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        isv = new int[N][M];

        total = 0;
        broken = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '@') {
                    start = new Position(i, j);
                    //진원지는 다시 올 필요 없음
                    isv[i][j] = 2;
                } else if (map[i][j] == '*' || map[i][j] == '#') {
                    total++;
                }
            }
        }
        Queue<Position> queue = first();
        bfs(queue);
        System.out.println(broken + " " + (total - broken));
    }

    private static void bfs(Queue<Position> queue) {
        while (!queue.isEmpty()) {
            Position position = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = position.x + dx[i];
                int ny = position.y + dy[i];
                if (isOutRange(nx, ny)) continue;
                earthquake(queue, nx, ny);
            }
        }
    }

    private static void earthquake(Queue<Position> queue, int nx, int ny) {
        if (map[nx][ny] == '*') {
            if (++isv[nx][ny] == 1) {
                queue.offer(new Position(nx, ny));
                map[nx][ny] = '.';
                broken++;
            }
        } else if (map[nx][ny] == '#') {
            if (++isv[nx][ny] == 2) {
                queue.offer(new Position(nx, ny));
                map[nx][ny] = '.';
                broken++;
            }
        }
    }

    private static Queue<Position> first() {
        Queue<Position> queue = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 2; j++) {
                int nx = start.x + (dx[i] * j);
                int ny = start.y + (dy[i] * j);
                if (isOutRange(nx, ny)) {j++; continue;}
                earthquake(queue, nx, ny);
            }
        }
        return queue;
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M || map[x][y] == '|';
    }
}

class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}