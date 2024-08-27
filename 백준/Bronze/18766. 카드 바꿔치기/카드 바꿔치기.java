import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String card = st.nextToken();
                int cnt = map.getOrDefault(card, 0);
                map.put(card, cnt + 1);
            }
            st = new StringTokenizer(br.readLine());
            boolean flag = true;
            for (int j = 0; j < N; j++) {
                String card = st.nextToken();
                int cnt = map.getOrDefault(card, 0) - 1;
                if(cnt < 0) {
                    flag = false;
                    break;
                }
                map.put(card, cnt);
            }
            sb.append(flag ? "NOT CHEATER" : "CHEATER").append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}