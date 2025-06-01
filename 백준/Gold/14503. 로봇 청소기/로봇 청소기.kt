import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.StringTokenizer

var N = 0
var M = 0

//북, 동, 남, 서
val dx = arrayOf(-1, 0, 1, 0)
val dy = arrayOf(0, 1, 0, -1)
lateinit var map:Array<IntArray>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    st = StringTokenizer(readLine())
    val x = st.nextToken().toInt()
    val y = st.nextToken().toInt()
    val dir = st.nextToken().toInt()

    var start = Robot(x, y, dir)

    map = Array(N) {
        st = StringTokenizer(readLine())
        IntArray(M) { st.nextToken().toInt()}
    }

    println(simulate(start))
}

//1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
//2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
//  2.1 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
//  2.2 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
//3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
//  3.1 반시계 방향으로 90도 회전한다.
//  3.2 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
//  3.3 1번으로 돌아간다.
fun simulate(start: Robot):Int {
    val queue = ArrayDeque<Robot>()
    queue.offer(start)

    var count = 0
    while (!queue.isEmpty()) {
        val robot = queue.poll()

        if(map[robot.x][robot.y] == 0) {
            map[robot.x][robot.y] = 2
            count++
        }
        var flag = false
        for (i in 0..3) {
            val nx = robot.x + dx[i]
            val ny = robot.y + dy[i]
            if (isOutRange(nx, ny)) continue

            if(map[nx][ny] == 0) flag = true
        }

        if (flag) {
            val dir = (robot.dir - 1 + 4) % 4
            var nx = robot.x + dx[dir]
            var ny = robot.y + dy[dir]
            if (map[nx][ny] != 0) {
                nx = robot.x
                ny = robot.y
            }
            queue.offer(Robot(nx, ny, dir))
        } else {
            val dir = (robot.dir + 2) % 4
            var nx = robot.x + dx[dir]
            var ny = robot.y + dy[dir]

            if(map[nx][ny] == 1) break
            queue.offer(Robot(nx, ny, robot.dir))
        }
    }
    return count;
}

fun isOutRange(x: Int, y: Int): Boolean {
    return x < 0 || y < 0 || x >= N || y >= M
}

class Robot(val x: Int, val y: Int, val dir: Int)