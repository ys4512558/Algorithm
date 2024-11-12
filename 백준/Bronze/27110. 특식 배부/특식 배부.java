import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int result=0;

        int[] num = new int[3];
        for(int i=0;i<num.length;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<num.length;i++){
            if(num[i]<=N){
                result += num[i];
            }else{
                result += N;
            }
        }

        System.out.println(result);
    }
}