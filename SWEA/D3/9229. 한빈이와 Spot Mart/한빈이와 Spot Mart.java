import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] snack;
    static int[] selectedNum;
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
        selectedNum = new int[2];

        max = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
        }
        comb(N, M, 0, 0);
    }

    private static void comb(int n, int m, int depth, int start) {
        if (depth == 2) { //기저 2개 뽑으면 끝
            int sum = selectedNum[0] + selectedNum[1];
            max = m >= sum ? Math.max(max, sum) : max;
            return;
        }
        for (int i = start; i < n; i++) {
            selectedNum[depth] = snack[i];
            comb(n, m, depth + 1, i + 1);
        }
    }
}