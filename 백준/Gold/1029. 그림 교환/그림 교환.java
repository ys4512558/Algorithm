import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //구매했던 사람들의 비트, (마지막으로 구매한 사람의 번호, 정보)
    // 비트가 같지만 순서가 다르게 구매할 수 있고, 해당 번호에서 다른 경로를 통해 더 적은 금액으로 구매해왔을 수 있으니
    static Map<Integer, Map<Integer, Info>> isv;
    static int[][] arr;
    static int max, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        isv = new HashMap<>();
        arr = new int[N][N];
        max = 1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(max);
    }

    private static void bfs() {
        Queue<Info> queue = new ArrayDeque<>();
        int start = 1;
        Map<Integer, Info> m = new HashMap<>();
        Info startInfo = new Info(0, 0, 1, start);
        m.put(0, startInfo);
        isv.put(start, m);
        queue.offer(startInfo);

        while (!queue.isEmpty()) {
            Info info = queue.poll();

            for (int i = 0; i < N; i++) {
                int flag = 1 << i;
                //같은 그림 중복 구매 불가
                if ((info.bit & flag) != 0) continue;
                //구매 가격보다 더 싸게 판매 불가
                if (info.cost > arr[info.n][i]) continue;
                int bit = info.bit | 1 << i;
                Map<Integer, Info> map = isv.getOrDefault(bit, new HashMap<>());
                Info next = map.get(i);
                if (next == null || next.cost > arr[info.n][i]) {
                    next = new Info(i, arr[info.n][i], info.cnt + 1, bit);
                    map.put(i, next);
                    isv.put(bit, map);
                    max = Math.max(max, next.cnt);
                    queue.offer(next);
                }
            }
        }
    }
}

class Info {
    //현재 사람 : n
    //구매 가격 : cost
    //구마했던 사람들 수
    //현재까지의 판매한 사람들 : bit (비트 마스킹)
    int n, cost, cnt, bit;

    public Info(int n, int cost, int cnt, int bit) {
        this.n = n;
        this.cost = cost;
        this.cnt = cnt;
        this.bit = bit;
    }
}