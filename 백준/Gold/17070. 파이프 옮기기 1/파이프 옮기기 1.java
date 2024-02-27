import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, 1, 0};
    static int[] dy = {0, 1, 1};
    static final int ROW = 0;
    static final int DIAG = 1;
    static final int COL = 2;

    static int N;
    static int[][] map;
    static int count = 0;
    static class Pair{
        int startX, startY, endX, endY;

        public Pair(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(map[0][2] != 1){
            dfs(new Pair(0, 0, 0, 1));
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    private static void dfs(Pair pair) {
        if(pair.endX == N - 1 && pair.endY == N - 1){
            count++;
            return;
        }
        int startX = pair.startX;
        int startY = pair.startY;
        int endX = pair.endX;
        int endY = pair.endY;

        if ((startX == endX || (startX == endX - 1 && startY == endY - 1))
                && (endX + dx[COL]) < N && (endY + dy[COL] < N)
                && map[endX + dx[COL]][endY + dy[COL]] != 1) { //행이 같으면 열이동
            //열이동
            dfs(new Pair(endX, endY, endX + dx[COL], endY + dy[COL]));

        }
        if((startY == endY || (startX == endX - 1 && startY == endY - 1))
                && (endX + dx[ROW] < N && endY + dy[ROW] < N)
                && map[endX + dx[ROW]][endY + dy[ROW]] != 1) { //열이 같으면 행이동
            //행이동
            dfs(new Pair(endX, endY, endX + dx[ROW], endY + dy[ROW]));
        }

        //대각선은 어떻게 왔든 가능함
        if ((endX + dx[DIAG] < N && endY + dy[DIAG] < N)
                && map[endX + dx[DIAG]][endY + dy[DIAG]] != 1
                && map[endX + dx[DIAG] - 1][endY + dy[DIAG]] != 1
                && map[endX + dx[DIAG]][endY + dy[DIAG] - 1] != 1) {

            dfs(new Pair(endX, endY, endX + dx[DIAG], endY + dy[DIAG]));
        }
    }
}

