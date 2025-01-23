import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] top = new int[N];
        StringBuilder sb = new StringBuilder();

        if (N + 1 < a + b || a == 0 || b == 0) {
            sb.append(-1);
        } else {
            fillOne(N - (a + b - 1), top);


            int max = Math.max(a--, b--);
            int maxIdx = N - 1 - b;
            setting(maxIdx, top, a, -1);
            setting(maxIdx, top, b, 1);
            top[maxIdx] = max;

            if (a == 0) {
                top[maxIdx] = 1;
                top[0] = max;
            }
            for (int i = 0; i < N; i++) {
                sb.append(top[i] + " ");
            }

        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void setting(int maxIdx, int[] top, int count, int add) {
        int idx = maxIdx + add;
        for (int i = count; i >= 1; i--, idx += add) {
            top[idx] = i;
        }
    }

    private static void fillOne(int count, int[] top) {
        for (int i = 0; i < count; i++) {
            top[i] = 1;
        }
    }
}