import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

lateinit var adjList: Array<Node?>
lateinit var set: HashSet<Int>
var N = 0
var M = 0
var K = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    K = st.nextToken().toInt()

    set = HashSet()
    adjList = Array(N + 1) { null }

    for (i in 0..<M) {
        st = StringTokenizer(readLine())
        val v = st.nextToken().toInt() - 1
        val u = st.nextToken().toInt() - 1
        adjList[v] = Node(u, adjList[v])
        adjList[u] = Node(v, adjList[u])
    }
    st = StringTokenizer(readLine())
    for (i in 0..< K) {
        set.add(st.nextToken().toInt() - 1)
    }
    println(bfs())
}

fun bfs(): Int {
    val queue = ArrayDeque<Int>()
    var isv = BooleanArray(N + 1)
    isv[0] = true
    for(i in set) {
        isv[i] = true
    }
    queue.offer(0)

    var count = 0
    while (!queue.isEmpty()) {
        val num = queue.poll()

        var node = adjList[num]
        while (node != null) {
            if (!isv[node.v]) {
                queue.offer(node.v)
                isv[node.v] = true
                count++
            }
            node = node.next
        }
    }
    return count
}

class Node (val v:Int, val next:Node?)