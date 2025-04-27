import java.io.*;
import java.util.*;

public class Main {
    static Map<String, String> map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        init();
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < N; i++) {
            sb.append(map.get(br.readLine())).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    
    public static void init() {
        map = new HashMap<>();
        map.put("Algorithm", "204");
        map.put("DataAnalysis", "207");
        map.put("ArtificialIntelligence", "302");
        map.put("CyberSecurity", "B101");
        map.put("Network", "303");
        map.put("Startup", "501");
        map.put("TestStrategy", "105");
    }
}
