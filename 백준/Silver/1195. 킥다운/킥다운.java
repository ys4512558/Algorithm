import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String gear1 = br.readLine();
        String gear2 = br.readLine();

        int min = gear1.length() + gear2.length();
        min = Math.min(min, find(gear1, gear2));
        min = Math.min(min, find(gear2, gear1));
        System.out.println(min);
    }

    private static int find(String gear1, String gear2) {
        int min = gear1.length() + gear2.length();
        for (int start = 0; start < gear1.length(); start++) { //기어가 맞물리는 시작점 인덱스
            boolean flag = true;
            int l = start;
            int len = gear2.length() - 1;
            for (int r = 0; r <= Math.min(start, gear2.length() - 1); r++) {
                if (gear1.charAt(l) != gear2.charAt(len - r) || gear1.charAt(l) == '1') {
                    l--; continue;
                }
                flag = false;
                break;
            }
            //다 맞물리면
            if (flag) {
                min = gear1.length() + gear2.length() - Math.min(start + 1, gear2.length());
            }
        }
        return min;
    }
}