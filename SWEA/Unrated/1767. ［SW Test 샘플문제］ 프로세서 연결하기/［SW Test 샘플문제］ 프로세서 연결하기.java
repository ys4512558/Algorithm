import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[][] map, copy;
    static int N, use, min;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<Process> proceses;
    static ArrayList<Integer> selIdxs;
    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static int solve() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        proceses = new ArrayList<>();
        map = new int[N][N];
        use = 0;
        int count = 0;
        int used = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    count++;
                    if((i == 0 || i == N - 1 || j == 0 || j == N - 1)) {
                        used++;
                        continue;
                    }
                    proceses.add(new Process(i, j));
                }
            }
        }

        return binaryCounting(count - used);
    }

    private static int binaryCounting(int count) {
        for (int i = (1 << count) - 1; i > 0; i--) {
            selIdxs = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                if((i & (1 << j)) != 0) selIdxs.add(j);
            }
            copy = copy();
            dfs(copy, 0, 0, 0);
        }
        return min;
    }
    private static int[][] copy() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static void dfs(int[][] copy, int depth, int len, int useProcess) {
        if(selIdxs.size() == depth) {
            if(useProcess > use) {
                use = useProcess;
                min = len;
            } else if(useProcess == use) {
                min = Math.min(min, len);
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            int res = go(proceses.get(selIdxs.get(depth)), i, copy);
            if(res < 1) {
                removePath(copy, proceses.get(selIdxs.get(depth)), i, Math.abs(res));
            } else {
                dfs(copy, depth + 1, res + len, useProcess + 1);
                removePath(copy, proceses.get(selIdxs.get(depth)), i, Math.abs(res));
            }
        }
    }

    private static void print(int[][] copy2) {
        for (int i = 0; i < copy2.length; i++) {
            System.out.println(Arrays.toString(copy2[i]));
        }
        System.out.println("----------------------------------------");
    }
    private static int go(Process pro, int dir, int[][] copy) {
        int x = pro.x + dx[dir];
        int y = pro.y + dy[dir];
        int len = 1;
        while(true) {
            if(copy[x][y] != 0) {
                return -(len - 1);
            }
            copy[x][y] = 2;

            if((x == 0 || x == N - 1 || y == 0 || y == N - 1)) {
                return len;
            }

            x += dx[dir];
            y += dy[dir];
            len++;
        }
    }
    private static void removePath(int[][] copy, Process pro, int dir, int len) {
        int x = pro.x + dx[dir];
        int y = pro.y + dy[dir];

        while(len-- > 0) {
            if(copy[x][y] != 2) return;
            copy[x][y] = 0;

            x += dx[dir];
            y += dy[dir];
        }
    }

}

class Process{
    int x, y;

    public Process(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Process [x=" + x + ", y=" + y + "]";
    }

}