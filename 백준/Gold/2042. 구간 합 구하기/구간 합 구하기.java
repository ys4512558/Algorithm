import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 1, N);

        for (int i = 0; i < M + K; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int sel = Integer.parseInt(stk.nextToken());
            if (sel == 1) {
                int idx = Integer.parseInt(stk.nextToken());
                long val = Long.parseLong(stk.nextToken());
                segmentTree.update(1, 1, N, idx, val - arr[idx]);
                arr[idx] = val; //다음에 update할 때 arr[idx] = val을 안해주면 완전 초기의 값이 들어있게 되어 diff가 달라짐
            } else {
                long left = Long.parseLong(stk.nextToken());
                long right = Long.parseLong(stk.nextToken());
                long res = segmentTree.query(1, 1, N, left, right);
                sb.append(res).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class SegmentTree{
    long[] tree;
    int treeSize;

    public SegmentTree(int length) {
        int h = (int) Math.ceil(Math.log(length) / Math.log(2));
        this.treeSize = (int) Math.pow(2, h + 1);
        this.tree = new long[treeSize];
    }

    public long init(long[] arr, int node, int start, int end) {

        if(start == end) return tree[node] = arr[start];

        return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
    }

    public long query(int node, int start, int end, long left, long right) {
        if(left > end || right < start) return 0;
        if(left <= start && end <= right) return tree[node];

        return query(node * 2, start, (start + end) / 2, left, right)
                + query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }

    public void update(int node, int start, int end, int idx, long diff) {
        if(idx < start || idx > end) return;

        tree[node] += diff;

        if(start == end) return;

        update(node * 2, start, (start + end) / 2, idx, diff);
        update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
    }
}