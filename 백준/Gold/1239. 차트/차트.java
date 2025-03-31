import java.io.*;
import java.util.*;

public class Main {
    static int N, res;
    static int[] arr;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        res = 0;
        arr = new int[N];
        isSelected = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        int max = 0;
        do {
            int pre = 0;
            boolean[] circle = new boolean[100];
            for(int i = 0; i < N; i++) {
                circle[(arr[i] + pre) % 100] = true;
                // System.out.print((arr[i] + pre) + " ");
                pre += arr[i];
            }
            // System.out.println();
            int cnt = 0;
            for(int i = 0; i < 50; i++) {
                if(circle[i] && circle[(i + 50) % 100]) {
                    cnt++;
                    // System.out.println(i + " " + ((i + 50) % 100));
                }
            }
            max = Math.max(max, cnt);
        } while(nextPerm());
        
        System.out.println(max);
    }
    
    public static boolean nextPerm() {
        int i = arr.length - 1;
        while(i > 0 && arr[i] <= arr[i - 1]) i--;
        if(i == 0) return false;
        
        int j = arr.length - 1;
        while(j > 0 && arr[j] <= arr[i - 1]) j--;
        swap(arr, i - 1, j);
        
        int k = arr.length - 1;
        while(i < k) swap(arr, i++, k--);
        return true;
    }
    
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
