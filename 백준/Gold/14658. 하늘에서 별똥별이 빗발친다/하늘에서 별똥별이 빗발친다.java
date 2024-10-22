import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Pair[] pairs = new Pair[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pairs[i] = new Pair(x, y);
        }
        Arrays.sort(pairs);
        int max = 0;
        for (int i = 0; i < K; i++) {
            int x = pairs[i].x;
            for (int j = 0; j < K; j++) {
                int y = pairs[j].y;
                int cnt = 0;
                for (int k = 0; k < K; k++) {
                    if (pairs[k].x >= x && pairs[k].y >= y &&
                        pairs[k].x <= x + L && pairs[k].y <= y + L) {
                        cnt++;
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        System.out.println(K - max);
    }
}

class Pair implements Comparable<Pair> {

    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.x == o.x) {
            return Integer.compare(this.y, o.y);
        }
        return Integer.compare(this.x, o.x);
    }
}