import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] strs = new String[N];
            for (int j = 0; j < N; j++) {
                strs[j] = br.readLine();
            }
            Arrays.sort(strs);
            boolean flag = false;
            for (int j = 1; j < N; j++) {
                boolean isEqual = true;
                for (int k = 0; k < Math.min(strs[j].length(), strs[j - 1].length()); k++) {
                    if(strs[j].charAt(k) != strs[j - 1].charAt(k)) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) {
                    flag = true;
                    break;
                }
            }
            System.out.println(!flag ? "YES" : "NO");
        }
    }
}
