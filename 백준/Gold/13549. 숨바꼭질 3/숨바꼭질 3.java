import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static int bfs() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.offer(new Pair(N, 0));
        int[] dp = new int[200_001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;
        int res = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if(p.pos == K) {
                res = p.cost;
                break;
            }

            if(p.pos * 2 < 200_000 && dp[p.pos * 2] > p.cost) {
                pq.offer(new Pair(p.pos * 2, p.cost));
                dp[p.pos * 2] = p.cost;
            }
            if(p.pos > 0 && dp[p.pos - 1] > p.cost + 1) {
                pq.offer(new Pair(p.pos - 1, p.cost + 1));
                dp[p.pos - 1] = p.cost + 1;
            }
            if(p.pos < K && dp[p.pos + 1] > p.cost + 1) {
                pq.offer(new Pair(p.pos + 1, p.cost + 1));
                dp[p.pos + 1] = p.cost + 1;
            }
        }
        return res;
    }
}

class Pair implements Comparable<Pair>{
    int pos, cost;

    public Pair(int pos, int cost) {
        this.pos = pos;
        this.cost = cost;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.cost, o.cost);
    }
}