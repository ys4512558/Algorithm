import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;
    static int[][] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        //[0] : 집합의 원소 수, [1] : 사탕 수
        rank = new int[N + 1][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int candy = Integer.parseInt(st.nextToken());
            parents[i] = i;
            rank[i] = new int[]{1, candy};
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());

            union(v, u);
        }

        int max = 0;
        int[] dp = new int[K];
        for (int i = 1; i <= N; i++) {
            if(rank[i][0] == 0) continue;
            for (int j = K - 1; j >= 0; j--) {
                if(j < rank[i][0]) break;
                dp[j] = Math.max(dp[j], dp[j - rank[i][0]] + rank[i][1]);
            }
        }
        System.out.println(dp[K - 1]);
    }

    private static int find(int v) {
        if (parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int v, int u) {
        int p1 = find(v);
        int p2 = find(u);

        if (p1 == p2) return false;

        if (rank[p1][0] < rank[p2][0]) {
            parents[p1] = p2;
            rank[p2] = new int[]{rank[p1][0] + rank[p2][0], rank[p1][1] + rank[p2][1]};
            rank[p1] = new int[]{0, 0};
            return true;
        }
        parents[p2] = p1;
        rank[p1] = new int[]{rank[p1][0] + rank[p2][0], rank[p1][1] + rank[p2][1]};
        rank[p2] = new int[]{0, 0};
        return true;
    }
}