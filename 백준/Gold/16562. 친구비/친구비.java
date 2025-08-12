import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] costs;
    static Node[] adjList;
    static boolean[] isv;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        costs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new Node[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;
            adjList[v] = new Node(w, adjList[v]);
            adjList[w] = new Node(v, adjList[w]);
        }

        isv = new boolean[N];
        count = 0;
        int cost = 0;
        for (int i = 0; i < N; i++) {
            if(isv[i]) continue;
            cost += bfs(i);
        }
        System.out.println(count != N || cost > K ? "Oh no" : cost);
    }

    public static int bfs(int v) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(v);
        isv[v] = true;
        count++;

        int min = costs[v];
        while (!queue.isEmpty()) {
            int w = queue.poll();

            for (Node n = adjList[w]; n != null; n = n.next) {
                if(isv[n.v]) continue;
                isv[n.v] = true;
                queue.offer(n.v);
                count++;
                min = Math.min(min, costs[n.v]);
            }
        }
        return min;
    }
}

class Node {
    int v;
    Node next;

    public Node(int v, Node next) {
        this.v = v;
        this.next = next;
    }
}