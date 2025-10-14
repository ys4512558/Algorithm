import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//1. Info 객체를 통해 벽을 가장 적게 부순 애들 먼저 BFS로 보내기?
//2. 이렇게 하면 방문한 위치를 어떻게 다시 이동하지 않을까
//3. [부순 벽 수][x][y]로 하면 될 것 같은데 부순 벽 수를 몇 차원으로 만들어야 할까?
//4. 최대 50 x 50 이고 2500칸에서 시작, 끝 제외 직선거리 49(우) + 49(하)가 최대일듯 모두 막혀있다는 가정하에
public class Main {
    static int[][] map;
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(solve());
    }

    public static int solve() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        //최대 (N + N - 2)개의 벽을 부숴야함
        boolean[][][] isv = new boolean[N + N - 2][N][N];
        pq.offer(new Info(0, 0, 0));
        isv[0][0][0] = true;

        int result = 0;
        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if(info.x == N - 1&& info.y == N - 1) {
                result = info.count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = info.x + dx[i];
                int ny = info.y + dy[i];

                if(isOutRange(nx, ny)) continue;

                int count = info.count;
                //검정색이면
                if (map[nx][ny] == 0) count++;
                //최대 개수보다 많은 벽을 부수면 무조건 답이 아니므로 버리기
                if(count >= (N + N - 2)) continue;
                if (isv[count][nx][ny]) continue;
                pq.offer(new Info(nx, ny, count));
                isv[count][nx][ny] = true;
            }
        }
        return result;
    }

    public static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Info implements Comparable<Info> {
    int x, y;
    int count; //부순 벽 수

    public Info(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    public int compareTo(Info o) {
        return Integer.compare(this.count, o.count);
    }
}