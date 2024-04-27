import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Main {

    static Node[] adjList;
    static final int INF = Integer.MAX_VALUE;
    static long[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        adjList = new Node[N + 1];
        dist = new long[3][N + 1];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, cost, adjList[from]);
            adjList[to] = new Node(from, cost, adjList[to]);
        }

        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        int[] nodes = new int[]{1, first, second};
        for (int i = 0; i < 3; i++) {
            dijkstra(i, nodes[i]);
        }

        long res1 = -1;
        long res2 = -1;
        if(dist[0][first] != INF && dist[1][second] != INF && dist[2][N] != INF){
            res1 = dist[0][first] + dist[1][second] + dist[2][N];
        }
        if(dist[0][second] != INF && dist[2][first] != INF && dist[1][N] != INF){
            res2 = dist[0][second] + dist[2][first] + dist[1][N];
        }
        long res;
        if(res1 == -1 && res2 == -1) res = -1;
        else if(res1 == -1) res = res2;
        else if(res2 == -1) res = res1;
        else res = Math.min(res1, res2);
        System.out.println(res);
    }

    public static void dijkstra(int idx, int current) {
        Arrays.fill(dist[idx], INF);
        dist[idx][current] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(current, 0, null));
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if (dist[idx][n.vertex] > dist[idx][node.vertex] + n.cost) {
                    dist[idx][n.vertex] = dist[idx][node.vertex] + n.cost;
                    pq.offer(new Node(n.vertex, dist[idx][n.vertex], null));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int vertex;
    long cost;
    Node next;

    public Node(int vertex, long cost, Node next) {
        this.vertex = vertex;
        this.cost = cost;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}