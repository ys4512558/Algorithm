import java.io.*;
import java.util.*;

public class Main {
    static final int N = 9;
    static boolean end = false;
    static List<int[]> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static int[][] sdoku = new int[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                sdoku[i][j] = input.charAt(j) - '0';
                if(sdoku[i][j] == 0) list.add(new int[]{i, j});
            }
        }
        backtracking(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void backtracking(int cur) {
        if (list.size() == cur && !end) {
            end = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(sdoku[i][j]);
                }
                sb.append("\n");
            }
            return;
        } else if (end) return;

        int[] point = list.get(cur);
        int x = point[0];
        int y = point[1];

        boolean[] isValid = new boolean[N + 1];
        //내가 속한 행, 열을 확인해서 이미 있는 것을 true로 변경
        for (int i = 0; i < N; i++) {
            //열
            if (sdoku[x][i] != 0) isValid[sdoku[x][i]] = true;
            //행
            if (sdoku[i][y] != 0) isValid[sdoku[i][y]] = true;
        }

        //내가 속한 3 x 3을 확인
        checkRect(x, y, isValid);

        //되는 경우의 수를 작은 것 부터 넣어보고 다음꺼 해보러 가기.
        for (int i = 1; i <= N; i++) {
            if (isValid[i]) continue;
            sdoku[x][y] = i;
            backtracking(cur + 1);
            sdoku[x][y] = 0;
        }
    }

    private static void checkRect(int x, int y, boolean[] isValid) {
        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                if (sdoku[i][j] != 0) isValid[sdoku[i][j]] = true;
            }
        }
    }
}