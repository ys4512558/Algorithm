import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, sum;
    static int[][] coins;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            N = Integer.parseInt(br.readLine());

            coins = new int[N][2];
            sum = 0;
            boolean flag = true;
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int money = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                coins[j] = new int[]{money, count};
                sum += (money * count);
                if(count % 2 == 0) continue;
                flag = false;
            }

            if (flag) {
                System.out.println(1);
            } else if (sum % 2 == 0) {
                sum /= 2;
                dp = new int[sum + 1];
                dp[0] = 1;
                knapsack();
                System.out.println(dp[sum] == 0 ? 0 : 1);
            } else {
                System.out.println(0);
            }
        }
    }

    public static void knapsack() {
        for (int i = 0; i < N; i++) {
            int money = coins[i][0];
            int count = coins[i][1];
            for (int j = sum; j > 1; j--) {
                for (int k = 1; k <= count; k++) {
                    if(j < (money * k)) break;
                    dp[j] += dp[j - (money * k)];
                }
            }
        }
    }
}
