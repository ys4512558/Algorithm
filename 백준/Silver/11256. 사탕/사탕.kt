import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val T = readLine().toInt()

    val sb = StringBuilder()
    for (i in 0..<T) {
        var st = StringTokenizer(readLine())
        var J = st.nextToken().toInt()
        var N = st.nextToken().toInt()

        val pq: PriorityQueue<Box> = PriorityQueue()
        for (i in 0..<N) {
            st = StringTokenizer(readLine())
            var R = st.nextToken().toInt()
            var C = st.nextToken().toInt()
            pq.offer(Box(R, C))
        }
        var count = 0;
        var candy = 0
        while (!pq.isEmpty() && candy < J) {
            candy += pq.poll().count
            count++
        }
        sb.append(count).append("\n");
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    bw.write(sb.toString())
    bw.flush()
}


class Box(val r: Int, val c: Int) : Comparable<Box> {

    val count: Int
        get() = r * c

    override fun compareTo(other: Box): Int {
        return Integer.compare(other.count, this.count)
    }

}