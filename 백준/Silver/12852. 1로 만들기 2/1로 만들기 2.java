import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(N, 0, null));
        dp[N] = 0;
        Node result = null;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int n = node.idx;
            int count = node.count;
            if(dp[n] < count) continue;
            dp[n] = count;
            if (n == 1) {
                result = node;
                continue;
            }

            if (n % 3 == 0) {
                pq.offer(new Node(n / 3, count + 1, node));
            }
            if (n % 2 == 0) {
                pq.offer(new Node(n / 2, count + 1, node));
            }
            if (n > 1) {
                pq.offer(new Node(n - 1 , count + 1, node));
            }
        }

        StringBuilder sb = new StringBuilder();

        List<Integer> list = new ArrayList<>();
        for (Node n = result; n != null; n = n.prev) {
            list.add(n.idx);
        }
        sb.append(dp[1] + "\n");
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i) + " ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Node implements Comparable<Node> {

    int idx, count;
    Node prev;

    public Node(int idx, int count, Node prev) {
        this.idx = idx;
        this.count = count;
        this.prev = prev;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.count, o.count);
    }
}