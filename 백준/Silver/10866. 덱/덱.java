import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push_front")) {
                int value = Integer.parseInt(st.nextToken());
                deque.offerFirst(value);
            } else if (command.equals("push_back")){
                int value = Integer.parseInt(st.nextToken());
                deque.offerLast(value);
            } else if (command.equals("pop_front")){
                sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
            } else if (command.equals("pop_back")){
                sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
            } else if (command.equals("size")){
                sb.append(deque.size()).append("\n");
            } else if (command.equals("empty")){
                sb.append(deque.isEmpty() ? 1 : 0).append("\n");
            } else if (command.equals("front")){
                sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
            } else if (command.equals("back")){
                sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}