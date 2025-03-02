import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int N;
        String str = "";
        while (!(str = br.readLine()).equals("end")) {
            int con = 0;
            int vowel = 0;
            int totalVowel = 0;
            char pre = ' ';

            boolean flag = true;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    totalVowel++;
                    vowel++;
                    con = 0;
                } else {
                    con++;
                    vowel = 0;
                }

                if (vowel == 3 || con == 3) {
                    flag = false;
                    break;
                }
                if (pre == c) {
                    if (!(c == 'e' || c == 'o')) {
                        flag = false;
                        break;
                    }
                }
                pre = c;
            }
            sb.append("<").append(str).append("> ").append("is ");
            if (!flag || totalVowel == 0) {
                sb.append("not ");
            }
            sb.append("acceptable.");
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}