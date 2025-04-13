import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import java.util.TreeSet

lateinit var sumR: Array<Int>
lateinit var sumB: Array<Int>
lateinit var setR: TreeSet<Int>
lateinit var setB: TreeSet<Int>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val Q = st.nextToken().toInt()

    sumB = Array(N + 1) {0};
    sumR = Array(N + 1) {0};
    setR = TreeSet()
    setB = TreeSet()
    val str = readLine().toCharArray()
    for (i in 1..N) {
        val c = str[i - 1]
        if (c == 'R') {
            sumR[i]++
            setR.add(i - 1)
        } else if (c == 'B') {
            sumB[i]++
            setB.add(i - 1)
        }
        sumR[i] += sumR[i - 1]
        sumB[i] += sumB[i - 1]
    }

    val sb = StringBuilder()
    for (i in 0..< Q) {
        st = StringTokenizer(readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt() + 1
        var result =
                if (sumR[e] - sumR[s] >= 2 && sumB[e] - sumB[s] >= 2) getAnswer(s, e - 1)
                else "-1"
        sb.append(result).append("\n")
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    bw.write(sb.toString())
    bw.flush()
    bw.close()
}

fun getAnswer(s: Int, e: Int): String {
    val a = setR.ceiling(s) ?: -1
    if (a == -1) return "-1";
    val b = setR.higher(a) ?: -1
    if (b == -1 || b > e) return "-1";
    val c = setB.ceiling(b) ?: -1
    if (c == -1 || c > e) return "-1";
    val d = setB.higher(c) ?: -1
    if (d == -1 || d > e) return "-1";
    return "${a} ${b} ${c} ${d}"
}