import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[][] points = new int[N + 2][2];
            for (int j = 0; j < N + 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                points[j][0] = Integer.parseInt(st.nextToken());
                points[j][1] = Integer.parseInt(st.nextToken());
            }
            floydwarshall(points, N + 2);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void floydwarshall(int[][] points, int n) {
        int[][] dist = new int[n][n];
        final int INF = Integer.MAX_VALUE / 100;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = INF;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                int d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                if (d <= 1000) {
                    dist[i][j] = d;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        sb.append(dist[0][n - 1] == INF ? "sad" : "happy");
        sb.append("\n");
    }
}