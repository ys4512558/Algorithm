import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int[][][] map;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    //물고기가 살아있는지 확인하기 위한 map + 살아있다면 물고기 정보를 사용하며, 이동 시 이를 변경함
    static Map<Integer, Fish> fishMap;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[4][4][2];
        //키를 작은 것부터 꺼내기 위해 TreeMap 구현체 사용
        fishMap = new TreeMap<Integer, Fish>();

        //맵 복사 대신 fish
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                map[i][j][1] = Integer.parseInt(st.nextToken()) - 1;
                fishMap.put(map[i][j][0], new Fish(i, j, map[i][j][1]));
            }
        }

        //0, 0물고기 없애기
        fishMap.remove(map[0][0][0]);
        //x, y, dir, cost
        int[] shark = new int[]{0, 0, map[0][0][1], map[0][0][0]};
        map[0][0][0] = -1;
        simulation(shark, map, fishMap, 0);
        System.out.println(max);
    }

    private static void simulation(int[] shark, int[][][] map, Map<Integer, Fish> fishMap, int depth) {
        fishMove(map, fishMap, shark);

        int mul = 1;
        while (true) {
            int nx = shark[0] + (dx[shark[2]] * mul);
            int ny = shark[1] + (dy[shark[2]] * mul);

            //경계를 나가거나 물고기가 없으면 못간다.
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
                max = Math.max(max, shark[3]);
                break;
            }
            mul++;
            if (map[nx][ny][0] == -1) {
                continue;
            }
            Map<Integer, Fish> copyMap = copyFishMap(fishMap);
            int[][][] copy = copyMap(map);
            int cost = shark[3] + copy[nx][ny][0];
            //물고기 잡아먹기
            copyMap.remove(copy[nx][ny][0]);
            copy[nx][ny][0] = -1;

            simulation(new int[]{nx, ny, copy[nx][ny][1], cost}, copy, copyMap ,depth+1);
        }
    }

    private static void fishMove(int[][][] map, Map<Integer, Fish> fishMap, int[] shark) {
        //트리 맵 구현체 이므로 RBTree -> 작은 것부터 (기본 오름차순)
        for (Integer key : fishMap.keySet()) {
            Fish fish = fishMap.get(key);

            while (true){
                int nx = fish.x + dx[fish.dir];
                int ny = fish.y + dy[fish.dir];
                if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == shark[0] && ny ==shark[1])) {
                    //반시계 -> 인덱스 ++
                    fish.dir = (++fish.dir) % 8; //모듈러로 인덱싱 범위내로 넣기
                    continue;
                }
                int[] temp = new int[]{map[nx][ny][0], map[nx][ny][1]};
                //이동 위치에 물고기 존재 하면
                Fish target = fishMap.get(temp[0]);
                if (target != null) {
                    target.x = fish.x;
                    target.y = fish.y;
                }
                map[nx][ny][0] = map[fish.x][fish.y][0];
                map[nx][ny][1] = fish.dir;
                map[fish.x][fish.y][0] = temp[0];
                map[fish.x][fish.y][1] = temp[1];
                fish.x = nx;
                fish.y = ny;
                break;
            }
        }
    }

    private static Map<Integer, Fish> copyFishMap(Map<Integer, Fish> fishMap) {
        Map<Integer, Fish> copy = new TreeMap<>();
        for (Integer key : fishMap.keySet()) {
            //
            Fish fish = fishMap.get(key);
            Fish copyFish = new Fish(fish.x, fish.y, fish.dir);
            copy.put(key, copyFish);
//            아래처럼 하면 객체의 참조값 복사라서 얕은 복사 -> 문제 생긴다. (주의)
//            copy.put(key, fish);
        }

        return copy;
    }

    public static int[][][] copyMap(int[][][] map){
        int[][][] copy = new int[4][4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 2; k++) {
                    copy[i][j][k] = map[i][j][k];
                }
            }
        }
        return copy;
    }
}

class Fish {

    int x, y, dir;
    public Fish(int x, int t, int dir) {
        this.x = x;
        this.y = t;
        this.dir = dir;
    }
}