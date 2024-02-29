import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0, -1, 0, 1, 0};
	static int[] dy = {0, 0, 1, 0, -1};

	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		
	}
	//---------
	static int max;
	static int[][] map;
	static int[][] count;
	static Map<Pair, ArrayList<Integer>> duplicates;
	static ArrayList<Integer> powerList;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			init();
			sb.append(solve()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void init() {
		max = 0;
		map = new int[10][10];
		duplicates = new HashMap<>();
		count = new int[10][10];
		powerList = new ArrayList<>();
	}

	private static int solve() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		
		int[] moveA = new int[M + 1];
		int[] moveB = new int[M + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			moveA[i] = Integer.parseInt(st.nextToken());
		}
		

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			moveB[i] = Integer.parseInt(st.nextToken());
		}
		
		//차지 영역 bfs
		powerList.add(0);
		for (int i = 1; i <= A; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(stk.nextToken()) - 1;
			int row = Integer.parseInt(stk.nextToken()) - 1;
			int breadth = Integer.parseInt(stk.nextToken());
			int power = Integer.parseInt(stk.nextToken());
			
			powerList.add(power);
			bfs(row, col, breadth, i);
		}
		
		Pair person1 = new Pair(0, 0);
		Pair person2 = new Pair(9, 9);
		
		for (int i = 0; i <= M; i++) {
			go(person1, moveA[i], person2, moveB[i]);
			chargable(person1, person2);
		}
		
		return max;
	}

	private static void chargable(Pair person1, Pair person2) {
		ArrayList<Integer> list1 = duplicates.get(person1);
		ArrayList<Integer> list2 = duplicates.get(person2);
		
		if(list1 == null && list2 == null) {
			if(map[person1.x][person1.y] == map[person2.x][person2.y]) {
				max += (powerList.get(map[person1.x][person1.y]));
				return;
			}
			max += (powerList.get(map[person1.x][person1.y]))
					+ (powerList.get(map[person2.x][person2.y]));
		} else if(list1 != null && list2 != null) { //둘 다 중복되는 영역에 있다.	
			if(person1.equals(person2)) {
				int[] p = getMax(list1);
				max += p[1] + p[3];
				return;
			}
			
			int[] p1 = getMax(list1);
			int[] p2 = getMax(list2);
			if(p1[0] == p2[0]) {
				max += Math.max(p1[3], p2[3]) + p1[1];
			} else {
				max += p1[1] + p2[1];
			}
			
		} else if(list1 != null) {
			int[] p = getMax(list1);
			if(p[0] == map[person2.x][person2.y]) {
				max += p[3] + powerList.get(map[person2.x][person2.y]);
				return;
			}
			max += getMax(list1)[1] + powerList.get(map[person2.x][person2.y]);
		} else if(list2 != null) {
			int[] p = getMax(list2);
			if(p[0] == map[person1.x][person1.y]) {
				max += p[3] + powerList.get(map[person1.x][person1.y]);
				return;
			}
			max += getMax(list2)[1] + powerList.get(map[person1.x][person1.y]);
		}
		
	}
	
	private static int[] getMax(ArrayList<Integer> list) {
		int maxVal1 = 0;
		int maxIdx1 = 0;
		
		int maxVal2 = 0;
		int maxIdx2 = 0;
		
		for (int i = 0; i < list.size(); i++) {
			int power = powerList.get(list.get(i));
			if(maxVal1 < power) {
				maxVal2 = maxVal1;
				maxIdx2 = maxIdx1;
				
				maxVal1 = power;
				maxIdx1 = list.get(i);
			} else if(maxVal2 < power) {
				maxVal2 = power;
				maxIdx2 = list.get(i);
			}
		}
		return new int[]{maxIdx1, maxVal1, maxIdx2, maxVal2};
	}

	private static void go(Pair person1, int dir1, Pair person2, int dir2) {
		person1.x += dx[dir1];
		person1.y += dy[dir1];
		
		person2.x += dx[dir2];
		person2.y += dy[dir2];
	}

	private static void bfs(int row, int col, int breadth, int chargerNum) {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] isv = new boolean[10][10];
		queue.offer(new int[]{row, col, 0});
		isv[row][col] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			
			if(point[2] > breadth) break;

			
			count[point[0]][point[1]]++;
			
			if(count[point[0]][point[1]] > 1) {
				ArrayList<Integer> list = duplicates.get(new Pair(point[0], point[1]));
				if(list != null) {
					list.add(chargerNum);
				} else {
					Pair temp = new Pair(point[0], point[1]);
					duplicates.put(temp, new ArrayList<Integer>());
					duplicates.get(temp).add(map[point[0]][point[1]]);
					duplicates.get(temp).add(chargerNum);
					map[point[0]][point[1]] = chargerNum;
				}
			} else {
				map[point[0]][point[1]] = chargerNum;	
			}
			
			for (int i = 1; i < 5; i++) {
				int x = point[0] + dx[i];
				int y = point[1] + dy[i];
				
				if(x < 0 || x >= 10 || y < 0 || y >= 10 || isv[x][y]) continue;
				isv[x][y] = true;
				queue.offer(new int[] {x, y, point[2] + 1});
			}
		}
	}
}