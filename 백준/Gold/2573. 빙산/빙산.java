import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BOJ2573.prob2573();
    }
}

class BOJ2573 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int[][] meltMap;

    static boolean[][] isVisited;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int N;
    static int M;
    static int year = 0;
    public static void prob2573() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        meltMap = new int[N][M];
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                meltMap[i][j] = map[i][j];
            }
        }
        int res = 0;
        while (true) {
            res = melt();
            if (res != -1) {
                break;
            }
        }
        sb.append(res);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static int melt(){
        year++;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = meltMap[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int row = i + dy[k];
                    int col = j + dx[k];
                    if (row < 0 || row >= N || col < 0 || col >= M) {
                        continue;
                    }
                    //주의 : 왼쪽이 2 -> 0이 된 상태로
                    //다음 탐색 시 이번에 녹은 0도 포함해서 녹을 수 있는 것
                    if (map[row][col] > 0 || meltMap[i][j] <= 0) {
                        continue;
                    }
                    meltMap[i][j]--;
                }
            }
        }
        int cnt = 0;
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (meltMap[i][j] == 0 || isVisited[i][j]) {
                    continue;
                }
                bfs(i, j);
                cnt++;
            }
        }
        if (cnt == 1) {
            return -1;
        } else {
            return cnt == 0 ? 0 : year;
        }
    }
    private static void bfs(int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        isVisited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int curY = point[0];
            int curX = point[1];

            for (int i = 0; i < 4; i++) {
                int y = curY + dy[i];
                int x = curX + dx[i];

                if (y < 0 || y >= N || x < 0 || x >= M || isVisited[y][x] || meltMap[y][x] == 0) {
                    continue;
                }
                isVisited[y][x] = true;
                queue.add(new int[]{y, x});
            }
        }
    }

}