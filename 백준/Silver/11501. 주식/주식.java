import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] prices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            prices = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                prices[j] = Integer.parseInt(st.nextToken());
            }
            long result = 0;
            int max = prices[N - 1];
            for (int j = N - 2; j >= 0; j--) {
                if (prices[j] >= max) {
                    max = prices[j];
                    continue;
                }
                result += max - prices[j];
            }
            sb.append(result).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}