import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static long[][] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        res = new long[N + 1][K + 1];

        sb.append(dynamic(N - 1, K - 1));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static long dynamic(int N, int K){
        if(res[N][K] > 0){
            return res[N][K];
        }

        if (N == K || K == 0) {
            res[N][K] = 1L;
            return res[N][K];
        }
        res[N][K] = (dynamic(N-1, K-1)) + (dynamic(N-1, K));
        return res[N][K];
    }
}