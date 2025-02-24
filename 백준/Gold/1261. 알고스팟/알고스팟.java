import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};
    static boolean[][] map;
    static int[][] dp;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        map = new boolean[N][M];
        dp = new int[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) == '0' ? false : true;
                dp[i][j] = 100 * 100;
            }
            
        }
        
        System.out.println(dijkstra());
    }
    
    public static int dijkstra() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(0, 0, 0));
        dp[0][0] = 0;
        
        int result = 0;
        while(!pq.isEmpty()) {
            Info info = pq.poll();
            
            if(dp[info.x][info.y] < info.cost) continue;
            
            if(info.x == N - 1 && info.y == M - 1) {
                result = info.cost;
                break;
            };
            
            for(int i = 0; i < 4; i++) {
                int nx = info.x + dx[i];
                int ny = info.y + dy[i];
                
                if(isOutRange(nx, ny)) continue;
                int count = info.cost + (map[nx][ny] ? 1 : 0);
                if(dp[nx][ny] > count) {
                    dp[nx][ny] = count;
                    pq.offer(new Info(nx, ny, count));
                }
            }
        }
        return result;
    }
    
    public static boolean isOutRange(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= M) return true;
        return false;
    }
}


class Info implements Comparable<Info> {
    int x, y, cost;
    
    public Info(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
    
    public int compareTo(Info o) {
        return Integer.compare(this.cost, o.cost);
    }
    
}