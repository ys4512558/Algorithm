import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()

    val arr = IntArray(100001) { Integer.MAX_VALUE}

    arr[2] = 1
    arr[4] = 2
    arr[5] = 1

    for (i in 6..N) {
        if(arr[i - 2] != Integer.MAX_VALUE) arr[i] = min(arr[i], arr[i - 2] + 1)
        if(arr[i - 5] != Integer.MAX_VALUE) arr[i] = min(arr[i], arr[i - 5] + 1)
    }
    println(if(arr[N] == Integer.MAX_VALUE) -1 else arr[N])
}