import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val str = readLine()

    var num = 0
    var mul = 1
    for (i in 0..<str.length) {
        if (str[i] == '*') {
            mul = if(i % 2 == 0) 1 else 3
            continue
        }
        val n = str[i] - '0'
        if (i % 2 == 0) {
            num = (num + n) % 10
        } else {
            num = (num + (3 * n)) % 10
        }
    }
    for (i in 0..9) {
        val temp = (num + (mul * i)) % 10
        if(temp == 0) {
            println(i)
            break
        }
    }

}