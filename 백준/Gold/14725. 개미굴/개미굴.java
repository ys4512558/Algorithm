import java.io.*;
import java.util.*;

public class Main{
    static PriorityQueue<Info> infos;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        infos = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            String num = st.nextToken();
            int cnt = Integer.parseInt(num);
            String[] strs = new String[cnt];
            for(int j = 0; j < cnt; j++) {
                strs[j] = st.nextToken();
            }
            String str = input.substring(num.length());
            infos.offer(new Info(cnt, str, strs));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while(!infos.isEmpty()) {
            Info info = infos.poll();

            List<Info> currents = new ArrayList<>();
            String[] pre = info.strs;
            while(!infos.isEmpty()) {
                Info other = infos.peek();
                if (!other.strs[0].equals(info.strs[0])) break;
                currents.add(infos.poll());
            }
            for(int i = 0; i < info.n; i++) {
                for(int j = 0; j < i; j++) {
                    sb.append("--");
                }
                sb.append(info.strs[i]).append("\n");
            }

            for(int i = 0; i < currents.size(); i++) {
                Info next = currents.get(i);
                for(int j = 0; j < Math.min(pre.length, next.n); j++) {
                    if (!pre[j].equals(next.strs[j])) {
                        for (int k = j; k < next.n; k++) {
                            for (int l = 0; l < k; l++) {
                                sb.append("--");
                            }
                            sb.append(next.strs[k])
                              .append("\n");
                        }
                        pre = next.strs;
                        break;
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}

class Info implements Comparable<Info> {
    int n;
    String str;
    String[] strs;

    public Info(int n, String str, String[] strs) {
        this.n = n;
        this.str = str;
        this.strs = strs;
    }

    public int compareTo(Info o) {
        return this.str.compareTo(o.str);
    }
}