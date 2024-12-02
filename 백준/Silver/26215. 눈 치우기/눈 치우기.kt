import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt();
    var st = StringTokenizer(br.readLine())

    var arr = Array(N, { st.nextToken().toInt()})

    var cnt = 0;

    while (cnt <= 1440) {
        arr = arr.sortedArrayDescending()
        if(arr[0] == 0) break
        if(N > 1) arr[1]--
        arr[0]--
        cnt++
    }
    println(if (cnt > 1440) -1 else cnt)
}