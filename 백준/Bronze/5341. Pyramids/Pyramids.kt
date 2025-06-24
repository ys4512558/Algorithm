import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    while (true) {
        val n = readLine().toInt()
        if(n == 0) break
        println(n * (n + 1) / 2)
    }
}