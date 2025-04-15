import java.io.*
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()

    val st = StringTokenizer(readLine())
    val arr = Array(N) { st.nextToken().toInt()}
    val prefixSum = LongArray(N + 1) { 0}

    for (i in 1..N) {
        prefixSum[i] += prefixSum[i - 1] + arr[i - 1]
    }

    //개수
    var min = Long.MAX_VALUE
    var K = 0
    for (k in 1..(N / 2)) {
        var numbers = LongArray(N - k + 1) {0}
        for (i in 0..N - k) {
            numbers[i] = (prefixSum[i + k] - prefixSum[i])
        }
        for (i in 0..<numbers.size) {
            for (j in i + k..<numbers.size) {
                val abs = Math.abs(numbers[i] - numbers[j])
                if(min < abs) continue
                min = abs
                K = k
            }
        }
//        println(Arrays.toString(numbers))
    }
    println(K)
    println(min)
}