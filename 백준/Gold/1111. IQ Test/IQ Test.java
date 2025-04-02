import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(calc());
    }

    private static String calc() {
        if (N == 1) return "A";

        int result = 0;
        Set<Integer> set = new HashSet<>();
        Loop:
        for (int a = -10000; a <= 10000; a++) {
            int b = arr[1] - (arr[0] * a);
            for (int i = 2; i < N; i++) {
                int num = a * arr[i - 1] + b;
                if (num != arr[i]) continue Loop;
            }
            int num = a * arr[N - 1] + b;
            set.add(num);
            result = num;
        }
        if(set.isEmpty()) return "B";
        else if(set.size() != 1) return "A";
        return String.valueOf(result);
    }
}