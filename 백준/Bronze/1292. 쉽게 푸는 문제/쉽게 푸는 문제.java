import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        int num = 1;
        int cnt = 1;
        int[] prefixSum = new int[B + 1];
        
        for(int i = 1; i <= B; i++) {
            prefixSum[i] = num + prefixSum[i - 1];
            if(--cnt == 0) {
                cnt = ++num;
            }
        }
        System.out.println(prefixSum[B] - prefixSum[A - 1]);
    }
}
