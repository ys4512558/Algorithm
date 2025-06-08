import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

var N = 0
var M = 0
lateinit var houses: ArrayList<Info>
lateinit var chickens: ArrayList<Info>
lateinit var selected: IntArray
var result = Int.MAX_VALUE
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    houses = ArrayList()
    chickens = ArrayList()
    selected = IntArray(M)
    for (i in 0 until N) {
        st = StringTokenizer(readLine())
        for (j in 0 until N) {
            val num = st.nextToken().toInt()
            if (num == 1) {
                houses.add(Info(i, j))
            } else if (num == 2) {
                chickens.add(Info(i, j))
            }
        }
    }
    combination(0, 0)
    println(result)
}

fun combination(start: Int, depth: Int) {
    if (depth == M) {
        var sum = 0
        for (info in houses) {
            var min = Int.MAX_VALUE
            for (i in 0 until M) {
                var cur = chickens[selected[i]]
                var dist = abs(cur.x - info.x) + abs(cur.y - info.y)
                min = min(min, dist)
            }
            sum += min
        }
        result = min(result, sum)
        return;
    }

    for (i in start until chickens.size) {
        selected[depth] = i
        combination(i + 1, depth + 1)
    }
}

class Info(val x: Int, val y: Int)