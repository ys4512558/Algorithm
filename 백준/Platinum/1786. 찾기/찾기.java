import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        char T[] = br.readLine().toCharArray();
        char P[] = br.readLine().toCharArray();
        int pi[] = new int[P.length];

        int j = 0;
        for (int i = 1; i < P.length; i++) {
            while (j > 0 && P[i] != P[j]) j = pi[j - 1];
            if (P[i] == P[j]) pi[i] = ++j;
        }

        j = 0;
        int cnt = 0;
        for (int i = 0; i < T.length; i++) {
            while (j > 0 && T[i] != P[j]) j = pi[j - 1];
            if (T[i] == P[j]) j++;
            if(j >= P.length) {
                cnt++;
                sb.append(i - P.length + 2).append(" ");
                j = pi[j - 1];
            }
        }
        bw.write(String.valueOf(cnt) + "\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}