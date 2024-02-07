import java.io.*;
import java.util.PriorityQueue;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) {
                return Integer.compare(o1, o2);
            }
            return Integer.compare(Math.abs(o1), Math.abs(o2));
        });

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                sb.append(pq.isEmpty() ? 0 : pq.poll()).append("\n");
                continue;
            }
            pq.offer(num);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}