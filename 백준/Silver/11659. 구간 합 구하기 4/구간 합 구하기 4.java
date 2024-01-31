import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] sums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        sums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            //이전 누적합 + 자기 자신의 값
            sums[i] = sums[i - 1] + arr[i];
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(sums[end] - sums[start - 1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}