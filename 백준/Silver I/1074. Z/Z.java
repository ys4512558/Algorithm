import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, r, c, size;
    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};
    static int num = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken()) + 1;
        c = Integer.parseInt(st.nextToken()) + 1;

        size = (int) Math.pow(2, N);
        Z(1, 1, size);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void Z(int row, int col, int size){
        if(row == r && col == c) {
            sb.append(num);
            return;
        }
        if (size == 1) {
            num++;
            return;
        }
        int half = size / 2;
        for (int i = 0; i < 4; i++) {
            int x = row + half * dx[i];
            int y = col + half * dy[i];
            if((x <= r && r < x + half) && (y <= c && c < y + half)){
                Z(x, y, half);
                continue;
            }
            num += half * half;
        }
    }
}