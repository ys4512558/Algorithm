import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val T = readLine().toInt()

    val sb = StringBuilder()
    for (i in 0..<T) {
        val N = readLine().toInt()
        val map = HashMap<String, Int>()
        for (j in 0..<N) {
            val st = StringTokenizer(readLine())
            val name = st.nextToken()
            val type = st.nextToken()
            val count = map.getOrDefault(type, 0)
            map.put(type, count + 1)
        }
        var total = 1
        for (count in map.values) {
            total *= (count + 1)
        }
        sb.append(total - 1).append("\n")
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    bw.write(sb.toString())
    bw.flush()
}