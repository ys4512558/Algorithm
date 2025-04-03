import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int num = 0;
        int mul = N;
        while (mul != 0) {
            int mod = mul % 2;
            mul /= 2;
            if (mod != 0) pq.offer((int) Math.pow(2, num));
            num++;
        }

        int result = 0;
        while (pq.size() > K) {
            int cur = pq.poll();
            int next = pq.poll();
            result += next - cur;
            pq.offer(next * 2);
        }
        System.out.println(result);
    }
}
