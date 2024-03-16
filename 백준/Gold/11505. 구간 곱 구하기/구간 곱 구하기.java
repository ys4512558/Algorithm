import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static int treeSize;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];

        init(1, 1, N);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if (cmd == 1) {
                update(1, num1, 1, N, num2);
                continue;
            }
            sb.append(query(1, 1, N, num1, num2)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    public static long init(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];

        long l = init(node * 2, start, (start + end) / 2) % 1000000007;
        long r = init(node * 2 + 1, (start + end) / 2 + 1, end) % 1000000007;

        return tree[node] = (l * r) % 1000000007;
    }

    public static long query(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return 1;
        if(left <= start && end <= right) return tree[node];

        long l = query(node * 2, start, (start + end) / 2, left, right) % 1000000007;
        long r = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right)  % 1000000007;
        return (l * r) % 1000000007;
    }

    public static long update(int node, int idx, int start, int end, int change) {
        if (idx < start || end < idx) return tree[node];

        if (start == end) return tree[node] = change;

        long l = update(node * 2, idx, start, (start + end) / 2, change) % 1000000007;
        long r = update(node * 2 + 1, idx, (start + end) / 2 + 1, end, change) % 1000000007;

        return tree[node] = (l * r) % 1000000007;
    }
}