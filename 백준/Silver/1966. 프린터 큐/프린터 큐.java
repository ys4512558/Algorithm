import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());

            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
            Queue<Integer> queue = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                pq.offer(num);
                queue.offer(num);
            }

            int size = queue.size();
            int cnt = 0;
            while (true) {
                int important = pq.peek();
                int cur = queue.poll();

                if (cur == important) {
                    cnt++;
                    size--;
                    if (pos == 0) {
                        break;
                    }
                    pq.poll();
                    pos--;
                } else {
                    queue.offer(cur);
                    pos = (pos - 1 + size) % size;
                }
            }
            System.out.println(cnt);
        }
    }

}