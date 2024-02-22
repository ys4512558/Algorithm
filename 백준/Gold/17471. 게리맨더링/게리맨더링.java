import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    static int[] arr, parents;
    static boolean[] isSelected;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

    static int N, areaCnt, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        areaCnt = N;
        arr = new int[N + 1];
        isSelected = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        makeSet();

        adjList.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            adjList.add(new ArrayList<>());
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(stk.nextToken());
            for (int j = 0; j < cnt; j++) {
                int area = Integer.parseInt(stk.nextToken());
                adjList.get(i).add(area);
                if (union(i, area)) {
                    areaCnt--;
                }
            }
        }

        if (areaCnt == 1) {
            oneArea(1);
            System.out.println(min);
        } else if (areaCnt == 2) {
            twoArea();
        } else {
            System.out.println(-1);
        }
    }

    private static void oneArea(int depth) {
        if (depth == N + 1) {
            if(!bfs()) return;
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 1; i <= N; i++) {
                sum1 = isSelected[i] ? sum1 + arr[i] : sum1;
                sum2 = !isSelected[i] ? sum2 + arr[i] : sum2;
            }
            min = Math.min(min, Math.abs(sum1 - sum2));
            return;
        }
        isSelected[depth] = true;
        oneArea(depth + 1);
        isSelected[depth] = false;
        oneArea(depth + 1);
    }

    private static boolean bfs() {
        Queue<Integer> queue1 = new ArrayDeque<>();
        Queue<Integer> queue2 = new ArrayDeque<>();
        boolean[] isv = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if(isSelected[i]) {
                queue1.offer(i);
                isv[i] = true;
                break;
            }
        }
        for (int i = 1; i <= N; i++) {
            if (!isSelected[i]) {
                queue2.offer(i);
                isv[i] = true;
                break;
            }
        }
        if(queue1.isEmpty() || queue2.isEmpty()) return false;

        while (!queue1.isEmpty()) {
            int v = queue1.poll();

            ArrayList<Integer> list = adjList.get(v);
            for (int i = 0; i < list.size(); i++) {
                int to = list.get(i);
                if (isv[to] || !isSelected[to]) continue;
                queue1.offer(to);
                isv[to] = true;
            }
        }
        while (!queue2.isEmpty()) {
            int v = queue2.poll();

            ArrayList<Integer> list = adjList.get(v);
            for (int i = 0; i < list.size(); i++) {
                int to = list.get(i);
                if (isv[to] || isSelected[to]) continue;
                queue2.offer(to);
                isv[to] = true;
            }
        }
        for (int i = 1; i <= N; i++) {
            if(!isv[i]) return false;
        }

        return true;
    }

    private static void twoArea() {
        int rep1 = -1;
        int rep2 = -1;
        for (int i = 1; i <= N; i++) {
            find(i);
        }
        for (int i = 1; i <= N; i++) {
            if(rep1 == -1){
                rep1 = find(i);
            }
            if (rep1 != -1 && rep2 == -1) {
                rep2 = find(i);
            }
            if(rep1 == rep2){
                rep2 = -1;
            }
        }
        //부모가 rep1과 같은 것 합
        int sum1 = 0, sum2 = 0;

        for (int i = 1; i <= N; i++) {
            if(parents[i] == rep1){
                sum1 += arr[i];
            } else{
                sum2 += arr[i];
            }
        }
        System.out.println(Math.abs(sum1 - sum2));
    }

    private static void makeSet(){
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int v){
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    private static boolean union(int v1, int v2){
        int rep1 = find(v1);
        int rep2 = find(v2);

        if(rep1 == rep2) return false;
        parents[rep2] = rep1;
        return true;
    }
}