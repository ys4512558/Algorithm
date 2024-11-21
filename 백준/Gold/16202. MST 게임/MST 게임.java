import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;
    static int[] ranks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        ranks = new int[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(v, u, i + 1));
        }

        PriorityQueue<Edge> next = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            for (int j = 0; j <= N; j++) {
                parents[j] = j;
                ranks[j] = 1;
            }
            int total = 0;
            int size = 0;
            boolean flag = false;
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                if (union(edge.v, edge.u)) {
                    size++;
                    total += edge.cost;
                    if (!flag) {
                        flag = true;
                        continue;
                    }
                    next.offer(edge);
                    continue;
                }
                next.offer(edge);
            }
            if (size == N - 1) {
                sb.append(total + " ");
            } else {
                sb.append(0 + " ");
            }
            while (!next.isEmpty()) {
                pq.offer(next.poll());
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int find(int v) {
        if (v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int v, int u) {
        int p1 = find(v);
        int p2 = find(u);

        if(p1 == p2) return false;

        if (ranks[p1] < ranks[p2]) {
            ranks[p2]++;
            parents[p1] = p2;
            return true;
        }
        ranks[p1] = ranks[p1] == ranks[p2] ? ranks[p1] + 1 : ranks[p1];
        parents[p2] = p1;
        return true;
    }

}

class Edge implements Comparable<Edge>{
    int v, u, cost;

    public Edge(int v, int u, int cost) {
        this.v = v;
        this.u = u;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}