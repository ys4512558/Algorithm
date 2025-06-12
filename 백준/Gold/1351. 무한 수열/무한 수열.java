import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static Map<Long, Long> map = new HashMap<>();
    static long n, p, q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());
        map.put(0L, 1L);
        System.out.println(dp(n));
    }

    private static long dp(long n) {
        long value = map.getOrDefault(n, -1L);
        if(value != - 1) return value;
        long num = dp(n / p) + dp(n / q);
        map.put(n, num);
        return num;
    }
}
