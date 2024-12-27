import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int[][] panels = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            panels[i] = new int[]{start, end};
        }

        Arrays.sort(panels, (o1, o2) -> {
            if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o1[0], o2[0]);
        });

        int max = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = i + 1; j < N; j++) {
                //현재 구간의 끝점보다 확인하려는 구간의 시작점이 더 작으면 포함 X
                if (panels[i][1] < panels[j][0]) break;
                //현재 구간의 끝점보다 확인하려는 구간의 끝점이 더 작으면 포함 X
                if (panels[i][1] < panels[j][1]) continue;
                //구간에 포함이면 ++
                cnt++;
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}