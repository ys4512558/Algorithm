import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int l = 0, r = 0;
        int sum = 0;
        int max = 0;
        while (r < N) {
            if (sum + arr[r] <= M) {
                sum += arr[r++];
                max = Math.max(max, sum);
            } else {
                sum -= arr[l++];
            }
            if (l > r) {
                sum += arr[r++];
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}