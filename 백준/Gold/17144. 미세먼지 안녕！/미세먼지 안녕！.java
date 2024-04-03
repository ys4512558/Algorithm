import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int[] airCleaner1, airCleaner2;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static Queue<Dust> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1 && airCleaner1 == null) {
                    airCleaner1 = new int[]{i, j};
                    airCleaner2 = new int[]{i + 1, j};
                }
                if (map[i][j] > 0) {
                    Dust dust = new Dust(i, j, map[i][j]);
                    queue.offer(dust);
                }
            }
        }

        while (T-- > 0) {
            diffusion();
        }
        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] > 0) res += map[i][j];
            }
        }
        System.out.println(res);
    }
    private static void diffusion() {
        while (!queue.isEmpty()) {
            Dust d = queue.poll();

            int quantity = (int) Math.floor(d.cost / 5);
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                int nx = d.x + dx[i];
                int ny = d.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == -1) continue;
                map[nx][ny] += quantity;
                cnt++;
            }
            map[d.x][d.y] -= quantity * cnt;
        }
        windUp();
        windDown();
        addQueue();
    }

    private static void addQueue() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] > 0) queue.offer(new Dust(i, j, map[i][j]));
            }
        }
    }

    //밀기.
    //아래쪽 공기
    private static void windDown() {
        int row = airCleaner2[0];
        int col = airCleaner2[1];

        //공청 같은 행
        int tmp1 = map[row][C - 1];
        for (int c = C - 1; c >= 2; c--) {
            map[row][c] = map[row][c - 1];
        }
        map[row][1] = 0;
        // 오른쪽 열
        int tmp2 = map[R - 1][C - 1];
        for (int r = R - 1; r >= row + 2; r--) {
            map[r][C - 1] = map[r - 1][C - 1];
        }
        map[row + 1][C - 1] = tmp1;
        //아래 행
        tmp1 = map[R - 1][0];
        for (int c = 0; c <= C - 2; c++) {
            map[R - 1][c] = map[R - 1][c + 1];
        }
        map[R - 1][C - 2] = tmp2;
        //왼 열
        for (int r = row + 1; r < R - 2; r++) {
            map[r][col] = map[r + 1][col];
        }
        map[R - 2][0] = tmp1;
    }

    //위쪽 공기
    private static void windUp() {
        int row = airCleaner1[0];
        int col = airCleaner1[1];

        //공청 같은 행
        int tmp1 = map[row][C - 1];
        for (int c = C - 1; c >= 2; c--) {
            map[row][c] = map[row][c - 1];
        }
        map[row][1] = 0;
        // 오른쪽 열
        int tmp2 = map[0][C - 1];
        for (int r = 0; r <= row - 2; r++) {
            map[r][C - 1] = map[r + 1][C - 1];
        }
        map[row - 1][C - 1] = tmp1;
        //위 행
        tmp1 = map[0][0];
        for (int c = 0; c <= C - 2; c++) {
            map[0][c] = map[0][c + 1];
        }
        map[0][C - 2] = tmp2;
        //왼 열
        for (int r = row - 1; r >= 2; r--) {
            map[r][col] = map[r - 1][col];
        }
        map[1][col] = tmp1;
    }


    private static void print(int[][] map) {
        System.out.println("print");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Dust{
    int x, y, cost;

    public Dust(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}