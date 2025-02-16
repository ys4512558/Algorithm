import java.io.*;
import java.util.*;

public class Main {
    static Node[] adjList;
    static int[] depth;
    static int[][] parents;
    static int N, M, H;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = (int) (Math.log(N) / Math.log(2)) + 1;
        adjList = new Node[N + 1];
        depth = new int[N + 1];
        parents = new int[N + 1][H];
        
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            adjList[v] = new Node(u, adjList[v]);
            adjList[u] = new Node(v, adjList[u]);
        }
        //depth 세팅
        bfs();
        setParents();
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            
            sb.append(LCA(v, u)).append("\n");
        }
        // printDepth();
        // printParents();
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    public static void setParents(){
        for(int h = 1; h < H; h++) {
            for(int v = 1; v <= N; v++) {
                if(parents[v][h - 1] != 0) {
                    parents[v][h] = parents[parents[v][h - 1]][h - 1];
                }
            }
        }
    }
    
    public static int LCA(int v, int u) {
        int offset = 0;
        if(depth[v] < depth[u]) {
            int tmp = v;
            v = u;
            u = tmp;   
        }
        int diff = depth[v] - depth[u];
        
        for(int i = 0; i < H; i++) {
            if((diff & (1 << i)) != 0) {
                v = parents[v][i];
            }
        }
        
        if(v == u) return v;
        
        for(int i = H - 1; i >= 0; i--) {
            if(parents[v][i] != parents[u][i]) {
                v = parents[v][i];
                u = parents[u][i];
                continue;
            }
        }
        
        return parents[v][0];
    }
    
    public static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] isv = new boolean[N + 1];
        queue.offer(1);
        isv[1] = true;
        
        int dep = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            dep++;
            while(size-- > 0) {
                int v = queue.poll();
                
                for(Node n = adjList[v]; n != null; n = n.next) {
                    if(isv[n.v]) continue;
                    queue.offer(n.v);
                    isv[n.v] = true;
                    depth[n.v] = dep;
                    parents[n.v][0] = v;
                }
            }
        }
    }
    
    public static void printDepth() {
        for(int i = 1; i <= N; i++) {
            System.out.print(depth[i] + " ");
        }
        System.out.println();
    }
    
    public static void printParents() {
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < parents[i].length; j++) {
                System.out.print(parents[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
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