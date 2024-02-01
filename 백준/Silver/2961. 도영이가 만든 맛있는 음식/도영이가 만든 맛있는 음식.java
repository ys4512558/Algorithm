import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static long res;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] taste= new int[N][2];
//        boolean[] visit = new boolean[N];
        int visit = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            taste[i][0] = Integer.parseInt(st.nextToken());
            taste[i][1] = Integer.parseInt(st.nextToken());
        }
        res = Math.abs(taste[0][0] - taste[0][1]);


        for (int i = 1; i <= N; i++) {
            recur2961(taste, visit, N, i, 0);
        }
        sb.append(res);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recur2961(int[][] taste, int visit, int n, int r, int depth) {
        if (r == 0) {
            long S = 1;
            long B = 0;

            for (int i = 0; i < n; i++) {
                if ((visit & 1 << i) != 0) {
                    S *= taste[i][0];
                    B += taste[i][1];
                }
            }
            res = Math.min(res, Math.abs(S - B));
            return;
        }
        if (n == depth) {
            return;
        }
        visit |= 1 << depth;
        recur2961(taste, visit, n, r - 1, depth + 1);

        visit ^= 1 << depth;
        recur2961(taste, visit, n, r, depth + 1);
    }
}