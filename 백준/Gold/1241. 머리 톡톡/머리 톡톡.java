import java.io.*;
import java.util.*;

public class Main {
    static int[] counts, arr, dp;
    static boolean[] isv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        counts = new int[1_000_001];
        dp = new int[1_000_001];
        isv = new boolean[1_000_001];
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
            counts[num]++;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < N; i++) {
            sb.append(calc(arr[i])).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    public static int calc(int num) {
        if(num == 1) return counts[num] - 1;
        if(isv[num]) return dp[num];
        
        int result = counts[1] + counts[num] - 1;     
        int SQRT = (int) Math.sqrt(num);
        for(int i = 2; i <= SQRT; i++) {
            if(num % i != 0) continue;
            result += counts[i];
            if((num / i) > SQRT) result += counts[num / i];
        }
        isv[num] = true;
        return dp[num] = result;
    }
}
