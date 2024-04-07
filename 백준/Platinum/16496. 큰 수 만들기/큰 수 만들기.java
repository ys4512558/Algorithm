import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean isZero = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] numbers = new String[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = st.nextToken();
        }
        Arrays.sort(numbers, (o1, o2) -> {
            BigInteger num1 = new BigInteger(new String(o1) + new String(o2));
            BigInteger num2 = new BigInteger(new String(o2) + new String(o1));
            int compare = num2.compareTo(num1);
            return compare;
        });

        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            for (int i = 0; i < number.length(); i++) {
                int n = number.charAt(i) - '0';
                if (n != 0) isZero = false;
                sb.append(n);
            }
        }

        bw.write(isZero ? "0" : sb.toString());
        bw.flush();
        bw.close();
    }
}