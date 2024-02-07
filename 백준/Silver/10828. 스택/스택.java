import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("push")){
                stack.push(Integer.valueOf(st.nextToken()));
            } else if(cmd.equals("pop")){
                sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            } else if(cmd.equals("size")){
                sb.append(stack.size()).append("\n");
            } else if(cmd.equals("empty")){
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else{
                sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}