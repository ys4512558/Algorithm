import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        
        int odd = 0;
        int even = 0;
        for(int i = 0; i < N; i++) {
            int num = str.charAt(i) - '0';
            odd += num % 2 != 0 ? 1 : 0;
            even += num % 2 == 0 ? 1 : 0;
        }
        System.out.println(odd == even ? -1 : (odd < even) ? 0 : 1);
    }
}
