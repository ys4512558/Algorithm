import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var map = Array(N, {
        var st = StringTokenizer(br.readLine())
        IntArray(N, { st.nextToken().toInt()})
    })
    var dp = Array(N, {
        IntArray(N, {Integer.MAX_VALUE / 2 })
    })

    for (i in 0..N - 1) {
        for (j in 0..N - 1) {
            if (i == 0 && j == 0) {
                dp[i][j] = 0
            }else if (i == 0) {
                dp[i][j] =
                    if(map[i][j] < map[i][j - 1]) Math.min(dp[i][j], dp[i][j - 1])
                    else Math.min(dp[i][j], dp[i][j - 1] + map[i][j] - map[i][j - 1] + 1)
            } else if (j == 0) {
                dp[i][j] =
                    if(map[i][j] < map[i - 1][j]) Math.min(dp[i][j], dp[i - 1][j])
                    else Math.min(dp[i][j], dp[i - 1][j] + map[i][j] - map[i - 1][j] + 1)
            } else {
                dp[i][j] =
                    if(map[i][j] < map[i][j - 1]) Math.min(dp[i][j], dp[i][j - 1])
                    else Math.min(dp[i][j], dp[i][j - 1] + map[i][j] - map[i][j - 1] + 1)
                dp[i][j] =
                    if(map[i][j] < map[i - 1][j]) Math.min(dp[i][j], dp[i - 1][j])
                    else Math.min(dp[i][j], dp[i - 1][j] + map[i][j] - map[i - 1][j] + 1)
            }
        }
    }
    println(dp[N - 1][N - 1])
}