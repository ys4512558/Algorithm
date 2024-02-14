import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] height = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(height);
        int top = height[N - 1];
        int bottom = 0;
        int mid = (top + bottom) / 2;
        while (top >= bottom){
            long len = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (height[i] <= mid) break;
                len += height[i] - mid;
            }
            if(len == M) break;
            if(len > M) {
                bottom = mid + 1;
            } else {
                top = mid - 1;
            }
            mid = (bottom + top) / 2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(mid);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}