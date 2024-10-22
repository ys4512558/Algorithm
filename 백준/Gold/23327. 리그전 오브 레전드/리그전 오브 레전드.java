import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        // 입력 배열
        int[] arr = new int[N + 1];
        // 누적합 (1, 2, 3, 4) 일때 4번은 1, 2, 3과 경기해서 얻을 수 있는 재미 점수가
        // (1 + 2 + 3) * 4이므로 이를 구하기 위해 누적합 이용
        long[] prefixSum = new long[N + 1];
        // dp[i] = dp[i - 1] + prefixSum[i - 1] * arr[i]로 정의
        // 1, 2, 3이면 dp[2] = [1, 2]의 경기 재미 점수
        // dp[3] = prefixSum(1 + 2) * 3 -> [1, 3], [2, 3]의 경기 재미 점수)
        // + dp[2] [1, 2]의 경기 재미 점수 => 모든 경기 커버 가능
        long[] dp = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + arr[i];
            //이전까지의 재미 점수 + (나 이전의 상대들의 재미 점수 합 * 내 점수)
            dp[i] = dp[i - 1] + ((long) prefixSum[i - 1] * arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            long preSum = prefixSum[l - 1];
            long divideSum = prefixSum[r] - prefixSum[l - 1];
            //dp[r] 1 ~ r 까지 모든 경기 경우의 수 재미 합 (1, 2, 3) = 1-2,1-3,2-3의 합
            //divideSum : 구하려는 구간의 합
            //preSum : 구하려는 구간에 포함되지 않는 왼쪽의 합 [1, 2, 3] [4, 5]에서 [4, 5]를 구하려면
            //1~5의 재미 점수 dp[5]에서 - [1, 2, 3]과 4의 경기 점수 - [1, 2, 3]과 5의 경기 점수
            //이는 dp[5] - {(1 + 2 + 3) * (4 + 5)} - dp[3]
            // (여기서 dp[3]은 [1, 2, 3]들끼리 경기해서 얻을 수 있는 재미 점수이므로 이것 또한 빼주어야 함.
            sb.append(dp[r] - divideSum * preSum - dp[l - 1]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}