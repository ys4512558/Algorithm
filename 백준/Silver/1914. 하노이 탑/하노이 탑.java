import java.io.*;
import java.math.BigInteger;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        if (N <= 20) {
            sb.append((int)Math.pow(2, N) - 1).append("\n");
            hanoi(N, 1, 2, 3);
        } else {
            BigInteger two = new BigInteger("2");
            sb.append(two.pow(N).subtract(new BigInteger("1")));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void hanoi(int N, int start, int mid, int end) {
        if (N == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }
        hanoi(N - 1, start, end, mid);
        sb.append(start).append(" ").append(end).append("\n");
        hanoi(N - 1, mid, start, end);
    }
}