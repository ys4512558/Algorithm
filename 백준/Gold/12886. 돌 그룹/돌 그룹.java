import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        boolean[][][] isv = new boolean[501][1000][1000];
        Queue<Tuple> queue = new ArrayDeque<>();
        queue.offer(new Tuple(arr[0], arr[1], arr[2]));
        isv[arr[0]][arr[1]][arr[2]] = true;

        boolean flag = false;
        while (!queue.isEmpty()) {
            Tuple tuple = queue.poll();

            if (tuple.a == tuple.b && tuple.b == tuple.c) {
                flag = true;
                break;
            }
            int a = tuple.a + tuple.a;
            if (tuple.b - tuple.a >= 0) {
                int b = tuple.b - tuple.a;
                int[] temp = new int[]{a, b, tuple.c};
                Arrays.sort(temp);
                if (!isv[temp[0]][temp[1]][temp[2]]) {
                    queue.offer(new Tuple(temp[0], temp[1], temp[2]));
                    isv[temp[0]][temp[1]][temp[2]] = true;
                }
            }
            if (tuple.c - tuple.a >= 0) {
                int c = tuple.c - tuple.a;
                int[] temp = new int[]{a, tuple.b, c};
                Arrays.sort(temp);
                if (!isv[temp[0]][temp[1]][temp[2]]) {
                    queue.offer(new Tuple(temp[0], temp[1], temp[2]));
                    isv[temp[0]][temp[1]][temp[2]] = true;
                }
            }
        }
        System.out.println(flag ? 1 : 0);
    }
}

class Tuple {
    int a, b, c;

    public Tuple(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}