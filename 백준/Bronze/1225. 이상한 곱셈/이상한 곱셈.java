import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();

        String str1 = A.length() >= B.length() ? A : B;
        String str2 = A.length() >= B.length() ? B : A;
        long sum = 0;
        for (int i = 0; i < str1.length(); i++) {
            sum += str1.charAt(i) - '0';
        }
        long result = 0;
        for (int i = 0; i < str2.length(); i++) {
            result += sum * (str2.charAt(i) - '0');
        }
        System.out.println(result);
    }
}
