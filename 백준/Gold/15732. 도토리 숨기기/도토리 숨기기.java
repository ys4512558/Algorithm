import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static Rule[] rules;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        long min = Integer.MAX_VALUE;
        long max = 0;
        rules = new Rule[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            min = Math.min(min, A);
            max = Math.max(max, B);
            rules[i] = new Rule(A, B, C);
        }

        while (min < max) {
            long mid = (min + max) / 2;

            long count = 0;
            for (int i = 0; i < K; i++) {
                long end = Math.min(mid, rules[i].B);
                if(end < rules[i].A) continue;
                count += ((end - rules[i].A) / rules[i].C) + 1;
            }
            if (count < D) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        System.out.println(min);
    }
}

class Rule {
    int A, B, C;

    public Rule(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
    }
}