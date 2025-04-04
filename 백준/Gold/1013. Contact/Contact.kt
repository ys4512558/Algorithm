import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val T = readLine().toInt();

    for (i in 1..T) {
        val pattern = readLine()
        val regex = Regex("^(100+1+|01)+$")
        println(if(pattern.matches(regex)) "YES" else "NO")
    }
}