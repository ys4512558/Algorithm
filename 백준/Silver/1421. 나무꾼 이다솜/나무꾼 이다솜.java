import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] len = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            len[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, len[i]);
        }

        long result = 0;
        for (int i = 1; i <= max; i++) {
            long cost = 0;
            for (int j = 0; j < N; j++) {
                if(len[j] < i) continue;
                int cnt = len[j] / i;
                int slice = (len[j] % i == 0) ? cnt - 1 : cnt;
                long temp = ((long) cnt * W * i) - ((long) slice * C);
                if(temp <= 0) continue;
                cost += temp;
            }
            result = Math.max(result, cost);
        }
        System.out.println(result);
    }
}