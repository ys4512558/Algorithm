import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Info> pq = new PriorityQueue<>();

        //시간 관리 PQ
        PriorityQueue<int[]> times = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o1[1], o2[1]);
        });

        for (int i = 0; i < K; i++) {
            times.offer(new int[]{i, 0});
        }

        //기다린 시간 + 내 시간을 통해 하나의 큐로 관리?
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int[] time = times.poll();
            time[1] += w;
            times.offer(new int[]{time[0], time[1]});
            pq.offer(new Info(id, time[1], time[0]));
        }

        long result = 0;
        long cnt = 1;
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            result += info.id * cnt++;
        }
        System.out.println(result);
    }
}

class Info implements Comparable<Info> {

    int id, w, k;

    public Info(int id, int w, int k) {
        this.id = id;
        this.w = w;
        this.k = k;
    }

    @Override
    public int compareTo(Info o) {
        if(this.w == o.w) return Integer.compare(o.k, this.k);
        return Integer.compare(this.w, o.w);
    }
}