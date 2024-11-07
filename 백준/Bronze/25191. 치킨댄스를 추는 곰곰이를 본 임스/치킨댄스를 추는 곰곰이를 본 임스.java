import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (A >= 2) {
                cnt++;
                A -= 2;
                continue;
            }
            if (B >= 1) {
                cnt++;
                B--;
                continue;
            }
            break;
        }
        System.out.println(cnt);
    }

}