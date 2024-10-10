import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int[][] points = new int[4][2];
            int[] lens = new int[6];
            int idx = 0;
            for (int j = 0; j < 4; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                for (int k = 0; k < j; k++) {
                    lens[idx++] = (int) (Math.pow(x - points[k][0], 2) + Math.pow(y - points[k][1], 2));
                }
                points[j] = new int[]{x, y};
            }
            Arrays.sort(lens);
            if (lens[0] == lens[1] && lens[1] == lens[2] && lens[2] == lens[3] && lens[4] == lens[5]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}