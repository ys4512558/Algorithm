import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int min;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            count = 0;
            min = Integer.MAX_VALUE;

            for (int j = 0; j < arr.length; j++) {
                int num = arr[j];
                int target = K - num;
                int idx = lowerBound(target);

                int temp1 = (idx != j) ? Math.abs(num + arr[idx] - K) : Integer.MAX_VALUE;
                int temp2 = (idx + 1 < arr.length && idx + 1 != j) ? Math.abs(num + arr[idx + 1] - K) : Integer.MAX_VALUE;
                int temp3 = (idx - 1 >= 0 && idx - 1 != j) ? Math.abs(num + arr[idx - 1] - K) : Integer.MAX_VALUE;

                getCount(temp1);
                getCount(temp2);
                getCount(temp3);
            }

            System.out.println(count / 2);
        }
    }

    private static void getCount(int sum) {
        if (min == sum) {
            count++;
        } else if (min > sum) {
            count = 1;
            min = Math.min(min, sum);
        }
    }

    private static int lowerBound(int target) {
        int l = 0;
        int r = arr.length - 1;

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
