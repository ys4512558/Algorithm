import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Arrays
import java.util.TreeMap
import kotlin.math.max
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()

    var len = 0;
    val strs = Array<String>(N) {
        var str = readLine()
        len = max(len, str.length)
        str
    }

    val alphabets = Array(26) { Info(('A' + it), IntArray(len))}

    for (str in strs) {
        for (i in 0..<str.length) {
            val info = alphabets[str[str.length - i - 1] - 'A']
            info.arr[len - i - 1]++
        }
    }

    alphabets.sort()
    var cur = 9
    var result = 0
    for (info in alphabets) {
        result += info.sum() * cur
        if(cur-- == 0) break
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

    fun sum(): Int {
        var sum = 0
        for (i in 0..< arr . size) {
            sum += arr[i] * (10.0.pow(arr.size - i - 1).toInt())
        }
        return sum
    }
}
