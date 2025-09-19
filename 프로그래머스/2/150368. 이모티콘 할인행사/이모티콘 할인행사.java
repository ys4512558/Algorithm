/**
* 이모티콘마다 할인율은 10, 20, 30, 40% 중 하나로 설정
*/
import java.util.*;

class Solution {
    static final int disCountCnt = 4; //할인율 4가지
    static int[][] prices;
    static int n, m;
    static int[] disCounts;
    static int epCnt = 0;
    static int totalPrice = 0;
    static int[][] users;
    
    public int[] solution(int[][] users, int[] emoticons) {
        //1. 이모티콘의 할인율을 모두 설정하고 이를 유저에게 대입해서 최대를 모두 찾아보기 (브루트포스)
        //이때, 이모티콘의 할인율을 재계산 하지 않아야함 
        //10,10,10,10, 10,10,10,20일때 이모티콘 1,2,3은 다시 계산해도 똑같은 값이 나오니까 한번만 계산해서 사용하기
        //-> prices 배열에 [이모티콘 할인율] = 구매 가격으로 저장해두기
        n = users.length;
        m = emoticons.length;
        this.users = users;
        calcDisCountPrice(emoticons);
        
        disCounts = new int[m];
        recur(0);
        return new int[]{ epCnt, totalPrice };
    }
    
    public void calcDisCountPrice(int[] emoticons){
        prices = new int[m][disCountCnt];
        for(int i = 0; i < m; i++) { //각 이모티콘마다 할인된 가격을 계산
            for(int j = 1; j <= disCountCnt; j++) {
                prices[i][j - 1] = calcPrice(emoticons[i], j);
            }
        }
    }
    
    public void recur(int depth) {
        if(m == depth) {
            int ep = 0;
            int price = 0;
            //각 할인율을 기준으로 계산하기
            // printDisCountRate();
            for(int i = 0; i < n; i++) { //유저별로 계산
                int sum = 0;
                for(int j = 0; j < m; j++) {
                    if(disCounts[j] * 10 >= users[i][0]) sum += prices[j][disCounts[j] - 1]; 
                }
                if(sum >= users[i][1]) ep++;
                else price += sum;
                // System.out.println("user " + (i + 1) + " = " + "sum = " + sum);
            }
            if(ep > epCnt) {
                epCnt = ep;
                totalPrice = price;
            } else if(ep == epCnt) {
                totalPrice = Math.max(totalPrice, price);
            }
            // System.out.println("Emoticon Plus = " + ep + ", total = " + totalPrice);
            return;
        }
        
        for(int i = 1; i <= disCountCnt; i++) {
            disCounts[depth] = i;
            recur(depth + 1);
        }
    }
    
    public int calcPrice(int price, int disCountRate) {
        return price * (100 - (disCountRate * 10)) / 100;
    }
    
    public void printDisCountRate(){
        for(int i = 0; i < m; i++) {
            System.out.print(disCounts[i] + " ");
        }
        System.out.println();
    }
}