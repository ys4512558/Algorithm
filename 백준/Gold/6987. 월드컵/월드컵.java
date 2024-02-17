import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static int res = 0;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++) {
            int[][] game = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    game[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            res = 0;
            solve(0, 1, game);
            sb.append(res).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int team1, int team2, int[][] game) {
        if (team1 != 5 && team2 == 6) {
            solve(team1 + 1, team1 + 2, game);
            return;
        }
        if(team1 == 5){
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game[i][j] != 0) {
                        res = 0;
                        return;
                    }
                }
            }
            res = 1;
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (game[team1][i] > 0 && game[team2][2 - i] > 0) {
                game[team1][i]--;
                game[team2][2 - i]--;
                solve(team1, team2 + 1, game);
                game[team1][i]++;
                game[team2][2 - i]++;
            }
        }
    }
}