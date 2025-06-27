import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Info> list = new ArrayList<>();
        int pre = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (pre == num) {
                Info info = list.get(list.size() - 1);
                info.count++;
            } else {
                list.add(new Info(num, 1));
                pre = num;
            }
        }

        int max = list.get(0).count;
        for (int i = 0; i < list.size() - 1; i++) {
            Info start = list.get(i);
            Info end = list.get(i + 1);
            int count = start.count + end.count;
            for (int j = i - 1; j >= 0; j--) {
                Info info = list.get(j);
                if (info.num != start.num && info.num != end.num) break;
                count += info.count;
            }
            int nextIdx = i + 1;
            for (int j = i + 2; j < list.size(); j++) {
                Info info = list.get(j);
                if (info.num != start.num && info.num != end.num) break;
                count += info.count;
                nextIdx = j;
            }
            i = nextIdx - 1;
            max = Math.max(max, count);
        }
        System.out.println(max);
    }
}

class Info {
    int num, count;

    public Info(int num, int count) {
        this.num = num;
        this.count = count;
    }
}