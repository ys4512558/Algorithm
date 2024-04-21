import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Node[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new Node[N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[to] = new Node(from, cost, adjList[to]);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bw.write(dijkstra(end, start));
        bw.flush();
        bw.close();
    }

    private static String dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node s = new Node(start, 0, null);
        pq.offer(s);

        Node e = null;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.vertex == end) {
                e = node;
                break;
            }

            for (Node n = adjList[node.vertex]; n != null; n = n.next) {
                if(dist[n.vertex] > dist[node.vertex] + n.cost){
                    dist[n.vertex] = dist[node.vertex] + n.cost;
                    pq.offer(new Node(n.vertex, dist[n.vertex], node));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sbr = new StringBuilder();
        sb.append(dist[end]).append("\n");
        int cnt = 0;
        for (Node n = e; n != null; n = n.next) {
            cnt++;
            sbr.append(n.vertex).append(" ");
        }
        sb.append(cnt).append("\n");
        sb.append(sbr.toString());
        return sb.toString();
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