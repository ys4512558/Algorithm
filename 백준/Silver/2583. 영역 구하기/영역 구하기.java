import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] map;
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            for (int j = x1; j < x2; j++) {
                for (int k = y1; k < y2; k++) {
                    map[j][k] = true;
                }
            }
        }

        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]) continue;
                count++;
                pq.offer(bfs(i, j));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(count);
        sb.append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll() + " ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        boolean[][] isv = new boolean[M][N];
        isv[x][y] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            count++;
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if(isOutRange(nx, ny) || map[nx][ny] || isv[nx][ny]) continue;
                queue.offer(new int[]{nx, ny});
                isv[nx][ny] = true;
                map[nx][ny] = true;
            }
        }
        return count;
    }

    public static boolean isOutRange(int x, int y) {
        return x < 0 || x >= M || y < 0 || y >= N;
    }
}