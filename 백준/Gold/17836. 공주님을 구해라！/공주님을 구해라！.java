import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = bfs();
        System.out.println(res == -1 ? "Fail" : res);

    }

    private static int bfs() {
        Queue<Info> queue = new ArrayDeque<>();
        queue.offer(new Info(0, 0, false));
        boolean[][][] isv = new boolean[2][N][M];
        isv[0][0][0] = true;

        int t = 0;
        while (!queue.isEmpty() && t < T) {
            int size = queue.size();
            while (size-- > 0) {
                Info info = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = info.x + dx[i];
                    int ny = info.y + dy[i];

                    if(isOutRange(nx, ny)) continue;
                    if(!info.sward && map[nx][ny] == 1) continue;
                    if(nx == N - 1 && ny == M - 1) return t + 1;
                    boolean sward = info.sward || map[nx][ny] == 2;
                    if (sward) {
                        if(isv[1][nx][ny]) continue;
                        isv[1][nx][ny] = true;
                        queue.offer(new Info(nx, ny, sward));
                    } else if(!isv[0][nx][ny]){
                        if(isv[0][nx][ny]) continue;
                        isv[0][nx][ny] = true;
                        queue.offer(new Info(nx, ny, sward));
                    }
                }
            }
            t++;
        }

        return -1;
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

}

class Info {
    int x, y;
    boolean sward;

    public Info(int x, int y, boolean sward) {
        this.x = x;
        this.y = y;
        this.sward = sward;
    }
}