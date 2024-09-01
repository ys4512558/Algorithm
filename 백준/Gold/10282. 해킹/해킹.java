import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Node[] adjList;
    static final int INF = Integer.MAX_VALUE / 2;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList = new Node[n + 1];

            for (int j = 0; j < d; j++) {
                StringTokenizer stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());
                int s = Integer.parseInt(stk.nextToken());

                adjList[b] = new Node(a, s, adjList[b]);
            }

            dijkstra(c, n);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dijkstra(int start, int size) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, null));
        int[] dist = new int[size + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.vertex] < node.cost) continue;
            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if (dist[n.vertex] > dist[node.vertex] + n.cost) {
                    dist[n.vertex] = dist[node.vertex] + n.cost;
                    pq.offer(new Node(n.vertex, dist[n.vertex], null));
                }
            }
        }
        int max = 0;
        int cnt = 0;
        for (int i = 1; i < dist.length; i++) {
            if(dist[i] == INF) continue;
            max = Math.max(max, dist[i]);
            cnt++;
        }
        sb.append(cnt)
          .append(" ")
          .append(max)
          .append("\n");
    }
}

class Node implements Comparable<Node> {
    int vertex, cost;
    Node next;

    public Node(int vertex, int cost, Node next) {
        this.vertex = vertex;
        this.cost = cost;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}