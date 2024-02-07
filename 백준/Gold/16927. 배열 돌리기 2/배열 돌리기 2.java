import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int R = Integer.parseInt(stk.nextToken());
        map = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int depth = 0;
        int sRow = 1;
        int sCol = 1;
        int eRow = N;
        int eCol = M;

        while (true) {
            int startX = sRow + depth;
            int startY = sCol + depth;
            int endX = eRow - depth;
            int endY = eCol - depth;

            if (startX > endX || startY > endY) {
                break;
            }
            int cnt = ((((endX - startX + 1) + (endY - startY + 1)) * 2) - 4);
            int rotateCnt = cnt == 0 ? R : R % cnt;
            for (int i = 0; i < rotateCnt; i++) {
                rotate(startX, startY, endX, endY);
            }
            depth++;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void rotate(int startX, int startY, int endX, int endY) {
        int temp = map[startX][startY];
        for (int i = 1; i <= endX - startX; i++) {
            int cur = map[startX + i][startY];
            map[startX + i][startY] = temp;
            temp = cur;
        }
        for (int i = 1; i <= endY - startY; i++) {
            int cur = map[endX][startY + i];
            map[endX][startY + i] = temp;
            temp = cur;
        }
        for (int i = 1; i <= endX - startX; i++) {
            int cur = map[endX - i][endY];
            map[endX - i][endY] = temp;
            temp = cur;
        }
        for (int i = 1; i <= endY - startY; i++) {
            int cur = map[startX][endY - i];
            map[startX][endY - i] = temp;
            temp = cur;
        }
    }
}