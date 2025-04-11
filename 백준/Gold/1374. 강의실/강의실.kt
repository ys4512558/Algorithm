package BOJ1374

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.Queue
import java.util.StringTokenizer

lateinit var times: PriorityQueue<Info>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()

    times = PriorityQueue()
    for (i in 0..<N) {
        var st = StringTokenizer(readLine())
        val idx = st.nextToken().toInt()
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        times.offer(Info(start, end))
    }

    val pq = PriorityQueue<Info>({ o1, o2 -> Integer.compare(o1.end, o2.end)})
    pq.offer(times.poll())
    while (!times.isEmpty()) {
        if (times.peek().start >= pq.peek().end) {
            pq.poll()
        }
        pq.offer(times.poll())
    }
    println(pq.size)
}

class Info(val start: Int, val end: Int): Comparable<Info> {
    override fun compareTo(other: Info): Int {
        if(this.start == other.start) return Integer.compare(this.end, other.end)
        return Integer.compare(this.start, other.start)
    }
}