import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Set<Integer> set = new HashSet<>();
            set.add(start);
            Queue<Info> queue = new ArrayDeque<>();
            queue.offer(new Info(start, new StringBuilder()));

            while (!queue.isEmpty()) {
                Info info = queue.poll();

                if (info.num == end) {
                    result.append(info.sb.toString());
                    result.append("\n");
                    break;
                }

                //D
                int num = (info.num * 2) % 10000;
                checkAndOffer(set, num, info, queue, "D");

                //S
                num = (info.num == 0) ? 9999 : info.num - 1;
                checkAndOffer(set, num, info, queue, "S");

                //L
                num = (info.num % 1000) * 10 + info.num / 1000;
                checkAndOffer(set, num, info, queue, "L");

                //R
                num = info.num / 10 + ((info.num % 10) * 1000);
                checkAndOffer(set, num, info, queue, "R");
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result.toString());
        bw.flush();
    }

    private static void checkAndOffer(Set<Integer> set, int num, Info info, Queue<Info> queue, String command) {
        if (set.add(num)) {
            StringBuilder sb = new StringBuilder(info.sb);
            sb.append(command);
            queue.offer(new Info(num, sb));
        }
    }
}

class Info {
    int num;
    StringBuilder sb = new StringBuilder();

    public Info(int num, StringBuilder sb) {
        this.num = num;
        this.sb = sb;
    }
}