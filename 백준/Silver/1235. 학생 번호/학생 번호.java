import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            strs[i] = br.readLine();
        }

        int len = 1;
        for (int i = 0; i < strs[0].length(); i++) {
            boolean flag = true;
            Set<String> set = new HashSet<>();
            for (int j = 0; j < N; j++) {
                StringBuilder sb = new StringBuilder();
                int l = strs[j].length();
                for (int k = l - len; k < l; k++) {
                    sb.append(strs[j].charAt(k));
                }
                if (!set.add(sb.toString())) {
                    flag = false;
                    break;
                }
            }
            if(flag) break;
            len++;
        }
        System.out.println(len);
    }
}
