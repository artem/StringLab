import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.max
import kotlin.math.min
 
private fun zCompute(s: String): IntArray {
    val z = IntArray(s.length)
    var maxL = 0
    for (i in 1 until s.length) {
        z[i] = min(z[i-maxL], max(0, maxL + z[maxL] - i))
 
        while (i + z[i] < s.length && s[i + z[i]] == s[z[i]]) {
            z[i]++
        }
 
        if (i + z[i] > maxL + z[maxL]) {
            maxL = i
        }
    }
 
    return z
}
 
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val t = reader.readLine()
 
    val z = zCompute(t)
 
    var ans = t.length
    for (i in 1 until t.length/2) {
        if (z[i] + i == t.length && i < ans) {
            ans = i
        }
    }
 
    out.println(ans)
    out.close()
}