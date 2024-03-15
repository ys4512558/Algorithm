import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            if(minHeap.isEmpty()) {
                sb.append(maxHeap.peek()).append("\n");
                continue;
            }
            if (maxHeap.peek() > minHeap.peek()) {
                int temp = maxHeap.poll();
                maxHeap.offer(minHeap.poll());
                minHeap.offer(temp);
            }
            sb.append(maxHeap.peek()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}