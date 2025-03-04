import java.util.*;
import java.io.*;

public class Main {
    static int N;
    //팀 빌딩 여부 상관없이 시도해서 끝난것, 팀 빌딩에 성공
    static boolean[] isDone, isTeam;
    static int[] wanted;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            isDone = new boolean[N];
            isTeam= new boolean[N];
            wanted = new int[N];
            int cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                wanted[j] = Integer.parseInt(st.nextToken()) - 1;
            }
            for(int j = 0; j < N; j++) {
                if(isDone[j]) {
                    if(isTeam[j]) cnt++;
                    continue;
                }
                isDone[j] = true;
                Set set = new HashSet<Integer>();
                set.add(j);
                dfs(j, new Node(j, null), set);
                if(isTeam[j]) cnt++;
            }
            sb.append(N - cnt).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();       
    }
    
    public static void dfs(int cur, Node node, Set<Integer> set) {
        int next = wanted[cur];
        if(set.add(next)) {
            if(isDone[next]) return;
            isDone[next] = true;
            dfs(next, new Node(next, node), set);
        } else {
            int dest = next;
            isTeam[next] = true;
            for(Node n = node; n != null; n = n.pre) {
                if(n.vertex == dest) break;
                isTeam[n.vertex] = true;
            }
        }
    }
}

class Node {
    int vertex;
    Node pre;
    
    public Node(int vertex, Node pre) {
        this.vertex = vertex;
        this.pre = pre;
    }
}