import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C;
    static Info start, end;
    static char[][][] map;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, -1, 0, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L + R + C == 0) break;
            map = new char[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = line.charAt(k);
                        if(map[i][j][k] == 'S') {
                            start = new Info(j, k, i);
                            map[i][j][k] = '.';
                        } else if(map[i][j][k] == 'E') {
                            end = new Info(j, k, i);
                            map[i][j][k] = '.';
                        }

                    }
                }
                br.readLine();
            }
            int time = bfs();
            if(time == -1) sb.append("Trapped!");
            else sb.append("Escaped in " + time + " minute(s).");
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int bfs() {
        Queue<Info> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[][][] isv = new boolean[L][R][C];
        isv[start.z][start.x][start.y] = true;

        int breadth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Info info = queue.poll();

                if(info.x == end.x && info.y == end.y && info.z == end.z) return breadth;
                for (int i = 0; i < 6; i++) {
                    int nx = info.x + dx[i];
                    int ny = info.y + dy[i];
                    int nz = info.z + dz[i];

                    if (isOutRange(nz, nx, ny) || isv[nz][nx][ny] || map[nz][nx][ny] != '.') continue;
                    queue.offer(new Info(nx, ny, nz));
                    isv[nz][nx][ny] = true;
                }
            }
            breadth++;
        }
        return -1;
    }

    public static boolean isOutRange(int z, int x, int y) {
        return x < 0 || y < 0 || z < 0 || z >= L || x >= R || y >= C;
    }
}

class Info {
    int x, y, z;

    public Info(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
