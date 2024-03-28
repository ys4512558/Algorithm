import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[] start;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int res = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        Queue<int[]> water = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    start = new int[]{i, j, 0};
                    map[i][j] = '.';
                }
                if(map[i][j] == '*') water.offer(new int[]{i, j});
            }
        }
        bfs(water);
        System.out.println(res == -1 ? "KAKTUS" : res);
    }

    private static void bfs(Queue<int[]> water) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] isv = new boolean[N][M];
        queue.offer(start);

        while (!queue.isEmpty()) {
            //물 채우기
            int waterSize = water.size();
            while (waterSize-- > 0) {
                int[] w = water.poll();

                for (int i = 0; i < 4; i++) {
                    int wx = w[0] + dx[i];
                    int wy = w[1] + dy[i];
                    if(wx < 0 || wy < 0 || wx >= N || wy >= M || map[wx][wy] != '.') continue;
                    map[wx][wy] = '*';
                    water.offer(new int[]{wx, wy});
                }
            }
            int size = queue.size();
            while (size-- > 0) {
                int[] p = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = p[0] + dx[i];
                    int ny = p[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 'X' || map[nx][ny] == '*' || isv[nx][ny]) continue;
                    if(map[nx][ny] == 'D') {
                        res =  p[2] + 1;
                        return;
                    }
                    queue.offer(new int[]{nx, ny, p[2] + 1});
                    isv[nx][ny] = true;
                }
            }
        }
    }
}