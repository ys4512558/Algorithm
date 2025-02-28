import java.io.*;
import java.util.*;

public class Main {
    static int[] a, b;
    static Map<Long, Long> sumA, sumB;
    static int T, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());       
        a = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            a[i] += a[i - 1];
        } 
        M = Integer.parseInt(br.readLine());       
        b = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            b[i] += b[i - 1];
        } 
        sumA = new TreeMap<>();
        sumB = new TreeMap<>();
        calc(a, sumA);
        calc(b, sumB);
        
        long count = 0;
        for(long A : sumA.keySet()) {
            count += sumA.get(A) * sumB.getOrDefault(T - A, 0L);
        }
        System.out.println(count);                
    }
    
    public static void calc(int[] arr, Map<Long, Long> sum) {
        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < i; j++) {
                long value = arr[i] - arr[j];
                long cnt = sum.getOrDefault(value, 0L);                                
                sum.put(value, cnt + 1);
            }
        }
    }
}
