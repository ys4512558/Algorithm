import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int[] wins = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            wins[i] = Integer.parseInt(st.nextToken());
        }

        int W = Integer.parseInt(br.readLine());

        int[] loses = new int[W + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= W; i++) {
            loses[i] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[N + 1][W + 1];

        dp[0][0] = 0;
        dp[1][0] = wins[1];
        dp[0][1] = loses[1];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + wins[i];
        }
        for (int i = 1; i <= W; i++) {
            int mod = dp[0][i - 1] % K;
            dp[0][i] = dp[0][i - 1];
            if (mod == 0) {
                dp[0][i] -= loses[i];
            } else {
                dp[0][i] -= Math.min(loses[i], Math.floorMod(dp[0][i], K));
            }
        }


        //[몇번 이겼는지][몇번 졌는지]


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= W; j++) {
                //이번에 이겼을 때
                int win = dp[i - 1][j] + wins[i];

                int mod = dp[i][j - 1] % K;
                int lose = dp[i][j - 1];
                if (mod == 0) {
                    lose -= loses[j];
                } else {
                    lose -= Math.min(loses[j], Math.floorMod(dp[i][j - 1], K));
                }

                //이번에 졌을 때
                //[i][j]
                // [i - 1번 승리][j번 패배] + i번째 승리 포인트 vs
                // [i번 승리][j - 1]번 패배] - (j번째 패배 포인트 or 모듈러 값)
                dp[i][j] = Math.max(win, lose);
            }
        }

        System.out.println(dp[N][W]);
    }
}