import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static Map<Character, PriorityQueue<Info>> attach;
    static Map<Character, PriorityQueue<Info>> detach;
    static Info[] infos;
    static int[] start;
    static String result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        attach = new HashMap<>();
        infos = new Info[M];
        start = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int d = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            infos[i] = new Info(c, d, a);
            PriorityQueue<Info> pq = attach.getOrDefault(c, new PriorityQueue<>());
            pq.offer(infos[i]);
            attach.put(c, pq);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int idx = Integer.parseInt(st.nextToken()) - 1;
            start[i] = idx;
        }

        result = br.readLine();

        System.out.println(sliding());
    }

    public static int sliding() {
        int min = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 0; i <= N - K; i++) {
            int l = i;
            int r = i + K - 1;
            int cost1 = detach(l, r);
            int cost2 = attach(l);
            if(cost2 == -1) continue;
            min = Math.min(min, cost1 + cost2);
            flag = true;
        }
        return flag ? min : -1;
    }

    public static int detach(int l, int r) {
        detach = new HashMap<>();
        int idx = 0;
        int cost = 0;
        for (int j = 0; j < N; j++) {
            Info info = infos[start[j]];
            char c = info.c;
            PriorityQueue<Info> pq = detach.getOrDefault(c, new PriorityQueue<>());
            if (j >= l && j <= r) {
                if (c != result.charAt(idx++)) {
                    pq.offer(new Info(c, 0, 0));
                    cost += info.d;
                }
            } else {
                pq.offer(new Info(c, 0, info.d));
            }
            detach.put(c, pq);
        }
        return cost;
    }

    public static int attach(int l) {
        int cost = 0;
        for (int j = 0; j < K; j++) {
            Info info = infos[start[j + l]];
            char c = info.c;

            if (c == result.charAt(j)) continue;
            PriorityQueue<Info> detached = detach.getOrDefault(result.charAt(j), new PriorityQueue<>());
            PriorityQueue<Info> attached = attach.getOrDefault(result.charAt(j), new PriorityQueue<>());

            if (detached.isEmpty() && attached.isEmpty()) {
                return -1;
            } else if (!detached.isEmpty() && !attached.isEmpty()) {
                Info sticker1 = detached.peek();
                Info sticker2 = attached.peek();
                int cost1 = sticker1.d + sticker1.a;
                int cost2 = sticker2.a;
                if (cost1 < cost2) {
                    detached.poll();
                    cost += cost1;
                } else {
                    cost += cost2;
                }
            } else if (!attached.isEmpty()) {
                Info sticker = attached.peek();
                cost += sticker.a;
            } else {
                Info sticker = detached.poll();
                cost += sticker.a + sticker.d;
            }
        }
        return cost;
    }
}

class Info implements Comparable<Info>{
    char c;
    int d, a;

    public Info(char c, int d, int a) {
        this.c = c;
        this.d = d;
        this.a = a;
    }

    @Override
    public int compareTo(Info o) {
        if(this.a == o.a) return Integer.compare(this.d, o.d);
        return Integer.compare(this.a, o.a);
    }
}