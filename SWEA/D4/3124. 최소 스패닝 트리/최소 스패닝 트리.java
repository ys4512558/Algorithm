import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents, rank;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
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
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);

        makeSet(V);
        int count = V - 1;
        long weight = 0;
        for (int i = 0; i < E; i++) {
            int from = edges[i].from;
            int to = edges[i].to;
            if (union(from, to)) {
                count++;
                weight += edges[i].weight;
            }
            if(count == 0) break;
        }

        return weight;
    }

    private static void makeSet(int v){
        parents = new int[v + 1];
        rank = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    private static int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int v1, int v2){
        int rep1 = find(v1);
        int rep2 = find(v2);
        if (rep1 == rep2) return false;

        if (rank[rep1] < rank[rep2]) {
            parents[rep1] = rep2;
            return true;
        }
        parents[rep2] = rep1;
        rank[rep1] = rank[rep1] == rank[rep2] ? rank[rep1] + 1 : rank[rep1];
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