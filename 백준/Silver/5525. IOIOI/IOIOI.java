import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] chars = br.readLine().toCharArray();
        int[] dp = new int[chars.length];
        int cnt = 0;

        for (int i = 2; i < M; i++) {
            if (chars[i - 1] == 'O' && chars[i] == 'I' && chars[i - 2] == 'I') {
                dp[i] = dp[i - 2] + 1;
                if(dp[i] >= N && chars[i - 2 * N] == 'I') cnt++;
            }
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }
}