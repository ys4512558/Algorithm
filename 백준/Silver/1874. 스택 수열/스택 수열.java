import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
//            deque.offerLast(Integer.valueOf(st.nextToken()));
            deque.offerLast(Integer.parseInt(br.readLine()));
        }

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        int idx = 1;
        Loop:
        while (!deque.isEmpty()) {
            if(idx > deque.peekFirst()){
                if (stack.isEmpty() || !stack.peek().equals(deque.peekFirst())) {

                    flag = false;
                    break Loop;
                }
                sb.append("-");
                sb.append("\n");
                stack.pop();
                deque.pollFirst();
            } else if (idx == deque.peekFirst()) {
                deque.pollFirst();
                sb.append("+");
                sb.append("\n");
                sb.append("-");
                sb.append("\n");
                idx++;
            } else {
                for (int j = idx; j <= deque.peekFirst(); j++) {
                    sb.append("+");
                    sb.append("\n");
                    stack.push(j);
                }
                idx = deque.pollFirst() + 1;
                stack.pop();
                sb.append("-");
                sb.append("\n");
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(flag ? sb.toString() : "NO");
        bw.flush();
        bw.close();
    }

}