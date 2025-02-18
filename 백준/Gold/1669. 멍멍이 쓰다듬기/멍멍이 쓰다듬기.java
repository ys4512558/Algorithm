import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        
        long diff = Y - X;
        //1(1^2) = 1 (제곱 수 이면 + 1일 해야함) 1
        //2 = 2 : 3 = 3 (1개 커버 가능 2) 2
        //4(2^2) = 3 (제곱 수 이면 + 1일 해야함) 3 
        //5 = 4 : 7 = 5 (2개 커버 가능 5, 6) 
        //9(3^2) = 5 (제곱 수 이면 + 1일 해야함)
        //10 = 6 : 13 = 7 (3개 커버 가능 10, 11, 12)
        //16(4^2) = 7 (제곱 수 이면 + 1일 해야함)
        //17 = 8 : 21 = 9 (4개 커버 가능 17, 18, 19, 20)
        //if 제곱수 -> 다음수 + 1일
        //제곱 1(+1) -> 1개 2(+1) -> 1개 3 
        //제곱 4(+1) -> 2개 5, 6(+1) -> 2개 7, 8 
        //제곱 9(+1) -> 3개 10, 11, 12(+1) -> 3개 13, 14, 15 
        //제곱 16(+1)-> 4개 17, 18, 19, 20(+1) -> 4개 21, 22, 23, 24 
        //제곱 25(+1)
        
        //상한 제곱 찾기
        //21억은 N^2  47000^2 약 5만 번
        
        long MAX = (long) Math.ceil(Math.sqrt(diff));
        long day = 1;
        long cnt = 1; //커버 가능 개수
        for(long i = 1; i <= MAX; i++) {
            long pow = (long) Math.pow(i, 2);
            //차이가 제곱 수 이면 그만
            if(pow == diff) break;
            // System.out.println("제곱, day = " + day);
            day++; //제곱수 일때 ++해야함
            //제곱 수  + 커버 가능 개수 안에 들어오면 그만
            if(diff <= pow + cnt) break;
            //커버 못하면 하루 더 있어야함.
            // System.out.println("pow + cnt = " + (pow + cnt));
            day++;
            if(diff <= pow + cnt + cnt) break;
            //커버 가능 수 증가
            cnt++;
        }
        System.out.println(MAX == 0 ? 0 : day);
    }
}
