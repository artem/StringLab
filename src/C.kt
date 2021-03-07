import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
 
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val s = reader.readLine()
    val pref = IntArray(s.length + 1)
    pref[0] = -1
    for (i in 2 until pref.size) {
        var curLen = pref[i-1]
        while (curLen != -1) {
            if (s[i-1] == s[curLen]) {
                break
            }
            curLen = pref[curLen]
        }
        pref[i] = curLen + 1
    }
 
    for (i in 1 until pref.size) {
        out.print("${pref[i]} ")
    }
    out.close()
}