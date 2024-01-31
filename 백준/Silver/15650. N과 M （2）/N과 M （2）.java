import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isSelected;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        isSelected = new boolean[N + 1];
        numbers = new int[M];

        comb(N, M, 0, 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void comb(int n, int m, int depth, int start) {
        if (m == depth) {
            for (int i : numbers) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            if (isSelected[i]) {
                continue;
            }
            isSelected[i] = true;
            numbers[depth] = i;
            comb(n, m, depth + 1, i + 1);
            isSelected[i] = false;
        }
    }
}