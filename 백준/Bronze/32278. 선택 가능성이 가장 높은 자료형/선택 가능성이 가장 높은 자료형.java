import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());

        if (num >= -32768 && num <= 32767) {
            System.out.println("short");
        } else if (num >= -2147483648 && num <= 2147483647) {
            System.out.println("int");
        } else {
            System.out.println("long long");
        }
    }
}
