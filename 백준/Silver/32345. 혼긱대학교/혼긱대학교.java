import java.io.*;

public class Main {
    static int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            sb.append(solution(str)).append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static long solution(String str) {
        int len = str.length();
        //전체 가능한 수
        long total = 1;
        //모음 수
        int totalVowel = 0;
        for (int j = 0; j < len; j++) {
            int cnt = 1;
            //모음이면 다음 모음 전까지의 자음 개수 찾기
            if (isVowel(str.charAt(j))) {
                for (int k = j + 1; k < len; k++) {
                    if (!isVowel(str.charAt(k))) {
                        cnt++;
                        continue;
                    }
                    //다음 모음
                    j = k - 1;
                    total = ((total % MOD) * (cnt % MOD)) % MOD;
                    break;
                }
                totalVowel++;
            }
        }
        return totalVowel == 0 ? -1 : total;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}