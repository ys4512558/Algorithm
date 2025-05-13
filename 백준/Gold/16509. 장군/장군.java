import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int[][] dxy = new int[][]{
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1},
            {-1, -1},
            {1, -1},
            {-1, 1},
            {1, 1},
    };
    static final int R = 10;
    static final int C = 9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(new Info(r1, c1), new Info(r2, c2)));
    }

    private static int bfs(Info start, Info end) {
        Queue<Info> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[][] isv = new boolean[10][9];
        isv[start.r][start.c] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while (size-- > 0) {
                Info info = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = info.r + dx[i];
                    int ny = info.c + dy[i];

                    if(nx == end.r && ny == end.c) continue;
                    for (int j = 0; j < 2; j++) {
                        for (int k = 1; k <= 2; k++) {
                            int nnx = nx + (dxy[i * 2 + j][0] * k);
                            int nny = ny + (dxy[i * 2 + j][1] * k);
                            if(k == 1 && nnx == end.r && nny == end.c) break;
                            if(isOutRange(nnx, nny)) break;
                            if (k == 2 && !isv[nnx][nny]) {
                                if(nnx == end.r && nny == end.c) return count;
                                isv[nnx][nny] = true;
                                queue.offer(new Info(nnx, nny));
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isOutRange(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}

class Info {
    int r, c;

    public Info(int r, int c) {
        this.r = r;
        this.c = c;
    }
}