import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int W, H;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H + 2][W + 2];
            Info start = null;
            Queue<Info> fires = new ArrayDeque<>();
            boolean[][] isFire = new boolean[H + 2][W + 2];
            for (int j = 1; j <= H; j++) {
                String input = br.readLine();
                for (int k = 1; k <= W; k++) {
                    map[j][k] = input.charAt(k - 1);
                    if (map[j][k] == '@') {
                        map[j][k] = '.';
                        start = new Info(j, k);
                    } else if(map[j][k] == '*') {
                        fires.offer(new Info(j, k));
                        isFire[j][k] = true;
                    }
                }
            }
            int res = simulate(start, fires, isFire);
            sb.append(res == -1 ? "IMPOSSIBLE" : res);
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int simulate(Info start, Queue<Info> fires, boolean[][] isFire) {
        Queue<Info> queue = new ArrayDeque<>();
        boolean[][] isv = new boolean[H + 2][W + 2];
        queue.offer(start);
        isv[start.x][start.y] = true;

        int breadth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            bfs(fires, isFire);
            while (size-- > 0) {
                Info info = queue.poll();

                if(info.x == 0 || info.y == 0 || info.x == H + 1 || info.y == W + 1) return breadth;
                for (int i = 0; i < 4; i++) {
                    int nx = info.x + dx[i];
                    int ny = info.y + dy[i];

                    if (isOutRange(nx, ny) || isv[nx][ny] || isFire[nx][ny]) continue;
                    queue.offer(new Info(nx, ny));
                    isv[nx][ny] = true;
                }
            }
            breadth++;
        }
        return -1;
    }

    private static void bfs(Queue<Info> fires, boolean[][] isFire) {
        int size = fires.size();

        while (size-- > 0) {
            Info fire = fires.poll();

            for (int i = 0; i < 4; i++) {
                int nx = fire.x + dx[i];
                int ny = fire.y + dy[i];

                if (isOutRange2(nx, ny) || isFire[nx][ny]) continue;
                fires.offer(new Info(nx, ny));
                isFire[nx][ny] = true;
            }
        }
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x > H + 1 || y > W + 1 || map[x][y] == '#';
    }
    private static boolean isOutRange2(int x, int y) {
        return x <= 0 || y <= 0 || x >= H + 1 || y >= W + 1 || map[x][y] == '#';
    }
}

class Info{
    int x, y;

    public Info(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
