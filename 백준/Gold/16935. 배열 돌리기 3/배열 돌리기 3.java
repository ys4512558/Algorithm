import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static int N;
    static int M;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int oper = Integer.parseInt(st.nextToken());
            switch (oper) {
                case 1:
                    reverseHorizon();
                    break;
                case 2:
                    reverseVerticle();
                    break;
                case 3:
                    rotateRight();
                    break;
                case 4:
                    rotateLeft();
                    break;
                case 5:
                    SubSetRotateRight();
                    break;
                case 6:
                    SubSetRotateLeft();
                    break;
            }
        }


        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    //oper1
    private static void reverseHorizon(){
        int[] temp = new int[M + 1]; //"행"을 바꾸기
        for (int i = 1; i <= (N / 2); i++) {
            System.arraycopy(map[i], 1, temp, 1, M);
            System.arraycopy(map[N - i + 1], 1, map[i], 1, M);
            System.arraycopy(temp, 1, map[N - i + 1], 1, M);
        }
    }

    //oper2
    private static void reverseVerticle() {
        //"열"을 바꾸기
        for (int i = 1; i <= (M / 2); i++) {
            for (int j = 1; j <= N; j++) {
                int temp = map[j][i];
                map[j][i] = map[j][M - i + 1];
                map[j][M - i + 1] = temp;
            }
        }
    }

    //oper3
    private static void rotateRight(){
        int[][] copy = new int[M + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                copy[j][N - i + 1] = map[i][j];
            }
        }
        int temp = N;
        N = M;
        M = temp;
        map = copy;
    }
    //oper4
    private static void rotateLeft(){
        int[][] copy = new int[M + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                copy[M - j + 1][i] = map[i][j];
            }
        }
        int temp = N;
        N = M;
        M = temp;
        map = copy;
    }
    //oper5
    private static void SubSetRotateRight(){
        int[][] temp1 = new int[N / 2 + 1][M / 2 + 1];
        int[][] temp2 = new int[N / 2 + 1][M / 2 + 1];
        for (int i = 1; i <= N / 2; i++) {
            //왼 아래 -> 왼 위
            System.arraycopy(map[i], 1, temp1[i], 1, M / 2);
            System.arraycopy(map[N / 2 + i], 1, map[i], 1, M / 2);
            //왼 위 -> 오른 위
            System.arraycopy(map[i], M / 2 + 1, temp2[i], 1, M / 2);
            System.arraycopy(temp1[i], 1, map[i], M / 2 + 1, M / 2);
            //오른 위 -> 오른 아래
            System.arraycopy(map[N / 2 + i], M / 2 + 1, temp1[i], 1, M / 2);
            System.arraycopy(temp2[i], 1, map[N / 2 + i], M / 2 + 1, M / 2);
            //오른 아래 -> 왼 아래
            System.arraycopy(temp1[i], 1, map[N / 2 + i], 1, M / 2);
        }
    }
    //oper6
    private static void SubSetRotateLeft(){
        int[][] temp1 = new int[N / 2 + 1][M / 2 + 1];
        int[][] temp2 = new int[N / 2 + 1][M / 2 + 1];
        for (int i = 1; i <= N / 2; i++) {
            //왼 위 -> 왼 아래
            System.arraycopy(map[N / 2 + i], 1, temp1[i], 1, M / 2);
            System.arraycopy(map[i], 1, map[N / 2 + i], 1, M / 2);

            //왼 아래 -> 오른 아래
            System.arraycopy(map[N / 2 + i], M / 2 + 1, temp2[i], 1, M / 2);
            System.arraycopy(temp1[i], 1, map[N / 2 + i], M / 2 + 1, M / 2);

            //오른 아래 -> 오른 위
            System.arraycopy(map[i], M / 2 + 1, temp1[i], 1, M / 2);
            System.arraycopy(temp2[i], 1, map[i], M / 2 + 1, M / 2);

            //오른 아래 -> 왼 아래
            System.arraycopy(temp1[i], 1, map[i], 1, M / 2);
        }
    }
}