import java.util.*;
import java.io.*;

public class Main {
    static int[][] pos;
    //동 남 서 북 (+ 2 % 4로 반대 방향 확인)
    static final int E = 3, S = 2, W = 1, N = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        //마지막은 동근이
        pos = new int[K + 1][2]; //[0] : DIR, [1] : 좌표
        for(int i = 0; i < K + 1; i++) {
            st = new StringTokenizer(br.readLine());
            //동 - 서, 남 - 북 으로 2개씩 차이나게 바꿔주기
            //기존 -> 0 : 북, 1 : 남, 2 : 서, 3 : 동
            //변경 -> 0 : 북, 1 : 서. 2 : 남, 3 : 동
            int dir = Integer.parseInt(st.nextToken()) - 1;
            if(dir == 1 || dir == 2) dir = 3 - dir; //둘이 바꿔주기
            int p = Integer.parseInt(st.nextToken());
            pos[i] = new int[]{dir, p};
        }
        
        //동근이 위치
        int dDir = pos[K][0];
        int dP = pos[K][1];
        int result = 0;
        for(int i = 0; i < K; i++) {
            //같은 방향이라면 두 위치의 차의 절대값
            if(dDir == pos[i][0]) {
                result += Math.abs(dP - pos[i][1]);
            } else if(((dDir + 2) % 4) == pos[i][0]) { //정반대방향이면
                //동 - 서 (1, 3 홀수) 방향으로 반대이면 가로 (M) 만큼 이동해야함
                //남 - 북 (0, 2 짝수) 방향으로 반대이면 세로 (N) 만큼 이동해야함
                int dist = dDir % 2 == 0 ? N : M;
                //동 - 서 기준 위로 vs 아래로 돌아가기
                //남 - 북 기준 왼쪽 vs 오른쪽 돌아가기 
                //dist = M 이면 N을 사용하기 위해 (M + N - dist)
                int other = (M + N - dist);
                int downOrRight = other - pos[i][1] + other - dP; //아래, 오른쪽으로 이동
                int upOrLeft = pos[i][1] + dP; //위, 왼쪽으로 이동
                result += Math.min(downOrRight, upOrLeft) + dist;
            } else { //반대 / 같은 위치 아닌 경우 (왼쪽, 오른쪽)
                //북 서 남 동 0 1 2 3
                //북 -> 서 1 (왼쪽) 동 3(오른쪽) 아래로 가야함
                //남 -> 서 1 (왼쪽) 동 3(오른쪽) 위로 가야함
                if(dDir == 0) { //북
                    result += (pos[i][0] == 1) ? dP : M - dP;
                    result += pos[i][1]; //아래로 내려가는건 그대로
                } else if(dDir == 2) { //남
                    result += (pos[i][0] == 1) ? dP : M - dP;
                    result += N - pos[i][1]; //위로 올라가기
                } else if(dDir == 1) { //서
                    result += pos[i][1]; //오른쪽으로 가는건 그대로
                    result += (pos[i][0] == 0) ? dP : N - dP; 
                } else { //동
                    result += M - pos[i][1]; //왼쪽으로 가기
                    result += (pos[i][0] == 0) ? dP : N - dP;
                }  
            }
        }
        System.out.println(result);
    }
}
