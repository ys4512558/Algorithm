import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] blue;
    static boolean[][] green;
    static final int ROW = 6, COL = 4;
    static final int[][] blocks = {{0, 0}, {0, 1}, {1, 0}};
    static int score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        //파란색을 행열 반전시켜서 초록색과 동일한 로직으로 사용하기 위해 행열크기를 똑같게 맞춰줌
        blue = new boolean[ROW][COL];
        green = new boolean[ROW][COL];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            simulation(t, x, y);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(score).append("\n");

        int cnt = 0;
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[i][j]) cnt++;
                if (green[i][j]) cnt++;
            }
        }
        sb.append(cnt);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    //블록을 해당 위치에 두고 파란색, 초록색으로 밀기
    private static void simulation(int t, int x, int y) {
        //파란색 행열 반전 고려해야할 사항
        slide(t, x, y, true);
        slide(t, x, y, false);
    }

    private static void slide(int t, int x, int y, boolean isBlue) {
        //파란색이면 행열 반전 시켜주기
        int[] block = blocks[t];
        if (isBlue) {
            int temp = x;
            x = y;
            y = temp;
            //파란색이면 행열 반전했으므로 더해주는 것도 반대 블록을 더 해주어야 함. (2 x 1 -> 1 x 2)행열 반전했기 때문에 행에 열값을 더해준다는 의미
            block = t != 0 ? blocks[3 - t] : block;
        }

        boolean[][] map = isBlue ? blue : green;
        //특수 블록의 맨 밑에 두고 시작
        int start = 1;
        for (int i = 2; i < 6; i++) {
            //확인하는 위치에 블록이 있으면 start위치에 블록을 둬야함
            if(map[i][y]) break;
            if (t == 0) {
                start = i;
                continue;
            }
            //t가 0이 아니면 다른 위치의 블록도 확인해주어야 함.
            if(map[i][y + block[1]]) break;
            //두 블록 모두 들어갈 수 있으면 해당 위치에 두겠다고 일단 저장해두기
            start = i;
        }

        //블록 두개 두기
        map[start][y] = true;
        map[start - block[0]][y + block[1]] = true;
        //점수 계산하기
        calcScore(map);
    }

    private static void calcScore(boolean[][] map) {
        //없애야 하는 블록의 행
        boolean[] remove = new boolean[6];
        for (int i = 5; i >= 2; i--) {
            boolean flag = true;
            for (int j = 0; j < 4; j++) {
                if (!map[i][j]) {
                    flag = false;
                    break;
                }
            }
            //전부 블록으로 채워져있으면
            if (flag) {
                //점수 추가
                score++;
                //없애야 하는 행에 추가
                remove[i] = true;
            }
        }
        removeRow(remove, map);
    }

    private static void removeRow(boolean[] remove, boolean[][] map) {
        int removeCnt = 0;
        for (int i = 5; i >= 0; i--) {
            if(!remove[i]) continue;
            for (int j = i + removeCnt - 1; j >= 0; j--) {
                map[j + 1] = map[j];
                map[j] = new boolean[4];
//                for (int k = 0; k < 4; k++) {
//                    map[j + 1][k] = map[j][k];
//                }
            }
            removeCnt++;
        }

        //특별한 칸에 있는 경우 체크
        int cnt = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j]) {
                    cnt++;
                    break;
                }
            }
        }
        if (cnt == 0) return;
        //아래로 당겨주기
        for (int i = 5; i >= cnt; i--) {
            map[i] = map[i - cnt];
        }
        for (int i = cnt - 1; i >= 0; i--) {
            map[i] = new boolean[4];
        }
    }
}