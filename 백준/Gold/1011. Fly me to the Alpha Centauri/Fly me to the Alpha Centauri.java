import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int diff = y - x;

            //결과
            int total = 1;
            //현재 수 범위에서 필요한 개수
            int cnt = 1;
            long dist = 1;
            while (true){
                if(dist == diff) break;
                dist += cnt;
                total++;
//                System.out.println("dist = " + dist + ", total = " + total + ", cnt = " + cnt);
                if(dist <= diff && diff <= dist + cnt - 1) break;
                total++;
                dist += cnt++;
//                System.out.println("dist = " + dist + ", total = " + total + ", cnt = " + cnt);
                if(dist <= diff && diff <= dist + cnt - 1) break;

            }
            System.out.println(total);
        }
    }
}
