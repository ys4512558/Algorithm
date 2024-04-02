import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
    static long[] bit;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        //업데이트
        int M = Integer.parseInt(st.nextToken());
        //출력
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        bit = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            update(i, arr[i]);
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            //업데이트
            if(cmd == 1){
                int idx = Integer.parseInt(st.nextToken());
                long num = Long.parseLong(st.nextToken());
                long diff = num - arr[idx];
                arr[idx] = num;
                update(idx, diff);
            } else { //출력
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long res = query(left - 1, right);
                sb.append(res).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void print() {
        System.out.println("Print Start");
        for (int i = 1; i < bit.length; i++) {
            System.out.println(bit[i]);
        }
        System.out.println("Print End");
    }

    private static long query(int left, int right) {
        long start = 0;
        long end = 0;

        int startIdx = left;
        int endIdx = right;

        while (startIdx > 0){
            start += bit[startIdx];
            startIdx -= (startIdx & -startIdx);
        }

        while (endIdx > 0) {
            end += bit[endIdx];
            endIdx = endIdx - (endIdx & -endIdx);
        }

         return end - start;
    }

    private static void update(int idx, long diff) {
        while (idx < bit.length) {
            bit[idx] += diff;
            idx += (idx & -idx);
        }
    }
}