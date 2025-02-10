import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] times = new int[N];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            times[i] = A - B;
        }
        
        Arrays.sort(times);
        int mid = times[N / 2];
        for(int i = 0; i < N; i++) {
            times[i] -= mid;
        }
        
        //중앙을 기준으로 기다리는 시간이 음수 (중앙 : 0) 양수 순으로 정렬되어 있다.
        //N이 홀수라면 중앙은 1개 (0)을 기준으로 음수만큼 더 기다리기, 양수만큼 더 기다리기로 나눠짐
        //이때, 홀수면 음수쪽, 양수쪽 개수가 동일하므로 절댓값은 (음수들의 합), 0, (양수들의 합)일때
        //음수만큼 기다리면 음수 개수 만큼 시간 손해 + 양수 개수 만큼 시간 이득 ( = 0 (개수가 동일하므로))
        //하지만 중앙은 절대값이 무조건 증가하게 되어 손해 (따라서 중앙이 0일때, 1가지가 최소임)
        //N이 짝수라면 
        //(음수(a개)), 0, (양수 (a + 1개)) 
        //(음수(a + 1개)), 0, (양수 (a개)) 두 경우가 가능
        //이때, (음수 (a개)), 음수, 0, (양수 (a개))로 두거나
        //(음수 (a개)), 0, 양수, (양수 (a개))로 둘 수 있다.
        // 그럼 홀수와 동일하게 양수, 음수 개수가 동일하면 가운데의 절대값을 기준으로 체크 가능
        // (음수, 0) -> 음수 -> 0까지 가는 경우가 양쪽 (음수, 양수) 개수를 해치지 않는 선에서 절대값이 동일하다.
        //즉, 양쪽 개수는 어차피 (a), 음수(1), 0(1), (a)일때 음수가 0이 되어도 나머지 a개는 여전히 음수이므로 개수를 해치지 않음.
        //그리고 (음수, 0) 이 두개는 하나가 감소하면 하나가 증가하는 절대값 (즉, 둘의 절대값 합은 동일) ->  (음수가 0이 될때까지만)
        //반대의 경우도 동일 (a, (0), (양수), a)이면 양수 -> 0까지 가는 경우
        //따라서 짝수이면 [N / 2] - [N / 2 - 1] + 1개
        System.out.println(N % 2 == 0 ? times[N / 2] - times[N / 2 - 1] + 1 : 1);
    }
}
