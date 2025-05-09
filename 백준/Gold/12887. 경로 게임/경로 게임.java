import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int N, count;
    static int[] dx = {0, -1, 1};
    static int[] dy = {1, 0, 0};
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[2][N];

        count = 0;
        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == '.') count++;
            }
        }
        int count1 = Integer.MAX_VALUE;
        int count2 = Integer.MAX_VALUE;
        if(map[0][0] != '#') count1 = bfs(new int[]{0, 0});
        if(map[1][0] != '#') count2 = bfs(new int[]{1, 0});
        System.out.println(count - Math.min(count1, count2));
    }

    private static int bfs(int[] start) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[][] isv = new boolean[2][N];
        isv[start[0]][start[1]] = true;

        int breadth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if(cur[1] == N - 1) return breadth;
                for (int i = 0; i < 3; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if(isOutRange(nx, ny) || isv[nx][ny] || map[nx][ny] == '#') continue;
                    isv[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
            breadth++;
        }
        return breadth;
    }

    public static boolean isOutRange(int x, int y) {
        return (x < 0 || y < 0 || x >= 2 || y >= N);
    }
}
