import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[][] sums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        sums = new int[N+1][N+1];

        //둘러 싸기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sums[i][j] = prefixSum(i, j) + arr[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(calcSum(x1, y1, x2, y2)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int prefixSum(int row, int col) {
        return sums[row - 1][col]
                + sums[row][col - 1]
                - sums[row - 1][col - 1]; //두번 더해지는 부분 빼기
    }

    private static int calcSum(int row1, int col1, int row2, int col2) {
        int res = sums[row2][col2] // 1, 1 ~ y2, x2 까지의 구간 합
                - sums[row2][col1 - 1] // 1, 1 ~ y2, x1 - 1 까지의 구간 합 (왼쪽 여백)
                - sums[row1 - 1][col2] // 1, 1 ~ y1 - 1, x2 까지의 구간 합 (아래쪽 여백)
                + sums[row1 - 1][col1 - 1]; // 위의 2번의 뺄셈 과정에서 중복 뺄셈 된 구간 합 더하기
        return res;
    }
}