import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //1차원 배열의 인덱스 = 행, 값 = 열
    static int[] map;
    static int N;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N];

        dfs(0, 0);
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void dfs(int row, int cnt) {
        if(cnt == N) {
            ans++;
            return;
        }
        for (int i = 0; i < N; i++) {
            //놓을 수 있는 자리면 놓고 다음 행에 퀸을 놓으러 감
            map[row] = i;
            if (available(row)) {
                dfs(row + 1, cnt + 1);
            }
        }
    }
    private static boolean available(int row) {
        //행은 확인할 필요 X 어차피 다음 행에 놓도록 로직 설계 했기 때문
        for (int i = 0; i < row; i++) {
            //같은 열인지 확인
            if(map[i] == map[row]) return false;
            //대각선 확인
            if(Math.abs(i - row) == Math.abs(map[i] - map[row])) return false;
        }
        return true;
    }
}