import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int gcd = gcd(c, d);
            int p = c / gcd;
            int q = d / gcd;
            //작은 수가 더 앞으로 오도록 설정
            if (a > b) {
                queue.offer(new int[]{b, a, q, p});
            } else {
                queue.offer(new int[]{a, b, p, q});
            }
        }

        int[] result = new int[N];
        int[] first = queue.poll();
        result[first[0]] = first[2];
        result[first[1]] = first[3];
        //0 3 1 2, 1 2 3 4, 2 3 4 5인 경우
        //[1, 0, 0, 2]로 초기화
        //이후 for문에서 2, 3을 사용하는데 둘다 0이라서 비례식 사용이 안됨.
        while (!queue.isEmpty()) {
            int[] array = queue.poll();
            int[] temp = new int[N];
            int a = array[0];
            int b = array[1];
            temp[a] = array[2];
            temp[b] = array[3];

            //위 이유에 따라 둘다 0인 경우는 나중에 연산해주어야 함
            //큐에 넣고 나중에 다시 연산하기
            if(result[a] == 0 && result[b] == 0) {
                queue.offer(array);
                continue;
            }
            int idx = result[a] != 0 ? a : b;
            int lcm = (result[idx] * temp[idx]) / gcd(result[idx], temp[idx]);
            int mul1 = lcm / result[idx];
            int mul2 = lcm / temp[idx];
            for (int j = 0; j < N; j++) {
                result[j] *= mul1;
            }
            result[a + b - idx] = temp[a + b - idx] * mul2;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i] + " ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static int gcd(int a, int b) {
        int max = Math.max(a, b);
        int min = Math.min(a, b);

        int mod = max % min;
        if(mod == 0) return min;
        return gcd(min, mod);
    }
}