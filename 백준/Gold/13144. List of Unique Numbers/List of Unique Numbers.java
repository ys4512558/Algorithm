import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] idxs;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        idxs = new int[100_001];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int cnt = 0;
        int dupl = 0;
        long res = 0;
        for (int r = 1; r <= N; r++) {
            //l부터 겹치지 않는 r까지
            if (idxs[arr[r]] == 0 || idxs[arr[r]] < l) {
                idxs[arr[r]] = r;
                continue;
            }
            res += dp[r - l] - dp[dupl];
            l = idxs[arr[r]] + 1;
            idxs[arr[r]] = r;
            dupl = r - l;
        }
        res += dp[N - l + 1] - dp[dupl];
        System.out.println(res);
    }
    public static void init() {
        dp = new long[100_001];
        for (int i = 1; i <= 100000; i++) {
            dp[i] = dp[i - 1] + i;
        }
    }
}
