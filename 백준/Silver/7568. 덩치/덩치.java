import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Person[] people = new Person[N];
        int[] results = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            people[i] = new Person(height, weight);
        }
        for (int i = 0; i < N; i++) {
            results[i] = 1;
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                if(people[i].height < people[j].height && people[i].weight < people[j].weight){
                    results[i]++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append(results[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Person{
    int height, weight;

    public Person(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }
}