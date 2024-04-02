import java.io.*;
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
        int M = Integer.parseInt(st.nextToken());
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
            } else if(cmd == 2){ //출력
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(right) - query(left - 1)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static long query(int idx) {
        long res = 0;

        while (idx > 0){
            res += bit[idx];
            idx = idx - (idx & -idx);
        }
        return res;
    }

    private static void update(int idx, long diff) {
        int next = idx;
        while (next < bit.length) {
            bit[next] += diff;
            next += next & -next;
        }
    }
}