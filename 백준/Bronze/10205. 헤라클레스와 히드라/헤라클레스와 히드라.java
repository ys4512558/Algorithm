import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                if(input.charAt(j) == 'c') N++;
                else N--;
            }
            sb.append("Data Set " + (i + 1) + ":");
            sb.append("\n");
            sb.append(N);
            sb.append("\n\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}
