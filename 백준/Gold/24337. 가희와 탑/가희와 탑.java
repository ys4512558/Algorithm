import java.io.*;
import java.util.StringTokenizer;

//N + 1 >= a + b는 최소 한칸 이상을 1로 채울 수 있는 여유 공간이 생김
// ex. 7 4 2
// 1 2 3 4로 가희가 볼 수 있는 최소치를 앞에서부터 사전순으로 맞춰주고
// b에서 - 1을 해줌 (가희가 볼 수 있는 가장 오른쪽을 본다고 정해두고 (여기서는 4) 2 - 1 = 1 (더 봐야할 개수를 의미)
// b만큼 a의 오른쪽에 추가 b - 1부터 하나씩 감소하게끔 추가하면 됨
// 즉 ({1 2 3 4}, (b - 1 = 1)) -> 1 2 3 4 1
// 이렇게 해서 5칸이 만들어졌고 7 - 5는 2이므로 앞에 1을 개수만큼 추가 1 1 2 3 4 1 -> 사전순 최소 만족할듯?
// ex. 7 2 4  b > a인 경우
// 4 3 2 1로 단비가 볼 수 있는 것을 오른쪽부터 왼쪽으로 배치
// a - 1 = 1 이므로 1을 단비의 가장 왼쪽 탑부터 채움 1 4 3 2 1 남은 개수 => 7 - 5 = 2
// 1 1 1 4 3 2 1 -> 사전순 최소 만족?

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] top = new int[N];
        StringBuilder sb = new StringBuilder();

        if (N + 1 < a + b || a == 0 || b == 0) {
            sb.append(-1);
        } else {
            //N개의 탑에서 a + b - 1개의 탑을 세팅하면 나머지는 1의 높이의 탑으로 앞에서 채울 것임
            //a + b - 1의 의미는 (a개의 탑과 b개의 탑을 각각 가희와 단비가 볼 수 있게 세팅하는데 1개의 가장 큰 높이의 탑은 함께 볼 것이므로 - 1)
            fillOne(N - (a + b - 1), top);

            //a, b 중 큰 값이 최대 높이임
            //사전순일때 최대높이가 오른쪽에 있을 수록 사전순으로 가장 앞선다.
            //->b기준으로 놓자
            //max높이의 탑을 둘이 함께 본다고 정의할 것이므로 a, b가 봐야할 탑의 수를 하나씩 줄여주자.

            int max = Math.max(a--, b--);
            // 제일 오른쪽 (N - 1)에서 b가 추가적으로 봐야할 개수가 들어갈 자리를 비우고 최대 높이 탑 세팅
            //7 4 2 일때 max = 4, b-- -> 1 (추가로 1개 더 봐야할 공간 남기기)
            //즉 [?, ?, ?, ?, ?, 4, {1}]의 형태에서 {1}이 들어갈 공간을 남긴다는 의미
            //이를 통해 4를 N - 1 - b(1)을 통해 6 - 1 = 5의 인덱스 위치에 넣는 것
            int maxIdx = N - 1 - b;
            setting(maxIdx, top, a, -1);
            setting(maxIdx, top, b, 1);
            top[maxIdx] = max;

            if (a == 0) {
                top[maxIdx] = 1;
                top[0] = max;
            }
            for (int i = 0; i < N; i++) {
                sb.append(top[i] + " ");
            }

        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    //단비는 가장 높은 위치부터 오른쪽으로 내림차순 세팅
    //가희는 가장 높은 위치부터 왼쪽으로 내림차순으로 세팅
    private static void setting(int maxIdx, int[] top, int count, int add) {
        int idx = maxIdx + add;
        //추가적으로 봐야할 개수만큼 내림차순 세팅
        for (int i = count; i >= 1; i--, idx += add) {
            top[idx] = i;
        }
    }

    private static void fillOne(int count, int[] top) {
        for (int i = 0; i < count; i++) {
            top[i] = 1;
        }
    }
}