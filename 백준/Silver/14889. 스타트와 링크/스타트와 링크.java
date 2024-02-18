import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static boolean visit[];
    static int min = 1000000000;

    static int status[][];

    static int start[];
    static int link[];
    static int temp[];

    static boolean temp_visit[];

    static int start_Status = 0;
    static int link_Status = 0;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        status = new int[N][N];
        start = new int[N / 2];
        link = new int[N / 2];
        visit = new boolean[N];
        temp = new int[2];
        temp_visit = new boolean[N / 2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs14889(N, 0, 0);
        sb.append(min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void dfs14889(int N, int depth, int pre) {
        if (N / 2 == depth) {
            int idx = 0;
            for (int i = 0; i < visit.length; i++) {
                if (!visit[i]) {
                    link[idx] = i;
                    idx++;
                }
            }

            calc_status(N / 2, 2, 0, 0,0);
            calc_status(N / 2, 2, 0, 0, 1);
            min = (int) Math.min(Math.abs(start_Status - link_Status), min);
            start_Status = link_Status = 0;
            return;
        }

        for (int i = pre; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                start[depth] = i;
                dfs14889(N, depth + 1, start[depth]);
                visit[i] = false;
            }
        }
    }

    private static void calc_status(int N, int M, int depth, int pre, int sel) {
        if (M == depth) {
            int n = temp[0];
            int m = temp[1];
            if (sel == 0)
                start_Status += (status[n][m] + status[m][n]);
            else
                link_Status += (status[n][m] + status[m][n]);
            return;
        }

        for (int i = pre; i < N; i++) {
            temp[depth] = sel == 0 ? start[i] : link[i];
            calc_status(N, M, depth + 1, i+1, sel);
        }
    }
}