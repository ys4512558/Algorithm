import java.io.*;
import java.util.*;

public class Main {
    static int cnt = 0;
    static Date total = new Date(0, 0, 0, 0);
    static Date pre1 = null;
    static Date pre2 = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Date[] dates = new Date[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Date date = new Date(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()));
            dates[i] = date;
        }

        Arrays.sort(dates);
        for (int i = 0; i < N; i++) {
            Date current = dates[i];
            if(current.eMonth < 3 || current.sMonth > 12) continue;
            if (total.start2Int() == 0 && total.end2Int() == 0) {
                total = new Date(current.sMonth, current.sDay, current.eMonth, current.eDay);
                pre1 = current;
                cnt++;
                continue;
            }
            condition(current);
            if(total.end2Int() >= 1201) break;
        }
        System.out.println(total.start2Int() <= 301 && total.end2Int() >= 1201 ? cnt : 0);
    }
    private static void condition(Date current){
        if(current.start2Int() <= 301) {
            if (pre1.end2Int() <= current.end2Int()) {
                total.eMonth = current.eMonth;
                total.eDay = current.eDay;
                pre1 = current;
                return;
            }
        }
        //이전 선택보다 빨리 지면 추가 X
        if(current.end2Int() <= pre1.end2Int()) return;
        if(pre1.end2Int() < current.start2Int()) return;
        if(pre2 == null){
            pre2 = pre1;
            pre1 = current;
            total.eMonth = current.eMonth;
            total.eDay = current.eDay;
            cnt++;
            return;
        }
        if (pre2.end2Int() >= current.start2Int() && pre1.end2Int() <= current.end2Int()) {
            pre1 = current;
        } else {
            pre2 = pre1;
            pre1 = current;
            cnt++;
        }
        total.eMonth = current.eMonth;
        total.eDay = current.eDay;
    }
}


class Date implements Comparable<Date> {

    int sMonth, sDay;
    int eMonth, eDay;

    public Date(int sMonth, int sDay, int eMonth, int eDay) {
        this.sMonth = sMonth;
        this.sDay = sDay;
        this.eMonth = eMonth;
        this.eDay = eDay;
    }

    @Override
    public int compareTo(Date o) {
        if (this.sMonth == o.sMonth) {
            if(this.sDay == o.sDay){
                if (this.eMonth == o.eMonth) {
                    return Integer.compare(this.eDay, o.eDay);
                }
                return Integer.compare(this.eMonth, o.eMonth);
            }
            return Integer.compare(this.sDay, o.sDay);
        }
        return Integer.compare(this.sMonth, o.sMonth);
    }
    public int start2Int() {
        StringBuilder sb = new StringBuilder();
        sb.append(sMonth).append(sDay < 10 ? "0"+sDay : sDay);
        return Integer.parseInt(sb.toString());
    }
    public int end2Int() {
        StringBuilder sb = new StringBuilder();
        sb.append(eMonth).append(eDay < 10 ? "0" + eDay : eDay);
        return Integer.parseInt(sb.toString());
    }
}