import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";
        StringBuilder sb = new StringBuilder();
        while (!(str = br.readLine()).equals("end")) {
            int cntX = 0;
            int cntO = 0;
            char[][] map = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int idx = i * 3 + j;
                    char c = str.charAt(idx);
                    if (c == 'X') {
                        map[i][j] = 'X';
                        cntX++;
                    } else if (c == 'O') {
                        map[i][j] = 'O';
                        cntO++;
                    } else {
                        map[i][j] = '.';
                    }
                }
            }
            boolean xWin = false;
            boolean oWin = false;
            //O가 더 많거나 차이가 2이상이면 불가능
            if (cntX < cntO || Math.abs(cntX - cntO) >= 2) {
                sb.append("invalid");
            } else {
                //행 확인
                for (int i = 0; i < 3; i++) {
                    if (map[i][0] == map[i][1] && map[i][0] == map[i][2]) {
                        if(map[i][0] == '.') continue;
                        else if(map[i][0] == 'X') xWin = true;
                        else oWin = true;
                    }
                }
                for (int i = 0; i < 3; i++) {
                    if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                        if(map[0][i] == '.') continue;
                        else if(map[0][i] == 'X') xWin = true;
                        else oWin = true;
                    }
                }
                if ((map[0][0] == map[1][1] && map[1][1] == map[2][2]) || (map[2][0] == map[1][1] && map[1][1] == map[0][2])) {
                    if(map[1][1] == 'X') xWin = true;
                    else if(map[1][1] == 'O') oWin = true;
                }

                if (oWin && xWin) {
                    sb.append("invalid");
                } else if (oWin || xWin) {
                    if ((oWin && cntX == cntO) || (xWin && cntX == cntO + 1)) {
                        sb.append("valid");
                    } else{
                        sb.append("invalid");
                    }
                } else if (cntX + cntO != 9) { //둘다 끝나지 않았는데 게임이 끝난 경우
                    sb.append("invalid");
                } else {
                    sb.append("valid");
                }
            }
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
