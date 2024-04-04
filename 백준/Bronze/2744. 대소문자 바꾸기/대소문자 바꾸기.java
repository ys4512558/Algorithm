import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a') {
                sb.append((char) (c - 32));
            } else {
                sb.append((char) (c + 32));
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}