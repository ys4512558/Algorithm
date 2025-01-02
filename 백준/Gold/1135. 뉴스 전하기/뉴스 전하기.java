import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] trees;
    static int[] times;
    static List<Integer>[] adjList;
    static boolean[] isLeaf;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        trees = new int[N];
        times = new int[N];
        isLeaf = new boolean[N];
        adjList = new List[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            isLeaf[i] = true;
            adjList[i] = new ArrayList<>();
        }

        //-1 세팅
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if(i == 0) continue;
            adjList[trees[i]].add(i);
            isLeaf[trees[i]] = false;
        }
        System.out.println(dfs(0) - 1);
    }

    private static int dfs(int idx) {
        if(isLeaf[idx]) return times[idx] = 1;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < adjList[idx].size(); i++) {
            int next = adjList[idx].get(i);
            pq.offer(new Node(next, dfs(next)));
        }

        int addTime = 0;
        int max = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            times[node.idx] += addTime++;
            max = Math.max(max, times[node.idx]);
        }
        return times[idx] = max + 1;
    }
}

class Node implements Comparable<Node> {
    int idx, time;

    public Node(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        //시간이 많이 걸리는 거 우선순위 높혀주기
        return Integer.compare(o.time, this.time);
    }
}