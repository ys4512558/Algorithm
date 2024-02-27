import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        UnionFind uf = new UnionFind();
        uf.makeSet(N);

        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        for (int i = 0; i < size; i++) {
            uf.union(0, Integer.parseInt(st.nextToken()));
        }

        ArrayList<Integer> party = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            for (int j = 1; j < size; j++) {
                uf.union(first, Integer.parseInt(st.nextToken()));
            }
            party.add(uf.find(first));
        }
        int cnt = M;
        for (int i = 0; i < M; i++) {
            if(uf.find(party.get(i)) == 0){
                cnt--;
            }
        }

        System.out.println(cnt);
    }
}

class UnionFind{
    static int[] parents, rank;

    public void makeSet(int N){
        parents = new int[N + 1];
        rank = new int[N + 1];
        rank[0] = N;
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }
    public int find(int v){
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }

    public boolean union(int v1, int v2){
        int rep1 = find(v1);
        int rep2 = find(v2);
        if (rep1 == rep2) return false;

        if(rank[rep1] < rank[rep2]){
            parents[rep1] = rep2;
            return true;
        }
        parents[rep2] = rep1;
        rank[rep1] = rank[rep1] == rank[rep2] ? rank[rep1] + 1 : rank[rep1];

        return true;
    }
}