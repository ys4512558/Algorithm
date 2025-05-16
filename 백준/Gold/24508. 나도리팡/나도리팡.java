import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long arr[];
    static int N, K;
    static long T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T = Long.parseLong(st.nextToken());

        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        long sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        if (sum % K != 0) {
            System.out.println("NO");
        } else {
            Arrays.sort(arr);
            System.out.println(calc() ? "YES" : "NO");
        }
    }

    private static boolean calc() {
        int l = 0;
        int r = N - 1;

        long count = 0;
        while (l < r) {
            long need = K - arr[r];
            if (arr[l] < need) {
                arr[r] += arr[l];
                count += arr[l];
                arr[l] = 0;
                l++;
            } else if (arr[l] > need) {
                arr[r] = 0;
                count += need;
                arr[l] -= need;
                r--;
            } else {
                arr[r] = 0;
                count += need;
                arr[l] = 0;
                l++;
                r--;
            }

            if(count > T) return false;
        }
        return true;
    }
}
