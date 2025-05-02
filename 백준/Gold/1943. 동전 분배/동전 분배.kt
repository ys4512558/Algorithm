import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

lateinit var coins: Array<IntArray>
lateinit var dp: BooleanArray
var n: Int = 0
var sum: Int = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    for (i in 0..<3) {
        n = readLine().toInt()

        coins = Array(n) { IntArray(2) { 0} }
        sum = 0
        for (j in 0..<n) {
            val st = StringTokenizer(readLine())
            val money = st.nextToken().toInt()
            val count = st.nextToken().toInt()
            coins[j][0] = money
            coins[j][1] = count
            sum += (money * count)
        }
        if (sum % 2 == 0) {
            sum /= 2
            knapsack()
            println(if(dp[sum]) 1 else 0)
        } else {
            println(0)
        }
    }
}

private fun knapsack() {
    dp = BooleanArray(sum + 1) { false }
    dp[0] = true
    for (j in 0..<n) {
        val money = coins[j][0]
        val count = coins[j][1]
        for (k in sum downTo 1) {
            for (l in 1..count) {
                if (k < (money * l)) break
                if(dp[k - (money * l)]) {
                    dp[k] = true
                }
            }
        }
    }
}

