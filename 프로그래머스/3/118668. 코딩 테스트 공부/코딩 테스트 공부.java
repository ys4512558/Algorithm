import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problemArray) {        
        int n = problemArray.length;
        Problem[] problems = new Problem[n];
        
        int maxAlpReq = 0; // 최대 필요한 알고력
        int maxCopReq = 0; // 최대 필요한 코딩력
        
        for(int i=0; i<n; i++) {
            problems[i] = new Problem(problemArray[i][0],
                                      problemArray[i][1],
                                      problemArray[i][2],
                                      problemArray[i][3],
                                      problemArray[i][4]);
            // 최대 필요 능력 업데이트
            maxAlpReq = Math.max(maxAlpReq, problems[i].alpReq);
            maxCopReq = Math.max(maxCopReq, problems[i].copReq);
        }
        
        // dp(i,j) : i알고력, j코딩력을 가질 수 있는 최소 시간
        // dp 값은 공부로 알고력을 올리거나 / 공부로 코딩력을 올리거나 / 문제를 풀거나
        // dp(i,j) = min(dp(i-1,j)+1, dp(i, j-1) + 1, dp(i-문제.필요알고력, j-문제.필요알고력)+문제.걸린 시간))

        //dp[i][j] = min(dp[i-1][j]+1, dp[i][j-1]+1, 
        //       dp[i - problem.alpReq][j - problem.copReq] + problem.cost)
        int[][] dp = new int[maxAlpReq+31][maxCopReq+31];
        
        // 짱큰값으로 초기화 : 능력이 최대 150이니까 151로 할까?
        for(int[] tmp : dp) {
            Arrays.fill(tmp, 151);
        }
        
        // 주어진 초기값까지 0으로 설정
        for(int i=0; i<=alp; i++) {
            for(int j=0; j<=cop; j++) {
                dp[i][j] = 0;
            }
        }
        
        // dp 채우자! 
        // i=alp, j=cop 으로 하면, maxAlpReq보다 초기값이 클때에 대한 처리가 안되더라구요,,
        for(int i=0; i<=maxAlpReq; i++) {
            for(int j=0; j<=maxCopReq; j++) {
                // 일단 공부로 알고력 올리기 vs 공부로 코딩력 올리기
                dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                
                // 문제 돌면서 순회
                for(Problem problem : problems) {
                    // 만약 현재 알고력/코딩력이 요구치 미만이면 패스
                    if(i < problem.alpReq || j < problem.copReq) continue;
                    int k = Math.min(i + problem.alpRwd, maxAlpReq);
                    int l = Math.min(j + problem.copRwd, maxCopReq);
                    dp[k][l] = Math.min(dp[k][l], dp[i][j] + problem.cost);
                }
            }
        }
        
        return dp[maxAlpReq][maxCopReq];
    }
    
    static class Problem {
        int alpReq; // 필요 알고력
        int copReq; // 필요 코딩력
        int alpRwd; // 증가 알고력
        int copRwd; // 증가 코딩력
        int cost;   // 풀이 시간
        
        public Problem(int alpReq, int copReq, int alpRwd, int copRwd, int cost) {
            this.alpReq = alpReq;
            this.copReq = copReq;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.cost = cost;
        }
    }
}