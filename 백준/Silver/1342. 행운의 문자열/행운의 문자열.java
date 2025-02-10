import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Character, Integer> counts;

    static int SIZE, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        SIZE = str.length();
        counts = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            int count = counts.getOrDefault(str.charAt(i), 0);
            counts.put(str.charAt(i), count + 1);
        }

        backtracking(0, "", ' ');
        System.out.println(result);

    }

    private static void backtracking(int idx, String str, char pre) {
        if (idx == SIZE) {
            result++;
            return;
        }

        for (char c : counts.keySet()) {
            int cnt = counts.get(c);
            if (cnt == 0) continue;
            if (pre != c) {
                String next = str + c;
                counts.put(c, cnt - 1);
                backtracking(idx + 1, next, c);
                counts.put(c, cnt);
            }
        }
    }
}