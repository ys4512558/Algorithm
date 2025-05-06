import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, S;
    static Edge[] edges;
    static int[] parents;
    static int[] dist;
    static int end;
    static final int INF = Integer.MAX_VALUE / 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        parents = new int[N];
        edges = new Edge[M];
        dist = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, cost);
        }
        int cycle = bellmanford();
        StringBuilder sb = new StringBuilder();
        if (cycle != -1) {
            sb.append("CYCLE");
            sb.append("\n");
            int v = cycle;
            //내부 사이클 진입 보장
            for (int i = 0; i < N; i++) {
                v = parents[v];
            }
            int start = v;
            List<Integer> list = new ArrayList<>();
            list.add(v);
            do list.add((v = parents[v])); while (v != start);
            sb.append(list.size() - 1).append("\n");
            for (int i = list.size() - 1; i >= 0; i--) {
                sb.append(list.get(i) + " ");
            }
        } else {
            sb.append("PATH");
            sb.append("\n");
            for (int i = 0; i < N; i++) {
                sb.append(dist[i] + " ");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int bellmanford() {
        Arrays.fill(dist, INF);
        dist[S] = 0;
        int updateIdx = 0;
        for (int i = 0; i < N; i++) {
            updateIdx = -1;
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];
                if (dist[edge.start] == INF) continue;
                int cost = dist[edge.start] + edge.cost;
                if (dist[edge.end] <= cost) continue;
                parents[edge.end] = edge.start;
                dist[edge.end] = cost;
                updateIdx = edge.end;
            }
            //업데이트 없으면 경로 탐색 중단
            if (updateIdx == -1) break;
        }
        return updateIdx;
    }
}
class Edge {
    int start, end, cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}