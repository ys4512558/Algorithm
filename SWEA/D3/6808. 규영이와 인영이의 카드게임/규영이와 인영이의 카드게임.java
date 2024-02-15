import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static final int N = 9;
    static int win;
    static int[] card1, card2, factorial = new int[10];
    public static void main(String[] args) throws IOException {
        factorial[0] = factorial[1] = 1;
        for (int i = 2; i < 10; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            solve();
            sb.append(win).append(" ").append(factorial[9] - win).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        card1 = new int[N];
        card2 = new int[N];
        for (int i = 0; i < N; i++) {
            card1[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        for (int i = 1; i <= 2 * N; i++) {
            boolean using = false;
            for (int j = 0; j < N; j++) {
                if(card1[j] == i) {
                    using = true;
                    break;
                }
            }
            if(using) continue;
            card2[idx++] = i;
        }
        win = 0;
        dfs(0, 0, 0);
    }

    private static void dfs(int depth, int score1, int bitMask) {
        if (depth == N) {
            win = score1 >= 86 ? win + 1 : win;
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((bitMask & (1 << i)) != 0) {
                continue;
            }
            int s = card1[depth] > card2[i] ? card1[depth] + card2[i] : 0;
            dfs(depth + 1, score1 + s, bitMask | 1 << i);
        }
    }
}