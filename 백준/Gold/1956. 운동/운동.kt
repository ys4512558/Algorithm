import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()

    val adjList = Array(N, { IntArray(N, { Int.MAX_VALUE / 2 }) })

    for (i in 0 until M) {
        st = StringTokenizer(readLine())
        var v = st.nextToken().toInt()
        var u = st.nextToken().toInt()
        var cost = st.nextToken().toInt()
        adjList[v - 1][v - 1] = 0
        adjList[u - 1][u - 1] = 0
        adjList[v - 1][u - 1] = cost
    }
    floydWarshall(N, adjList)
    var min = Int.MAX_VALUE / 2

    for (i in 0 until N) {
        for (j in 0 until N) {
            if(i == j) continue
            var dist = adjList[i][j] + adjList[j][i]
            min = Math.min(min, dist)
        }
    }
    println(if(min == (Int.MAX_VALUE / 2)) -1 else min)
}

fun floydWarshall(N: Int, dists: Array<IntArray>) {
    for(k in 0 until N) {
        for (i in 0 until N) {
            for (j in 0 until N) {
                dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j])
            }
        }
    }
}