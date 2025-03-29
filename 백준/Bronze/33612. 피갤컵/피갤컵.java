import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int y = 2024;
        int m = 7 + ((N - 1) * 7);
        
        y += m / 12;
        m %= 12;
        
        System.out.println(y + " " + (m + 1));
    }
}
