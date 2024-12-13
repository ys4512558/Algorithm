import java.io.*
import java.util.ArrayList
import java.util.PriorityQueue
import java.util.StringTokenizer

var N = 0
var K = 0
var R = 0
var res = 0
var dx = arrayOf(-1, 1, 0, 0)
var dy = arrayOf(0, 0, -1, 1)
lateinit var roads: HashMap<Int, ArrayList<Int>>
lateinit var cowMap: HashMap<Int, Boolean>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    var st = StringTokenizer(readLine())
    N = st.nextToken().toInt()
    K = st.nextToken().toInt()
    R = st.nextToken().toInt()

    roads = HashMap()
    for (i in 0 until R) {
        st = StringTokenizer(readLine())
        var x1 = st.nextToken().toInt() - 1
        var y1 = st.nextToken().toInt() - 1
        var x2 = st.nextToken().toInt() - 1
        var y2 = st.nextToken().toInt() - 1
        var list = roads.getOrDefault(flat(x1, y1), ArrayList<Int>())
        list.add(flat(x2, y2))
        roads.put(flat(x1, y1), list)

        list = roads.getOrDefault(flat(x2, y2), ArrayList<Int>())
        list.add(flat(x1, y1))
        roads.put(flat(x2, y2), list)
    }

    cowMap = HashMap()
    var cows = Array(K, {
        st = StringTokenizer(readLine())
        var x = st.nextToken().toInt() - 1
        var y = st.nextToken().toInt() - 1
        cowMap.put(flat(x, y), true)
        arrayOf(x, y)
    })

    for (i in 0 until K) {
        //a 소에서 b, c, d, f로 가는 경우 == b, c, d, f에서 a로 가는 경우 이므로 탐색의 시작점으로 사용된 소는 맵에서 삭제 (중복 제거)
        //또한 자기 자신의 위치를 지우고 시작
        cowMap.remove(flat(cows[i][0], cows[i][1]))
        //각 소의 위치에서 bfs탐색
        bfs(cows[i][0], cows[i][1])
    }
    println(res)
}

fun bfs(x: Int, y: Int) {
    var pq = PriorityQueue<Info>()
    //3차원 visit 배열 [0] : 다리 안건넘. [1] : 다리 건넘
    var isv = Array(N, { Array(N, { BooleanArray(2, { false }) })})

    pq.offer(Info(x, y, false))

    isv[x][y][0] = true
    isv[x][y][1] = true
    var cows = HashSet<Int>()

    while (!pq.isEmpty()) {
        val info = pq.poll()

        //현재 위치가 소일때
        if (cowMap.getOrDefault(flat(info.x, info.y), false) && cows.add(flat(info.x, info.y))) {
            if(info.isRoad) {
                res++
            }
            if(cows.size == cowMap.size) return
        }
        var idx = if(info.isRoad) 1 else 0
        for (i in 0 until 4) {
            var nx = info.x + dx[i]
            var ny = info.y + dy[i]

            if (isOutRange(nx, ny)) continue
            //길을 타고, 안타고 동일하게 도착한적 있으면 스킵
            if (isv[nx][ny][idx]) continue
            //가려는 곳이 길을 타고왔을 때 (info.isRoad), 길을 타고 가는지 (맵에서 가려는 곳, 온 곳이 같을 때)
            var list = roads.getOrDefault(flat(nx, ny), null)
            var road = list?.any {it == flat(info.x, info.y)} ?: false //해당 위치에서 길이 있으면서 info.x, y와 연결된 길이라면 true, null이거나 없으면 false
            var isRoad = info.isRoad || road

            pq.offer(Info(nx, ny, isRoad))
            isv[nx][ny][if(isRoad) 1 else 0] = true
        }
    }
}

fun isOutRange(x: Int, y: Int) : Boolean {
    return x < 0 || y < 0 || x >= N || y >= N
}

fun flat(x : Int, y : Int): Int {
    return x * N + y
}

class Info(val x: Int, val y: Int, val isRoad: Boolean): Comparable<Info> {
    override fun compareTo(other: Info): Int {
        var num1 = if(this.isRoad) 1 else -1
        var num2 = if(other.isRoad) 1 else -1
        return Integer.compare(num1, num2)
    }
}