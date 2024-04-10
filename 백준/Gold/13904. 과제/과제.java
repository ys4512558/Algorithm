import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Assignment[] assignments = new Assignment[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            assignments[i] = new Assignment(deadLine, score);
        }

        Arrays.sort(assignments);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int day = 1;
        for (int i = 0; i < N; i++) {
            if(day > assignments[i].deadLine){
                if(pq.peek() > assignments[i].score) continue;
                pq.poll();
                pq.offer(assignments[i].score);
                continue;
            }
            day++;
            pq.offer(assignments[i].score);
        }
        int score = 0;
        while (!pq.isEmpty()) {
            score += pq.poll();
        }
        System.out.println(score);
    }
}

class Assignment implements Comparable<Assignment>{
    int deadLine;
    int score;

    public Assignment(int deadLine, int score) {
        this.deadLine = deadLine;
        this.score = score;
    }

    @Override
    public int compareTo(Assignment o) {
        if(this.deadLine == o.deadLine) return Integer.compare(o.score, this.score);
        return Integer.compare(this.deadLine, o.deadLine);
    }
}