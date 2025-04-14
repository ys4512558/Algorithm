import java.io.*
import java.util.*

lateinit var array: Array<Int>
var N = 0
var V = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    N = st.nextToken().toInt()
    V = st.nextToken().toInt()

    st = StringTokenizer(readLine())
    array = Array(N) { st.nextToken().toInt() }
    println(getCount())
}

fun getCount(): Int {
    var flag = false
    var count = Int.MAX_VALUE
    for (i in 0..<N) {
        for (j in 0..<i) {
            if (Math.abs(array[j] - array[i]) >= V) {
                var diff = if (j == 0) 0 else ((j / 2) + (j % 2))
                diff += ((i - j) / 2) + ((i - j) % 2) + 1
                count = Math.min(count, diff)
                flag = true
            }
        }
    }
    if(!flag) return N
    return count
}
