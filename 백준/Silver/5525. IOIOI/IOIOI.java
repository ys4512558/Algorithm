import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringBuilder P = new StringBuilder("IOI");
        String tmp = "OI";
        for (int i = 2; i <= N; i++) {
            P.append(tmp);
        }
        String input = br.readLine();
        int cnt = 0;
        for (int i = 0; i <= input.length() - P.length(); i++) {
            String sub = input.substring(i, P.length() + i);
            if(P.toString().equals(sub)){
                cnt++;
            }
        }
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }
}