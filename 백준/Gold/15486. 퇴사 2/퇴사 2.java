import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int arr[][] = new int[2][N + 1];
        int dp[] = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken()) + (i-1);
            arr[1][i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i <= N; i++) {
            if (arr[0][i] <= N) {
                dp[arr[0][i]] = Math.max(dp[arr[0][i]], dp[i-1] + arr[1][i]);
            }
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}