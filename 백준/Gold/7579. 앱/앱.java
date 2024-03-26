import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /**
     * BOJ 7579 앱
     */
    static int[] m;
    static int[] c;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        m = new int[N];
        c = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) m[i] = Integer.parseInt(st.nextToken());

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            sum += c[i];
        }
        dp = new int[sum + 1];

        for (int i = 0; i < N; i++) {
            for (int j = sum; j >= 0; j--) {
                if (c[i] > j) continue;
                dp[j] = Math.max(dp[j], dp[j - c[i]] + m[i]);
            }
        }
        for (int i = 0; i <= sum; i++) {
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}