import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String N = "";
        while (!(N = br.readLine()).equals("0")) {
            String reverse = new StringBuilder(N).reverse().toString();
            if(N.equals(reverse)) sb.append("yes");
            else sb.append("no");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}