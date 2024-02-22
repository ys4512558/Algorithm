import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String vowel = "aeiou";
    static char[] chars, cryptogram;
    static int L, C;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new char[C];
        cryptogram = new char[L];
        StringTokenizer stk = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            chars[i] = stk.nextToken().charAt(0);
        }
        Arrays.sort(chars);
        comb(0, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void comb(int start, int depth) {
        if(depth == L){
            if (isValid()) {
                sb.append(new String(cryptogram)).append("\n");
            }
            return;
        }
        for (int i = start; i < C; i++) {
            cryptogram[depth] = chars[i];
            comb(i + 1, depth + 1);
        }
    }

    private static boolean isValid() {
        int cons = 0, vow = 0;
        for (int i = 0; i < L; i++) {
            int idx = vowel.indexOf(cryptogram[i]);
            if(idx == -1){
                cons++;
            } else {
                vow++;
            }
            if (cons >= 2 && vow >= 1) {
                return true;
            }
        }
        return false;
    }
}