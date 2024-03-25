import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, max;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            Integer value = map.putIfAbsent(line, 1);
            if (value != null) {
                map.put(line, value + 1);
            }
        }
        int K = Integer.parseInt(br.readLine());
        for (String key : map.keySet()) {
            int cnt = 0;
            for (int i = 0; i < key.length(); i++) {
                if(key.charAt(i) == '0') cnt++;
            }
            if(cnt > K || (cnt % 2 != K % 2)) continue;
            max = Math.max(max, map.get(key));
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }
}