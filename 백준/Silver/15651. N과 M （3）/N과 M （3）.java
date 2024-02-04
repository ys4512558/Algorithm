import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        solve(N, M, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int n, int m, int depth) {
        if (m == depth) {
            for (int i = 0; i < m; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= n; i++) {
            numbers[depth] = i;
            solve(n, m, depth+1);
        }
    }
}