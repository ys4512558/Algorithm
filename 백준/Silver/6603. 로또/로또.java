import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] selected;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();

        String str;
        while (!(str = br.readLine()).equals("0")) {
            StringTokenizer st = new StringTokenizer(str);
            int N = Integer.parseInt(st.nextToken());
            arr = new int[N];
            selected = new int[6];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            combination(0, 0, N);
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void combination(int start, int depth, int N) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(selected[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            selected[depth] = arr[i];
            combination(i + 1, depth + 1, N);
        }
    }
}
