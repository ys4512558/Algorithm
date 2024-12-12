import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] counts;
    static Top[] tops;
    static int N, R, D, X, Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //탑의 개수
        N = Integer.parseInt(st.nextToken());
        //사정거리
        R = Integer.parseInt(st.nextToken());
        //초기에너지
        D = Integer.parseInt(st.nextToken());
        //적의 X, Y좌표
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        //최대 N임
        counts = new int[N]; //어떤 너비에 몇개의 탑이 속하는지 (적 - R(a) - R(b)) 이런식으로 계속 뻗어나가도록 할때 2차원 리스트에 각 범위별로관리

        //최소 이동 -> 최대 효율
        //적의 X, Y에서 시작해서 R 거리인 애들 A
        //A에 속한 그룹 제외 A에서 R거리인 애들 B ... 반복
        tops = new Top[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tops[i] = new Top(x, y, D);
        }

        bfs(new Top(X, Y, 0));
        double res = 0;
        for (int i = 0; i < N; i++) {
            if(counts[i] == 0) break;
            res += (D * counts[i]) / Math.pow(2, i);
        }

        System.out.println(res);
    }

    private static void bfs(Top start) {
        Queue<Top> queue = new ArrayDeque<>();
        queue.offer(start);

        boolean[] isv = new boolean[N];
        int breadth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Top top = queue.poll();

                for (int i = 0; i < N; i++) {
                    if (getDist(tops[i].x, tops[i].y, top.x, top.y) > R || isv[i]) continue;
                    counts[breadth]++;
                    isv[i] = true;
                    queue.offer(tops[i]);
                }
            }
            breadth++;
        }
    }

    public static double getDist(int sx, int sy, int ex, int ey) {
        return Math.sqrt(Math.pow(sx - ex, 2) + Math.pow(sy - ey, 2));
    }
}

class Top {
    int x, y;
    double energy;

    public Top(int x, int y, double energy) {
        this.x = x;
        this.y = y;
        this.energy = energy;
    }
}