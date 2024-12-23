import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        boolean[][][] road = new boolean[N + 1][M + 1][2];
        long[][] dp = new long[N + 2][M + 2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (a == c) {
                int from = Math.max(b, d);
                road[a][from][0] = true; //왼쪽에서 오는 길이 있는 경우
            } else {
                int from = Math.max(a, c);
                road[from][b][1] = true; //위족에서 오는 길이 있는 경우
            }
        }
        dp[1][1] = 1;
        for (int i = 1; i <= N + 1; i++) {
            for (int j = 1; j <= M + 1; j++) {
                if (road[i - 1][j - 1][0] && road[i - 1][j - 1][1]) {
                    continue;
                } else if (road[i - 1][j - 1][0]) {
                    dp[i][j] += dp[i - 1][j];
                } else if (road[i - 1][j - 1][1]) {
                    dp[i][j] += dp[i][j - 1];
                } else {
                    dp[i][j] += dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        System.out.println(dp[N + 1][M + 1]);
    }
}