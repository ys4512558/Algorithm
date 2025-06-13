import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val T = st.nextToken().toInt()

    val dp = IntArray(T  + 1) { 0 }

    for (i in 0 until N) {
        st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()
        val s = st.nextToken().toInt()
        for (j in T downTo 0) {
            if(t > j) break
            dp[j] = max(dp[j], dp[j - t] + s)
        }
    }
    println(dp[T])
}