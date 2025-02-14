import java.io.*;
import java.util.*;

public class Main {
    static long[] arr1; //1_000_000_000
    static long[] arr2; //1_000_000_000
    static long[][] dp;
    static final int MOD = 1_000_000_007;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        arr1 = new long[N + 2];
        arr2 = new long[N + 2];
        dp = new long[N + 2][4]; 
        //[0] : 현재 i에서 arr2를 안쓴경우
        //[1] : 현재 i에서 arr2[i - 1]을 사용한 경우
        //[2] : 현재 i에서 arr[i]를 사용한 경우
        //[0]은 dp[i-1][0, 1, 2]과 arr1[i]를 곱한 개수만큼 카운팅
        //[1]은 dp[i-1][0, 1]과 arr[i]를 곱한 개수만큼 카운팅 (arr2[i - 1]을 dp[i - 1][2]에서 사용했기 때문에 현재 dp[i][1]에선 사용할 수 없기 때문)
        //[2]도 dp[i-1][0, 1]을 사용 같은 이유
        
        //초기 DP값 초기화 (dp[0][]일 때 곱셈으로 카운팅해야해서 처음 값을 넣어줘야함)
        dp[1][0] = 1;
        for(int i = 2; i <= N + 1; i++) {
            arr1[i] = Long.parseLong(st1.nextToken());
            if(i != N + 1) {
                arr2[i] = Long.parseLong(st2.nextToken());            
            }
            long sum = 0;
            long sum2 = 0;
            for(int j = 0; j < 4; j++) {
                sum = (sum + dp[i - 1][j]) % MOD;
                sum2 = (sum2 + dp[i - 2][j]) % MOD; 
            }
            dp[i][0] = ((sum * arr1[i]) % MOD);
            dp[i][1] = (((sum - dp[i - 1][2]) * arr2[i - 1]) % MOD);
            dp[i][2] = ((sum * arr2[i]) % MOD);
            //[3] : arr2[i - 1]을 사용해서 [i - 1]과 [i]모두 커버하는 경우
            dp[i][3] = (((sum2 * arr2[i - 1]) % MOD) * (arr2[i - 1] - 1) % MOD);
                //arr2[i - 1]C2 * 2공식 (arr[i - 1]개 중 2개 뽑고 이들간의 순서도 고려해야하므로 * 2)
        } 
        // print();
        long result = 0;
        for(int i = 0; i < 4; i++) {
            result = (result + dp[N + 1][i]) % MOD;
        }
        System.out.println(result);
    }
    public static void print() {
        for(int i = 0; i <= N + 1; i++) {
            System.out.println(dp[i][0] + " " + dp[i][1] + " " + dp[i][2] + " " + dp[i][3]);
        }
    }
}
