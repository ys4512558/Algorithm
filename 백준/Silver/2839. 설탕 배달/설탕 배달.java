import java.io.*;

public class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        memo(N);
        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int memo(int cur) {
        if(dp[cur] != 0) return dp[cur];
        if(cur == 3 || cur == 5) return dp[cur] = 1;

        if(cur < 3 || cur == 4){
            return dp[cur] = -1;
        }

        int res1 = memo(cur - 5);
        int res2 = memo(cur - 3);

        if(res1 != -1 && res2 != -1) return dp[cur] = Math.min(res1, res2) + 1;

        return dp[cur] = res1 == -1 && res2 == -1 ? -1 :
                ((res2 == -1) ? res1 : res2) + 1;
    }
}