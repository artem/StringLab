import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
 
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val s = reader.readLine()
    val m = reader.readLine().toInt()
 
    if (s.length == 1) {
        for (i in 0 until m) {
            out.println("Yes")
        }
        out.close()
        return
    }
    val hash = LongArray(s.length)
    val pows = LongArray(s.length)
 
    pows[0] = 1
    for (i in 1 until s.length) {
        pows[i] = pows[i-1] * 17
    }
 
    hash[0] = (s[0] - 'a' + 1).toLong()
    for (i in 1 until s.length) {
        hash[i] = (hash[i - 1] + (s[i] - 'a' + 1) * pows[i])
    }
 
    for (i in 0 until m) {
        val (a, b, c, d) = reader.readLine().split(" ").map { it.toInt() - 1 }
 
        if (request(hash, pows, a, b, c, d) && eq(s, a, b, c, d)) {
            out.println("Yes")
        } else {
            out.println("No")
        }
    }
    out.close()
}
 
private fun eq(s: String, a: Int, b: Int, c: Int, d: Int): Boolean {
    return true
    val d1 = b - a
    
 
    for (i in 0 until d1) {
        if (s[a+i] != s[c+i]) {
            return false
        }
    }
    return true
}
 
private fun request(hash: LongArray, pows: LongArray, a: Int, b: Int, c: Int, d: Int): Boolean {
    val ret = if (a == 0) {
        hash[b]
    } else {
        (hash[b] - hash[a - 1])
    }
    val ret1 = if (c == 0) {
        hash[d]
    } else {
        (hash[d] - hash[c - 1])
    }
 
    return if (c > a) {
        ret * pows[c-a] == ret1
    } else {
        ret == ret1 * pows[a-c]
    }
}
