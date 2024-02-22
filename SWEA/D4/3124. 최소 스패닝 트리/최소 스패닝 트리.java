import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] parents, rank;
    static int N, M;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static long solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];

        makeSet(N);

        for (int i = 0; i < M; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());

            edges[i] = new Edge(from, to, weight);
        }
        Arrays.sort(edges);
        return kruskal(edges);
    }

    private static long kruskal(Edge[] edges) {
        int edgeCount = N - 1;
        long weight = 0;
        int idx = 0;
        while (edgeCount > 0) {
            if (union(edges[idx].from, edges[idx].to)) {
                edgeCount--;
                weight += edges[idx].weight;
            }
            idx++;
        }
        return weight;
    }

    private static void makeSet(int N) {
        parents = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    private static int find(int v){
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int v1, int v2) {
        int rep1 = find(v1);
        int rep2 = find(v2);
        if(rep1 == rep2) return false;
        if(rank[rep1] >= rank[rep2]){
            rank[rep1] = rank[rep1] == rank[rep2] ? rank[rep1] + 1 : rank[rep1];
            parents[rep2] = rep1;
            return true;
        }
        parents[rep1] = rep2;
        return true;
    }

}

class Edge implements Comparable<Edge>{
    int from, to, weight;

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