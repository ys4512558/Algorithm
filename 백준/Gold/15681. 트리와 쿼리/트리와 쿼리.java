import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    static Node[] adjList;
    static int[] dp;
    static boolean[] isv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        adjList = new Node[N + 1];
        dp = new int[N + 1];
        isv = new boolean[N + 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            adjList[v] = new Node(u, adjList[v]);
            adjList[u] = new Node(v, adjList[u]);
        }

        dfs(R);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int vertex = Integer.parseInt(br.readLine());
            sb.append(dp[vertex]).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int vertex) {
        int cnt = 1; //자기 자신
        isv[vertex] = true;
        for (Node n = adjList[vertex]; n != null; n = n.next) {
            if (isv[n.vertex]) continue;
            dfs(n.vertex);
            cnt += dp[n.vertex];
        }
        dp[vertex] = cnt;
    }
}

class Node {
    int vertex;
    Node next;

    public Node(int vertex, Node next) {
        this.vertex = vertex;
        this.next = next;
    }
}