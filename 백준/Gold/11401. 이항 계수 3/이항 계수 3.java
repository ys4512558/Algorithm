import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[] facto;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //페르마의 소정리 = a^p = a (% p)
        //a^(p-1) = 1 (% p)
        //nCk = n! / (n-k)! * k!
        //n! = n! % MOD
        //1 / n-k! = (n-k)! ^ -1 = (n-k)! ^ (MOD-2)
        //1 / k! = k!^(MOD-2)
        //(n! % MOD * (k!)^MOD-2 * MOD * (n-k)!^MOD-2 % MOD) % MOD

        facto = new long[N + 1];
        facto[0] = facto[1] = 1;
        for (int i = 2; i <= N; i++) {
            facto[i] = ((facto[i - 1] % MOD) * i) % MOD;
        }

        long nf = facto[N] % MOD;
        long rf = facto[K] % MOD;
        long powerR = power(rf, MOD - 2) % MOD;
        long powerNR = power(facto[N-K], MOD - 2) % MOD;

        long res = ((((nf * powerR) % MOD) * powerNR) % MOD) % MOD;
        System.out.println(res);
    }
    private static long power(long num, long pow) {
        if (pow == 1) return num % MOD;
        long temp = power(num, pow / 2) % MOD;
        if (pow % 2 != 0) return (temp % MOD * temp % MOD * num) % MOD;
        return (temp % MOD * temp % MOD) % MOD;
    }
}