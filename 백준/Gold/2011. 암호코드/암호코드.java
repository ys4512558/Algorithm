import java.io.*;

public class Main {
    static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int N = str.length();
        int[] arr = new int[N + 1];
        long[] dp = new long[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = str.charAt(i - 1) - '0';
        }

        dp[0] = 1;
        dp[1] = 1;
        boolean flag = arr[1] != 0;
        for (int i = 2; i <= N; i++) {
            int num = arr[i - 1] * 10 + arr[i];
            if (arr[i] == 0) {
                if (isValid(num)) {
                    dp[i] = dp[i - 2];
                } else {
                    flag = false;
                    break;
                }
            } else {
                if (isValid(num)) {
                    dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        System.out.println(flag ? dp[N] : 0);
    }

    private static boolean isValid(int num) {
        return num >= 10 && num <= 26;
    }
}
