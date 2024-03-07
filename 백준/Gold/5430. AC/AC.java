import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            boolean isFront = true, isValid = true;
            String cmd = br.readLine();
            int N = Integer.parseInt(br.readLine());
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            String input = br.readLine();
            input = input.substring(1, input.length() - 1);
            StringTokenizer st = new StringTokenizer(input, ",");
            for (int j = 0; j < N; j++) {
                deque.offer(Integer.parseInt(st.nextToken()));
            }
            for (int j = 0; j < cmd.length(); j++) {
                if(cmd.charAt(j) == 'R'){
                    isFront = !isFront;
                    continue;
                }
                if(deque.isEmpty()){
                    sb.append("error").append("\n");
                    isValid = false;
                    break;
                }
                if(isFront){
                    deque.pollFirst();
                } else {
                    deque.pollLast();
                }
            }
            if(isValid){
                sb.append("[");
                int size = deque.size();
                for (int j = 0; j < size; j++) {
                    if(isFront){
                        sb.append(deque.pollFirst());
                    } else {
                        sb.append(deque.pollLast());
                    }
                    if(j == size - 1) break;
                    sb.append(",");
                }
                sb.append("]").append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}