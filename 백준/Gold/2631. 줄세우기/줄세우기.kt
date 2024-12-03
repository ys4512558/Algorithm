import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var N = br.readLine().toInt()

    var array = Array(N, { br.readLine().toInt()})
    var dp = Array(N, { 0})

    var lis = 0;
    for (i in 0..N - 1) {
        dp[i] = 1;
        for (j in 0..i - 1) {
            if (array[i] > array[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1)
                lis = Math.max(lis, dp[i])
            }
        }
    }

    println(N - lis)
}