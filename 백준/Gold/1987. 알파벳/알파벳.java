import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, max;
    static char[][] map;
    static boolean[] isv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = 0;
        map = new char[N][M];
        isv = new boolean[26];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        isv[getIdx(map[0][0])] = true;
        dfs(new Info(0, 0), 1);

        System.out.println(max);
    }

    private static void dfs(Info info, int cnt) {
        max = Math.max(max, cnt);
        for (int i = 0; i < 4; i++) {
            int nx = info.x + dx[i];
            int ny = info.y + dy[i];

            if (isOutRange(nx, ny) || isv[getIdx(map[nx][ny])]) continue;
            isv[map[nx][ny] - 'A'] = true;
            dfs(new Info(nx, ny), cnt + 1);
            isv[map[nx][ny] - 'A'] = false;
        }
    }

    private static boolean isOutRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    private static int getIdx(char c) {
        return c - 'A';
    }
}

class Info {
    int x, y;

    public Info(int x, int y) {
        this.x = x;
        this.y = y;
    }
}