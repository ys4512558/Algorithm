import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Linear[] linears = new Linear[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            linears[i] = new Linear(a, b);
        }

        Arrays.sort(linears);

        int time = 0;
        for (int i = 0; i < N; i++) {
            time += linears[i].calc(time);
            time %= 40000;
        }
        System.out.println(time);

    }
}

class Linear implements Comparable<Linear> {
    int a, b;

    public Linear(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int calc(int time) {
        return a * time + b;
    }

    @Override
    public int compareTo(Linear o) {
        if(this.a == 0 && o.a == 0) return 0;
        else if(this.a == 0) return 1;
        else if(o.a == 0) return -1;
        else if(this.b == 0 && o.b == 0) return Integer.compare(o.a, this.a);
        return Double.compare((double) this.b / this.a, (double) o.b / o.a);
    }
}