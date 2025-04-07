import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()

    val arr = Array(N, { 0 });
    val st = StringTokenizer(readLine())
    for (i in 0 until N) {
       arr[i] =  st.nextToken().toInt()
    }

    var max = 0
    for (i in 0 until N) {
        var cnt = 0
        var hasBigTop = false
        //현재 탑보다 작은 탑에 대한 기울기
        var downGradient = Int.MAX_VALUE.toDouble()
        //현재 탑보다 큰 탑에 대한 기울기
        var upGradient = (Int.MIN_VALUE).toDouble()
        for (j in i - 1 downTo 0) {
            var gradient = Math.abs(arr[i] - arr[j]).toDouble() / Math.abs(j - i).toDouble()
            if (arr[i] <= arr[j] && gradient > upGradient) {
                upGradient = gradient;
                cnt++
                hasBigTop = true
            } else if (!hasBigTop && arr[i] > arr[j] && gradient < downGradient) {
                downGradient = gradient
                cnt++
            }
        }
        downGradient = Int.MAX_VALUE.toDouble()
        //현재 탑보다 큰 탑에 대한 기울기
        upGradient = (Int.MIN_VALUE).toDouble()
        hasBigTop = false
        for (j in i + 1 until N) {
            var gradient = Math.abs(arr[i] - arr[j]).toDouble() / Math.abs(j - i).toDouble()
            if (arr[i] <= arr[j] && gradient > upGradient) {
                upGradient = gradient;
                cnt++
                hasBigTop = true
            } else if (!hasBigTop && arr[i] > arr[j] && gradient < downGradient) {
                downGradient = gradient
                cnt++
            }
        }
        max = Math.max(max, cnt)
    }
    println(max)
}