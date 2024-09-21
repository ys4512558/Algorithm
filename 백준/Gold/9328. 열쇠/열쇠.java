import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<State> starts;
    static char[][] map;
    static boolean[][] isv;
    static BufferedReader br;
    static boolean flag;
    static int N, M, cnt;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            solution();
        }
    }

    private static void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        State.key = 0;
        starts = new ArrayList<>();
        map = new char[N][M];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '*') continue;
                if (i == 0 || j == 0 || i == (N - 1) || j == (M - 1)) {
                    if (map[i][j] >= 'a' && map[i][j] <= 'z') {
                        State.key |= (1 << (map[i][j] - 'a'));
                        map[i][j] = '.';
                    } else if (map[i][j] == '$') {
                        map[i][j] = '.';
                        cnt++;
                    }
                    starts.add(new State(i, j));
                }
            }
        }
        String str = br.readLine();
        int key = 0;
        if (!str.equals("0")) {
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                key |= (1 << c - 'a');
            }
        }
        State.key |= key;
        flag = false;
        while (true) {
            for (int i = 0; i < starts.size(); i++) {
                State start = starts.get(i);
                if (map[start.x][start.y] >='A' && map[start.x][start.y] <= 'Z'){
                    int bit = (1 << (map[start.x][start.y] - 'A'));
                    if ((State.key & bit) != 0) {
                        map[start.x][start.y] = '.';
                        bfs(start);
                    }
                } else {
                    bfs(start);
                }
            }
            if(!flag) break;
            flag = false;
        }
        System.out.println(cnt);
    }

    private static void bfs(State start) {

        Queue<State> queue = new ArrayDeque<>();
        isv = new boolean[N][M];
        isv[start.x][start.y] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            State state = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = state.x + dx[i];
                int ny = state.y + dy[i];

                if (isOutRange(nx, ny)) continue;
                if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z'){
                    int bit = (1 << (map[nx][ny] - 'a'));
                    flag = (State.key & bit) != 0 ? flag : true;
                    State.key |= (1 << (map[nx][ny] - 'a'));
                    map[nx][ny] = '.';
                } else if(map[nx][ny] == '$'){
                    map[nx][ny] = '.';
                    cnt++;
                } else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') {
                    int bit = (1 << (map[nx][ny] - 'A'));
                    if ((State.key & bit) != 0) {
                        map[nx][ny] = '.';
                        isv[nx][ny] = true;
                        queue.offer(new State(nx, ny));
                    }
                    continue;
                }
                isv[nx][ny] = true;
                queue.offer(new State(nx, ny));
            }
        }
    }

    private static boolean isOutRange(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= M || isv[x][y] || map[x][y] == '*') return true;
        return false;
    }
}

class State {
    int x, y;
    static int key;

    public State(int x, int y) {
        this.x = x;
        this.y = y;
    }
}