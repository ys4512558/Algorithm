import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        System.out.println(bfs(A, B));
    }

    private static int bfs(int A, int B) {
        Queue<Long> queue = new ArrayDeque<>();
        queue.offer((long) A);

        int breadth = 1;
        Loop:
        while (!queue.isEmpty()) {
            int size = queue.size();
            breadth++;
            while (size-- > 0) {
                long num = queue.poll();

                long next1 = num * 10 + 1;
                long next2 = num * 2;
                if (next1 == B || next2 == B) {
                    return breadth;
                }
                if (next1 < B) {
                    queue.offer(next1);
                }
                if (next2 < B) {
                    queue.offer(next2);
                }
            }
        }
        return -1;
    }
}