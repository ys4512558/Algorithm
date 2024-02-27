import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Pair[] pairs = new Pair[K];
        int[] perms = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            perms[i] = i;
            pairs[i] = new Pair(r, c, s);
        }
        int min = Integer.MAX_VALUE;

        do {
            int[][] array = copyArray(arr);

            for (int i = 0; i < K; i++) {
                int r = pairs[perms[i]].r;
                int c = pairs[perms[i]].c;
                int s = pairs[perms[i]].s;

                int idx = 0;
                while (true){
                    int r1 = r - s + idx;
                    int c1 = c - s + idx;
                    int r2 = r + s - idx;
                    int c2 = c + s - idx;
                    if(r1 >= r2 ||c1 >= c2) break;

                    rotate(array, r1, c1, r2, c2);

                    idx++;
                }
            }

            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int j = 1; j <= M; j++) {
                    sum += array[i][j];
                }
                min = Math.min(min, sum);
            }
        } while (nextPerm(perms));


        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private static boolean nextPerm(int[] perms) {
        int i = K - 1;
        while (i > 0 && perms[i-1] >= perms[i]) i--;
        if (i == 0) return false;
        int dest = i - 1;

        int j = K - 1;
        while (j > i && perms[dest] >= perms[j]) j--;
        swap(perms, dest, j);

        int k = K - 1;
        while (i < k) swap(perms, i++, k--);
        return true;
    }

    private static void swap(int[] perms, int dest, int j) {
        int temp = perms[dest];
        perms[dest] = perms[j];
        perms[j] = temp;
    }

    private static void rotate(int[][] arr, int r1, int c1, int r2, int c2) {
        int[][] copy = copyArray(arr);

        for (int i = c2; i > c1; i--) {
            arr[r1][i] = copy[r1][i - 1];
        }
        arr[r1][c1] = copy[r1 + 1][c1];

        for (int i = r2; i > r1; i--) {
            arr[i][c2] = copy[i - 1][c2];
        }
        arr[r1 + 1][c2] = copy[r1][c2];

        for (int i = c1; i < c2; i++) {
            arr[r2][i] = copy[r2][i + 1];
        }
        arr[r2][c2 - 1] = copy[r2][c2];

        for (int i = r1; i < r2; i++) {
            arr[i][c1] = copy[i + 1][c1];
        }
        arr[r1][c1 + 1] = copy[r1][c1];
    }

    private static int[][] copyArray(int[][] arr) {
        int[][] copy = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
}
class Pair{
    int r, c, s;

    public Pair(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}