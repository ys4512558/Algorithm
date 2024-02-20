import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list;
    static int[] edgeCount;

    static Queue<Integer> queue = new ArrayDeque<>();
    static ArrayList<Integer> sortedList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>(N + 1);
        edgeCount = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(stk.nextToken());
            int second = Integer.parseInt(stk.nextToken());
            list.get(first).add(second);
            edgeCount[second]++;
        }

        for (int i = 1; i <= N; i++) {
            if(edgeCount[i] == 0) {
                queue.offer(i);
                sortedList.add(i);
            }
        }
        topologySort();
        for (Integer i : sortedList) {
            sb.append(i).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void topologySort() {
        while (!queue.isEmpty()) {
            int idx = queue.poll();

            for (int i = 0; i < list.get(idx).size(); i++) {
                int next = list.get(idx).get(i);
                edgeCount[next]--;
                if(edgeCount[next] == 0) {
                    queue.offer(next);
                    sortedList.add(next);
                }
            }
        }
    }
}