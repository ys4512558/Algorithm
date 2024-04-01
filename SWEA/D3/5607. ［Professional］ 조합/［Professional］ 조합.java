import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static long[] facto = new long[1_000_001];
    static final long MOD = 1234567891;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        facto[0] = facto[1] = 1;
        for (int i = 2; i < facto.length; i++) {
            facto[i] = ((facto[i - 1] % MOD) * (i % MOD)) % MOD;
        }

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    //(n! % MOD * (r!)^MOD-2 * MOD * (n-r)!^MOD-2 % MOD) % MOD
    private static long solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        long nf = facto[n] % MOD;
        long rf = facto[r] % MOD;
        long powerR = power(rf, MOD - 2) % MOD;
        long powerNR = power(facto[n-r], MOD - 2) % MOD;

        return ((((nf * powerR) % MOD) * powerNR) % MOD) % MOD;
    }

    private static long power(long num, long pow) {
        if (pow == 1) return num % MOD;
        long temp = power(num, pow / 2) % MOD;
        if (pow % 2 != 0) return (temp % MOD * temp % MOD * num) % MOD;
        return (temp % MOD * temp % MOD) % MOD;
    }
}
