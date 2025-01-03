import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Set<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjList = new Set[N + 1];
        //세명이 모두 친구여야함.
        for (int i = 1; i <= N; i++) {
            adjList[i] = new HashSet<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(B);
            adjList[B].add(A);
        }

        int result = Integer.MAX_VALUE;
        for (int A = 1; A <= N; A++) {
            int sizeA = adjList[A].size();
            //2보다 작으면 조건 성립 X
            if(sizeA < 2) continue;
            for(int B : adjList[A]) {
                int sizeB = adjList[B].size();
                if (sizeB < 2) continue;
                for(int C : adjList[B]) {
                    int sizeC = adjList[C].size();
                    if (!adjList[C].contains(A)) continue;
                    result = Math.min(result, sizeA - 2 + sizeB - 2 + sizeC - 2);
                }
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}