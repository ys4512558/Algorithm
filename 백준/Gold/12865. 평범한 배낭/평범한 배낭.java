import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            for (int j = K; j >= 0; j--) {
                if (W > j) continue;
                dp[j] = Math.max(dp[j], dp[j - W] + V);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dp[K]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}