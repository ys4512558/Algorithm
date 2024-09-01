import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] isOn = new boolean[N + 2];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            isOn[i] = num != 0;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (isOn[i]) {
                cnt++;
                for (int j = 0; j <= 2; j++) {
                    isOn[i + j] = !isOn[i + j];
                }
            }
        }
        System.out.println(cnt);
    }
}