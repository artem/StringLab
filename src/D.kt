import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max
import kotlin.math.min
 
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val s = reader.readLine()
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
 
    for (i in 1 until z.size) {
        out.print(" ${z[i]}")
    }
    out.close()
}