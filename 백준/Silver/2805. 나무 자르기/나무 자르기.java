import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static Integer[] height;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        height = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            height[i] = Integer.valueOf(st.nextToken());
        }
        //내림차순 정렬
        Arrays.sort(height, Comparator.reverseOrder());
        int top = 2000000000;
        int bottom = 0; //제일 높은 나무 - M인 경우가 하한선
        int mid = (top + bottom) / 2;
        while (top >= bottom){
            long len = 0;
            for (int i = 0; i < N; i++) {
                if(height[i] <= mid) break;
                len += height[i] - mid;
            }

            if (len > M) {
                bottom = mid + 1;
            } else if(len < M){
                top = mid - 1;
            } else {
                break;
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