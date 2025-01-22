import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            sb.append(str.charAt(i) == 'I' ? 'i' : 'L');
        }
        System.out.println(sb);
    }
}