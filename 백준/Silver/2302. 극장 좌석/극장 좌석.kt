import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque
import java.util.Queue

lateinit var fibo: Array<Int>

fun main()  {
    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()
    var M = br.readLine().toInt()

    var queue: Queue<Int> = ArrayDeque()
    for (i in 0..M - 1) {
        queue.offer(br.readLine().toInt())
    }
    fibo = Array(N + 1, { 0 })
    fibo[0] = 1
    fibo[1] = 1

    var pre = 0
    var res = 1
    for (i in 1..queue.size) {
        var num = queue.poll()
        var count = (num - 1) - pre
        res *= fibonacci(count)
        pre = num
    }
    println(res * fibonacci(N - pre))
}

fun fibonacci(num: Int): Int {
    if (fibo[num] == 0) {
        fibo[num] = fibonacci(num - 1) + fibonacci(num - 2)
    }
    return fibo[num]
}