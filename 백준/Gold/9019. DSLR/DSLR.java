import java.io.*;
import java.util.*;

public class Main {
    static final  int D = 0, S = 1, L = 2, R = 3;
    static final char[] commands = {'D', 'S', 'L', 'R'};
    static Set<Integer> set;
    static Queue<Info> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            set = new HashSet<>();
            set.add(start);
            queue = new ArrayDeque<>();
            queue.offer(new Info(start, -1, null));

            Info endInfo = null;
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                Info info = queue.poll();

                if (info.num == end) {
                    endInfo = info;
                    break;
                }

                //D
                int num = (info.num * 2) % 10000;
                checkAndOffer(num, D, info);

                //S
                num = (info.num == 0) ? 9999 : info.num - 1;
                checkAndOffer(num, S, info);

                //L
                num = (info.num % 1000) * 10 + info.num / 1000;
                checkAndOffer(num, L, info);

                //R
                num = info.num / 10 + ((info.num % 10) * 1000);
                checkAndOffer(num, R,info);
            }
            for (Info info = endInfo; info.prev != null; info = info.prev) {
                sb.append(commands[info.command]);
            }
            result.append(sb.reverse()).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result.toString());
        bw.flush();
    }

    private static void checkAndOffer(int num, int command, Info info) {
        if (set.add(num)) {
            queue.offer(new Info(num, command, info));
        }
    }
}

class Info {
    int num, command;
    Info prev;

    public Info(int num, int command, Info prev) {
        this.num = num;
        this.command = command;
        this.prev = prev;
    }
}