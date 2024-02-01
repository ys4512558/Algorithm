import java.io.*;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[] start = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            interestPrime(start[i], 1);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void interestPrime(int pre, int depth){
        if (!checkPrime(pre)) {
            return;
        }
        if (depth == N) {
            sb.append(pre).append("\n");
            return;
        }
        pre *= 10;
        for (int i = 0; i <= 9; i++) {
            interestPrime(pre + i, depth + 1);
        }
    }

    private static boolean checkPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}