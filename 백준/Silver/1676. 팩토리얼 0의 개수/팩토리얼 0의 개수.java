import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            int n = i;
            while (n % 5 == 0) {
                cnt++;
                n /= 5;
            }
        }
        System.out.println(cnt);
    }
}