import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int time = Integer.MAX_VALUE;
        int height = min;
        for (int i = min; i <= max; i++) {
            int b = B;
            int t = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] > i) {
                        t += (map[j][k] - i) * 2;
                        b += (map[j][k] - i);
                    } else if (map[j][k] < i) {
                        t += (i - map[j][k]);
                        b -= (i - map[j][k]);
                    }
                }
            }
            if (b >= 0 && time >= t) {
                time = Math.min(time, t);
                height = i;
            }
        }
        System.out.println(time + " " + height);
    }
}
