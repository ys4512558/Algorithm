import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long min = Long.MAX_VALUE;
        Arrays.sort(arr);
        long[] result = new long[3];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                long sum = arr[i] + arr[j];
                int idx = lowerBound(-sum);
                min = calc(idx, i, j, min, sum, result);
                if(--idx< 0) continue;
                min = calc(idx, i, j, min, sum, result);
            }
        }
        Arrays.sort(result);
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static long calc(int idx, int i, int j, long min, long sum, long[] result) {
        if (idx != i && idx != j) {
            if (Math.abs(min) > Math.abs(sum + arr[idx])) {
                min = Math.abs(sum + arr[idx]);
                result[0] = arr[i];
                result[1] = arr[j];
                result[2] = arr[idx];
            }
        }
        return min;
    }

    private static int lowerBound(long target) {
        int l = 0;
        int r = N - 1;
        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
