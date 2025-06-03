import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            sb.append(calc(x1, y1, r1, x2, y2, r2)).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int calc(int x1, int y1, int r1, int x2, int y2, int r2) {
        //무한히 많을 때
        if(x1 == x2 && y1 == y2 && r1 == r2) return -1;

        long diffX = (long) (x2 - x1) * (x2 - x1);
        long diffY = (long) (y2 - y1) * (y2 - y1);
        long diffR1 = (long) (r2 - r1) * (r2 - r1);
        long diffR2 = (long) (r2 + r1) * (r2 + r1);

        //접점이 한개
        if(diffX + diffY == diffR1 || diffX + diffY == diffR2) return 1;
        else if(diffX + diffY > diffR2 || diffX + diffY < diffR1) return 0;
        return 2;
    }
}
