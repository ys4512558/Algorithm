import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    static boolean[] isPrime;
    static long[] dp;
    static Set<Integer> primes;
    static final int MOD = 123_456_789;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        isPrime = new boolean[N + 1];
        dp = new long[N + 1];
        init(N);

        dp[0] = 1;
        for (int num : primes) {
            for (int i = num; i <= N; i++) {
                dp[i] = (dp[i] + dp[i - num]) % MOD;
            }
        }
        System.out.println(dp[N]);
    }

    public static void init(int N) {
        Arrays.fill(isPrime, true);
        primes = new TreeSet<>();
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= N; i++) {
            if (!isPrime[i]) continue;
            primes.add(i);
            for (int j = i * i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
