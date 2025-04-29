import java.io.*;
import java.util.*;

public class Main {
    static int[] prerequisite;
    static int[] results;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        prerequisite = new int[N];
        results = new int[N];
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            adjList[A].add(B);
            prerequisite[B]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if(prerequisite[i] != 0) continue;
            queue.offer(i);
            results[i] = 1;
        }

        topologySort(queue);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.length; i++) {
            sb.append(results[i] + " ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static void topologySort(Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 0; i < adjList[num].size(); i++) {
                int next = adjList[num].get(i);
                if (--prerequisite[next] == 0) {
                    results[next] = results[num] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}
