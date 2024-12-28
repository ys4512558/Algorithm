import java.util.*;

class Solution {
    static final int MAX_SIZE = 50; // 표 최대 크기
    
    static String[][] graph; // 표
    static int[] parent; // 부모 노드 표시(유니온 파인드)
    
    public String[] solution(String[] commands) {
        graph = new String[51][51];
        // 인덱스 : (r, c) -> r*100 + c
        // 5050 까지 인덱싱 해야돼서(0101: (1, 1), 5050 : (50, 50))
        parent = new int[5051];
        
        // parent 초기화
        init();
        
        List<String> answers = new ArrayList<>();
        
        for(String command : commands) {
            String[] strings = command.split(" ");
            String majorCommand = strings[0]; // UPDATE, MERGE, UNMERGE, PRINT
            
            int r, c, cellParentIdx, cellIndex;
            String value;
            
            switch(majorCommand) {
                case "UPDATE":
                    // UPDATE value1 value2 케이스
                    if(strings.length == 3) {
                        String value1 = strings[1];
                        String value2 = strings[2];

                        updateValue(value1, value2);
                    }
                    // UPDATE r c value 케이스
                    else {
                        r = Integer.parseInt(strings[1]);
                        c = Integer.parseInt(strings[2]);
                        value = strings[3];

                        cellParentIdx = find(position2Index(r, c));
                        int[] parentPosition = index2Position(cellParentIdx);
                        
                        // 표 바꾸기
                        graph[parentPosition[0]][parentPosition[1]] = value;
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(strings[1]);
                    int c1 = Integer.parseInt(strings[2]);
                    int r2 = Integer.parseInt(strings[3]);
                    int c2 = Integer.parseInt(strings[4]);
                    
                    merge(r1, c1, r2, c2);
                    break;
                case "UNMERGE":
                    r = Integer.parseInt(strings[1]);
                    c = Integer.parseInt(strings[2]);

                    // 해제
                    disunion(r, c);
                    break;
                case "PRINT":
                    r = Integer.parseInt(strings[1]);
                    c = Integer.parseInt(strings[2]);
                    
                    String answer = findAnswer(r, c);
                    
                    // answers 리스트에 삽입
                    answers.add(answer);
                    break;
            }
            print(command);
            // printReal(command);
        }
        
        // 정답 배열 만들기
        String[] answerArray = new String[answers.size()];
        for(int i=0; i<answers.size(); i++) {
            answerArray[i] = answers.get(i);
        }
        return answerArray;
    }
    
    /**
    * 2번 케이스. UPDATE value1 value2 
    * graph에서 value1을 value2로 바꾸기
    **/
    static void updateValue(String value1, String value2) {
        for(int i=1; i<=MAX_SIZE; i++) {
            for(int j=1; j<=MAX_SIZE; j++) {
                if(graph[i][j] == null) continue;
                if(graph[i][j].equals(value1)) {
                    graph[i][j] = value2;
                }
            }
        }
    }
    
    /**
    * parent 배열 초기화
    **/
    static void init() {
        // 0101 이 가장 작은 값이니((1,1)일 때의 값이므로) 이 값부터 초기화
        for(int i=101; i<parent.length; i++) {
            if(i%100 > 50) continue; // 51부터는 표 크기 벗어나니까 초기화 안해도 됨
            parent[i] = i;
        }
    }
    
    /**
    * value 찾기
    **/
    static String findValue(int v) {
        // 1. 루트 인덱스 찾기
        int rootIndex = find(v);
        // 2. 루트에 해당하는 표의 좌표 찾기
        int[] rootPosition = index2Position(rootIndex);
        // 3. 해당 좌표의 값 반환
        return graph[rootPosition[0]][rootPosition[1]];
    }
    
    /**
    * find 함수 : v 인덱스에 대해 루트 노드 찾기
    **/
    static int find(int v) {
        if(parent[v] == v) {
            return v;
        } else {
            return parent[v] = find(parent[v]);
        }
    }
    
    /**
    * 유니온
    **/
    static boolean union(int indexToMerge, int indexToBeMerged) {
        int aRoot = find(indexToMerge); // a의 루트 노드
        int bRoot = find(indexToBeMerged); // b의 루트 노드
        
        if(aRoot == bRoot) { // 루트 노드가 같으면 union 할 거 없음
            return false;
        }
        
        int[] bRootPosition = index2Position(bRoot);
        graph[bRootPosition[0]][bRootPosition[1]] = null;
        
        parent[bRoot] = aRoot; // a에 병합하기
        return true;
    }
    
    /**
    * A 와 B 병합
    **/
    static void merge(int r1, int c1, int r2, int c2) {
        // r1, c1 에 해당하는 값
        String value1 = findValue(position2Index(r1, c1));
        // r2, c2 에 해당하는 값
        String value2 = findValue(position2Index(r2, c2));
        
        // 병합하는 아이의 인덱스, 병합되는 아이의 인덱스
        int indexToMerge, indexToBeMerged;
        // value1이 없고, value2가 있을 경우 : cell2가 병합하는 친구, cell1이 병합되는 친구
        if(value1 == null && value2 != null) {
            indexToMerge = position2Index(r2, c2);
            indexToBeMerged = position2Index(r1, c1);
        } else { // 그 외에는 cell1이 병합하는 친구, cell2이 병합되는 친구
            indexToMerge = position2Index(r1, c1);
            indexToBeMerged = position2Index(r2, c2);
        }
        // 유니온
        union(indexToMerge, indexToBeMerged);
    }
    
    /**
    * index에 대해 해제
    * index의 루트 노드를 가리키고 있는 애들을 본인을 바라보게 하기
    **/
    static void disunion(int r, int c) {
        int index = position2Index(r, c);
        int root = find(index); // 루트 노드 인덱스
        
        int[] rootPosition = index2Position(root); // 루트 노드의 좌표
        String value = graph[rootPosition[0]][rootPosition[1]]; // 루트 노드의 표 값
        graph[rootPosition[0]][rootPosition[1]] = null; // 루트 노드가 가리키는 표 값 null 로 바꾸기
        
        for(int i=101; i<parent.length; i++) {
            // 51부터는 표 크기 벗어나니까 대상이 아님(51일테니까 거기에 50을 더해서 다음 단계 검색)
            // 예시) 1051(10,51) -> 1101(11, 1)
            if(i%100 > 50) {
                i += 50;
            };
            // 경로 압축 진행
            find(i);
        }
        
        for(int i=101; i<parent.length; i++) {
            // 51부터는 표 크기 벗어나니까 대상이 아님(51일테니까 거기에 50을 더해서 다음 단계 검색)
            // 예시) 1051(10,51) -> 1101(11, 1)
            if(i%100 > 50) {
                i += 50;
            };            
            // root 노드를 가리키고 있는 애들을 바꾸기
            if(find(i) == root) {
                parent[i] = i;
            }
        }
        
        // 병합 해제 할 때, 이미 값이 있으면 r, c에 해당 값 넣기
        if(value != null)
            graph[r][c] = value;
    }
    
    /**
    * parent의 index에 해당하는 셀의 값 반환
    * 만약 null이면 EMPTY 넣기
    **/
    static String findAnswer(int r, int c) {
        int index = position2Index(r, c);
        int root = find(index);
        int[] rootPosition = index2Position(root); // 루트 노드의 좌표
        String answer = graph[rootPosition[0]][rootPosition[1]]; // 루트 노드의 표 값
        
        return answer == null ? "EMPTY" : answer;
    }
    
    /**
    * (r, c)로 parent 배열의 인덱스 만들기
    * (r, c) -> r * 100 + c 로 하면 r,c 에 대해 안 겹치고 인덱스 만들기 가능
    **/
    static int position2Index(int r, int c) {
        return r * 100 + c;
    }
    
    /**
    * parent 배열 인덱스를 (r, c) 로 바꾸기
    **/
    static int[] index2Position(int index) {
        int[] res = new int[2];
        res[0] = index / 100;
        res[1] = index % 100;
        return res;
    }
    
    static void print(String command) {
        System.out.println(command + ": ----------출력---------");
        for(int i=1; i<=4; i++) {
            for(int j=1; j<=4; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
    
    static void printReal(String command) {
        System.out.println(command + ": ----------출력---------");
        for(int i=1; i<=4; i++) {
            for(int j=1; j<=4; j++) {
                int index = position2Index(i, j);
                int root = find(index);
                int[] rootPosition = index2Position(root);
                System.out.print(graph[rootPosition[0]][rootPosition[1]] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }
}