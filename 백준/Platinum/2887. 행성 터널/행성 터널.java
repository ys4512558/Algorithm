import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents, rank;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        int[][] planet = new int[N][4];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planet[i] = new int[]{(i+1), x, y, z};
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int idx = 1; idx <= 3; idx++) {
            int v = idx;
            Arrays.sort(planet, (o1, o2) -> Integer.compare(o1[v], o2[v]));
            for (int i = 1; i < N; i++) {
                pq.offer(new Edge(planet[i][0], planet[i - 1][0], Math.abs(planet[i][v] - planet[i - 1][v])));
            }
        }

        makeSet();
        sb.append(kruskal(pq));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void makeSet() {
        parents = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    private static long kruskal(PriorityQueue<Edge> pq) {
        long res = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (union(edge.from, edge.to)) {
                res += edge.weight;
            }
            if (count == N - 1) break;
        }
        return res;
    }

    public static int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int v1, int v2) {
        int rep1 = find(v1);
        int rep2 = find(v2);

        if (rep1 == rep2) return false;
        if(rank[rep1] < rank[rep2]){
            parents[rep1] = rep2;
            return true;
        }
        parents[rep2] = rep1;
        rank[rep1] = rank[rep1] == rank[rep2] ? rank[rep1] + 1 : rank[rep1];
        return true;
    }
}
class Edge implements Comparable<Edge>{
    int from, to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}