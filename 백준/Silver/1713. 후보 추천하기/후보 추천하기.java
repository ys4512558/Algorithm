import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] people;
    static int[][] infos;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int cnt = 0;
        people = new int[101];
        Arrays.fill(people, -1);

        infos = new int[N][3]; //[0] : 후보 번호, [1] : 투표 수, [2] : 게시된 턴

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(people[n] != -1) {
                infos[people[n]][1]++;
            } else if (cnt < N) {
                infos[cnt] = new int[]{n, 1, i};
                people[n] = cnt++;
            } else {
                int min = i + 1; //현재까지 총 추천이 i개임 (i + 1보다 큰 추천을 받은 후보는 없음)
                int time = i + 1; //얘도 마찬가지
                int idx = -1;
                for (int j = 0; j < N; j++) {
                    if(infos[j][1] < min) {
                        min = infos[j][1];
                        time = infos[j][2];
                        idx = j;
                    } else if(infos[j][1] == min && infos[j][2] < time) {
                        time = infos[j][2];
                        idx = j;
                    }
                }
                people[infos[idx][0]] = -1;
                infos[idx] = new int[]{n, 1, i};
                people[n] = idx;
            }
        }
        Arrays.sort(infos, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        StringBuilder sb = new StringBuilder();
        //추천 받은 사람 2명, 사진 수 최대 3개일때 2명만 나와야하는데 [0][1][2]가 나오면 틀림 [1][2]만 나와야함.
        for (int i = N - cnt; i < N; i++) {
            sb.append(infos[i][0] + " ");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}