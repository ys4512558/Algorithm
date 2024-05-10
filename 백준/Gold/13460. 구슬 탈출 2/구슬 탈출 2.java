import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static final int L = 0, R = 1, U = 2, D = 3;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N, M;
    static Marble dest;
    static int count;
    static boolean[][][][] isv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        count = -1;
        dest = null;
        isv = new boolean[11][11][11][11];
        Marble red = null, blue = null;
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'R') red = new Marble(i, j);
                if (map[i][j] == 'B') blue = new Marble(i, j);
                if (map[i][j] == 'O') dest = new Marble(i, j);
                if (map[i][j] == '#') continue;
                map[i][j] = '.';
            }
        }
        map[dest.x][dest.y] = 'O';
        bfs(red, blue);
        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    private static void bfs(Marble red, Marble blue) {
        Queue<Marble> redQ = new ArrayDeque<>();
        Queue<Marble> blueQ = new ArrayDeque<>();
        redQ.offer(red);
        blueQ.offer(blue);
        isv[red.x][red.y][blue.x][blue.y] = true;

        int cnt = 1;
        while ((!redQ.isEmpty() && !blueQ.isEmpty())) {
            if(cnt == 11) break;

            int size = redQ.size();

            while (size-- > 0){
                Marble cred = redQ.poll();
                Marble cblue = blueQ.poll();
                for (int i = 0; i < 4; i++) {
                    Marble nred = null;
                    Marble nblue = null;
                    if (i == L) {
                        //왼쪽 먼저
                        if (cred.y < cblue.y) {
                            nred = move(cred.x, cred.y, dx[i], dy[i], null);
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], nred);
                        } else {
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], null);
                            nred = move(cred.x, cred.y, dx[i], dy[i], nblue);
                        }
                    } else if (i == R) {
                        //오른쪽 먼저
                        if (cred.y > cblue.y) {
                            nred = move(cred.x, cred.y, dx[i], dy[i], null);
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], nred);
                        } else {
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], null);
                            nred = move(cred.x, cred.y, dx[i], dy[i], nblue);
                        }
                    } else if (i == U) {
                        //위쪽 먼저
                        if (cred.x < cblue.x) {
                            nred = move(cred.x, cred.y, dx[i], dy[i], null);
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], nred);
                        } else {
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], null);
                            nred = move(cred.x, cred.y, dx[i], dy[i], nblue);
                        }
                    } else if (i == D) {
                        //왼쪽 먼저
                        if (cred.x > cblue.x) {
                            nred = move(cred.x, cred.y, dx[i], dy[i], null);
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], nred);
                        } else {
                            nblue = move(cblue.x, cblue.y, dx[i], dy[i], null);
                            nred = move(cred.x, cred.y, dx[i], dy[i], nblue);
                        }
                    }
                    if (isv[nred.x][nred.y][nblue.x][nblue.y]) continue;
                    if (nred.x == dest.x && nred.y == dest.y) {
                        if (nblue.x == dest.x && nblue.y == dest.y) continue;
                        else count = cnt; return;
                    } else if(nblue.x == dest.x && nblue.y == dest.y){
                        continue;
                    }
                    isv[nred.x][nred.y][nblue.x][nblue.y] = true;
                    redQ.offer(nred);
                    blueQ.offer(nblue);
                }
            }
            cnt++;
        }
    }


    private static Marble move(int x, int y, int dx, int dy, Marble other) {
        int mul = 1;
        int cx = x;
        int cy = y;
        while (true) {
            int nx = x + (dx * mul);
            int ny = y + (dy * mul);
            if (map[nx][ny] == 'O') {
                return new Marble(nx, ny);
            } else if ((map[nx][ny] != '.') || (other != null && other.x == nx && other.y == ny)) {
                return new Marble(cx, cy);
            }
            cx = nx;
            cy = ny;
            mul++;
        }
    }
}

class Marble {
    int x, y;

    public Marble(int x, int y) {
        this.x = x;
        this.y = y;
    }
}