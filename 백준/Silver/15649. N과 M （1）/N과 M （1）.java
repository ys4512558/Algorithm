import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static boolean[] isSeleted;
    static int[] seleted;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        isSeleted = new boolean[N];
        seleted = new int[M];
        permutation(N, M, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void permutation(int n, int m, int depth) {
        if (depth == m) {
            for (int i = 0; i < seleted.length; i++) {
                sb.append(seleted[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isSeleted[i]) {
                continue;
            }
            isSeleted[i] = true;
            seleted[depth] = i + 1;
            permutation(n, m, depth+1);
            isSeleted[i] = false;
        }
    }
}