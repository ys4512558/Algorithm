import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Set<String>> map;
    static Set<String> isv;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        String start = st.nextToken();
        
        map = new HashMap<>();
        isv = new HashSet<>();
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            int len = str.length();
            Set<String> set = map.getOrDefault(len, new TreeSet<>());
            set.add(str);
            map.put(len, set);
        }
        
        System.out.println(solution(start));
    }
    
    public static String solution(String start) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        isv.add(start);
        
        String result = start;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            if(size != 0) result = queue.peek();
            
            while(size-- > 0) {
                String str = queue.poll();
            
                int len = str.length();
            
                Set<String> set = map.get((len + 1));
                if(set == null) break;  
                for(String s : set) {
                    if(isValid(str, s, len) && isv.add(s)) {
                        queue.offer(s);
                    }
                }
            }
        }
        
        return result;
    }
    
    public static boolean isValid(String s, String o, int len) {
        int skip = 0; //넘어간 카운트 (다른 문자가 나타난 횟수)
        //(s와 o는 한자리 차이나는데 두번 이상 스킵하면 문자를 끼워넣어서 o를 못만듦)
        
        for(int i = 0; i < len; i++) {
            if(s.charAt(i) != o.charAt(i + skip)) {
                i--; 
                skip++;
            }                
            if(skip == 2) break;
        }
        return skip == 2 ? false : true;
    }
}
