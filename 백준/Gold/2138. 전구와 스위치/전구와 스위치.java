import java.util.*;
import java.io.*;

public class Main {
        static char[] origin, dest;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        origin = new char[N + 1];
        dest = new char[N + 1];
        String str1 = br.readLine();
        String str2 = br.readLine();
        for(int i = 1; i <= N; i++) {
            origin[i] = str1.charAt(i - 1);
            dest[i] = str2.charAt(i - 1);
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Info> queue = new ArrayDeque<>();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 1; i <= 2; i++){
            sb1.append(origin[i]);
            sb2.append(1 - (origin[i] - '0'));
        }
        if(origin.length > 3) {
            sb1.append(origin[3]);
            sb2.append(origin[3]);
        }
        queue.offer(new Info(sb1.toString(), 0));
        queue.offer(new Info(sb2.toString(), 1));

        for(int i = 2; i <= N; i++) {
            int size = queue.size();
            while(size-- > 0) {
                Info info = queue.poll();
                String str = info.str;
//                System.out.println(info);
                if(str.charAt(0) == dest[i - 1]) {
                    StringBuilder next = new StringBuilder();
                    for(int j = 1; j < str.length(); j++) {
                        next.append(str.charAt(j));
                    }
                    if(i + 2 <= N) {
                        next.append(origin[i + 2]);
                    }
//                    System.out.println("i = " + i + ", next = " + next.toString());
                    queue.offer(new Info(next.toString(), info.cnt));
                } else if(str.charAt(0) != dest[i - 1]) {
                    StringBuilder next = new StringBuilder();
                    for(int j = 1; j < str.length(); j++) {
                        next.append(1 - (str.charAt(j) - '0'));
                    }
                    if(i + 2 <= N) {
                        next.append(origin[i + 2]);
                    }
//                    System.out.println("i = " + i + ", next = " + next.toString());
                    queue.offer(new Info(next.toString(), info.cnt + 1));
                }
            }
        }
        int cnt = -1;
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            String str = info.str;
            if (str.charAt(0) == dest[N]) {
                cnt = cnt == -1 ? info.cnt : Math.min(cnt, info.cnt);
            }
        }
        return cnt;
    }
}

class Info {
    String str;
    int cnt;

    public Info(String str, int cnt) {
        this.str = str;
        this.cnt = cnt;
    }
}
