import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static Map<Character, ArrayList<Character>> adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        adj = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char key = st.nextToken().charAt(0);
            adj.put(key, new ArrayList<>());
            for (int j = 0; j < 2; j++) {

                char cur = st.nextToken().charAt(0);
                adj.get(key).add(cur);
            }
        }
        dfsByPreOrder('A');
        sb.append("\n");
        dfsByInOrder('A');
        sb.append("\n");
        dfsByPostOrder('A');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfsByPostOrder(char key) {
        if (key == '.') {
            return;
        }
        ArrayList<Character> list = adj.get(key);

        if(list.get(0) != '.') dfsByPostOrder(list.get(0));
        if(list.get(1) != '.') dfsByPostOrder(list.get(1));
        sb.append(key);
    }

    private static void dfsByInOrder(char key) {
        if (key == '.') {
            return;
        }
        ArrayList<Character> list = adj.get(key);

        if(list.get(0) != '.') dfsByInOrder(list.get(0));
        sb.append(key);
        if(list.get(1) != '.') dfsByInOrder(list.get(1));
    }

    private static void dfsByPreOrder(char key) {
        if (key == '.') {
            return;
        }
        ArrayList<Character> list = adj.get(key);

        sb.append(key);
        if(list.get(0) != '.') dfsByPreOrder(list.get(0));
        if(list.get(1) != '.') dfsByPreOrder(list.get(1));
    }
}