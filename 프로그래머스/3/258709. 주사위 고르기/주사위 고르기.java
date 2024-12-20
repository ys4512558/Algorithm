import java.util.*;

class Solution {
    
    static int N;
    static int half;
    static int sel; //선택된 주사위의 비트 연산을 위한 변수
    static int selectedDice[]; //선택된 주사위 조합
    static int notSelectedDice[]; //선택되지 않은 주사위 조합
    
    //선택되지 않은 주사위의 점수 배열
    //하나의 선택 조합 (N = 4)이면 선택 주사위 1개의 조합당 6^2의 경우가 나오는데
    //미선택 주사위 조합은 선택 주사위의 모든 조합에 동일한 조합으로 비교가 됨 6^2의 조합을 반복적으로 계산하는 것은 효율X
    //즉 선택 조합 36개에 대해 미선택 36을 비교해야해서 36 * 36인데 여기서 뒤의 36은 매번 동일하므로 중복연산이라는 의미
    static int notSelectedScore[]; 
    
    static int scoreIdx;
    static int[] result;
    static int maxWin;
    static int win;
    static int[][] dices;
    static int score1; //선택된 애들의 점수 합
    static int score2; //선택되지 않은 애들의 점수 합
    static final int SIX = 6;
    
    public int[] solution(int[][] dice) {
        dices = dice;
        N = dice.length;
        half = N / 2;
        sel = 0;
        maxWin = 0;
        result = new int[half];
        selectedDice = new int[half];
        combination(0, 0);
        int[] answer = result;
        return answer;
    }
    
    //주사위 N/2개 뽑는 조합 로직
    public void combination(int start, int depth) {
        if(half == depth) { //N / 2만큼 뽑았다.
            //뽑은 N/2개를 통해 다시 주사위눈의 모든 조합 구하기
            //최대 6^5 (6개 중 1개뽑기가 5번 이므로)
            //하지만 선택되지 않은애도 해야함 6^5 * 6^5 = 6^10
            //선택된애 하나의 조합(1)당 선택되지 않은 애의 모든 조합(6^5) = 6^5
            //선택된애의 모든 조합에 대해 위의 경우를 해봐야함 6^5 * 6^5
            score1 = 0;
            score2 = 0;
            notSelectedDice = new int[half];
            int idx = 0;
            for(int i = 0; i < N; i++) {
                if((sel & 1 << i) != 0) continue;
                //선택되지 않은 것들 담기
                notSelectedDice[idx++] = i;
            }
            
            // for(int i = 0; i < N; i++) {
            //     if(!((sel & 1 << i) != 0)) System.out.print(i + " ");
            // }
            // System.out.println();
            
            scoreIdx = 0;
            //최대 조합의 수는 6 * N / 2
            int size = (int) Math.pow(SIX, half);
            notSelectedScore = new int[size];
            setNotSelectedScore(0, 0);
            Arrays.sort(notSelectedScore);
            win = 0;
            toDice(0, 0);
            
            if(win > maxWin) {
                maxWin = win;
                System.out.println("win : " + win);
                
                // System.out.println(win + " " + maxWin);
                for(int i = 0; i < half; i++) {
                    //출력을 위한 결과 배열에 selectedDice에 있는 인덱스 (0부터 시작하므로 + 1) 세팅
                    result[i] = selectedDice[i] + 1;
                }
                System.out.println();
                
                System.out.println("=================================");
                for(int i = 0; i < half; i++) {
                    System.out.print(result[i] + " ");
                }
                System.out.println();
                
                System.out.println("=================================");
                

            }
            return;
        }
        for(int i = start; i < N; i++) {
            int temp = sel; //켜기 전 비트 상태 저장
            selectedDice[depth] = i; //선택된 주사위 배열에 세팅
            sel |= 1 << i; //비트 연산으로 해당 위치 비트 켜기
            combination(i + 1, depth + 1);
            sel = temp; //켰던 것 다시 원상 복구
        }
    }
    
    //diceDepth : selectedDice에서의 어떤 주사위를 쓸지 선택
    //score: 그렇게 해서 더해진 주사위의 합이 몇인지
    //선택 or 미선택의 로직은 같으므로 flag를 통해 어떤 배열의 주사위를 사용할 것인지 분기
    public void toDice(int diceDepth, int score) {
        //선택된 주사위의 끝인 경우 N = 4 이면 2개 선택했으면
        //선택된 주사위의 조합에서 1가지 경우를 뽑은 것
        if(diceDepth == half) {
            //선택되지 않은 주사위 조합 점수 정렬
            // win += upperBound(score);
            win += counting(score);
            
            // System.out.println("score :" + score);
            // for(int i = 0; i < notSelectedScore.length; i++) {
            //     System.out.print(notSelectedScore[i] + " ");
            // }
            // System.out.println();
            // System.out.println("win : " + win);

            // //선택 주사위의 결과가 나왔으니(score매개변수) 이 값과 미선택 주사위 조합 점수 비교
            // if(flag) { //선택된 주사위의 조합이 완성되었으면 미선택 조합 만들기
            //     score1 = score;
            //     // 미선택 조합 만들기
            //     // toDice(0, 0, false);
            // } else { //선택되지 않은 경우 조합이 완성되었으면 비교
            //     //미선택 조합의 점수 vs score1(선택 주사위 조합 점수)
            //     if(score < score1) win++; //선택 주사위가 더 높으면 win ++
            // }
            return;
        }
        //주사위 안에서 눈 선택
        for(int i = 0; i < SIX; i++) {
            //선택 or 미선택의 로직은 같으므로 flag를 통해 어떤 배열의 주사위를 사용할 것인지 분기
            int idx = selectedDice[diceDepth];
            //선택 or 미선택 여부 그대로 넘겨주기
            //점수는 이전 점수 + 현재 선택한 주사위 눈 점수를 넘겨주기
            toDice(diceDepth + 1, score + dices[idx][i]);
        }
    }
    
    public void setNotSelectedScore(int diceDepth, int score) {
        if(diceDepth == half) {
            notSelectedScore[scoreIdx++] = score;
            return;
        }
        //주사위 안에서 눈 선택
        for(int i = 0; i < SIX; i++) {
            int idx = notSelectedDice[diceDepth];
            setNotSelectedScore(diceDepth + 1, score + dices[idx][i]);
        }
    }
    
    public int counting(int score) {
        int cnt = 0;
        for(int i = 0; i < notSelectedScore.length; i++) {
            if(notSelectedScore[i] < score) cnt++;
        }
        return cnt;
    }
    
    //score보다 같거나 큰 첫번째 수 = score의 승리 수 
    //(score보다 같거나 큰 첫번째 인덱스 - 1은 score보다 무조건 작으므로 이기는 경우임)
    public int upperBound(int score) {
        int l = 0;
        int r = notSelectedScore.length - 1;
        
        while(l < r) {
            int mid = (l + r) / 2;
            
            if(notSelectedScore[mid] <= score) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        int res = notSelectedScore[l];
        //score가 2이고
        //1 2 2 3 4 일때 1 2 [2] 3 4 이 선택된 경우 2번 승리로 카운팅 하면 안됨.
        //따라서 가장 왼쪽의 수를 찾아야함
        int index = l;
        for(int i = l; i >= 0; i--) {
            if(res != notSelectedScore[i]) break;
            index = i;
        }
        System.out.println("index : " + index);
        return index;
    }
}