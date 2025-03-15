import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] tips = new int[N];
        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(tips);
        long result = 0;
        for (int i = N - 1, rank = 1; i >= 0; i--, rank++) {
            result += Math.max(tips[i] - (rank - 1), 0);
        }

        System.out.println(result);
    }
}
