import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = 10;
        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            decode();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void decode() throws IOException {
        int N = Integer.parseInt(br.readLine());

        LinkedList list = new LinkedList();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(new Node(Integer.parseInt(st.nextToken())));
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            String command = st.nextToken();
            if (command.equals("I")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                for (int j = 0; j < y; j++) {
                    int code = Integer.parseInt(st.nextToken());
                    list.insert(x + j, new Node(code));
                }
            } else if (command.equals("D")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.delete(x, y);
            } else if (command.equals("A")) {
                int y = Integer.parseInt(st.nextToken());
                for (int j = 0; j < y; j++) {
                    int s = Integer.parseInt(st.nextToken());
                    list.add(new Node(s));
                }
            }
        }
        sb.append(list.toString()).append("\n");
    }
}

class LinkedList{
    Node head = new Node();
    Node tail = null;
    int size = 0;

    //맨 뒤에 삽입
    public void add(Node node) {
        if (tail != null) {
            tail.next = node;
        }
        if (head.next == null) {
            head.next = node;
        }
        tail = node;
        size++;
    }

    //중간에 삽입
    public void insert(int next, Node node) {
        Node pre = getNode(next);
        node.next = pre.next;
        pre.next = node;
        size++;
    }

    public Node getNode(int count) {
        Node currentNode = head;
        while (count > 0) {
            currentNode = currentNode.next;
            count--;
        }
        return currentNode;
    }

    public void delete(int start, int end) {
        Node pre = getNode(start);
        Node dest = getNode(start + end+1);
        pre.next = dest;
        size -= end;
    }

    @Override
    public String toString() {
        int N = 10;
        StringBuilder sb = new StringBuilder();
        Node node = head.next;
        while (N > 0) {
            N--;
            sb.append(node.code).append(" ");
            node = node.next;
        }
        return sb.toString();
    }
}
class Node{
    int code;
    Node next;

    public Node() {
    }

    public Node(int code) {
        this.code = code;
        this.next = null;
    }
}