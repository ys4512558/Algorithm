import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


var N = 0
var M = 0
var K = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())

    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    K = st.nextToken().toInt()

    st = StringTokenizer(readLine())
    val A = Array(N, { st.nextToken().toInt()})
    st = StringTokenizer(readLine())
    val B = Array(M, { st.nextToken().toInt()})
    st = StringTokenizer(readLine())
    val C = Array(K, { st.nextToken().toInt()})

    println(calc(A, B, C))
}


fun calc(A: Array<Int>, B: Array<Int>, C: Array<Int>): Int {
    Arrays.sort(A)
    Arrays.sort(B)
    Arrays.sort(C)
    var score = Int.MAX_VALUE
    for (i in 0 until N) {
        for (j in 0 until M) {
            score = Math.min(score, calcScore(A[i], B[j], C))
        }
        for (j in 0 until K) {
            score = Math.min(score, calcScore(A[i], C[j], B))
        }
    }
    return score
}

fun calcScore(score1: Int, score2: Int, arr: Array<Int>): Int {
    var max = Math.max(score1, score2)
    var min = Math.min(score1, score2)

    //min ~ max 사이 값이
    //존재하면 : max - min 이 벌점
    //존재하지 않으면 : Math.max(max, score) - Math.min(min, score)
    var l = 0;
    var r = arr.size - 1
    //min 보다 크거나 같은 값 중 첫번째 (lowerBound)
    while (l < r) {
        var mid = (l + r) / 2
        if(arr[mid] < min) l = mid + 1
        else r = mid
    }
    var temp1 = Math.max(max, arr[l]) - Math.min(min, arr[l])
    //min 보다 작거나 같은 값 중 첫번째
    l = 0
    r = arr.size - 1
    var res = Int.MIN_VALUE
    //floor
    while (l <= r) {
        var mid = (l + r) / 2
        if (arr[mid] <= min) {
            res = arr[mid]
            l = mid + 1
        } else {
            r = mid - 1
        }

    }
    var temp2 = if(res == Int.MIN_VALUE) Int.MAX_VALUE else Math.max(max, res) - Math.min(min, res)
    return Math.min(temp1, temp2)
}
