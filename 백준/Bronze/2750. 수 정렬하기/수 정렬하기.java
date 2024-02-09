import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        selectionSort(arr);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(arr[i] > arr[j]) swap(arr, i, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}