import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ",");
            int cnt = st.countTokens();
            Map<String, Integer> costs = new HashMap<>();
            for (int j = 0; j < cnt; j++) {
                StringTokenizer stk = new StringTokenizer(st.nextToken(), ":");
                String str = stk.nextToken();
                int cost = Integer.parseInt(stk.nextToken());
                costs.put(str, cost);
            }

            int min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine(), "|");
            cnt = st.countTokens();
            for (int j = 0; j < cnt; j++) {
                StringTokenizer stk = new StringTokenizer(st.nextToken(), "&");
                int cost = 0;
                while (stk.hasMoreTokens()) {
                    String str = stk.nextToken();
                    cost = Math.max(cost, costs.get(str));
                }
                min = Math.min(min, cost);
            }
            sb.append(min).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}
