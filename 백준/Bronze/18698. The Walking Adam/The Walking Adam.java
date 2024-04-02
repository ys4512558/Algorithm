import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            int cnt = 0;
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) != 'U') {
                    break;
                }
                cnt++;
            }
            sb.append(cnt).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}