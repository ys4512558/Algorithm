import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = Integer.parseInt(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            int K = Integer.parseInt(br.readLine());
            result--;
            result += K;
        }
        System.out.println(result);
    }
}