import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int start, end;
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calcLen());
    }

    private static int calcLen() {
        int total = 0;
        while (!queue.isEmpty()) {
            int[] line = queue.poll();

            if(line[1] < end) continue;
            int len = line[1] - Math.max(end, line[0]);
            end = line[1];
            total += len;
        }
        return total;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        start = Integer.MAX_VALUE;
        end = Integer.MIN_VALUE;

        queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            queue.offer(new int[]{x, y});
        }
    }
}
