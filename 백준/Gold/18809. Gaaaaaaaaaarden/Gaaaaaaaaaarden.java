import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, G, R;
    static int[][] map;
    static int max = 0;
    static List<Pair> places;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        places = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) places.add(new Pair(i, j, 1));
            }
        }

        combinationPlace();
        System.out.println(max);
    }

    /**
     *  K개의 배양 가능한 토양의 개수에서 G + R개의 토양 선택 (조합)
     *  G + R개의 선택된 토양에서 G개의 토양에 G, 나머지에 R 두기 (조합)
     */

    //토양뽑기
    private static void combinationPlace() {
        int[] idxs = new int[places.size()];
        //places에서 조합으로 뽑아낼 인덱스
        for (int i = idxs.length - 1; i >= idxs.length - (G + R); i--) {
            idxs[i] = 1;
        }

        do {
            combinationColor(idxs);
        } while (nextPermutation(idxs));
    }

    //    뽑은 토양에서 다시 배치할 색 뽑기
    private static void combinationColor(int[] idxs) {
        int[] colors = new int[G + R];
        for (int i = colors.length - 1; i >= R; i--) {
            colors[i] = 1;
        }

        do {
            simulation(idxs, colors);
        } while (nextPermutation(colors));
    }

    private static void simulation(int[] idxs, int[] colors) {
        Queue<Pair> reds = new ArrayDeque<>();
        Queue<Pair> greens = new ArrayDeque<>();

        int idx = 0;
        int[][][] isv = new int[2][N][M];
        for (int i = 0; i < idxs.length; i++) {
            if (idxs[i] == 1) {
                Pair pair = places.get(i);
                if (colors[idx++] == 0) {
                    reds.offer(pair);
                    isv[1][pair.x][pair.y] = 1;
                } else {
                    greens.offer(pair);
                    isv[0][pair.x][pair.y] = 1;
                }

            }
        }

        bfs(reds, greens, isv);

    }

    private static void bfs(Queue<Pair> reds, Queue<Pair> greens, int[][][] isv) {
        int cnt = 0;

        while (!reds.isEmpty() || !greens.isEmpty()) {
            int size1 = greens.size();
            while (size1-- > 0) {
                cnt = go(greens, isv, cnt, 0);
            }
            int size2 = reds.size();
            while (size2-- > 0) {
                cnt = go(reds, isv, cnt, 1);
            }
        }
        max = Math.max(max, cnt);
    }

    public static int go(Queue<Pair> queue, int[][][] isv, int cnt, int flag){
        Pair p = queue.poll();

        if(isv[flag^1][p.x][p.y] == -1) return cnt;
        if(isv[flag^1][p.x][p.y] == p.t) {
            cnt++;
            isv[0][p.x][p.y] = -1;
            isv[1][p.x][p.y] = -1;
            return cnt;
        }

        for (int i = 0; i < 4; i++) {
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];

            if (isValid(nx, ny) || isv[flag][nx][ny] != 0) continue;
            if(isv[flag^1][nx][ny] == 0 || isv[flag^1][nx][ny] == p.t + 1){
                queue.offer(new Pair(nx, ny, p.t + 1));
                isv[flag][nx][ny] = p.t + 1;
            }
        }
        return cnt;
    }

    private static boolean isValid(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0;
    }

    public static boolean nextPermutation(int[] arr){
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if(i == 0) return false;
        int dest = i - 1;

        int j = arr.length - 1;
        while (j > dest && arr[dest] >= arr[j]) j--;
        swap(arr, j, dest);

        int k = arr.length - 1;
        while (i < k) swap(arr, i++, k--);

        return true;
    }

    private static void swap(int[] arr, int j, int dest) {
        int temp = arr[j];
        arr[j] = arr[dest];
        arr[dest] = temp;
    }
}

class Pair{
    int x, y, t;

    public Pair(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}