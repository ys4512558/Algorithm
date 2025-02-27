import java.io.*;
import java.util.*;
//3:03
public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int l = 0;
        int r = N - 1;
        int max = 0;
        while(r - l - 1 > 0) {
            int len = r - l - 1; 
            int skill = len * Math.min(arr[l], arr[r]);
            max = Math.max(max, skill);
            if(arr[l] < arr[r]) l++;
            else r--;
        }
        
        System.out.println(max);
    }
}
