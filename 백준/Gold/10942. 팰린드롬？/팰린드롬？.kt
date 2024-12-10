import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var N = readLine().toInt()
    var st = StringTokenizer(readLine())
    var array = Array(N, { st.nextToken().toInt()})
    var dp = Array(N, { BooleanArray(N, { true }) })

    for (i in 0..N - 1) {
        for (j in 0..N - 1) {
            var s = i
            var e = j
            while (s < e) {
                if (array[s++] != array[e--]) {
                    dp[i][j] = false
                    break
                }
            }
        }
    }

    var M = readLine().toInt()
    var sb = StringBuilder()
    for (i in 1..M) {
        st = StringTokenizer(readLine())
        var s = st.nextToken().toInt() - 1
        var e = st.nextToken().toInt() - 1
        sb.append(if(dp[s][e]) 1 else 0).append("\n")
    }
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    bw.write(sb.toString())
    bw.flush()
    bw.close()
}