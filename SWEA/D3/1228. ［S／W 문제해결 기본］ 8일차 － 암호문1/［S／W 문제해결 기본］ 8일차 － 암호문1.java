import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = 10;

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            solve();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve() throws IOException {
        int N = Integer.parseInt(br.readLine());

        LinkedList<Integer> list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.valueOf(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            if (st.nextToken().equals("I")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                for (int j = 0; j < y; j++) {
                    list.insertNode(x + j, Integer.valueOf(st.nextToken()));
                }
            }
        }
        sb.append(list.toString()).append("\n");
    }
}

class LinkedList<E>{
    ListNode<E> head = new ListNode<>();
    ListNode<E> tail;

    public void add(E cryptogram) {
        ListNode<E> newNode = new ListNode<>(cryptogram);
        if (tail != null) {
            tail.link = newNode;
        }
        if (head.link == null) {
            head.link = newNode;
        }
        tail = newNode;
    }
    public void insertNode(int x, E s) {
        ListNode<E> pre = getNode(x);
        ListNode<E> newNode = new ListNode<>(s);

        newNode.link = pre.link;
        pre.link = newNode;
    }

    private ListNode<E> getNode(int x) {
        ListNode<E> temp = head;
        for (int i = 0; i < x; i++) {
            temp = temp.link;
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        int num = 10;
        ListNode<E> temp = head.link;
        while (num > 0) {
            stb.append(temp.cryptogram).append(" ");
            temp = temp.link;
            num--;
        }

        return stb.toString();
    }
}

class ListNode<T>{
    T cryptogram;
    ListNode<T> link;

    public ListNode() {
    }

    public ListNode(T cryptogram) {
        this.cryptogram = cryptogram;
    }
}