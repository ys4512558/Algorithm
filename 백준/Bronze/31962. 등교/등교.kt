import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())

    val N = st.nextToken().toInt()
    val X = st.nextToken().toInt()

    var result = -1
    for (i in 0..<N) {
        st = StringTokenizer(readLine())
        var s = st.nextToken().toInt()
        var t = st.nextToken().toInt()
        if(s + t > X) continue
        if(result == -1) {
            result = s
        } else {
            result = max(result, s)
        }
    }
    println(result)
}