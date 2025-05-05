import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static Edge[] edges;
    static long[] dist;
    static int N, M;
    static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new Edge[M];
        dist = new long[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, cost);
        }
        Arrays.fill(dist, INF);

        boolean flag = bellmanford();
        if (flag) {
            System.out.println(-1);
        } else {
            for (int i = 1; i < N; i++) {
                System.out.println(dist[i] == INF ? -1 : dist[i]);
            }
        }
    }

    private static boolean bellmanford() {
        dist[0] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];
                if (dist[edge.start] == INF) continue;
                if (dist[edge.end] <= dist[edge.start] + edge.cost) continue;
                dist[edge.end] = dist[edge.start] + edge.cost;
            }
        }
        for (int j = 0; j < M; j++) {
            Edge edge = edges[j];
            if (dist[edge.start] == INF) continue;
            if (dist[edge.end] <= dist[edge.start] + edge.cost) continue;
            return true;
        }
        return false;
    }
}

class Edge {
    int start, end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}