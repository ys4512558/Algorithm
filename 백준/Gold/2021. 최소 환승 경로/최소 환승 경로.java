import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Integer>[] lines;
    static Set<Integer>[] stations;
    static int N, L;
    static int start, end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        lines = new Set[L];
        stations = new Set[N + 1];
        for (int i = 0; i < L; i++) {
            lines[i] = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            int count = st.countTokens() - 1;
            for (int j = 0; j < count; j++) {
                int num = Integer.parseInt(st.nextToken());
                lines[i].add(num);
                if(stations[num] == null) stations[num] = new HashSet<>();
                stations[num].add(i);
            }
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    public static int bfs() {
        PriorityQueue<Station> pq = new PriorityQueue<>();
        boolean[] isvLine = new boolean[L];
        boolean[] isvStation = new boolean[N + 1];
        isvStation[start] = true;
        for (int line : stations[start]) {
            pq.offer(new Station(start, line, 0));
            isvLine[line] = true;
        }

        int res = -1;
        while (!pq.isEmpty()) {
            Station station = pq.poll();

            if (station.v == end) {
                res = station.count;
                break;
            }

            for (int next : lines[station.line]) {
                if(isvStation[next]) continue;
                isvStation[next] = true;
                pq.offer(new Station(next, station.line, station.count));
                for (int line : stations[next]) {
                    if(isvLine[line]) continue;
                    isvLine[line] = true;
                    pq.offer(new Station(next, line, station.count + 1));
                }
            }
        }
        return res;
    }
}

class Station implements Comparable<Station> {
    int v;
    int line;
    int count;

    public Station(int v, int line, int count) {
        this.v = v;
        this.line = line;
        this.count = count;
    }

    @Override
    public int compareTo(Station o) {
        return Integer.compare(count, o.count);
    }
}