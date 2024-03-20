import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int count = 0;
    static Queue<Integer[]> points;
    final static int dx[] = {-1, 1, 0, 0};
    final static int dy[] = {0, 0, -1, 1};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int map[][] = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs14502(map, 0);

        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static void dfs14502(int[][] map, int wall) {
        if (wall == 3) {
            bfs14502(map, map.length, map[0].length);
            max = Math.max(max, count);
            return;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs14502(map, wall+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void bfs14502(int[][] map, int N, int M) {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        points = new LinkedList<>();
        boolean visit[][] = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 2) {
                    points.add(new Integer[]{i, j});
                }
            }
        }

        while (!points.isEmpty()) {
            Integer point[] = points.poll();
            int y = point[0];
            int x = point[1];

            for (int i = 0; i < 4; i++) {
                int curX = x + dx[i];
                int curY = y + dy[i];

                if (curY < 0 || curX < 0 || curY >= copyMap.length || curX >= copyMap[curY].length) {
                    continue;
                }
                if (copyMap[curY][curX] == 0 && !visit[curY][curX]) {
                    visit[curY][curX] = true;
                    copyMap[curY][curX] = 2;
                    points.add(new Integer[]{curY, curX});
                }
            }
        }
        calcCount(copyMap);
    }

    private static void calcCount(int[][] copyMap) {
        count = 0;
        for (int i = 0; i < copyMap.length; i++) {
            for (int j = 0; j < copyMap[i].length; j++) {
                if (copyMap[i][j] == 0) {
                   count++;
                }
            }
        }
    }
}