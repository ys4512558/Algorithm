import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        sb.append(N * 78 / 100);
        sb.append(" ");
        sb.append(N * 80 / 100 + (N * 20 / 100) * 78 / 100);
        System.out.println(sb);
    }
}
