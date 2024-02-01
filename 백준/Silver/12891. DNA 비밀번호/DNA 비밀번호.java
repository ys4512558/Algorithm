import javax.swing.*;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[] chars;
    static int cnt = 0;
    static int[] dest; //목표 조건
    static int[][] prefixState;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] dna = new char[]{'A', 'C', 'G', 'T'};

        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        chars = new char[s];
        String str = br.readLine();
        prefixState = new int[s+1][];
        prefixState[0] = new int[4];

        for (int i = 0; i < s; i++) {
            chars[i] = str.charAt(i);
            //이전 값으로 초기화
            prefixState[i + 1] = new int[4];
            for (int j = 0; j < 4; j++) {
                prefixState[i + 1][j] = prefixState[i][j];
            }

            if (chars[i] == 'A') {
                prefixState[i + 1][0]++;
            } else if (chars[i] == 'C') {
                prefixState[i + 1][1]++;
            } else if (chars[i] == 'G') {
                prefixState[i + 1][2]++;
            } else if (chars[i] == 'T') {
                prefixState[i + 1][3]++;
            }
        }

        st = new StringTokenizer(br.readLine());

        //조건 설정
        dest = new int[]{
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        };

        for (int i = 0; i <= s - p; i++) {
            if (check(i, p)) {
                cnt++;
            }
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean check(int move, int p) {
        for (int i = 0; i < 4; i++) {
            int count = prefixState[p+move][i] -prefixState[move][i];
            if (count < dest[i]) {
                return false;
            }
        }
        return true;
    }

}