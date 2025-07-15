import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        //A+B = sum
        //A-B = sub
        //sum + sub = 2A
        //sum - sub = 2B
        int A2 = sum + sub;
        int B2 = sum - sub;
        int A = A2 / 2;
        int B = B2 / 2;
        if ((A2 % 2 != 0 || B2 % 2 != 0) || A < 0 || B < 0) {
            System.out.println(-1);
        } else {
            System.out.println(A + " " + B);
        }
    }
}
