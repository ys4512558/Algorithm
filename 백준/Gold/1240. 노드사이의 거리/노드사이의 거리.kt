import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.Queue
import java.util.StringTokenizer

lateinit var dist: Array<IntArray>
lateinit var adjList: Array<Node?>
var N = 0
var M = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    dist = Array(N, {IntArray(N, { Integer.MAX_VALUE})})
    adjList = arrayOfNulls(N)

    for (i in 0 until N - 1) {
        st = StringTokenizer(readLine())

        var v = st.nextToken().toInt() - 1
        var u = st.nextToken().toInt() - 1
        var d = st.nextToken().toInt()

        dist[v][u] = d
        dist[u][v] = d
        dist[v][v] = 0
        dist[u][u] = 0
        adjList[v] = Node(u, adjList[v])
        adjList[u] = Node(v, adjList[u])
    }

    var sb = StringBuilder()

    for (i in 0 until M) {
        st = StringTokenizer(readLine())

        var v = st.nextToken().toInt() - 1
        var u = st.nextToken().toInt() - 1
        sb.append(bfs(v, u)).append("\n")
    }
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    bw.write(sb.toString())
    bw.flush()
    bw.close()
}

fun bfs(v: Int, u: Int): Int {
    if(dist[v][u] != Integer.MAX_VALUE) return dist[v][u]

    var queue: Queue<Int> = ArrayDeque()
    var isv = Array(N, {false})
    queue.offer(v)
    isv[v] = true

    while (!queue.isEmpty()) {
        var vertex = queue.poll()

        var n: Node? = adjList[vertex]
        while (n != null) {
            if(!isv[n.num]){
                isv[n.num] = true
                queue.offer(n.num)
                dist[v][n.num] = dist[v][vertex] + dist[vertex][n.num]
            }
            if(n.num == u) return dist[v][u]
            n = n.next
        }
    }
    return 0
}

class Node(val num:Int, val next:Node?)