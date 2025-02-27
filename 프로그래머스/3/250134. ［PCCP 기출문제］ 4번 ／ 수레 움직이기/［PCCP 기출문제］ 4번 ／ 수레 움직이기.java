import java.util.*;

class Solution {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    static int N, M; //행, 열 (최대 4)
    static int[][] map;
    static int[][][][] dp; //[빨x][빨y][파x][파y] = 해당 상태가 될때의 최소 턴 수
    static final int DIR = 4;
    static State dest;
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        dp = new int[N][M][N][M];
        map = new int[N][M];
        //0 : 빈칸, 1 : 빨간 수레의 시작 칸, 2 : 파란 수레의 시작 칸
        //3 : 빨간 수레의 도착 칸, 4 : 파란 수레의 도착 칸, 5 : 벽
        
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        dest = new State(0, 0, 0, 0, 0, null);
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = maze[i][j];
                switch (map[i][j]) {
                    case 1:
                        rx = i;
                        ry = j;
                        break;
                    case 2:
                        bx = i;
                        by = j;
                        break;
                    case 3:
                        dest.rx = i;
                        dest.ry = j;
                        break;
                    case 4:
                        dest.bx = i;
                        dest.by = j;
                        break;
                }
            }
        }
        
        int answer = bfs(new State(rx, ry, bx, by, 1, new int[N][M]));
        return answer;
    }
    
    public static int bfs(State start) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        start.isv[start.rx][start.ry] = 1;
        start.isv[start.bx][start.by] = 2;
        pq.offer(start);
        //0으로 초기화되어 있으므로 1부터 시작해서 출력 시 -1 해주기
        //답이 0인 경우는 
        //어차피 maze[i][j]에 시작이면서 끝을 표현할 수 없을 것이므로 없다고 생각하고 풀어도 될듯
        dp[start.rx][start.ry][start.bx][start.by] = 1;
        
        int result = 0;
        while(!pq.isEmpty()) {
            State state = pq.poll();
            
            int turn = dp[state.rx][state.ry][state.bx][state.by];
            if(turn != 0 && turn < state.turn) continue;
            if(isEnd(state)) {
                System.out.println(state.rx + " " + state.ry + " " + state.bx + " " + state.by + ", turn = " + state.turn );
                result = state.turn - 1;
                break;
            } 
            System.out.println(state.rx + " " + state.ry + " " + state.bx + " " + state.by + ", turn = " + state.turn );
            for(int i = 0; i < DIR; i++) {
                int rnx = state.rx + dx[i];
                int rny = state.ry + dy[i];
                if(isOutRange(rnx, rny)) continue;
                for(int j = 0; j < DIR; j++) {
                    int bnx = state.bx + dx[j];
                    int bny = state.by + dy[j];

                    if(isOutRange(bnx, bny)) continue;
                    
                    if(state.rx == dest.rx && state.ry == dest.ry) {
                        rnx = state.rx;
                        rny = state.ry;
                    }
                    if(state.bx == dest.bx && state.by == dest.by) {
                        bnx = state.bx;
                        bny = state.by;
                    }
                    if(rnx == bnx && rny == bny) continue;
                    if((rnx == state.bx && rny == state.by) 
                       && (bnx == state.rx && bny == state.ry)) continue;
                    if(dp[rnx][rny][bnx][bny] != 0 && dp[rnx][rny][bnx][bny] <= state.turn + 1) {
                        continue;
                    }
                    
                    int[][] visited = state.isv;
                    //1, 2, 3중 하나
                    //(3, 1) - 1 이 짝수 0, 2면 안됨. 9
                    if((!((rnx == dest.rx) && (rny == dest.ry)))
                       && (visited[rnx][rny] == 1 || visited[rnx][rny] == 3)) continue;
                    //(2, 3) - 2)이 0, 1
                    if((!((bnx == dest.bx) && (bny == dest.by)))
                       && (visited[bnx][bny] == 2 || visited[bnx][bny] == 3)) continue;
                    
                    visited = copy(state.isv);
                    int red = visited[rnx][rny];
                    int blue = visited[bnx][bny];
                    if(red == 0 || red == 2) {
                        visited[rnx][rny] = red + 1;
                    }
                    if(blue == 0 || blue == 1) {
                        visited[bnx][bny] = blue + 2;
                    }
                    
                    dp[rnx][rny][bnx][bny] = state.turn + 1;
                    pq.offer(new State(rnx, rny, bnx, bny, state.turn + 1, visited));
                }
            }
        }
        return result;        
    }
    
    public static int[][] copy(int[][] isv) {
        int[][] copy = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                copy[i][j] = isv[i][j];
            }
        }
        return copy;
    }
    
    public static boolean isEnd(State state) {
        return state.rx == dest.rx 
                && state.ry == dest.ry 
                && state.bx == dest.bx 
                && state.by == dest.by;
    }
    
    public static boolean isOutRange(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 5) return true;
        return false;
    } 
}

class State implements Comparable<State> {
    int rx, ry;
    int bx, by;
    int turn;
    int[][] isv;
    
    public State(int rx, int ry, int bx, int by, int turn, int[][] isv) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.turn = turn;
        this.isv = isv;
    }
    
    public int compareTo(State o) {
        return Integer.compare(this.turn, o.turn);
    }
}