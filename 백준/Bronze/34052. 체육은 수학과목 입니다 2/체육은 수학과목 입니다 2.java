import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int time = 0;
        for(int i = 0; i < 4; i++) {
            time += Integer.parseInt(br.readLine());
        }
        System.out.println(time + 300 > 1800 ? "No" : "Yes");
    }
}
