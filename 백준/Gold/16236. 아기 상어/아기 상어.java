import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static Shark shark = null;
	static int time = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
	
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) shark = new Shark(i, j, 2, 2); 
			}
		}
		
		bfs();
		
		sb.append(time);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	private static void bfs() {
		boolean[][] isv = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {shark.x, shark.y, 0});
		isv[shark.x][shark.y] = true;
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2)->{
			if(o1[0] == o2[0]) {
				return Integer.compare(o1[1], o2[1]);
			}
			return Integer.compare(o1[0], o2[0]);
		});
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				int[] p = queue.poll();
				int curX = p[0];
				int curY = p[1];
				int breadth = p[2];
				
				for (int i = 0; i < 4; i++) {
					int x = curX + dx[i];
					int y = curY + dy[i];
					
					if(x < 0 || x >= N || y < 0 || y >= N || isv[x][y] || shark.size < map[x][y]) continue;				
					if(map[x][y] != 0 && map[x][y] < shark.size) pq.offer(new int[] {x, y, breadth+1});
					queue.offer(new int[] {x, y, breadth + 1});
					isv[x][y] = true;
				}
			}
			if(pq.isEmpty()) continue;
			int[] feed = pq.poll();
			
			if(map[feed[0]][feed[1]] != 0 && map[feed[0]][feed[1]] < shark.size) {
				shark.eat();
				map[shark.x][shark.y] = 0; 
				shark.x = feed[0];
				shark.y = feed[1];
				map[shark.x][shark.y] = 9;
				time += feed[2];
				queue = new ArrayDeque<int[]>();
				queue.offer(new int[] {shark.x, shark.y, 0});
				pq = new PriorityQueue<int[]>((o1, o2)->{
					if(o1[0] == o2[0]) {
						return Integer.compare(o1[1], o2[1]);
					}
					return Integer.compare(o1[0], o2[0]);
				});
				isv = new boolean[N][N];
				isv[shark.x][shark.y] = true;
			}
		}
	}
}

class Shark{
	int x, y, size, feed;
	
	public Shark(int x, int y, int size, int feed) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.feed = feed;
	}
	
	public void eat() {
		if(--feed == 0) {
			size++;
			feed = size;
		}
	}
}