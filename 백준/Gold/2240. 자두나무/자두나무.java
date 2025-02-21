import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        //시간, 움직인 횟수, 1번위치, 2번 위치
        int[][][] dp = new int[T + 1][W + 1][2];

        int result = 0;
        for (int i = 1; i <= T; i++) {
            //위치 1, 2 -> 0, 1로 변경
            int p = Integer.parseInt(br.readLine()) - 1;

            //현재 시간 vs W최대 범위 중 더 작은 만큼 확인
            for (int j = 0; j <= Math.min(i, W); j++) {
                //i시간에 W움직였을 때 1번 위치인 경우
                int cur = j % 2; //0이면 1위치, 1이면 2위치
                int count = p == cur ? 1 : 0;
                if (j == 0) {
                    dp[i][j][0] = dp[i - 1][j][0] + count;
                    result = Math.max(result, dp[i][j][0]);
                    continue;
                }
                //이전에 반대 편에 있었던 경우 (이번에 움직이는 경우)
                //vs 이전에 움직이고 가만히 있던 경우
                dp[i][j][cur] = Math.max(dp[i - 1][j - 1][(cur + 1) % 2], dp[i - 1][j][cur]) + count;
                result = Math.max(result, dp[i][j][cur]);
//                System.out.println("i = " + i + " j =" + j + " [0] : " + dp[i][j][0] + " [1] : " + dp[i][j][1]);
            }
        }
        System.out.println(result);
    }
}