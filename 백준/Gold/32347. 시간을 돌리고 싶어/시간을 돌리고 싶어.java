import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static boolean[] timeMachine;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        timeMachine = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            timeMachine[i] = Integer.parseInt(st.nextToken()) == 1;
        }
        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int low = 1;
        int high = N - 1; //최대 N - 1일 이전으로 돌아가도록

        while (low < high) {
            int mid = (low + high) / 2;

            if (!simulate(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static boolean simulate(int T) {
        Queue<Info> queue = new ArrayDeque<>();
        int[] isv = new int[N]; //타임머신 사용 횟수
        isv[N - 1] = 1; //0이면 방문한적 없다고 처리
        //1부터 사용 Arrays.fill안하기 위해
        queue.offer(new Info(N - 1, 1));
        while (!queue.isEmpty()) {
            Info info = queue.poll();

            //시간 보내기
            int next = info.time + 1;
            if (next < N && (isv[next] == 0 || isv[next] > info.k)) {
                queue.offer(new Info(next, info.k));
                isv[next] = info.k;
            }
            //타임머신 사용 가능이라면
            next = Math.max(0, info.time - T); //0보다 작으면 0으로
            if (timeMachine[info.time] && (isv[next] == 0 || isv[next] > info.k) && info.k <= K) {
                queue.offer(new Info(next, info.k + 1));
                isv[next] = info.k + 1;
                if (next == 0) return true;
            }
        }
        return false;
    }
}

class Info {
    int time, k;

    public Info(int time, int k) {
        this.time = time;
        this.k = k;
    }
}