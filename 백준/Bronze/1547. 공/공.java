import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] ball = new boolean[50];

        ball[0] = true;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;

            boolean flag = ball[X];
            ball[X] = ball[Y];
            ball[Y] = flag;
        }
        for (int i = 0; i < 50; i++) {
            if (ball[i]) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}