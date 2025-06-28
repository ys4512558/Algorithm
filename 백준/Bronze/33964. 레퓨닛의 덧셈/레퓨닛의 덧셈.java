import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb1.append("1");
        }
        for (int i = 0; i < M; i++) {
            sb2.append("1");
        }
        System.out.println(Integer.parseInt(sb1.toString()) + Integer.parseInt(sb2.toString()));
    }
}
