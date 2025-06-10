import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()

    var len = 0;
    var isFirst = BooleanArray(10) { false}
    val strs = Array<String>(N) {
        var str = readLine()
        len = max(len, str.length)
        isFirst[str[0] - 'A'] = true
        str
    }

    val alphabets = Array(10) { Info(('A' + it), IntArray(len))}

    for (str in strs) {
        for (i in 0..<str.length) {
            val info = alphabets[str[str.length - i - 1] - 'A']
            info.arr[len - i - 1]++
        }
    }

    alphabets.sort()

    //첫번째로 나오면서 0인 경우
    for (i in 9 downTo 0) {
        val info = alphabets[i]
        if (alphabets[i].sum() == 0L) break
        if (isFirst[alphabets[i].c - 'A']) continue
        for (j in i until alphabets.size - 1) {
            alphabets[j] = alphabets[j + 1]
        }
        alphabets[9] = info
        break
    }

    var cur = 9L
    var result = 0L
    for (info in alphabets) {
        val sum = info.sum()
        result += sum * cur--
        if (sum == 0L) break
    }
    println(result)
}

class Info(val c: Char, arr: IntArray): Comparable<Info> {
    var arr: IntArray = arr
    override fun compareTo(other: Info): Int {
        var num1 = sum()
        var num2 = other.sum()
        return num2.compareTo(num1)
    }

    fun sum(): Long {
        var sum = 0L
        for (i in 0..< arr . size) {
            sum += arr[i] * (10.0.pow(arr.size - i - 1).toLong())
        }
        return sum
    }
}
