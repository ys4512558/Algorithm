import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Pro1000 pro1000 = new Pro1000();
        pro1000.sol1000();
    }

    static class Pro1000{
        void sol1000(){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = null;
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            System.out.println(num1+num2);
        }

    }
}