import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.min


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())

    val N = st.nextToken().toInt()
    val S = st.nextToken().toInt()
    val P = st.nextToken().toInt()

    val pq = PriorityQueue<Int>({ o1, o2 -> Integer.compare(o2, o1) })
    if(N != 0) st = StringTokenizer(readLine())
    var min = Integer.MAX_VALUE
    for (i in 0..<N) {
        val num = st.nextToken().toInt()
        pq.offer(num)
        min = min(min, num)
    }
    pq.offer(S)
    println(getRank(pq, N, S, P, min))
}

private fun getRank(pq: PriorityQueue<Int>, N: Int, S:Int, P: Int, min:Int):Int {
    if(N == P && S <= min) {
        return -1
    }
    var rank = 1
    while (!pq.isEmpty()) {
        val cur = pq.poll()
        if (cur == S) break
        rank++
    }
    return rank
}

