import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            boolean[] isv = new boolean[N + 1];
            PriorityQueue<Info> pq = new PriorityQueue<>();

            int cnt = 0;

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                pq.offer(new Info(a, b));
            }

            while (!pq.isEmpty() && cnt != N) {
                Info info = pq.poll();

                if (!isv[info.a]) {
                    isv[info.a] = true;
                    cnt++;
                    continue;
                }

                for (int j = info.a; j <= info.b; j++) {
                    if (isv[j]) continue;
                    isv[j] = true;
                    cnt++;
                    break;
                }
            }
            sb.append(cnt).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Info implements Comparable<Info> {
    int a, b;

    public Info(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Info o) {
        if(this.b == o.b) return Integer.compare(this.a, o.a);
        return Integer.compare(this.b, o.b);
    }
}