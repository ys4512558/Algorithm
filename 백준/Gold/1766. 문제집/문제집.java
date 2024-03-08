import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	static int[] counts;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		counts = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			adjList.get(first).add(second);
			counts[second]++;
		}
		topologySort();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void topologySort() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 1; i <= N; i++) {
			if(counts[i] == 0) pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			int num = pq.poll();
			
			sb.append(num + " ");
			
			ArrayList<Integer> list = adjList.get(num);
			for (int i = 0; i < list.size(); i++) {
				if(--counts[list.get(i)] == 0) pq.offer(list.get(i));
			}
		}
		
	}
}