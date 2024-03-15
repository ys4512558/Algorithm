import java.io.*;
import java.util.*;

public class Main {

    final static int dx[] = {0, 0, -1, 1};
    final static int dy[] = {1, -1, 0, 0};

    static int R, C, answer = 0;
    static char map[][];
    static boolean visited[][];


    static final char GROUND = '.';
    static final char ICE = 'X';
    static final int L = 'L';

    static boolean stop = false;
    static void show()
    {
        System.out.println("--------------------------------");
        for(int i=0; i<R; ++i)
        {
            for(int j=0; j<C; ++j)
                System.out.print(map[i][j] + " ");
            System.out.println();
        }
        System.out.println("--------------------------------");

    }

    static boolean isValid(final int x, final int y)
    {
        if(x<0 || y<0 || x>=R || y>=C)
            return false;
        return true;
    }

    static class Pair
    {
        int x, y;

        public Pair(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }

    static Queue<Pair> q = new ArrayDeque<Pair>();
    static Queue<Pair> swan = new ArrayDeque<Pair>();

    static Pair L1, L2;

    static boolean meet()
    {
        Pair top;
        int x, y;
        Queue<Pair> nextQueue = new ArrayDeque<>();

        while(!swan.isEmpty())
        {
            top = swan.poll();

            for(int d=0; d<4; ++d)
            {
                x = top.x + dx[d];
                y = top.y + dy[d];


                if(x == L2.x && y == L2.y){
                    stop = true;
                    return true;
                }
                if(!isValid(x, y) || visited[x][y])
                    continue;

                if(map[x][y] == ICE)
                {
                    nextQueue.offer(new Pair(x, y));
                }
                else if(map[x][y] == GROUND)
                {
                    swan.offer(new Pair(x, y));
                }
                visited[x][y] = true;

            }
        }

        swan = nextQueue;
        return false;

    }

    static void melt()
    {
        int x, y;
        Pair top;

        // 일반 땅은 옆에 있는 얼음을 녹인다.
        int size = q.size();
        while(size-- > 0)
        {
            top = q.poll();
            for(int d=0; d<4; ++d)
            {
                x = top.x + dx[d];
                y = top.y + dy[d];

                if(!isValid(x, y))
                    continue;

                if(map[x][y] == ICE)
                {
                    map[x][y] = GROUND;
                    q.offer(new Pair(x, y));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];


        for(int i=0; i<R; ++i)
        {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; ++j)
            {
                if(map[i][j] == GROUND) q.add(new Pair(i, j));
                else if(map[i][j] == L)
                {
                    q.add(new Pair(i, j));
                    if(L1 == null) L1 = new Pair(i, j);
                    else  L2 = new Pair(i, j);
                }
            }
        }

        swan.offer(new Pair(L1.x, L1.y));
        visited[L1.x][L1.y] = true;
        while(true)
        {
            if(meet())
                break;
            melt();
            answer++;
        }

        System.out.print(answer);
    }

}