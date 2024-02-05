import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] tower = new int[N + 1];
        int[] preTower = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
            if (tower[i - 1] > tower[i]) {
                preTower[i] = i - 1;
            } else {
                int res = 0;
                int idx = i - 1;
                while (idx != 0){
                    if (tower[preTower[idx]] > tower[i]) {
                        res = preTower[idx];
                        break;
                    }
                    idx = preTower[idx];
                }
                preTower[i] = res;
            }
        }
        for (int i = 1; i <= N; i++) {
            sb.append(preTower[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}