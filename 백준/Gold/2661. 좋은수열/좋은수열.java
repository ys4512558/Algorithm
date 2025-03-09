import java.io.*;

public class Main {
    static int N;
    static int[] result;
    static boolean isEnd;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        result = new int[N];
        isEnd = false;
        int[] arr = new int[N];
        for (int i = 1; i <= 3; i++) {
            arr[0] = i;
            dfs(1, arr);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(int depth, int[] arr) {
        if(isEnd) return;
        if (depth == N) {
            for (int i = 0; i < N; i++) {
                result[i] = arr[i];
            }
            isEnd = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            arr[depth] = i;
            boolean flag = false;
            for (int j = depth - 1; j >= 0; j--) {
                if (arr[j] != i) continue;
                boolean temp = true;
                for (int k = depth, l = j; k > j; k--, l--) {
                    if (l < 0) {
                        temp = false;
                        break;
                    }
                    if (arr[k] == arr[l]) continue;
                    temp = false;
                    break;
                }
                flag |= temp;
            }
            if (flag) continue;
            dfs(depth + 1, arr);
        }
    }
}
//1231312313