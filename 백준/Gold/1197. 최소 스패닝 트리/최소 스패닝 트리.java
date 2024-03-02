import java.io.*;
import java.util.*;

public class Main {
	static int[] parents, rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int E = N - 1;
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(from, to, weight));
		}
		
		makeSet(N);
		int w = 0;
		while(E > 0 && !pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.from, edge.to)) {
				E--;
				w += edge.weight;
			}
		}
		sb.append(w);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void makeSet(int N) {
		parents = new int[N+1];
		rank = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			rank[i] = -1;
		}
	}
	
	public static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	
	public static boolean union(int v1, int v2) {
		int r1 = find(v1);
		int r2 = find(v2);
		if(r1 == r2) return false;
		
		if(rank[v1] < rank[v2]) {
			parents[r1] = r2;
			return true;
		}
		parents[r2] = r1; 
		rank[r1] = rank[r1] == rank[r2] ? rank[r1] + 1 : rank[r1];   
		return true;
	}

}

class Edge implements Comparable<Edge>{
	int from, to, weight;
	
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
	
}