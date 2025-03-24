import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Node[] adjList;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new Node[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());
            adjList[A] = new Node(B, C, adjList[A]);
            adjList[B] = new Node(A, C, adjList[B]);
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, null));
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[0] = 0;

        int result = -1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.v] < node.cost) continue;
            if (node.v == N - 1) {
                result = node.cost;
                break;
            }

            for (Node n = adjList[node.v]; n != null; n = n.next) {
                int cost = dist[node.v] + n.cost;

                if (dist[n.v] > cost) {
                    pq.offer(new Node(n.v, cost, null));
                    dist[n.v] = cost;
                }
            }
        }

        return result;
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