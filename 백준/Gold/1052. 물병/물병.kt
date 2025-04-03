import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    var N = st.nextToken().toInt()
    var K = st.nextToken().toInt()

    var pq = PriorityQueue<Int>()
    var mul = N;
    var num = 0;
    while (mul != 0) {
        val mod = mul % 2
        mul /= 2
        if (mod != 0) pq.offer(Math.pow(2.0, num.toDouble()).toInt())
        num++
    }

    var result = 0
    while (pq.size > K) {
        val cur = pq.poll()
        val next = pq.poll()
        result += next - cur
        pq.offer(next * 2)
    }
    println(result)
}