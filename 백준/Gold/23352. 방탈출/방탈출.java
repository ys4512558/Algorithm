import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int len;
    static Info res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = new Info(0, 0, 0, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;
                if(len <= 1 && res.sum() < map[i][j]) res = new Info(i, j, map[i][j], 0);
                bfs(i, j);
            }
        }
        System.out.println(res.sum());
    }

    private static void bfs(int x, int y) {
        Queue<Info> queue = new ArrayDeque<>();
        Info start = new Info(x, y, map[x][y], 0);
        queue.offer(start);
        boolean[][] isv = new boolean[N][M];
        isv[x][y] = true;

        int breadth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Info info = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = info.x + dx[i];
                    int ny = info.y + dy[i];

                    if(isOutRange(nx, ny) || isv[nx][ny]) continue;
                    Info next = new Info(nx, ny, info.start, map[nx][ny]);
                    queue.offer(next);
                    isv[nx][ny] = true;
                    if ((len == breadth && res.sum() < next.sum()) || len < breadth) {
                        len = breadth;
                        res = next;
                    }
                }
            }
            breadth++;
        }

    }

    public static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 0;
    }
}

class Info {
    int x, y;
    int start;
    int end;

    public Info(int x, int y, int start, int end) {
        this.x = x;
        this.y = y;
        this.start = start;
        this.end = end;
    }

    public int sum() {
        return start + end;
    }
}
