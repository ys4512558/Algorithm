import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][][] cost;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static State start, end;
    static final int PASS = 1, TRASH = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cost = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                cost[i][j] = new int[]{(Integer.MAX_VALUE / 2), (Integer.MAX_VALUE / 2)};
                switch (c) {
                    case '.':
                        continue;
                    case 'g':
                        for (int k = 0; k < 4; k++) {
                            int x = i + dx[k];
                            int y = j + dy[k];
                            if (isOutRange(x, y) || map[x][y] != 0) continue;
                            map[x][y] = PASS;
                        }
                        map[i][j] = TRASH;
                        break;
                    case 'S':
                        start = new State(i, j, 0, 0);
                        break;
                    case 'F':
                        end = new State(i, j, 0, 0);
                        break;
                }
            }
        }
        map[start.x][start.y] = 0;
        map[end.x][end.y] = 0;
        State state = dijkstra();
        System.out.println(state);
    }

    private static State dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(start.x, start.y, 0, 0));
        cost[start.x][start.y] = new int[]{start.trash, start.pass};

        State result = null;
        while (!pq.isEmpty()) {
            State state = pq.poll();

            if (cost[state.x][state.y][0] < state.trash) continue;
            else if(cost[state.x][state.y][0] == state.trash && cost[state.x][state.y][1] < state.pass) continue;

            if (state.x == end.x && state.y == end.y) {
                result = state;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = state.x + dx[i];
                int ny = state.y + dy[i];

                if (isOutRange(nx, ny)) continue;
                int trash = state.trash + (map[nx][ny] == TRASH ? 1 : 0);
                int pass = state.pass + (map[nx][ny] == PASS ? 1 : 0);
                if(cost[nx][ny][0] < trash) continue;
                else if(cost[nx][ny][0] == trash && cost[nx][ny][1] <= pass) continue;
                pq.offer(new State(nx, ny, trash, pass));
                cost[nx][ny] = new int[]{trash, pass};
            }
        }

        return result;
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}

class State implements Comparable<State>{
    int x, y, trash, pass;

    public State(int x, int y, int trash, int pass) {
        this.x = x;
        this.y = y;
        this.trash = trash;
        this.pass = pass;
    }

    @Override
    public int compareTo(State o) {
        //trash : 쓰레기칸 지나가기
        //pass : 쓰레기옆칸 지나가기
        if(this.trash == o.trash) return Integer.compare(this.pass, o.pass);
        return Integer.compare(this.trash, o.trash);
    }

    @Override
    public String toString() {
        return trash + " " + pass;
    }
}