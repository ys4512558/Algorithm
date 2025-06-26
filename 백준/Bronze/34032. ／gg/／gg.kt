import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val str = readLine()
    val count = str.count { c -> c == 'O' }

    if(N % 2 == 0) {
        println(if(count >= N / 2) "Yes" else "No")
    } else {
        println(if(count > N / 2) "Yes" else "No")
    }

}
