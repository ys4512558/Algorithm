import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean flag = false;
        Loop:
        for (int i = 2; i <= 9; i++) {
            if (i == N) {
                flag = true;
                break;
            }
            for (int j = 1; j <= 9; j++) {
                if (i * j == N || j == N) {
                    flag = true;
                    break Loop;
                }
            }
        }
        System.out.println(flag ? 1 : 0);
    }
}
