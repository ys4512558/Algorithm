import java.io.*;
import java.util.*;

public class Main{
    static int[] facto;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init();
        String str = "";
        StringBuilder sb = new StringBuilder();
        while((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);

            String s = st.nextToken();
            char[] chars = s.toCharArray();
            int n = Integer.parseInt(st.nextToken());

            sb.append(s).append(" ").append(n).append(" = ");
            if(facto[chars.length] < n) {
                sb.append("No permutation");
            } else {
                int cnt = 0;
                do {
                    if(++cnt == n) {
                        for(int i = 0; i < chars.length; i++) {
                            sb.append(chars[i]);
                        }
                    }
                } while(nextPerm(chars));
            }
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    public static boolean nextPerm(char[] arr) {
        int i = arr.length - 1;
        while(i > 0 && arr[i] < arr[i - 1]) i--;

        if(i == 0) return false;
        int j = arr.length - 1;
        while(j > 0 && arr[i - 1] > arr[j]) j--;
        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while(i < k) swap(arr, i++, k--);
        return true;
    }

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void init() {
        facto = new int[11];
        facto[0] = facto[1] = 1;
        for(int i = 2; i <= 10; i++) {
            facto[i] = facto[i - 1]  * i;
        }
    }
}
