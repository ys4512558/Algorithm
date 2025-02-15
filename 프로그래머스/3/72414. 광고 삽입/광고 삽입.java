import java.io.*;
import java.util.*;

class Solution {
    //play_time : 총 동영상 재생 시간
    //adv_time : 광고 재생 시간
    //logs : 동영상 시청 기록 [H1:M1:S1-H2:M2:S2]
    
    static int playTime, advTime;
    static int[][] log; //[logs[i]][start, end]형식
    static int SIZE;
    static long[] timeTable; //시간 테이블 (imos 알고리즘 사용하기 위함)
    static long[] prefixSum; //시간 테이블의 누적합 (구간합을 구하기 위함)
    public String solution(String play_time, String adv_time, String[] logs) {
        //일단 String이 불편하니 HHMMSS -> 모두 초로 환산
        init(play_time, adv_time, logs);
        int start = fillTable();
        String answer = convert(start - 1);
        return answer;
    }
    
    public void init(String play_time, String adv_time, String[] logs) {
        playTime = convert(play_time);
        advTime = convert(adv_time);
        // System.out.println("advTime = " + advTime);
        SIZE = logs.length;
        log = new int[SIZE][2];
        timeTable = new long[playTime + 3]; // 0 ~ playTime까지 써야함(+ 1) (imos 끝점은 [i + 1]--해줘야하므로 + 1) (0번 인덱스 비워야하므로 + 1)
        prefixSum = new long[playTime + 3];
        for(int i = 0; i < SIZE; i++) {
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            String start = st.nextToken();
            String end = st.nextToken();
            //1씩 밀어주기(인덱스 0을 빈공간으로 써야하기 때문)
            log[i][0] = convert(start) + 1;
            log[i][1] = convert(end) + 1;
            //IMOS 사용 (누적합)
            timeTable[log[i][0]]++;
            timeTable[log[i][1]]--;
            // System.out.println(log[i][0] + " " + (log[i][1] + 1));
            // System.out.println(timeTable[log[i][0]] + " " + timeTable[log[i][1]]);
        }
        // printLog();        
    }
    
    public int fillTable() {
        int start = 0; // 구간 합이 최대가 될때 시작 시간
        long max = 0;
        // prefixSum[0] = timeTable[0];
        for(int i = 1; i <= playTime + 1; i++) {
            timeTable[i] += timeTable[i - 1];
            prefixSum[i] = prefixSum[i - 1] + timeTable[i];
            if(i <= advTime) continue;
            long sum = prefixSum[i - 1] - prefixSum[i - advTime - 1];
            if(max < sum) { //구간합이 클때만 갱신 (같으면 작은게 남아야 하기 때문)
                max = sum;
                start = i - advTime;
            }
        }
        System.out.println(convert(start) + " - " + convert(start + advTime) + ", max = " +  convert(max));
        return start;
    }
    
    /**
    * 문자열을 초로 컨버팅 (단위 통일)
    */
    public int convert(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int H = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        return H * 3600 + M * 60 + S;
    }
    
    public String convert(long time) {
        long H = time / 3600; //몫
        String HH = H < 10 ? ("0" + H) : String.valueOf(H);
        time %= 3600;
        long M = time / 60;
        String MM = M < 10 ? ("0" + M) : String.valueOf(M);
        time %= 60;
        long S = time;
        String SS = S < 10 ? ("0" + S) : String.valueOf(S);
        return (HH + ":" + MM + ":" + SS); 
    }
}