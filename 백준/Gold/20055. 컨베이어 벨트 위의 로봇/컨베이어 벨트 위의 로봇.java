import java.io.*;
import java.util.*;

public class Main {
    static int[] belt;
    static int N, K, N2, broken;
    static boolean[] isBroken;
    static boolean[] robots; //해당 인덱스의 벨트에 로봇이 있는지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        N2 = N * 2;
        
        belt = new int[N2];
        isBroken = new boolean[N2];
        robots = new boolean[N2];
        broken = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(move());
    }
    
    public static int move() {
        int turn = 0;
        while(broken < K) {
            turn++;
            // 0, 2N - 1, 2N - 2 순으로 역순 순회
            int start = (N2 - 1) - ((turn + N2 - 1) % N2);
            int end = (start + N - 1) % N2;
            robots[end] = false;
            moveRobot(end);
            

            //해당 벨트를 시작점으로 뒀을 때 올릴 수 있는지
            if(belt[start] > 0 && !robots[start]) {
                if(--belt[start] == 0) broken++;
                robots[start] = true;
            }
        }
        return turn;
    }
    
    public static void moveRobot(int end) {
        //총 N - 1개만 확인하면 됨
        //하나는 이미 벨트 돌면서 나가거나 해서 확인했음
        for(int i = 1; i < N; i++) { 
            int idx = (end - i + N2) % N2;
            //하나라도 이동 못하면 그만확인해도 됨 (정체되기 때문)
            int next = (idx + 1) % N2;
            if(!robots[idx]) continue; 
            if(belt[next] == 0) continue;
            
            //이동
            if(belt[next] > 0 && !robots[next]) {
                if(--belt[next] == 0) broken++;
                robots[idx] = false;
                robots[next] = true;
                if(next == end) {
                    robots[next] = false;
                    continue;
                }
            }
        }
    }
    
    public static void print(){
        for(int i = 0; i < N2; i++) {
            System.out.print(belt[i] + " ");
        }
        System.out.println();
        for(int i = 0; i < N2; i++) {
            System.out.print((robots[i] ? 1 : 0) + " ");
        }
        System.out.println();
        System.out.println();
    }
}