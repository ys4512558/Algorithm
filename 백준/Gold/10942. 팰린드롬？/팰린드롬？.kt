import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var N = readLine().toInt()
    var st = StringTokenizer(readLine())
    var array = Array(N, { st.nextToken().toInt()})
    var dp = Array(N, { BooleanArray(N, { false }) })

    //길이를 기준으로 체크 (길이가 1이면 무조건 true)
    for (i in 0 until N) { dp[i][i] = true}
    //길이가 2일때 인접한 두 문자가 같으면 true
    for (i in 0 until N - 1) { dp[i][i + 1] = (array[i] == array[i + 1]) }
    //길이가 3이상일때
    for (len in 3 .. N) {
        for (s in 0 .. N - len) { //시작점
            var e = s + len - 1 //끝점
            //시작점과 끝점이 같으며 시작점 - 끝점 바로 전 (s = 1, e = 5 (길이 5)일때 2 ~ 4(길이 3) 구간은 이미 구해진 상태)
            dp[s][e] = (array[s] == array[e] && dp[s + 1][e - 1])
        }
    }

    var M = readLine().toInt()
    var sb = StringBuilder()
    for (i in 1..M) {
        st = StringTokenizer(readLine())
        var s = st.nextToken().toInt() - 1
        var e = st.nextToken().toInt() - 1
        sb.append(if(dp[s][e]) 1 else 0).append("\n")
    }
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    bw.write(sb.toString())
    bw.flush()
    bw.close()
}