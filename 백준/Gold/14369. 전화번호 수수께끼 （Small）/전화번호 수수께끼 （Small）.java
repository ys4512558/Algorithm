import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            Map<Character, Integer> map = new HashMap<>();
            for (int j = 0; j < input.length(); j++) {
                int count = map.getOrDefault(input.charAt(j), 0);
                map.put(input.charAt(j), count + 1);
            }
            sb.append("Case #");
            sb.append((i + 1));
            sb.append(": ");
            sb.append(getNumber(map));
            sb.append("\n");
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }

    private static String getNumber(Map<Character, Integer> map) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //0 추가
        removeNum(map, 'Z', pq, 0, "ZERO");
        //2 추가
        removeNum(map, 'W', pq, 2, "TWO");
        //4 추가
        removeNum(map, 'U', pq, 4, "FOUR");
        //6 추가
        removeNum(map, 'X', pq, 6, "SIX");
        //8 추가
        removeNum(map, 'G', pq, 8, "EIGHT");

        //위에서 지워지면 이제 다음
        //1 추가
        removeNum(map, 'O', pq, 1, "ONE");
        //3 추가
        removeNum(map, 'H', pq, 3, "THREE");
        //5 추가
        removeNum(map, 'F', pq, 5, "FIVE");
        //7 추가
        removeNum(map, 'S', pq, 7, "SEVEN");

        //위에서 지워지면 남은건 다 9
        removeNum(map, 'I', pq, 9, "NINE");

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        return sb.toString();
    }

    private static void removeNum(Map<Character, Integer> map, char c, PriorityQueue<Integer> pq, int number, String num) {
        int count = map.getOrDefault(c, 0);
        if (count != 0) {
            for (int i = 0; i < count; i++) pq.offer(number);
            remove(map, count, num);
        }
    }

    private static void remove(Map<Character, Integer> map, int count, String num) {
        for (int i = 0; i < num.length(); i++) {
            int cnt = map.getOrDefault(num.charAt(i), 0);
            map.put(num.charAt(i), cnt - count);
        }
    }
}
