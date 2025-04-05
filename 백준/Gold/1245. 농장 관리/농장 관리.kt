import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

lateinit var map: Array<IntArray>
lateinit var isv: Array<BooleanArray>
val dx = arrayOf(-1, 1, 0, 0, -1, -1, 1, 1)
val dy = arrayOf(0, 0, -1, 1, -1, 1, -1, 1)
var N = 0
var M = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    map = Array(N) { IntArray(M)}
    isv = Array(N) { BooleanArray(M, { false }) }

    for (i in 0 until N) {
        st = StringTokenizer(readLine())
        for (j in 0 until M) {
            map[i][j] = st.nextToken().toInt()
        }
    }

    var cnt = 0
    for (i in 0 until N) {
        for (j in 0 until M) {
            if(isv[i][j] || map[i][j] == 0) continue
            isv[i][j] = true
            if(dfs(i, j)) {
                cnt++
            }
        }
    }
    println(cnt)
}

fun dfs(x: Int, y: Int): Boolean {
    var flag = true
    for (i in 0 until 8) {
        var nx = x + dx[i]
        var ny = y + dy[i]

        if(isOutRange(nx, ny, map[x][y])) continue
        if(map[x][y] < map[nx][ny]) flag = false
        if(isv[nx][ny] || map[nx][ny] != map[x][y]) continue
        isv[nx][ny] = true
        flag = flag and dfs(nx, ny)
    }
    return flag
}

fun isOutRange(x: Int, y: Int, num: Int): Boolean {
    if (x < 0 || y < 0 || x >= N || y >= M) return true
    return false
}
