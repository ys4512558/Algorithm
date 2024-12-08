import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

lateinit var parents: Array<Int>
lateinit var ranks: Array<Int>

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    var N = st.nextToken().toInt()
    var M = st.nextToken().toInt()

    parents = Array(N + 1, { it})
    ranks = Array(N + 1, { 1})

    for (i in 0..M - 1) {
        st = StringTokenizer(br.readLine())

        var v = st.nextToken().toInt()
        var u = st.nextToken().toInt()
        union(v, u)
    }
    st = StringTokenizer(br.readLine())
    var cnt = 0;
    var pre = st.nextToken().toInt()
    for (i in 1..N - 1) {
        var cur = st.nextToken().toInt()
        if (!check(pre, cur)) {
            cnt++
        }
        pre = cur;
    }
    println(cnt)
}

fun find(v: Int): Int {
    if(parents[v] == v) return v;
    parents[v] = find(parents[v])
    return parents[v]
}

fun check(v: Int, u: Int): Boolean {
    return find(v) == find(u)
}

fun union(v:Int, u:Int): Boolean {
    var p1 = find(v)
    var p2 = find(u)

    if(p1 == p2) return false;

    if (ranks[p1] < ranks[p2]) {
        ranks[p2]++
        parents[p1] = p2
        return true
    }
    ranks[p1] = if (ranks[p1] == ranks[p2]) ranks[p1] + 1 else ranks[p1]
    parents[p2] = p1
    return false;
}