import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int onion1 = 1;
        int onion2 = 1;

        for (int i = 0; i < N; i++) {
            onion1 += A;
            onion2 += B;
            if (onion2 > onion1) {
                int temp = onion1;
                onion1 = onion2;
                onion2 = temp;
            } else if (onion1 == onion2) {
                onion2--;
            }
        }

        System.out.println(onion1 + " " + onion2);

    }
}