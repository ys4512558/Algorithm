import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] strs = {
                "Never gonna give you up",
                "Never gonna let you down",
                "Never gonna run around and desert you",
                "Never gonna make you cry",
                "Never gonna say goodbye",
                "Never gonna tell a lie and hurt you",
                "Never gonna stop"
        };
        Set<String> set = new HashSet<>();

        for (int i = 0; i < strs.length; i++) {
            set.add(strs[i]);
        }

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (set.add(str)) {
                flag = false;
            }
        }
        System.out.println(!flag ? "Yes" : "No");
    }
}