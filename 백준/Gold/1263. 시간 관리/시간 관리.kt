import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var N = readLine().toInt()

    var times = Array(N, {
        var st = StringTokenizer(readLine())
        IntArray(2, {st.nextToken().toInt()})
    })

    //끝나는 시간이 빠른 것 우선 정렬
    Arrays.sort(times, {o1, o2 -> if(o1[1] == o2[1]) Integer.compare(o1[0], o2[0]) else Integer.compare(o2[1], o1[1])})

    times.sortWith(compareByDescending<IntArray> { it[1] }.thenByDescending { it[0] })

    var time = times[0][1]
    var flag = false
    for (i in 0 until N) {
        //이전 작업이 시작되어야 하는 시간 vs 현재 작업의 최대 종료 시간 중 최소
        //이렇게 하면 5 20, 1 16인 경우 20에 맞춰 끝내기 위해서는 15에 시작해야함 하지만 16까지 여유임 최대한 미루기 위해서는 15를 기준으로 계산해야함
        time = Math.min(time, times[i][1])
        time -= times[i][0] //해당 수행 시간만큼 빼기
        if(time < 0) flag = true
    }

    println(if(flag) -1 else time)
}