import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /**
     * 달이 차오른다 가자
     */
    static char[][] map;
    static boolean[][][] isv;
    static Pair start;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        // FEDCBA
        // 111111
        isv = new boolean[(1 << 6) + 1][N][M]; //각 상태 저장 visited
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == '0'){
                    start = new Pair(i, j, 0, 0);
                    map[i][j] = '.';
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> queue = new ArrayDeque<Pair>();
        queue.offer(start);
        isv[0][start.x][start.y] = true;

        int res = -1;

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || isv[p.key][nx][ny] || map[nx][ny] == '#') continue;
                if(map[nx][ny] == '1') {
                    res = p.breadth + 1;
                    return res;
                }
                //문이면
                if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    int offset = 1 << (map[nx][ny] - 'A');
                    if ((p.key & offset) != 0){ //키가 있으면
                        isv[p.key][nx][ny] = true;
                        queue.offer(new Pair(nx, ny, p.breadth + 1, p.key));
                    }
                //키이면
                } else if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f'){
                    int key = p.key | (1 << (map[nx][ny] - 'a'));
                    isv[key][nx][ny] = true;
                    queue.offer(new Pair(nx, ny, p.breadth + 1, key));
                //그냥 길이면
                } else if (map[nx][ny] == '.') {
                    isv[p.key][nx][ny] = true;
                    queue.offer(new Pair(nx, ny, p.breadth + 1, p.key));
                }
            }
        }

        return res;
    }
}

class Pair{
    int x, y, breadth, key;

    public Pair(int x, int y, int breadth, int key) {
        this.x = x;
        this.y = y;
        this.breadth = breadth;
        this.key = key;
    }
}