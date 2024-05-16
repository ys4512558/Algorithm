import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static Map<Integer, Boolean> holes;
    static Appliance[] appliances;
    static int[] use;
    static PriorityQueue<Appliance> usedAppliances;
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        use = new int[K + 1];
        holes = new HashMap<>();
        //사용중인 상태에서 뺄때는 더 안쓰이는 위주로 빼기
        usedAppliances = new PriorityQueue<>();
        appliances = new Appliance[K + 1];

        for (int i = 1; i <= K; i++) {
            appliances[i] = new Appliance(i, 0, new PriorityQueue<>());
            holes.put(i, false);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            int idx = Integer.parseInt(st.nextToken());
            use[i] = idx;
            appliances[idx].count++;
            appliances[idx].times.offer(i);
        }

        int usedCount = 0;
        int cnt = 0;
        for (int i = 1; i <= K; i++) {
            int idx = use[i];

            if (usedCount < N) {
                if (!holes.get(idx)) {
                    appliances[idx].count--;
                    appliances[idx].times.poll();
                    usedAppliances.offer(appliances[idx]);
                    holes.put(appliances[idx].num, true);
                    usedCount++;
                } else {
                    usedAppliances.remove(appliances[idx]);
                    appliances[idx].count--;
                    appliances[idx].times.poll();
                    usedAppliances.offer(appliances[idx]);
                }
            } else if (!holes.get(idx)) { //모두 사용하고 있고 꽂혀있지 않은 제품인 경우
                appliances[idx].count--;
                appliances[idx].times.poll();
                holes.put(usedAppliances.poll().num, false);
                usedAppliances.offer(appliances[idx]);
                holes.put(appliances[idx].num, true);
                cnt++;
            } else { //꽂혀있는 제품인 경우 갱신해서 다시 넣어줘야함.
                usedAppliances.remove(appliances[idx]);
                appliances[idx].count--;
                appliances[idx].times.poll();
                usedAppliances.offer(appliances[idx]);
            }
        }
        System.out.println(cnt);
    }
}

class Appliance implements Comparable<Appliance> {
    int num;
    int count;
    PriorityQueue<Integer> times;

    public Appliance(int num, int count, PriorityQueue<Integer> times) {
        this.num = num;
        this.count = count;
        this.times = times;
    }

    @Override
    public int compareTo(Appliance o) {
        if (this.times.isEmpty() && o.times.isEmpty()) {
            return 0;
        } else if (this.times.isEmpty()) {
            return -1;
        } else if (o.times.isEmpty()) {
            return 1;
        } else {
            return Integer.compare(o.times.peek(), this.times.peek());
        }
    }
}