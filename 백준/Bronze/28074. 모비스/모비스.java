import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        boolean[] isv = new boolean[5];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'M') {
                isv[0] = true;
            } else if (c == 'O') {
                isv[1] = true;
            } else if (c == 'B') {
                isv[2] = true;
            } else if (c == 'I') {
                isv[3] = true;
            } else if (c == 'S') {
                isv[4] = true;
            }
        }
        boolean flag = true;
        for (int i = 0; i < 5; i++) {
            if (!isv[i]) {
                flag = false;
            }
        }
        System.out.println(flag ? "YES" : "NO");
    }

}