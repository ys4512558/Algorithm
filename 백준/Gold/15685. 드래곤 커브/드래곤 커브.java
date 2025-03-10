import java.util.*;
import java.io.*;

//0
//0 -> 1
//1 -> 2, 0 -> 1
//1 -> 2, 2 -> 3, 1 -> 2, 0 -> 1
//이전 이동 경로의 역순에 90도 ((dir + 1) % 4)

public class Main {

    static int MAX = 100;
    static int N;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new boolean[MAX + 1][MAX + 1];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            Info info = new Info(y, x, d, g);
            info.makeCurve(map);
        }

        int count = 0;
        for(int i = 0; i < MAX; i++) {
            for(int j = 0; j < MAX; j++) {
                if(map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) count++;
            }
        }

        System.out.println(count);
    }

}

class Info {
    int x, y, d, g;
    Stack<Integer> history;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public Info(int x, int y, int d, int g) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.g = g;
        this.history = new Stack<>();
    }

    public void makeCurve(boolean[][] map) {
        //0세대 이동 (초기 이동 방향 세팅)
        history.push(d);
        map[this.x][this.y] = true;
        moving(map, d);

        //g번의 커브 이동
        for(int i = 1; i <= g; i++) {
            //이전 이동을 그대로 스택의 밑에 깔아줘야함.
            Stack<Integer> temp = new Stack<>();
            //현재 세대에서 추가되는 이동은 이전 이동의 역순에서 하나씩 꺼내서 90도 해준 것을 순서대로 넣어줘야함
            Queue<Integer> queue = new ArrayDeque<>();
            while(!history.isEmpty()) {
                //마지막 이동부터 처음 이동까지 역순으로 확인
                int dir = history.pop();
                temp.push(dir); //현재 위치 다시 스택에 쌓기 (history의 순서 보장을 위해)
                queue.offer((dir + 1) % 4); //90도 회전 방향 queue에 넣기
            }
            //기존 history 그대로 복구
            while(!temp.isEmpty()) {
                history.push(temp.pop());
            }
            //기존 이동 경로를 90도 돌린 애들은 그대로 스택에 넣어주기
            while(!queue.isEmpty()) {
                //큐 순서대로 이동시키기
                int dir = queue.poll();
                moving(map, dir);
                //다음 세대의 커브 만들기 위해 이동 내역 저장
                history.push(dir);
            }
        }
    }

    public void moving(boolean[][] map, int dir) {
        //스택에서 빼야하니까 현재 이동경로 그대로 복구해야함.
        x += dx[dir];
        y += dy[dir];
        map[x][y] = true;
    }

}
