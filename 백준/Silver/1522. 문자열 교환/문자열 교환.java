import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] chars = br.readLine().toCharArray();
        int countA = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'a') countA++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length; i++) {
            int countB = 0;
            for (int j = 0; j < countA; j++) {
                if(chars[(i + j) % chars.length] == 'b') countB++;
            }
            min = Math.min(min, countB);
        }
        sb.append(min);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}