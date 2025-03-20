import java.io.*;
import java.util.*;

public class Main {
    static int N, P, Q;
    static int[][] adjList;
    static int[][] dists;
    static Info[] travels, lodgings;
    static int max;
    public static void main(String[] args) throws IOException {
        init();
        floydwarshall();
        for(int i = 0; i < P; i++){
            Info travel = travels[i];
            for(int j = 0; j < Q; j++) {
                Info lodging = lodgings[j];
                int w = travel.w + lodging.w - dists[travel.l][lodging.l];
                max = Math.max(max, w);
            }
        }
        
        System.out.println(max);
    }
    
    public static void floydwarshall() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(dists[i][j] > dists[i][k] + dists[k][j]) {
                        dists[i][j] = dists[i][k] + dists[k][j];
                    }
                }
            }
        }
    }
    
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        max = Integer.MIN_VALUE;
        adjList = new int[N][N];
        dists = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                adjList[i][j] = cost;
                if(i == j) continue;
                if(adjList[i][j] == 0) dists[i][j] = Integer.MAX_VALUE / 2;
                else dists[i][j] = cost;
            }
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());  
        travels = new Info[P];
        lodgings = new Info[Q];
        
        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            travels[i] = new Info(l, w);
        }
        
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            lodgings[i] = new Info(l, w);
        }
    }
}

class Info{
    int l, w; //지점 번호, 기대치
    
    public Info(int l, int w) {
        this.l = l;
        this.w = w;
    }
}