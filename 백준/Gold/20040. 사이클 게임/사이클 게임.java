import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(v, u);
        }

        int res = 0;
        for (int i = 0; i < M; i++) {
            Edge edge = edges[i];
            if (union(edge.v, edge.u)) continue;
            res = i + 1;
            break;
        }
        System.out.println(res);
    }

    private static int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int v, int u) {
        int p1 = find(v);
        int p2 = find(u);

        if(p1 == p2) return false;
        if (rank[p1] < rank[p2]) {
            parents[p1] = p2;
            return true;
        }
        rank[p1] = rank[p1] == rank[p2] ? rank[p1] + 1 : rank[p1];
        parents[p2] = p1;
        return true;
    }
}

class Edge {
    int v, u;

    public Edge(int v, int u) {
        this.v = v;
        this.u = u;
    }
}