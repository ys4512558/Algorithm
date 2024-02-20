import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(stk.nextToken());
            int right = Integer.parseInt(stk.nextToken());
            int res = segmentTree.query(1, 0, N - 1, left - 1, right - 1);
            sb.append(res).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class SegmentTree{
    int[] tree;
    int treeSize;

    public SegmentTree(int len) {
        int h = (int) Math.ceil(Math.log(len) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        tree = new int[treeSize];
    }

    public int init(int[] arr, int node, int start, int end) {
        if(start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        int left = init(arr, node * 2, start, mid);
        int right = init(arr, node * 2 + 1, mid + 1, end);
        return tree[node] = Math.min(left, right);
    }

    public int query(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return Integer.MAX_VALUE;

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftValue = query(node * 2, start, mid, left, right);
        int rightValue = query(node * 2 + 1, mid + 1, end, left, right);
        return Math.min(leftValue, rightValue);
    }

    @Override
    public String toString() {
        return "SegmentTree{" +
                "tree=" + Arrays.toString(tree) +
                ", treeSize=" + treeSize +
                '}';
    }
}