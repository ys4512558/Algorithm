import java.io.*;
import java.util.*;

public class Main {
    static Queue<Edge> minHeap;
    static Queue<Edge> maxHeap;
    static int N, M;
    static int[] parents, ranks;
    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;
    static Edge[] edges;
    static boolean[] maxVisited, minVisited;
    public static void main(String[] args) throws IOException {
        minHeap = new PriorityQueue<Edge>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        maxHeap = new PriorityQueue<Edge>((o1, o2) -> Integer.compare(o2.cost, o1.cost));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maxVisited = new boolean[M];
        minVisited = new boolean[M];
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int u = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(i, v, u, cost);
            minHeap.offer(edge);
            maxHeap.offer(edge);
            edges[i] = edge;
        }

        if (N <= 2 || M == N - 1) {
            System.out.println("NO");
        } else {
            kruskal(minHeap, minVisited);
            kruskal(maxHeap, maxVisited);
            init();
            StringBuilder sb = new StringBuilder();
            sb.append("YES\n");
            for (int i = 0; i < M; i++) {
                if (!minVisited[i] && union(edges[i].v, edges[i].u)) {
                    sb.append((i + 1) + " ");
                    break;
                }
            }
            for (int i = 0; i < M; i++) {
                if (!maxVisited[i] && union(edges[i].v, edges[i].u)) {
                    sb.append((i + 1) + " ");
                    break;
                }
            }

            int count = 2;
            for (int i = 0; i < M; i++) {
                if(union(edges[i].v, edges[i].u)) {
                    sb.append((i + 1) + " ");
                    count++;
                }
                if(count == N - 1) break;
            }


            System.out.println(sb);
        }

    }

    public static void init() {
        parents = new int[N];
        ranks = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public static long kruskal(Queue<Edge> queue, boolean[] visited) {
        init();
        int count = 0;
        long cost = 0;
        while (!queue.isEmpty() && count != N - 1) {
            Edge edge = queue.poll();
            if(!union(edge.v, edge.u)) continue;
            count++;
            cost += edge.cost;
            visited[edge.idx] = true;
        }
        if(count != N - 1) return -1;
        return cost;
    }


    public static int find(int v) {
        if(v == parents[v]) return v;
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int v, int w) {
        int p1 = find(v);
        int p2 = find(w);

        if(p1 == p2) return false;

        if(ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
            return true;
        }

        ranks[p1] = ranks[p1] == ranks[p2] ? ranks[p1] + 1 : ranks[p1];
        parents[p2] = p1;
        return true;
    }
}

class Edge {
    int idx, v, u, cost;


    public Edge(int idx, int v, int u, int cost) {
        this.idx = idx;
        this.v = v;
        this.u = u;
        this.cost = cost;
    }
}
