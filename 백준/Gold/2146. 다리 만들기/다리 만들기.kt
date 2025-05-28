import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.ArrayList
import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

var N = 0
lateinit var map: Array<IntArray>
lateinit var isv: Array<BooleanArray>
var dx = arrayOf(-1, 1, 0, 0)
var dy = arrayOf(0, 0, -1, 1)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    N = readLine().toInt()

    map = Array(N) {
        var st = StringTokenizer(readLine())
        IntArray(N) { st.nextToken().toInt() }
    }

    isv = Array(N) { BooleanArray(N) { false} }
    val list = ArrayList<List<Info>>()
    for (i in 0..<N) {
        for (j in 0..<N) {
            if (map[i][j] == 1 && !isv[i][j]) {
                val infos = bfs(i, j)
                list.add(infos)
            }
        }
    }

    var min = Integer.MAX_VALUE
    for (i in 0..<list.size) {
        for (info in list.get(i)) {
            for (j in i + 1..<list.size) {
                for (other in list.get(j)) {
                    min = min(min, dist(info, other))
                }
            }
        }
    }
    println(min)
}

fun dist(info1: Info, info2: Info): Int {
    return abs(info1.x - info2.x) + abs(info1.y - info2.y) - 1
}

fun bfs(x: Int, y: Int): List<Info> {
    val queue = ArrayDeque<Info>()
    queue.offer(Info(x, y))
    isv[x][y] = true

    var infos = ArrayList<Info>()

    while (!queue.isEmpty()) {
        val info = queue.poll()

        var flag = false
        for (i in 0..3) {
            var nx = info.x + dx[i]
            var ny = info.y + dy[i]
            if(isOutRange(nx, ny)) continue
            if(map[nx][ny] == 0) {
                flag = true
                continue
            }
            if(isv[nx][ny]) continue
            queue.offer(Info(nx, ny))
            isv[nx][ny] = true
        }

        if(flag) infos.add(info)
    }
    return infos
}

fun isOutRange(x: Int, y: Int): Boolean {
    return x < 0 || y < 0 || x >= N || y >= N;
}

class Info(val x:Int, val y:Int)