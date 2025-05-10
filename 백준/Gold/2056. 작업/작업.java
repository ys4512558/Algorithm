import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Node[] adjList;
    static int[] counts;
    static int[] times;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new Node[N];
        times = new int[N];
        counts = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            counts[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < counts[i]; j++) {
                int pre = Integer.parseInt(st.nextToken()) - 1;
                adjList[pre] = new Node(i, 0, adjList[pre]);
            }
        }
        System.out.println(topologySort());
    }

    private static int topologySort() {
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if(counts[i] != 0) continue;
            queue.offer(new Node(i, times[i], null));
        }

        int res = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            res = node.time;
            for (Node n = adjList[node.v]; n != null; n = n.next) {
                if (--counts[n.v] == 0) {
                    queue.offer(new Node(n.v, node.time + times[n.v], null));
                }
            }
        }
        return res;
    }
}

class Node implements Comparable<Node>{
    int v, time;
    Node next;

    public Node(int v, int time, Node next) {
        this.v = v;
        this.time = time;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.time, o.time);
    }
}