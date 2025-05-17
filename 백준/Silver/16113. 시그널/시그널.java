import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][][] pattern = {
        {   // 0
            {1, 1, 1},
            {1, 0, 1},
            {1, 0, 1},
            {1, 0, 1},
            {1, 1, 1}
        },
        {   // 1
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 0}
        },
        {   // 2
            {1, 1, 1},
            {0, 0, 1},
            {1, 1, 1},
            {1, 0, 0},
            {1, 1, 1}
        },
        {   // 3
            {1, 1, 1},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 1},
            {1, 1, 1}
        },
        {   // 4
            {1, 0, 1},
            {1, 0, 1},
            {1, 1, 1},
            {0, 0, 1},
            {0, 0, 1}
        },
        {   // 5
            {1, 1, 1},
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 1},
            {1, 1, 1}
        },
        {   // 6
            {1, 1, 1},
            {1, 0, 0},
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        },
        {   // 7
            {1, 1, 1},
            {0, 0, 1},
            {0, 0, 1},
            {0, 0, 1},
            {0, 0, 1}
        },
        {   // 8
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        },
        {   // 9
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1},
            {0, 0, 1},
            {1, 1, 1}
        }
    };
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        board = new int[5][(N / 5) + 4];
        String input = br.readLine();
        for (int i = 0; i < 5; i++) {
            for (int j = 2; j < (N / 5) + 2; j++) {
                board[i][j] = input.charAt(i * (N / 5) + (j - 2)) == '#' ? 1 : 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (N / 5) + 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (isEqual(i, j)) {
                    sb.append(j);
                    break;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean isEqual(int col, int num) {
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 3; l++) {
                if(board[k][col + l] == pattern[num][k][l]) continue;
                return false;
            }
        }
        return true;
    }
}
