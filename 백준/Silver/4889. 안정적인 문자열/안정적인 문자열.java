import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String input = "";
        int T = 1;
        while (!(input = br.readLine()).contains("-")) {
            sb.append(T++).append(". ").append(solve(input)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve(String input) {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') {
                stack.push('{');
                continue;
            }
            if(!stack.isEmpty()){
                stack.pop();
            } else {
                stack.push('{');
                cnt++;
            }
        }
        return cnt + (stack.size() / 2);
    }
}