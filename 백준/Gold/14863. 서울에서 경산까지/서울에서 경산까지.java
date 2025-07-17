import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp1 = new long[N][K + 1];
        long[][] dp2 = new long[N][K + 1];
        st = new StringTokenizer(br.readLine());
        int time1 = Integer.parseInt(st.nextToken());
        int money1 = Integer.parseInt(st.nextToken());
        int time2 = Integer.parseInt(st.nextToken());
        int money2 = Integer.parseInt(st.nextToken());
        dp1[0][time1] = money1;
        dp2[0][time2] = money2;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time1 = Integer.parseInt(st.nextToken());
            money1 = Integer.parseInt(st.nextToken());
            time2 = Integer.parseInt(st.nextToken());
            money2 = Integer.parseInt(st.nextToken());
            for (int j = K; j >= 0; j--) {
                if (time1 <= j && (dp1[i - 1][j - time1] != 0 || dp2[i - 1][j - time1] != 0)) {
                    dp1[i][j] = Math.max(dp1[i - 1][j - time1], dp2[i - 1][j - time1]) + money1;
                }
                if (time2 <= j && (dp1[i - 1][j - time2] != 0 || dp2[i - 1][j - time2] != 0)) {
                    dp2[i][j] = Math.max(dp1[i - 1][j - time2], dp2[i - 1][j - time2]) + money2;
                }
            }
        }
        long max = 0;
        for (int i = 0; i <= K; i++) {
            max = Math.max(max, Math.max(dp1[N - 1][i], dp2[N - 1][i]));
        }
        System.out.println(max);
    }
}
