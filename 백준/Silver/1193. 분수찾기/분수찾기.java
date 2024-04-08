import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());
        int cnt = 1;
        int num = 2;
        int a = 1;
        int b = 1;

        while (cnt < X){
            for (int j = 0; j < num; j++) {
                if (num % 2 == 0){
                    a = j+1;
                    b = num-j;
                }
                else {
                    a = num-j;
                    b = j+1;
                }
                cnt++;
                if(cnt >= X){
                    break;
                }
            }
            num++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a).append("/").append(b);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}