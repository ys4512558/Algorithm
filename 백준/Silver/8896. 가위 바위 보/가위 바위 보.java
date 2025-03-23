import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            boolean[] isDead = new boolean[N];
            char[][] chars = new char[N][];
            for (int j = 0; j < N; j++) {
                chars[j] = br.readLine().toCharArray();
            }


            int len = chars[0].length;
            int cnt = 0; //죽은 로봇 카운트
            for (int j = 0; j < len; j++) {
                boolean r = false;
                boolean s = false;
                boolean p = false;
                if(cnt == N - 1) break;
                for (int k = 0; k < N; k++) {
                    if(isDead[k]) continue;
                    switch (chars[k][j]){
                        case 'R':
                            r = true;
                            break;
                        case 'S':
                            s = true;
                            break;
                        case 'P':
                            p = true;
                            break;
                    }
                }
                char win = (r && s && p) ? 'D' : ((r && s) ? 'R' : ((r && p) ? 'P' : ((p && s) ? 'S' : 'D')));
                if(win == 'D') continue;
                for (int k = 0; k < N; k++) {
                    if(chars[k][j] != win && !isDead[k]) {
                        isDead[k] = true;
                        cnt++;
                    }
                }
            }
            if (cnt == N - 1) {
                for (int j = 0; j < N; j++) {
                    if (isDead[j]) continue;
                    sb.append(j + 1);
                    break;
                }
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
