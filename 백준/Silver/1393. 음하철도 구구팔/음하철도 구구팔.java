import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken());
        int ey = Integer.parseInt(st.nextToken());
        int dx = Integer.parseInt(st.nextToken());
        int dy = Integer.parseInt(st.nextToken());
        int gcd = gcd(dx, dy);
        dx /= gcd;
        dy /= gcd;

        double dist = Double.MAX_VALUE;
        while (true) {
            double temp = Math.sqrt(Math.pow(sx - ex, 2) + Math.pow(sy - ey, 2));
            if (dist < temp) {
                break;
            }
            dist = temp;
            ex += dx;
            ey += dy;
        }
        System.out.println((ex - dx) + " " + (ey - dy));
    }

    public static int gcd(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        if(min == 0) return max;
        return gcd(min, max % min);
    }
}