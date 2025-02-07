import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int t = 0;
        int g = 0;
        int f = 0;
        int p = 0;
        
        int[][][][] dp = new int[4][4][4][4];
        String str = br.readLine();
        //나머지가 모두 0이면 흥미로운 구간
        dp[0][0][0][0] = 1;
        //(1, 0, 0, 0)이라는 구간이 나오고 이후 또 (1, 0, 0, 0)이라는 구간이 나오면
        //해당 구간 사이 (만약 i = 1일때 나오고, i = 5일때 나오면 1 ~ 5 사이는 3으로 나누어 지는 구간)
        int count = 0;
        for(int i = 0; i < N; i++) {
            char c = str.charAt(i);
            switch(c) {
                case 'T':
                    t = (t + 1) % 3;
                    break;
                case 'G':
                    g = (g + 1) % 3;
                    break;
                case 'F':
                    f = (f + 1) % 3;
                    break;
                case 'P':
                    p = (p + 1) % 3;
                    break;
            }
            //동일한 경우가 나온것을 누적합해준다.
            //i = 1, 5, 7일때 (1, 0, 0, 0)인 경우
            //1~5, 1~7, 5~7로 3구간이 흥미로운 구간이 된다.
            //즉, 현재 i와 같은 상태가 나온 횟수를 결과에 더해주고
            //현재 상태가 나온 횟수를 1 더해준다.
            //i = 1인 경우 (1, 0, 0, 0)은 흥미로운 구간이 아님 dp[1][0][0][0] = 0을 더해줌
            // 이후, 현재 상태가 나왔음을 dp[1][0][0][0]에 + 1
            //i = 5인 경우 (1, 0, 0, 0)은 i = 1에서 1번 나왔음 
            // -> 같은 상태 ~ 같은 상태 구간은 무조건 3의 배수
            // 따라서, count += dp[1][0][0][0] (1 ~ 5 구간 의미)
            //i = 7인 경우 2번 나옴 
            // 따라서, count += dp[1][0][0][0] (2) (1 ~ 7, 5 ~ 7 구간 의미)
            count += dp[t][g][f][p];
            dp[t][g][f][p]++;
        }
        System.out.println(count);
    }
}
