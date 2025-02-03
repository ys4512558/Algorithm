import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static PriorityQueue<Gem> gems;
    static TreeSet<Integer> bags;
    static HashMap<Integer, Integer> counts;
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gems = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            gems.offer(new Gem(m, v));
        }

        bags = new TreeSet<>();
        counts = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            int count = counts.getOrDefault(n, 0);
            if (count == 0) bags.add(n);
            counts.put(n, count + 1);
        }

        long result = 0;

        while (!gems.isEmpty()) {
            Gem gem = gems.poll();

            if(bags.isEmpty()) break;
            Integer bag = bags.ceiling(gem.m);
            if(bag == null) continue;
            result += gem.v;
            int count = counts.get(bag) - 1;
            if (count == 0) {
                counts.remove(bag);
                bags.remove(bag);
                continue;
            }
            counts.put(bag, count);
        }
        System.out.println(result);
    }

}

class Gem implements Comparable<Gem> {
    int m, v;

    public Gem(int m, int v) {
        this.m = m;
        this.v = v;
    }

    @Override
    public int compareTo(Gem o) {
        if(this.v == o.v) return Integer.compare(this.m, o.m);
        return Integer.compare(o.v, this.v);
    }
}