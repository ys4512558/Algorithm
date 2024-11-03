import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][][] dp;
    static int N, M;
    static int[] dx = new int[]{-1, 0, 0};
    static int[] dy = new int[]{0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1][3]; //0 : 위, 1 : 왼, 2 : 오

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE / 2;
                }
            }
        }
        dp[1][1] = new int[]{map[1][1], map[1][1], map[1][1]};
        for (int i = 2; i <= M; i++) {
            dp[1][i][1] = dp[1][i - 1][1] + map[1][i];
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][0] = Math.max(dp[i][j][0], dp[i - 1][j][k] + map[i][j]);
                }
            }
            for (int j = 2; j <= M; j++) {
                for (int k = 0; k < 3; k++) {
                    if (k != 2) {
                        dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j - 1][k] + map[i][j]);
                    }
                    if (k != 1) {
                        dp[i][M - j + 1][2] = Math.max(dp[i][M - j + 1][2], dp[i][M - j + 2][k] + map[i][M - j + 1]);
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[N][M][i]);
        }
        System.out.println(max);
    }
}