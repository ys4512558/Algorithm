import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, W, H, min, total;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE; total = 0;

        int[][] map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) total++;
            }
        }
        dfs(0, map, 0);

        return min;
    }

    private static void dfs(int depth, int[][] map, int cnt) {
        if(depth == N){
//            print(map);
            min = Math.min(min, total - cnt);
            return;
        }

        for (int i = 0; i < W; i++) {
            //i번째 열에 구슬 떨구기
            int[][] copy = copy(map);
            int c = drop(i, copy);
            dfs(depth + 1, copy, cnt + c);
        }
    }

    private static int drop(int col, int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            if (map[i][col] == 0) continue;
            cnt = boom(map, new Marble(i, col, map[i][col]));
            break;
        }
        return cnt;
    }

    private static int boom(int[][] map, Marble marble) {
        Queue<Marble> queue = new ArrayDeque<>();
        queue.offer(marble);
        int cnt = 0;
        while (!queue.isEmpty()) {
            Marble m = queue.poll();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < m.breadth; j++) {
                    int nx = m.x + dx[i] * j;
                    int ny = m.y + dy[i] * j;
                    if(nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 0) continue;
                    queue.offer(new Marble(nx, ny, map[nx][ny]));
                    map[nx][ny] = 0;
                    cnt++;
                }
            }
        }
        //떨궈주기
        reLocation(map);
        return cnt;
    }

    private static void reLocation(int[][] map) {
        for (int i = 0; i < W; i++) {
            int row = H - 1;
            List<Integer> list = new ArrayList<>();
            for (int j = row; j >= 0; j--) {
                if(map[j][i] == 0) continue;
                list.add(map[j][i]);
                map[j][i] = 0;
            }
            for (int j = 0; j < list.size(); j++) {
                map[row - j][i] = list.get(j);
            }
        }
    }

    private static int[][] copy(int[][] map) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static void print(int[][] map){
        System.out.println("==============================");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==============================");
    }
}

class Marble {
    int x, y, breadth;

    public Marble(int x, int y, int breadth) {
        this.x = x;
        this.y = y;
        this.breadth = breadth;
    }
}