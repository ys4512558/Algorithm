import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] tree;
    static Set<Integer> isv;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        tree = new int[N + 1][3];
        isv = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a][0] = b;
            tree[a][1] = c;
            if (b != -1) tree[b][2] = a;
            if (c != -1) tree[c][2] = a;

        }

        //순회의 끝을 어떻게 판단하는가
        //dfs의 리턴을 누적하는 것으로?
        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        int cnt = 0;
        while (!queue.isEmpty()) {
            int n = queue.poll();

            if(isv.size() == N) break;
            if(isv.contains(tree[n][0]) || tree[n][0] == -1) isv.add(n);
            if (tree[n][0] != -1 && !isv.contains(tree[n][0])) {
                queue.offer(tree[n][0]);
            } else if (tree[n][1] != -1 && !isv.contains(tree[n][1])) {
                queue.offer(tree[n][1]);
            } else if (tree[n][2] != 0) {
                queue.offer(tree[n][2]);
            }
            cnt++;
        }
        return cnt - 1;
    }
}