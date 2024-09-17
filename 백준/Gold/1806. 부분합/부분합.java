import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] prefixSum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int lo = 1, hi = 1;
        int len = Integer.MAX_VALUE;
        int sum = 0;

        while (hi <= N) {
            sum = prefixSum[hi] - prefixSum[lo - 1];
            if (sum >= M) {
                len = Math.min(len, hi - lo + 1);
                lo++;
            } else {
                hi++;
            }
        }

        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
    }
}