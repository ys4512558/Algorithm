import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.Queue
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    println(bfs(N, K))
}

fun bfs(n: Int, k: Int): String {
    val queue = ArrayDeque<Int>()

    var isv = IntArray(200001) { 0 }
    var counts = IntArray(200001) { 0 }
    queue.offer(n)
    isv[n] = 1
    counts[n] = 1
    var breadth = 1
    while (!queue.isEmpty()) {
        var size = queue.size
        while (size-- > 0) {
            val cur = queue.poll()

            var next1 = cur + 1
            var next2 = cur - 1
            var next3 = cur * 2

            if (!isOutRange(next1)) {
                if (isv[next1] == 0) {
                    queue.offer(next1)
                    isv[next1] = breadth + 1
                }
                if (isv[next1] == breadth + 1) {
                    counts[next1] += counts[cur]
                }
            }
            if (!isOutRange(next2)) {
                if (isv[next2] == 0) {
                    queue.offer(next2)
                    isv[next2] = breadth + 1
                }
                if (isv[next2] == breadth + 1) {
                    counts[next2] += counts[cur]
                }
            }
            if (!isOutRange(next3)) {
                if (isv[next3] == 0) {
                    queue.offer(next3)
                    isv[next3] = breadth + 1
                }
                if (isv[next3] == breadth + 1) {
                    counts[next3] += counts[cur]
                }
            }
        }
        breadth++
        if(counts[k] != 0) break
    }
    val sb = StringBuilder()
    sb.append(isv[k] - 1)
    sb.append("\n")
    sb.append(counts[k])
    return sb.toString()
}

fun isOutRange(next: Int): Boolean {
    return next < 0 || next >= 200000
}
