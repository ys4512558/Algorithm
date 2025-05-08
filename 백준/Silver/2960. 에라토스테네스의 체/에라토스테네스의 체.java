import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        isPrime = new boolean[N + 1];

        int count = 0;
        System.out.println(check(N, count, K));
    }

    private static int check(int N, int count, int K) {
        Arrays.fill(isPrime, true);
        int num = 0;
        for (int i = 2; i <= N; i++) {
            if(!isPrime[i]) continue;
            for (int j = i; j <= N; j += i) {
                if(!isPrime[j]) continue;
                isPrime[j] = false;
                count++;
                if (count == K) {
                    return j;
                }
            }
        }
        return num;
    }
}
