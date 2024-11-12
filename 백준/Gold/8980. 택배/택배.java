import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());


        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(start, end, cost));
        }
        int[] weights = new int[N + 1];

        int sum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            //남은 공간과 현재 무게 중 작은 것
            int min = Math.min(edge.cost, C - weights[edge.start]);
            for (int i = edge.start; i <= edge.end; i++) {
                min = Math.min(min, C - weights[i]);
                if(min <= 0) break;
            }
            if(min <= 0) continue;
            for (int i = edge.start; i < edge.end; i++) {
                weights[i] += min;
            }
            sum += min;
        }
        System.out.println(sum);
    }
}

class Edge implements Comparable<Edge> {
    int start, end, cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.end == o.end) return Integer.compare(o.start, this.start);
        return Integer.compare(this.end, o.end);
    }
}