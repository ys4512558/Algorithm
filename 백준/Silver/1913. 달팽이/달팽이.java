import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;
    static int N, M;
    static int X, Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        arr = new int[N][N];
        int x = N / 2;
        int y = N / 2;
        int pre = 1;
        X = -1;
        Y = -1;
        arr[x][y] = pre;
        //1x1,3x3,5x5 ... 
        for(int i = 3; i <= N; i += 2) {
            int[] next = fill(x, y, i, pre);
            x = next[0];
            y = next[1];
            pre = next[2];
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == M) {
                    X = i + 1;
                    Y = j + 1;
                }
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append(X + " " + Y);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    //pre = 이전 사각형의 마지막 수
    public static int[] fill(int x, int y, int size, int pre) {
        //한칸 위 + 오른쪽으로 채우기
        arr[--x][y] = ++pre;
        
        for(int i = 0; i < size - 2; i++) {
            arr[x][++y] = ++pre;
        }
        
        //오른쪽 아래로 채우기
        for(int i = 0; i < size - 1; i++) {
            arr[++x][y] = ++pre;
        }
        
        //오른쪽 아래에서 왼쪽 아래로 채우기
        for(int i = 0; i < size - 1; i++) {
            arr[x][--y] = ++pre;
        }
        
        //왼쪽 아래에서 왼쪽 위로 채우기
        for(int i = 0; i < size - 1; i++) {
            arr[--x][y] = ++pre;
        }
        // print();
        return new int[]{x, y, pre};
    }
    public static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == M) {
                    X = i + 1;
                    Y = j + 1;
                }
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        System.out.println();
    }
}
