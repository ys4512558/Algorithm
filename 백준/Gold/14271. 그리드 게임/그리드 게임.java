import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] isv;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isv = new boolean[4000][4000];
        map = new char[4000][4000];

        Queue<Pair> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                int x = i + 2000 - (N / 2);
                int y = j + 2000 - (M / 2);
                map[x][y] = c;
                if(c == '.') continue;
                queue.offer(new Pair(x, y));
                isv[x][y] = true;
            }
        }

        int cnt = Integer.parseInt(br.readLine());
        System.out.println(bfs(queue, cnt));
    }

    private static int bfs(Queue<Pair> queue, int cnt) {
        int res = queue.size();

        while (!queue.isEmpty() && cnt != 0) {
            int size = queue.size();
            cnt--;
            while (size-- > 0) {
                Pair pair = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = pair.x + dx[i];
                    int ny = pair.y + dy[i];

                    if(isv[nx][ny]) continue;
                    queue.offer(new Pair(nx, ny));
                    isv[nx][ny] = true;
                    res++;
                }
            }
        }
        return res;
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}