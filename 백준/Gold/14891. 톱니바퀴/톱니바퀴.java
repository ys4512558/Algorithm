import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] numbers = new int[4][8];
    static Gear[] gears = new Gear[4];
    static Gear[] copy = new Gear[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            gears[i] = new Gear(6, 2, 0);
            for (int j = 0; j < input.length(); j++) {
                numbers[i][j] = (input.charAt(j) - '0');
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            //1 : 시계, -1 : 반시계
            int clock = -(Integer.parseInt(st.nextToken()));
            for (int j = 0; j < 4; j++) {
                copy[j] = new Gear(gears[j].left, gears[j].right, gears[j].top);
            }
            rotate(num, clock, 0);
        }
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res += numbers[i][gears[i].top] == 1 ? (int) Math.pow(2, i) : 0;
        }
        System.out.println(res);
    }

    private static void rotate(int num, int clock, int dir) {
        gears[num].left = (gears[num].left + clock + 8) % 8;
        gears[num].right = (gears[num].right + clock + 8) % 8;
        gears[num].top = (gears[num].top + clock + 8) % 8;

        if (num - 1 >= 0 && numbers[num - 1][copy[num-1].right] != numbers[num][copy[num].left] && dir != 1) {
            rotate(num - 1, -clock, 2);
        }
        if (num + 1 < 4 && numbers[num + 1][copy[num + 1].left] != numbers[num][copy[num].right] && dir != 2) {
            rotate(num + 1, -clock, 1);
        }
    }
}

class Gear{
    int left, right, top;

    public Gear(int left, int right, int top) {
        this.left = left;
        this.right = right;
        this.top = top;
    }
}