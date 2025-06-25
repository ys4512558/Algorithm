import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[] isv;
    static boolean flag;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            adjList[v].add(u);
            adjList[u].add(v);
        }

        for (int i = 0; i < N; i++) {
            isv = new boolean[N];
            isv[i] = true;
            dfs(i, 1);
            if(flag) break;
        }
        System.out.println(flag ? 1 : 0);
    }

    private static void dfs(int v, int count) {
        if (count == 5) flag = true;
        if (flag) return;



        for (int i = 0; i < adjList[v].size(); i++) {
            int u = adjList[v].get(i);
            if(isv[u]) continue;
            isv[u] = true;
            dfs(u, count + 1);
            isv[u] = false;
        }
    }
}
