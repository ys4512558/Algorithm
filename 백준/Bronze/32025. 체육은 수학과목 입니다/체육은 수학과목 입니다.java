import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int H = Integer.parseInt(br.readLine()) * 50;
        int W = Integer.parseInt(br.readLine()) * 50;
        System.out.println(Math.min(H, W));
    }
}
