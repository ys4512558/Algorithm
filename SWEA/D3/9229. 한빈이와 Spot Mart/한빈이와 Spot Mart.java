import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] snack;
    static boolean[] isSelected;
    static int max;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            solve();
            sb.append(max).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        snack = new int[N];
        isSelected = new boolean[N];

        max = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = snack[i] + snack[j];
                if (sum > M) continue;
                max = Math.max(max, sum);
            }
        }
    }
}