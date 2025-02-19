import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, result;
    static boolean[][] map; //노트북
    static final int DIR = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        K = Integer.parseInt(st.nextToken()); //스티커 수
        
        map = new boolean[N][M];
        result = 0; //결과 출력
        
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            boolean[][] sticker = new boolean[R][C];
            int size = 0;
            for(int r = 0; r < R; r++) {    
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++) {
                    int num = Integer.parseInt(st.nextToken());
                    if(num == 1) {
                        sticker[r][c] = true;
                        size++;
                    }
                }
            }
            if(solution(sticker)) result += size;
        }
        System.out.println(result);
    }
    
    //map에 sticker + offset을 통해 겹쳐놓고 확인하기? 
    public static boolean solution(boolean[][] sticker) {
        for(int i = 1; i <= DIR; i++) {
            int R = sticker.length;
            int C = sticker[0].length;
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) {
                    //map의 r, c에 붙힐 수 있는지 확인
                    if(isValid(r, c, sticker)) {
                        stick(r, c, sticker);
                        //가능하면 리턴
                        return true;
                    }
                }
            }
            if(i == DIR) break;
            sticker = rotate(R, C, sticker);
        }
        return false;
    }
    
    //스티커 map에 붙히기
    public static void stick(int mr, int mc, boolean[][] sticker) {
        int sr = sticker.length;
        int sc = sticker[0].length;
        for(int r = 0; r < sr; r++) {
            for(int c = 0; c < sc; c++) {
                map[mr + r][mc + c] |= sticker[r][c];
            }
        }
    }
    
    //map의 r, c에 붙힐 수 있는지 확인
    public static boolean isValid(int mr, int mc, boolean[][] sticker) {
        int sr = sticker.length;
        int sc = sticker[0].length;
        int R = mr + sr;
        int C = mc + sc;
        if(R - 1 >= N || C - 1 >= M) return false;
        
        for(int r = 0; r < sr; r++) {
            for(int c = 0; c < sc; c++) {
                //빈 공간이면 스티커가 뭐든 가능
                //빈 공간 아니면 스티커가 0만 가능
                if(!map[mr + r][mc + c] || (map[mr + r][mc + c] && !sticker[r][c])) continue;
                //하나라도 안되면 불가능한 위치 + 방향
                return false;
            }
        }
        return true;
    }
    
    //회전 시키기 (시계방향으로 90도)
    public static boolean[][] rotate(int R, int C, boolean[][] sticker) {
        boolean[][] rotated = new boolean[C][R];
        
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                rotated[c][R - 1 - r] = sticker[r][c];
            }
        }
        return rotated;
    }
}