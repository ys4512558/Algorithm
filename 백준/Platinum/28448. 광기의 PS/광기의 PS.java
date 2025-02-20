import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Problem> pq = new PriorityQueue<>();

        long time = 0;        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            //5이하의 시간이 걸리면 푸는데 (걸리는 광기 - 해결 후 해소된 광기) == 0
            //따라서 그냥 시간만 더해주기
            if(T <= 5) { 
                time += T;
                continue;
            }
            pq.offer(new Problem(K, T));
        }
        
        int current = 0;
        
        //광기 해소가 무조건 손해인 경우만
        while(!pq.isEmpty()) {
            Problem problem = pq.poll();
            
            int mul = problem.mul();
            //L안넘으면서 문제 풀기 가능한지 확인
            if(L >= mul + current) {
                current += (mul - (problem.K * 5));
                //시간 더하기
                time += problem.T;
                continue;
            }
            //현재 광기로 못풀면 줄이기
            int addTime = current - (L - mul);
            //어차피 최대 광기에서 문제 풀어서 해소된 만큼이 현재
            current = L - (problem.K * 5);
            time += problem.T + addTime;
        }
        System.out.println(time);
    }
}

class Problem implements Comparable<Problem> {
    int K, T;
    
    public Problem(int K, int T) {
        this.K = K;
        this.T = T;
    }
    
    public int mul() {
        return this.K * this.T;
    }
    
    public int compareTo(Problem o) {
        if(this.K == o.K) return Integer.compare(o.T, this.T);
        return Integer.compare(o.K, this.K);
        // int madness1 = this.K * (this.T - 5);   
        // int madness2 = o.K * (o.T - 5);
        // //광기가 똑같이 해소되면 해결 도중 쌓이는 광기가 더 작을 때 우선
        // //확실하진 않지만 더 작은애를 먼저 해야 대기 시간이 줄어들듯 (이후 그만큼 다른애가 더 기다려야할지도 모르겠음)
        // if(madness1 == madness2) return Integer.compare(this.K * this.T, o.K * o.T);
        // //최종적으로 해소된 광기까지 해서 값이 작을 수록 이득 (대기시간이 줄어듦)
        // //광기 10얻고 5해소 스택 5
        // //광기 20얻고 18해소 스택 2 (스택이 적을수록 그 다음 애들이 기다릴 시간이 줄어듦) 
        // return Integer.compare(madness1, madness2);
    }
}