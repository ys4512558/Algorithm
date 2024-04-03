import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static ArrayList[] adjList1;
    static ArrayList[] adjList2;
    static int N, M;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
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

    private static int solve() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList1 = new ArrayList[N + 1];
        adjList2 = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList1[i] = new ArrayList<Integer>();
            adjList2[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjList1[s].add(e);
            adjList2[e].add(s);
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            boolean[] isv = new boolean[N + 1];

            int cnt1 = bfs(i, adjList1, isv);
            int cnt2 = bfs(i, adjList2, isv);

            if(N == (cnt1 + cnt2 + 1)) cnt++;
        }
        return cnt;
    }

    private static int bfs(int start, ArrayList[] adjList, boolean[] isv) {
        int cnt = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        isv[start] = true;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < adjList[cur].size(); i++) {
                int next = (int) adjList[cur].get(i);
                if (isv[next]) continue;
                queue.offer(next);
                isv[next] = true;
                cnt++;
            }
        }
        return cnt;
    }
}