import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static Tank tank = null;
    static char[][]map;
    static int H, W;
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            solve();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '^') tank = new Tank(i, j, 0);
                if (map[i][j] == 'v') tank = new Tank(i, j, 1);
                if (map[i][j] == '<') tank = new Tank(i, j, 2);
                if (map[i][j] == '>') tank = new Tank(i, j, 3);
            }
        }
        play();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
    }

    private static void play() throws IOException {
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            char command = input.charAt(i);
            if (command == 'S') {
                shoot();
                continue;
            }
            if(command == 'U') {
                tank.dir = 0;
                map[tank.row][tank.col] = '^';
            }
            if(command == 'D') {
                tank.dir = 1;
                map[tank.row][tank.col] = 'v';
            }
            if(command == 'L') {
                tank.dir = 2;
                map[tank.row][tank.col] = '<';
            }
            if(command == 'R') {
                tank.dir = 3;
                map[tank.row][tank.col] = '>';
            }
            move();
        }
    }

    private static void move() {
        int row = tank.row + dx[tank.dir];
        int col = tank.col + dy[tank.dir];
        if(row < 0 || row >= H || col < 0 || col >= W) return;
        if(map[row][col] == '.') {
            char temp = map[row][col];
            map[row][col] = map[tank.row][tank.col];
            map[tank.row][tank.col] = temp;
            tank.row = row;
            tank.col = col;
        }
    }

    private static void shoot() {
        Queue<int[]> queue = new ArrayDeque<>();
        int idx = tank.dir;
        int r = tank.row + dx[idx];
        int c = tank.col + dy[idx];
        queue.offer(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];

            if(row < 0 || row >= H || col < 0 || col >= W) break;
            if(map[row][col] == '*') {
                map[row][col] = '.';
                break;
            }
            if(map[row][col] == '#') break;
            queue.offer(new int[]{row + dx[idx], col + dy[idx]});
        }
    }
}

class Tank{
    int dir, row, col;

    public Tank(int row, int col, int dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
}