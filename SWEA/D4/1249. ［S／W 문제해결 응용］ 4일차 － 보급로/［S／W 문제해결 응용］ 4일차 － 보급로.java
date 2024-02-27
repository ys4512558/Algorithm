import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int map[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = (input.charAt(j) - '0');
            }
        }

        return bfs(map, N);
    }

    private static int bfs(int[][] map, int n) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[][] isv = new boolean[n][n];
        pq.offer(new Pair(0, 0, 0));
        isv[0][0] = true;
        int weight = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (p.x == n - 1 && p.y == n - 1) {
                weight = p.weight;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if (x < 0 || x >= n || y < 0 || y >= n || isv[x][y]) continue;
                isv[x][y] = true;
                pq.offer(new Pair(x, y, p.weight + map[x][y]));
            }
        }
        return weight;
    }
}

class Pair implements Comparable<Pair>{
    int x, y, weight;

    public Pair(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(this.weight, o.weight);
    }
}