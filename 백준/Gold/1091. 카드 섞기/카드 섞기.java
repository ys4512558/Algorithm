import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String end;
    static Set<String> set;
    static int[] S;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //2 0 1
        //1 2 0
        //i 위치에 S[i]카드가 오도록
        //0 1 2
        //1 2 0
        //2 0 1
        end = "";
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        set = new HashSet<>();
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        S = new int[N];
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st1.nextToken());
            int s = Integer.parseInt(st2.nextToken());
            sb1.append(p);
            sb2.append(i % 3);
            S[i] = s;
        }
        end = sb1.toString();
        System.out.println(bfs(sb2.toString()));
    }

    private static int bfs(String start) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        set.add(start);

        int cnt = 0;
        while (!queue.isEmpty()) {
            String str = queue.poll();

            if(end.equals(str)) return cnt;

            char[] chars = new char[N];
            for (int i = 0; i < N; i++) {
                chars[i] = str.charAt(S[i]);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(chars[i]);
            }
            if (set.add(sb.toString())) {
                queue.offer(sb.toString());
                cnt++;
            }
        }
        return -1;
    }
}