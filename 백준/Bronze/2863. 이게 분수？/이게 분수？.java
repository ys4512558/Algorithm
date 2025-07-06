import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double A = Double.parseDouble(st.nextToken());
        double B = Double.parseDouble(st.nextToken());

        st = new StringTokenizer(br.readLine());
        double C = Double.parseDouble(st.nextToken());
        double D = Double.parseDouble(st.nextToken());

        int count = 0;
        double res = A / C + B / D;
        double res1 = C / D + A / B;
        if(res < res1) {
            res = res1;
            count = 1;
        }
        double res2 = D / B + C / A;
        if(res < res2) {
            res = res2;
            count = 2;
        }
        double res3 = B / A + D / C;
        if(res < res3) {
            res = res3;
            count = 3;
        }
        System.out.println(count);
    }
}
