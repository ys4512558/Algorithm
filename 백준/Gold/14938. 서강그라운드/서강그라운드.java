import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static final int INF = Integer.MAX_VALUE;
    static int[] arr;
    static Node[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        adjList = new Node[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, cost, adjList[from]);
            adjList[to] = new Node(from, cost, adjList[to]);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dijkstra(i));
        }
        System.out.println(max);
    }

    private static int dijkstra(int start) {
        int[] dist = new int[n + 1];
        boolean[] isv = new boolean[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(start, 0, null));

        int res = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.vertex] != INF && dist[node.vertex] > m) break;
            if(!isv[node.vertex]) {
                isv[node.vertex] = true;
                res += arr[node.vertex];
            }
            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if (dist[n.vertex] > dist[node.vertex] + n.cost) {
                    dist[n.vertex] = dist[node.vertex] + n.cost;
                    pq.offer(new Node(n.vertex, dist[n.vertex], null));
                }
            }
        }
        return res;
    }
}

class Node implements Comparable<Node>{
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