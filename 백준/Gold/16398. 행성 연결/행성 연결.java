import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parents;
    static PriorityQueue<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = new PriorityQueue<>();
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            parents[i] = -1;
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if(i >= j) continue;
                edges.offer(new Edge(i, j, cost));
            }
        }
        System.out.println(kruskal());
    }

    public static long kruskal() {
        long sum = 0;
        int count = 0;
        while (!edges.isEmpty() && count != N - 1) {
            Edge edge = edges.poll();

            if (union(edge.v, edge.u)) {
                sum += edge.cost;
                count++;
            }
        }
        return sum;
    }

    public static int find(int v) {
        if(parents[v] < 0) return v;
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int v, int u) {
        int p1 = find(v);
        int p2 = find(u);

        if(p1 == p2) return false;
        int size1 = -parents[v];
        int size2 = -parents[u];

        if (size1 < size2) {
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }
        parents[p1] += parents[p2];
        parents[p2] = p1;
        return true;
    }
}

class Edge implements Comparable<Edge> {
    int v, u;
    long cost;

    public Edge(int v, int u, long cost) {
        this.v = v;
        this.u = u;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.cost, o.cost);
    }
}