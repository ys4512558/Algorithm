import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Position last;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        Position start = null;
        Position c[] = new Position[2];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    start = new Position(i, j, -1, false);
                    map[i][j] = '.';
                } else if (map[i][j] == 'C') {
                    c[idx++] = new Position(i, j, -1, false);
                }
            }
        }
        System.out.println(calcMin(start, c));
    }

    private static int calcMin(Position start, Position[] c) {
        last = new Position(start.x, start.y, -1, false);
        int temp1 =bfs(last, c, 0, false);
        last = new Position(start.x, start.y, -1, false);
        int temp2 = bfs(last, c, 1, false);
        return temp1 == temp2 ? temp1 : Math.min(temp1, temp2);
    }

    private static int bfs(Position start, Position c[], int idx, boolean flag) {
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(start);
        int[][][] visited = new int[4][N][M];

        int min = Integer.MAX_VALUE;
        int turn = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            turn++;
            while (size-- > 0) {
                Position position = queue.poll();

                for (int i = 0; i < 4; i++) {
                    if(position.dir == i) continue;
                    int nx = position.x + dx[i];
                    int ny = position.y + dy[i];
                    if(isOutRange(nx, ny) || visited[i][nx][ny] == 4) continue;
                    boolean isEnd = position.c || (c[idx].x == nx && c[idx].y == ny);
                    if (isEnd) {
//                        System.out.println("End");
//                        System.out.println(flag + " turn : " + turn + " " + position.x + " " + position.y + " -> " + nx + " " + ny);

                        last = new Position(nx, ny, i, false);
                        if(flag) return turn;
                        int temp = bfs(last, c, 1 - idx, true);
                        if(temp == -1) continue;
                        min = Math.min(min, turn + temp);
//                        System.out.println("min = " + min);
//                        System.out.println("end");
                        continue;
                    }
                    visited[i][nx][ny]++;
                    queue.offer(new Position(nx, ny, i, false));
//                    System.out.println(flag + " turn : " + turn + " " + position.x + " " + position.y + " -> " + nx + " " + ny);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M || map[x][y] == '#';
    }
}

class Position {
    int x, y, dir;
    boolean c;

    public Position(int x, int y, int dir, boolean c) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.c = c;
    }
}