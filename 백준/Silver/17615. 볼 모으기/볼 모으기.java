import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String shortBall, ball;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ball = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append(ball.charAt(0));
        int blue = ball.charAt(0) == 'B' ? 1 : 0;
        int red = ball.charAt(0) == 'R' ? 1 : 0;
        for (int i = 1; i < N; i++) {
            if (ball.charAt(i) == ball.charAt(i - 1)) continue;
            sb.append(ball.charAt(i));
            if(ball.charAt(i) == 'B') blue++;
            else red++;
        }
        shortBall = sb.toString();

        if (check(red, blue)) {
            System.out.println(0);
        } else {
            System.out.println(Math.min(check('B'), check('R')));
        }
    }

    private static int check(char c) {
        boolean flag = c == ball.charAt(0);
        int cnt1 = 0;
        for (int i = 1; i < N; i++) {
            if (c != ball.charAt(i)) {
                flag = false;
                continue;
            }
            if (flag) continue;
            cnt1++;
        }
        flag = c == ball.charAt(N - 1);
        int cnt2 = 0;
        for (int i = N - 2; i >= 0; i--) {
            if (c != ball.charAt(i)) {
                flag = false;
                continue;
            }
            if (flag) continue;
            cnt2++;
        }
        return Math.min(cnt1, cnt2);
    }


    private static boolean check(int red, int blue) {
        char c = shortBall.charAt(0);
        int cnt = c == 'R' ? red : blue;
        for (int i = 0; i < shortBall.length(); i++) {
            if (cnt == 0) break;
            if (c == shortBall.charAt(i)) {
                cnt--;
                continue;
            }
            return false;
        }
        return true;
    }
}