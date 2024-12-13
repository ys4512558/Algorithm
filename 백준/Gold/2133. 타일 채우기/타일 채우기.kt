import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    var N = readLine().toInt()
    var dp = Array(N + 1, { 0})

    if(N > 1) dp[2] = 3
    for (i in 4..N step 2) {
        dp[i] = dp[i - 2] * dp[2]
        for (j in 2..i - 4 step 2) {
            dp[i] += dp[j] * 2
        }
        dp[i] += 2
    }
    println(dp[N])
}