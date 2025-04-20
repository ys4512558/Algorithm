import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            char[] chars = new char[input.length()];
            Stack<Integer> stack = new Stack<>();
            int cnt = 0;
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == 'C') {
                    stack.push(j);
                    continue;
                } else if (stack.isEmpty()) {
                    stack.push(j);
                    cnt++;
                    continue;
                }
                chars[stack.pop()] = '(';
                chars[j] = ')';
                cnt++;
            }
            while (!stack.isEmpty()) {
                chars[stack.pop()] = ')';
                chars[stack.pop()] = '(';
                cnt += 2;
            }
            sb.append(cnt).append("\n").append(String.valueOf(chars)).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
