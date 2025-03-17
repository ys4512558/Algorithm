import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][][] prefixSum;
    static final int J = 0, O = 1, I = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        map = new int[N + 1][M + 1];
        prefixSum = new int[3][N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                char c = str.charAt(j - 1);
                switch (c) {
                    case 'J':
                        map[i][j] = J;
                        break;
                    case 'I':
                        map[i][j] = I;
                        break;
                    case 'O':
                        map[i][j] = O;
                        break;
                }
                for (int k = 0; k < 3; k++) {
                    prefixSum[k][i][j] = prefixSum[k][i - 1][j] + prefixSum[k][i][j - 1] - prefixSum[k][i - 1][j - 1];
                    if(map[i][j] == k) prefixSum[k][i][j]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 3; j++) {
                int count = prefixSum[j][c][d] - prefixSum[j][c][b - 1] - prefixSum[j][a - 1][d] + prefixSum[j][a - 1][b - 1];
                sb.append(count + " ");
            }
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}
