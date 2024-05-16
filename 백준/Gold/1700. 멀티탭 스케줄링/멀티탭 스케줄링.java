import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    //해당 번호의 가전제품이 사용중인지 확인하는 Map
    static Map<Integer, Boolean> holes;
    static Appliance[] appliances;
    static int[] use;
    //현재 사용중인 가전제품 (전원이 연결된 상태인 가전제품)
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

        //현재까지 꽂힌 가전제품 수
        int usedCount = 0;
        int cnt = 0;
        for (int i = 1; i <= K; i++) {
            int idx = use[i];
    
            //사용중인 콘센트 보다 콘센트가 더 많을때
            if (usedCount < N) {
                //새로운 가전제품이면 그냥 추가
                if (!holes.get(idx)) {
                    appliances[idx].count--;
                    appliances[idx].times.poll();
                    usedAppliances.offer(appliances[idx]);
                    holes.put(appliances[idx].num, true);
                    usedCount++;
                } else { 
                    //이미 꽂혀있는 가전제품이면 PQ갱신을 위해 원래 가전제품을 PQ에서 제거
                    usedAppliances.remove(appliances[idx]);
                    //갱신 로직 수행 후
                    appliances[idx].count--;
                    appliances[idx].times.poll();
                    //다시 추가
                    usedAppliances.offer(appliances[idx]);
                }
            } else if (!holes.get(idx)) { //모두 사용하고 있고 꽂혀있지 않은 제품인 경우
                appliances[idx].count--;
                appliances[idx].times.poll();
                holes.put(usedAppliances.poll().num, false);
                usedAppliances.offer(appliances[idx]);
                holes.put(appliances[idx].num, true);
                cnt++;
            } else { 
                //위와 동일(이렇게 하지 않으면 갱신되지 않은 상태)
                //(정렬상태가 변경되기 전의 상태로 남아있다)
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
        //정렬 기준 (뭘 먼저 뽑을것인지)
        //만약 둘 다 더 이상 사용되지 않는다면 아무나 상관없다.
        if (this.times.isEmpty() && o.times.isEmpty()) {
            return 0;
        //둘 중 하나가 더 이상 사용되지 않으면 해당 우선
        } else if (this.times.isEmpty()) {
            return -1;
        } else if (o.times.isEmpty()) {
            return 1;
        } else {
            //둘 다 이후 사용된다면 가장 가까운 사용 시간이 더 먼 것
            //(1) -> {3, 5, 7}
            //(2) -> {4, 6, 8}
            //가장 가까운 사용 시간 (1) = `3`, (2) -> `4`
            //(2)가 더 늦게 사용되므로 이를 우선순위 먼저
            return Integer.compare(o.times.peek(), this.times.peek());
        }
    }
}