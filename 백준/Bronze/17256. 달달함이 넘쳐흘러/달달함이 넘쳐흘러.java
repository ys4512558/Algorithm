import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int ax = Integer.parseInt(st1.nextToken());
        int ay = Integer.parseInt(st1.nextToken());
        int az = Integer.parseInt(st1.nextToken());
        int cx = Integer.parseInt(st2.nextToken());
        int cy = Integer.parseInt(st2.nextToken());
        int cz = Integer.parseInt(st2.nextToken());
        StringBuilder sb = new StringBuilder();
        sb.append(cx - az);
        sb.append(" ");
        sb.append(cy / ay);
        sb.append(" ");
        sb.append(cz - ax);
        System.out.println(sb);
    }

}