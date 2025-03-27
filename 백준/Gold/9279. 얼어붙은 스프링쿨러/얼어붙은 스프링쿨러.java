import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] adjList;
    static int N, C;
    //파이프 개수가 1개이면 스프링쿨러임

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        adjList = new int[1001][1001];
        while ((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken()) - 1;

            
            for (int i = 0; i < 1001; i++) {
                for (int j = 0; j < 1001; j++) {
                    adjList[i][j] = -1;
                }
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken()) - 1;
                int u = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());

                adjList[v][u] = c;
                adjList[u][v] = c;
            }
            int result = dfs(C, -1);
            System.out.println(result);
        }
    }

    private static int dfs(int v, int pre) {
        int cost = 0;
        for (int i = 0; i < N; i++) {
            if(adjList[v][i] == -1 || i == pre) continue;
            int sum = dfs(i, v);
            if (sum == 0) {
                cost += adjList[v][i];
                continue;
            }
            //v 에서 node.v를 바로 잠그기 vs node.v 에서 node.v의 자식들을 모두 잠그기
            cost += Math.min(adjList[v][i], sum);
        }

        return cost;
    }
}