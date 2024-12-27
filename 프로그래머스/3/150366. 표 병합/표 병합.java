import java.util.*;

class Solution {
    static final int N = 50;
    static int[] parents;
    //해당 flat한 셀이 가진 자식 (union-find의 반대)
    //이를 통해 unMerge를 수행하기 위함
    //기존 유니온 파인드는 부모를 기억하는데 unMerge를 위해서는 자식 정보가 필요함
    //최상위 부모를 찾고 부모에서 자식을 역추적하며 parents를 자기자신으로 바꿔주면 unMerger가 된다고 생각
    static List[] childs;
    static int[] ranks;
    static String[] values;
    static Map<String, Set<Integer>> valueMap;
    public String[] solution(String[] commands) {
        int idx = 0;
        valueMap = new HashMap<>();
        List<String> answerList = new ArrayList<>();
        parents = new int[N * N];
        childs = new ArrayList[N * N];
        ranks = new int[N * N];
        values = new String[N * N];
        
        //유니온 파인드 초기화
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int value = flat(i, j);
                childs[value] = new ArrayList<Integer>();
                parents[value] = value;
                ranks[value] = 1;
                values[value] = "";
            }
        }
        
        for(int i = 0; i < commands.length; i++) {
            StringTokenizer st = new StringTokenizer(commands[i]);
            String command = st.nextToken();
            
            if(command.equals("UPDATE")) {
                //남은 토큰 수로 어떤 업데이트인지 체크
                int cnt = st.countTokens();
                if(cnt == 3) {
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken()) - 1;
                    String value = st.nextToken();
                    update(r, c, value);
                } else {
                    String oldValue = st.nextToken();
                    String newValue = st.nextToken();
                    update(oldValue, newValue);
                }
            } else if(command.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;
                merge(r1, c1, r2, c2);
            } else if(command.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                unMerge(r, c);
            } else if(command.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                String value = print(r, c);
                answerList.add(value);
            }  
        }
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    //셀 값 업데이트
    //해당 셀 값을 value로 업데이트
    public void update(int r, int c, String value) {
        int rc = flat(r, c);
        int p = find(rc);
        values[p] = value;
        Set<Integer> set = valueMap.getOrDefault(value, new HashSet<>());
        set.add(p);
        valueMap.put(value, set);
    }
    
    //해당 값을 가진 모든 셀을
    //newValue로 업데이트
    //value : 위치 형태의 맵을 통해 List로 관리? Map<String, List<Integer>
    //이렇게 하며 값에 대해 리스트를 순회하며 values의 값과 비교하여 여전히 해당 값이라면 업데이트?
    //근데 그냥 업데이트가 많아서 시간이 터지려나??
    public void update(String oldValue, String newValue) {
        Set<Integer> oldSet = valueMap.getOrDefault(oldValue, new HashSet<>());
        Set<Integer> newSet = valueMap.getOrDefault(newValue, new HashSet<>());
    
        // for(int i = 0; i < N; i++) {
        //     for(int j = 0; j < N; j++) {
        //         int rc = flat(i, j);
        //         if(values[rc].equals(oldValue)) {
        //             values[rc] = newValue;
        //         }
        //     }
        // }
        for(int rc : oldSet) {
            int p = find(rc);
            if(values[p].equals(oldValue)) {
                newSet.add(p);
                values[p] = newValue;
            }
        }
        
        valueMap.put(oldValue, new HashSet<>());
        valueMap.put(newValue, newSet);
    }
    
    //r1, c1, r2, c2를 병합
    //같은 셀인 경우 : 무시
    //인접하지 않은 경우 : 해당 두 셀만 병합
    //두 셀 중 하나만 값을 가진 경우 : 해당 값을 세팅
    //두 셀 다 값을 가진 경우 : r1, c1의 값으로 세팅
    //의식의 흐름 : 그럼 이건 유니온 파인드로 부모만 알면 되겟다?
    //r1, c1, r2, c2중 어디를 접근해도 병합된 셀로 접근하니까    
    public void merge(int r1, int c1, int r2, int c2) {
        // System.out.println("Mergered");
        
        int p1 = find(flat(r1, c1));
        int p2 = find(flat(r2, c2));
        // System.out.println("p1 : " + p1);
        // System.out.println("p2 : " + p2);
        
        //이미 같은 경우는 스킵
        if(p1 == p2) return;
        String value = "";
        //r1, c1값이 있으면 그걸로 세팅
        if(!values[p1].equals("")) {
            value = values[p1];
            //r2, c2값은 없애주기
            values[p2] = "";
        } else { //r2, c2에만 값이 있으면 그걸로 세팅
            //r1, c1값은 없애주기
            value = values[p2];
            values[p1] = "";
        }
        //union 수행
        if(ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
            values[p2] = value;
            //자식으로 세팅
            childs[p2].add(p1);
            return;
        }
        ranks[p1] = ranks[p1] == ranks[p2] ? ranks[p1] + 1 : ranks[p1];
        values[p1] = value;
        parents[p2] = p1;
        //마찬가지로 자식으로 세팅
        childs[p1].add(p2);
        // printValue();
    }
    
    //병합 해제
    //해제 전 셀이 값을 가지고 있었다면 해당 값을 세팅
    //유니온 파인드 해제를 위해 추가적으로 저장해야한다.
    //유니온 파인드는 부모를 기억하는 것
    //그렇다면 반대의 경우도 저장해주어 역추적 가능하게 하면 unMerge가 가능하지 않을까?
    public void unMerge(int r, int c) {
        // System.out.println("unMergered");
        
        int rc = flat(r, c);
        //최상위 부모 찾기
        int p = find(rc);
        //기존에 병합된 값
        String value = values[p];
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(p);
        parents[p] = p;
        ranks[p] = 1;
        values[p] = "";
        
        //BFS로 자식을 모두 자기자신을 부모로 초기화
        while(!queue.isEmpty()) {
            int cell = queue.poll();
            
            List<Integer> childList = childs[cell];
            for(int i = 0; i < childList.size(); i++) {
                int child = childList.get(i);
                queue.offer(child);
                //자기자신을 부모로
                parents[child] = child;
                //랭크도 1
                ranks[child] = 1;
                //값도 빈값으로 초기화
                values[child] = "";
            }
            childs[cell] = new ArrayList<Integer>();
        }
        //값 세팅
        values[rc] = value;
    }
    
    //해당 셀의 값을 출력
    //비어 있다면 "EMPTY" 출력
    public String print(int r, int c) {
        int rc = flat(r,c);
        int p = find(rc);
        String value = values[p];
        // printValue();
        return value.equals("") ? "EMPTY" : value;   
    }
    
    //부모를 찾는 find 메서드 (유니온 파인드)
    public int find(int v) {
        if(parents[v] == v) return v;
        return parents[v] = find(parents[v]);
    }
    
    //r, c를 flat하게 사용하기 위한 메서드
    //의식의 흐름 : 2차원 배열에서 유니온 파인드를 하기 위해 flat이 더 효율적이라고 생각함
    public int flat(int r, int c) {
        return r * N + c;
    }
    
    public void printValue(){
        System.out.println("===============================");
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                int ij = flat(i, j);
                System.out.print((values[ij].equals("") ? "[ ]" : values[ij] )+ " ");
            }
            System.out.println();
        }
         System.out.println("------------------------------------");
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                int ij = flat(i, j);
                System.out.print((parents[ij]) + " ");
            }
            System.out.println();
        }
    }
}