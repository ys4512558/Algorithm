import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int [][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int T = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < R - 2; i++) {
            for (int j = 0; j < C - 2; j++) {
                int mid = calc(i, j);
                if (mid >= T) count++;
            }
        }
        System.out.println(count);
    }

    private static int calc(int r, int c) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int count = treeMap.getOrDefault(map[r + i][c + j], 0);
                treeMap.put(map[r + i][c + j], count + 1);
            }
        }
        int count = 0;
        int result = 0;
        for (int key : treeMap.keySet()) {
            int cnt = treeMap.get(key);
            count += cnt;
            if (count >= 5) {
                result = key;
                break;
            }
        }
        return result;
    }
}
