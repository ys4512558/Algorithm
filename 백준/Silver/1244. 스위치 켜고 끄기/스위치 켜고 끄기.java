import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] power;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        power = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (gender == 2) {
                power[num] ^= 1;
                checkAndToggle(num, 1);
                continue;
            }
            //남자일 때 토글 (비트연산)
            for (int j = num; j <= N; j+=num) {
                power[j] ^= 1;
            }
        }
        for (int i = 1; i <= N; i++) {
            sb.append(power[i]).append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    private static void checkAndToggle(int n, int move){
        //왼쪽, 오른쪽 넘어가면 끝
        if (n - move < 1 || n + move > N) {
            return;
        }
        //둘이 다르면 끝
        if (power[n - move] != power[n + move]) {
            return;
        }
        power[n - move] ^= 1;
        power[n + move] ^= 1;
        checkAndToggle(n, move+1);
    }
}