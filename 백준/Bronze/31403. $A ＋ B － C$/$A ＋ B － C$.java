import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strA = br.readLine();
        String strB = br.readLine();
        String strC = br.readLine();
        
        int A = Integer.parseInt(strA);
        int B = Integer.parseInt(strB);
        int C = Integer.parseInt(strC);
        StringBuilder sb = new StringBuilder();
        sb.append(A + B - C);
        sb.append("\n");
        
        int AB = Integer.parseInt(strA + strB);
        sb.append(AB - C);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
