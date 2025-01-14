import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder gear1 = new StringBuilder(br.readLine());
        StringBuilder gear2 = new StringBuilder(br.readLine());

        //길이가 긴걸 gear1에 저장
        if (gear1.length() < gear2.length()) {
            StringBuilder temp = new StringBuilder(gear1.toString());
            gear1 = gear2;
            gear2 = temp;
        }

        int min = gear1.length() + gear2.length();
        min = Math.min(min, find(gear1, gear2));
        min = Math.min(min, find(gear2, gear1));
        System.out.println(min);
    }

    private static int find(StringBuilder gear1, StringBuilder gear2) {
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