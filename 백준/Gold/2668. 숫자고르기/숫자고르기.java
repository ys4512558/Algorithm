import java.io.*;
import java.util.*;

public class Main {
    static Node[] adjList;
    static Set<Integer> selectedSet;
    static boolean[] isv;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] numbers = new int[N + 1];
        isv = new boolean[N + 1];
        adjList = new Node[N + 1];
        List<Integer> selfList = new ArrayList<>();
        selectedSet = new TreeSet<>();

        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            if (numbers[i] == i) {
                numbers[i] = -1;
                selfList.add(i);
                continue;
            }
            adjList[numbers[i]] = new Node(i, adjList[numbers[i]]);
        }

        for (int i = 1; i <= N; i++) {
            if(numbers[i] == -1) continue;
            dfs(i, i, true, new TreeSet<>());
        }
        selectedSet.addAll(selfList);
        StringBuilder sb = new StringBuilder();
        sb.append(selectedSet.size()).append("\n");
        for (int i : selectedSet) {
            sb.append(i).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs(int vertex, int start, boolean isFirst, Set<Integer> set) {
        if (vertex == start && !isFirst) {
            if (selectedSet.addAll(set)) return;
            if (selectedSet.size() < set.size()) {
                selectedSet = new TreeSet<>(set);
            }
            return;
        }

        for (Node node = adjList[vertex]; node != null; node = node.next) {
            if(!set.add(node.vertex)) continue;
            dfs(node.vertex, start, false, set);
            set.remove(node.vertex);
        }
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