import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
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

        ArrayList<ArrayList<Vertex>> adjList = new ArrayList<ArrayList<Vertex>>(N + 1);
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            adjList.get(from).add(new Vertex(to, weight));
            adjList.get(to).add(new Vertex(from, weight));
        }

        return prim(adjList);
    }

    private static long prim(ArrayList<ArrayList<Vertex>> adjList) {
        int[] minEdge = new int[N + 1];
        boolean[] isv = new boolean[N + 1];

        Arrays.fill(minEdge, Integer.MAX_VALUE);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        minEdge[1] = 0;
        pq.offer(new Vertex(1, minEdge[1]));
        long result = 0;

        while (!pq.isEmpty()) {
            Vertex v = pq.poll();

            if(isv[v.node]) continue;
            result += v.weight;
            isv[v.node] = true;

            ArrayList<Vertex> list = adjList.get(v.node);
            for (int i = 0; i < list.size(); i++) {
                int no = list.get(i).node;
                int weight = list.get(i).weight;
                if (!isv[no] && weight < minEdge[no]) {
                    minEdge[no] = weight;
                    pq.offer(new Vertex(no, weight));
                }
            }
        }

        return result;
    }
}

class Vertex implements Comparable<Vertex>{
    int node;
    int weight;

    public Vertex(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.weight, o.weight);
    }
}