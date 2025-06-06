import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
            sb.append((N / 2) + 1);
            sb.append("\n");
            StringTokenizer st = null;
            int count = 0;
            for (int j = 0; j < N; j++) {
                if(j % 10 == 0) st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                if (minHeap.size() == maxHeap.size()) {
                    maxHeap.offer(num);
                } else {
                    minHeap.offer(num);
                }
                if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    int temp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(temp);
                }
                if (j % 2 == 0) {
                    sb.append(maxHeap.peek());
                    sb.append(" ");
                    count++;
                }
                if (count == 10) {
                    sb.append("\n");
                    count = 0;
                }
            }
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
