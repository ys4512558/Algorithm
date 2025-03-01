import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] facto;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        factorial();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int command = Integer.parseInt(st.nextToken());
        if (command == 1) {
            long k = Long.parseLong(st.nextToken());
            int[] res = solve1(k);
            for (int i = 0; i < res.length; i++) {
                sb.append(res[i] + " ");
            }
        } else {
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken()) - 1;
            }
            sb.append(solve2(arr));
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static int[] solve1(long k) {
        int[] result = new int[N];
        boolean[] isv = new boolean[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //이미 사용된 수 넘어가기
                if(isv[j]) continue;
                long count = facto[N - i - 1];
                if (k - count > 0) {
                    k -= count;
                } else if (k == count) {
                    result[i] = (j + 1);
                    isv[j] = true;
                    int idx = 1;
                    for (int l = N - 1; l >= 0; l--) {
                        if(isv[l]) continue;
                        result[i + idx] = (l + 1);
                        idx++;
                    }
                    return result;
                } else {
                    result[i] = (j + 1);
                    isv[j] = true;
                    break;
                }

            }
        }
        return result;
    }

    private static long solve2(int[] arr) {
        boolean[] isv = new boolean[N];

        long result = 0;
        for (int i = 0; i < N; i++) {
            //나보다 작은 아직 안나온 수  * (현재 자리 수  - 1)!
            for (int j = 0; j < arr[i]; j++) {
                if(!isv[j]) {
                    result += facto[N - i - 1];
                }
            }
            isv[arr[i]] = true;
        }
        return result + 1;
    }

    public static void factorial() {
        facto = new long[N + 1];
        facto[0] = facto[1] = 1;
        for (int i = 2; i <= N; i++) {
            facto[i] = facto[i - 1] * i;
        }
    }
}