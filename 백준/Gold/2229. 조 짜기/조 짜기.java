import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] scores = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
            for (int j = i - 1; j >= 1; j--) {
                dp[i] = Math.max(dp[j], Math.max(dp[i], Math.abs(scores[i] - scores[j]) + dp[j - 1]));
            }
        }
        System.out.println(dp[N]);
    }
}
