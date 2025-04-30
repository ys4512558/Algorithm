import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb;
    static final int[] LEDS = {
        Integer.parseInt("1110111", 2), //0
        Integer.parseInt("0010010", 2), //1
        Integer.parseInt("1011101", 2), //2
        Integer.parseInt("1011011", 2), //3
        Integer.parseInt("0111010", 2), //4
        Integer.parseInt("1101011", 2), //5
        Integer.parseInt("1101111", 2), //6
        Integer.parseInt("1010010", 2), //7
        Integer.parseInt("1111111", 2), //8
        Integer.parseInt("1111011", 2)  //9
    };

    static int N, K, P, X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        //1~N
        //K자리
        //최대 P개 변경
        //현재 층 X
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if(X == i) continue;
            int p = 0;
            for (int j = 0; j < K; j++) {
                int num1 = (X / (int) Math.pow(10, j)) % 10;
                int num2 = (i / (int) Math.pow(10, j)) % 10;
                int xor = LEDS[num1] ^ LEDS[num2];
                int count = Integer.bitCount(xor);
                p += count;
            }
            if (p <= P) cnt++;
        }
        System.out.println(cnt);
    }
}
