import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int G = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        boolean isExist = false;
        for (int i = 1; i <= 50000; i++) {
            long cur = G + (long) i * i;

            double sqrt = Math.sqrt(cur);
            if (sqrt % 1 == 0) {
                isExist = true;
                sb.append((long) sqrt).append("\n");
            }
        }
        bw.write(isExist ? sb.toString() : String.valueOf(-1));
        bw.flush();
        bw.close();
    }
}