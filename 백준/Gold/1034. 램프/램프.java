import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        int K = Integer.parseInt(br.readLine());

        Loop:
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            boolean[] flags = new boolean[M];
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    cnt++;
                    flags[j] = true;
                }
                if (cnt > K) continue Loop;
            }
            if ((cnt % 2) == (K % 2)) {
                toggle(arr, flags);
            }
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static void toggle(int[][] arr, boolean[] flags) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = flags[j] ? arr[i][j] ^ 1 : arr[i][j];
            }
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }
        max = Math.max(max, cnt);
    }
}