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
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> electronics = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        PriorityQueue<Info> sockets = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            electronics.offer(Integer.parseInt(st.nextToken()));
        }

        int cnt = 0;
        int current = 0;
        while (!electronics.isEmpty()) {
            //충전
            for (int i = cnt; i < M && !electronics.isEmpty(); i++) {
                int time = electronics.poll();
                sockets.offer(new Info(time, current + time));
            }

            //빼기
            Info pre = sockets.peek();
            while (!sockets.isEmpty()) {
                if (pre.end == sockets.peek().end) {
                    Info info = sockets.poll();
                    current = info.end;
                    continue;
                }
                break;
            }

            cnt = sockets.size();
        }

        while (!sockets.isEmpty()) {
            Info info = sockets.poll();
            current = info.end;
        }

        System.out.println(current);
    }
}

class Info implements Comparable<Info> {

    int time;
    int end;

    public Info(int time, int end) {
        this.time = time;
        this.end = end;
    }

    @Override
    public int compareTo(Info o) {
        return Integer.compare(this.end, o.end);
    }
}