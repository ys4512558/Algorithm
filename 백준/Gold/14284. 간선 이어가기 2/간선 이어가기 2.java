import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Node[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[v] = new Node(u, cost, adjList[v]);
            adjList[u] = new Node(v, cost, adjList[u]);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra(s, t));
    }

    private static int dijkstra(int s, int t) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0, null));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.v == t) return node.cost;
            if(dist[node.v] < node.cost) continue;

            for (Node n = adjList[node.v]; n != null; n = n.next) {
                int cost = node.cost + n.cost;
                if (dist[n.v] > cost) {
                    dist[n.v] = cost;
                    pq.offer(new Node(n.v, cost, null));
                }
            }
        }

        return -1;
    }
}

class Node implements Comparable<Node> {

    int v, cost;
    Node next;

    public Node(int v, int cost, Node next) {
        this.v = v;
        this.cost = cost;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
