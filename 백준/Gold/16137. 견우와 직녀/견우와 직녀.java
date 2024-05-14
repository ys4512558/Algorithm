import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, min = Integer.MAX_VALUE;
    static Queue<Pair> cliffs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        cliffs = new ArrayDeque<Pair>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) cliffs.offer(new Pair(i, j));
            }
        }

        while (!cliffs.isEmpty()) {
            Pair p = cliffs.poll();
            //가로 세로 절벽 여부
            boolean r = false, c = false;
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N - 1 || ny >= N - 1 || map[nx][ny] != 0) continue;
                if(i <= 2) r = true;
                else c = true;
            }
            if(r && c) continue;
            map[p.x][p.y] = M;
            int time = bfs();
            min = time != -1 ? Math.min(min, time) : min;
            map[p.x][p.y] = 0;
        }

        System.out.println(min);
    }

    private static int bfs() {
        Queue<Pair> queue = new ArrayDeque<Pair>();
        queue.offer(new Pair(0, 0));
        boolean[][] isv = new boolean[N][N];
        isv[0][0] = true;
        int breadth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Pair p = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N || isv[nx][ny] || map[nx][ny] == 0) continue;

                    if (nx == N - 1 && ny == N - 1) {
                        return breadth + 1;
                    }

                    //주기 상 없을 때 (너비 mod 주기 == 0일때 다리가 있음
                    //다리가 생기면 가볼 수 있으니까 현재 꺼낸 큐를 다시 넣어보자!
                    if(map[nx][ny] == 1) {
                        isv[nx][ny] = true;
                        queue.offer(new Pair(nx, ny, false));
                        continue;
                    }
                    if(p.before) continue;
                    if (((breadth + 1) % map[nx][ny] != 0)) {
                        queue.offer(new Pair(p.x, p.y));
                    } else {
                        isv[nx][ny] = true;
                        queue.offer(new Pair(nx, ny, true));
                    }
                }
            }
            breadth++;
        }
        return -1;
    }
}

class Pair{
    int x, y;
    boolean before;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(int x, int y, boolean before) {
        this.x = x;
        this.y = y;
        this.before = before;
    }
}