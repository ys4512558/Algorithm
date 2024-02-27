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
            dfs(0, 0, 0, 1);
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    private static void dfs(int sX, int sY, int eX, int eY) {
        if(eX == N - 1 && eY == N - 1){
            count++;
            return;
        }

        if ((sX == eX || (sX == eX - 1 && sY == eY - 1))
                && (eX + dx[COL]) < N && (eY + dy[COL] < N)
                && map[eX + dx[COL]][eY + dy[COL]] != 1) { //행이 같으면 열이동
            //열이동
            dfs(eX, eY, eX + dx[COL], eY + dy[COL]);

        }
        if((sY == eY || (sX == eX - 1 && sY == eY - 1))
                && (eX + dx[ROW] < N && eY + dy[ROW] < N)
                && map[eX + dx[ROW]][eY + dy[ROW]] != 1) { //열이 같으면 행이동
            //행이동
            dfs(eX, eY, eX + dx[ROW], eY + dy[ROW]);
        }

        //대각선은 어떻게 왔든 가능함
        if ((eX + dx[DIAG] < N && eY + dy[DIAG] < N)
                && map[eX + dx[DIAG]][eY + dy[DIAG]] != 1
                && map[eX + dx[DIAG] - 1][eY + dy[DIAG]] != 1
                && map[eX + dx[DIAG]][eY + dy[DIAG] - 1] != 1) {

            dfs(eX, eY, eX + dx[DIAG], eY + dy[DIAG]);
        }
    }
}