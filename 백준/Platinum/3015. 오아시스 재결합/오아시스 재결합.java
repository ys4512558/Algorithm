import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int start = Integer.valueOf(br.readLine());
        stack.push(start);
        long total = 0;
        Num pre = new Num(start, 1, null);
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > stack.peek()) {
                for (Num n = pre; n != null && n.n <= num; n = n.pre) {
                    total += n.count;
                    pre = n;
                    stack.pop();
                }
                stack.push(num);
                total += (stack.size() == 1 ? 0 : 1);
                if (pre.n == num) {
                    pre.count++;
                } else if (pre.n < num) {
                    pre = new Num(num, 1, pre.pre);
                } else {
                    pre = new Num(num, 1, pre);
                }
            } else if (num == stack.peek()) {
                total += pre.count + (stack.size() == 1 ? 0 : 1);
                pre.count++;
            } else {
                total++;
                pre = new Num(num, 1, pre);
                stack.push(num);
            }
        }
        System.out.println(total);
    }
}

class Num {
    long n, count;
    Num pre;

    public Num(long n, long count, Num pre) {
        this.n = n;
        this.count = count;
        this.pre = pre;
    }
}