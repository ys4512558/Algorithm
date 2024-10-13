import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int N = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int white = 0;
        int black = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                switch (str.charAt(j)) {
                    case '.':
                        continue;
                    case 'K':
                        continue;
                    case 'k':
                        continue;
                    case 'P':
                        white += 1;
                        continue;
                    case 'p':
                        black += 1;
                        continue;
                    case 'N':
                        white += 3;
                        continue;
                    case 'n':
                        black += 3;
                        continue;
                    case 'B':
                        white += 3;
                        continue;
                    case 'b':
                        black += 3;
                        continue;
                    case 'R':
                        white += 5;
                        continue;
                    case 'r':
                        black += 5;
                        continue;
                    case 'Q':
                        white += 9;
                        continue;
                    case 'q':
                        black += 9;
                        continue;
                }
            }
        }
        System.out.println(white - black);
    }

}