import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.util.*
import kotlin.math.max
import kotlin.math.min
 
fun zCompute(s: String): IntArray {
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
    val p = reader.readLine()
    val t = p + '#' + reader.readLine()
    val hs = TreeSet<Int>()
 
    val z = zCompute(t)
 
    for (i in p.length + 1 until t.length) {
        if (z[i] == p.length) {
            hs.add(i - p.length)
        }
    }
 
    out.println(hs.size)
    for (i in hs) {
        out.print("$i ")
    }
    out.close()
}
