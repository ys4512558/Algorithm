import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr, dp, len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new int[N + 1]; //현재 원소를 마지막에 배치했을때 나올 수 있는 최대 길이
        len = new int[N + 1]; //길이에 대한 최소 값 (갱신을 위한 값)
        int idx = 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (len[idx - 1] < arr[i]) {
                len[idx++] = arr[i]; //뒤에 값 추가
                dp[i] = dp[i - 1] + 1;
                continue;
            }
            int index = lowerBound(0, idx - 1, arr[i]);
            len[index] = arr[i];
        }
        System.out.println(idx - 1);
    }

    public static int lowerBound(int l, int r, int target) {
        while (l < r) {
            int mid = (l + r) / 2;
            if (len[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}