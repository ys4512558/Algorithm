import java.util.*;

class Solution {
    
    static int[][] skills, prob;
    static int alpMax, copMax, alpOffset, copOffset, alpOffsetMax, copOffsetMax;
    static int N;
    
    public int solution(int alp, int cop, int[][] problems) {
        N = problems.length;
        prob = problems;
        //일단 문제에서 가장 큰 알고력/코딩력을 각각 구한다.
        alpMax = alp;
        copMax = cop;
        for(int i = 0; i < N; i++) {
            alpMax = Math.max(alpMax, prob[i][0]);
            copMax = Math.max(copMax, prob[i][1]);
        }
        //능력치 값을 모두 최대로 초기화한다.
        //최대 151 * 151
        //풀면서 떠오른 주의사항 : 예를들어 알고력은 충분한데 코딩력이 부족한 경우
        //문제를 해결했을 때 알고력/코딩력이 모두 모두 증가하게 될때
        //문제의 최대 필요 수치를 기준으로 배열을 초기화했으므로 더 증가하면 ArrayOutOfBounds가 날 수 있을듯
        //최대값으로 맞춰주는 작업이 필요할듯
        
        //처음부터 다 풀 수 있으면 뒤 작업 안해도 됨
        if(alp >= alpMax && cop >= copMax) return 0;
        
        //오프셋을 둔 이유 : 현재 알고력/코딩력이 감소하지 않음
        //만약 초기 알고력 : 10, 코딩력 : 10이라면 0~9,0~9인덱스는 미사용 -> 낭비
        //오프셋을 통해 메모리 절약
        
        alpOffset = alp;
        copOffset = cop;
        /*
        alpOffsetMax = alpMax - alp;
        copOffsetMax = copMax - cop;
        skills = new int[alpOffsetMax + 1][copOffsetMax + 1];
        */
        skills = new int[alpMax + 1][copMax + 1];
        for(int i = 0; i <= alpMax; i++) {
            Arrays.fill(skills[i], Integer.MAX_VALUE);
        }
        // //문제에도 offset 적용
        // for(int i = 0; i < N; i++) {
        //     prob[i][0] -= alp;
        //     prob[i][1] -= cop;
        // }
        
        
        return dijkstra();
    }
    
    public int dijkstra() {
        PriorityQueue<Skill> pq = new PriorityQueue<>();      
        pq.offer(new Skill(alpOffset, copOffset, 0));
        //현재 알고력/코딩력을 초기화
        skills[alpOffset][copOffset] = 0;
        
        int result = 0;
        while(!pq.isEmpty()) {
            Skill skill = pq.poll();

             if(skill.alp == alpMax && skill.cop == copMax) {
                result = skill.cost;
                break;
            }
            // System.out.println(skill.alp + " " + skill.cop + " " + skill.cost);
            
            if(skills[skill.alp][skill.cop] < skill.cost) continue;

            for(int i = 0; i < N; i++) {
                //현재 풀 수 있는지 확인 (못풀면 스킵)
                if(skill.alp < prob[i][0] || skill.cop < prob[i][1]) continue;
                //풀었을 때 알고력/코딩력
                //최대값 - 오프셋이 배열 인덱스의 최대값이므로 어차피 이걸 넘어가는 능력은 의미가 없어서 상한 조절
                int nAlp = Math.min(alpMax, skill.alp + prob[i][2]);
                int nCop = Math.min(copMax, skill.cop + prob[i][3]);
                if(skills[nAlp][nCop] > skill.cost + prob[i][4]) {
                    skills[nAlp][nCop] = skill.cost + prob[i][4];
                    pq.offer(new Skill(nAlp, nCop, skills[nAlp][nCop]));
                }
            }
            
            int nAlp = Math.min(alpMax, skill.alp + 1);
            int nCop = Math.min(copMax, skill.cop + 1);
            
            //이미 최대이면 굳이 안해도됨
            if(skills[nAlp][skill.cop] > skill.cost + 1) {
                skills[nAlp][skill.cop] = skill.cost + 1;
                pq.offer(new Skill(nAlp, skill.cop, skill.cost + 1));
            }
            if(skills[skill.alp][nCop] > skill.cost + 1) {
                skills[skill.alp][nCop] = skill.cost + 1;
                pq.offer(new Skill(skill.alp, nCop, skill.cost + 1));
            }
        }
        return result;
    }
}

class Skill implements Comparable<Skill> {
    int alp, cop, cost;
    
    public Skill(int alp, int cop, int cost) {
        this.alp = alp;
        this.cop = cop;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Skill o) {
        return Integer.compare(this.cost, o.cost);
    }
}