import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Node[] adjList;
    static long[][] dist;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());
            adjList[a] = new Node(b, c, 0, adjList[a]);
            adjList[b] = new Node(a, c, 0, adjList[b]);
        }
        System.out.println(dijkstra());
    }

    private static long dijkstra() {
        Queue<Node> pq = new PriorityQueue<>();
        dist = new long[N][2]; //실제 비용 / 할인 비용
        for (int i = 0; i < N; i++) {
            dist[i] = new long[]{INF, INF};
        }
        dist[0] = new long[]{0, 0};
        pq.offer(new Node(0, 0, 0, null));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (dist[node.v][0] < node.cost) continue;
            if (dist[node.v][0] == node.cost && dist[node.v][1] < node.discountCost) continue;
            for (Node n = adjList[node.v]; n != null; n = n.next) {
                long discountCost = ((node.cost * 9) / 10) + n.cost;
                if (dist[n.v][0] < node.cost + n.cost) continue;
                if (dist[n.v][0] == node.cost + n.cost && dist[n.v][1] <= discountCost) continue;
                dist[n.v][0] = node.cost + n.cost;
                dist[n.v][1] = discountCost;
                pq.offer(new Node(n.v, node.cost + n.cost, discountCost, null));
            }
        }
        long total = 0;
        for (int i = 0; i < N; i++) {
            total += dist[i][1];
        }
        return total;
    }
}

class Node implements Comparable<Node> {

    int v;
    long cost; //? - v의 실제 비용
    long discountCost; //? - v의 할인 비용
    Node next;

    public Node(int v, long cost, long discountCost, Node next) {
        this.v = v;
        this.cost = cost;
        this.discountCost = discountCost;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        if(this.cost == o.cost) Long.compare(this.discountCost, o.discountCost);
        return Long.compare(this.cost, o.cost);
    }
}