import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BOJ1967.prob1967();
    }
}


class BOJ1967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int max = 0;
    public static void prob1967() throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        //인접 리스트 생성
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(parent).add(new Node(child, weight));
        }

        dfs(1);
        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static int dfs(int currentNode){
        ArrayList<Node> currentList = list.get(currentNode);

        int firstWeight = 0;
        int secondWeight = 0;
        for (Node node : currentList) {
            int weight = dfs(node.idx) + node.weight;
            if (firstWeight < weight) {
                secondWeight = firstWeight;
                firstWeight = weight;
            } else if (secondWeight < weight) {
                secondWeight = weight;
            }
        }
        max = Math.max(max, firstWeight + secondWeight);
        //리프 노드는 0을 리턴하게 됨.
        return firstWeight;
    }
}

class Node{
    int idx;
    int weight;

    public Node(int idx, int weight) {
        this.idx = idx;
        this.weight = weight;
    }
}