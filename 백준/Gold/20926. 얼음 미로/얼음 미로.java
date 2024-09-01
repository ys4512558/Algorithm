import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][][] cost;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
    static final int INF = Integer.MAX_VALUE / 2;
    static final int R = -1, E = -2, H = -3;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        Tera start = null;
        map = new int[N][M];
        cost = new int[4][N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                for (int k = 0; k < 4; k++) {
                    cost[k][i][j] = INF;
                }
                if (map[i][j] == 'T') {
                    map[i][j] = 0;
                    start = new Tera(i, j, 0);
                } else if (map[i][j] == 'E') {
                    map[i][j] = E;
                } else if (map[i][j] == 'R') {
                    map[i][j] = R;
                } else if (map[i][j] == 'H') {
                    map[i][j] = H;
                }
            }
        }
        System.out.println(dijkstra(start));
    }

    private static int dijkstra(Tera start) {
        PriorityQueue<Tera> pq = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            cost[i][start.x][start.y] = 0;
        }
        pq.offer(start);

        while (!pq.isEmpty()) {
            Tera tera = pq.poll();

            if (map[tera.x][tera.y] == E) {
                return tera.slide;
            }

            for (int i = 0; i < 4; i++) {
                Tera next = sliding(tera, i);
                if(next == null) continue;
                if (cost[i][next.x][next.y] > next.slide) {
                    cost[i][next.x][next.y] = next.slide;
                    pq.offer(next);
                }
            }
        }

        return -1;
    }

    private static Tera sliding(Tera tera, int dir) {
        int cx = tera.x;
        int cy = tera.y;
        int slide = 0;

        while (true) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            if (isOutRange(nx, ny)) return null;
            if (cost[dir][nx][ny] <= tera.slide + slide) return null;
            if (map[nx][ny] == R) {
                return new Tera(cx, cy, tera.slide + slide);
            } else if (map[nx][ny] == E) {
                return new Tera(nx, ny, tera.slide + slide);
            }
            slide += map[nx][ny] - '0';
            cx = nx;
            cy = ny;
        }
    }

    private static boolean isOutRange(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= M || map[x][y] == H) return true;
        return false;
    }

}

class Tera implements Comparable<Tera> {

    int x, y, slide;

    public Tera(int x, int y, int slide) {
        this.x = x;
        this.y = y;
        this.slide = slide;
    }

    @Override
    public int compareTo(Tera o) {
        return Integer.compare(this.slide, o.slide);
    }
}