import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjList;
    static int[] heights, dp;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        heights = new int[N];
        adjList = new List[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
            heights[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int u = Integer.parseInt(st.nextToken()) - 1;
            if (heights[v] > heights[u]) {
                adjList[u].add(v);
            } else {
                adjList[v].add(u);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(dp[i] == 0) dfs(i);
            sb.append(dp[i]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int v) {
        if(dp[v] != 0) return dp[v];

        int max = 0;
        for (int i = 0; i < adjList[v].size(); i++) {
            int u = adjList[v].get(i);
            if (heights[u] > heights[v]) {
                max = Math.max(max, dfs(u));
            }
        }
        return dp[v] = max + 1;
    }
}
