import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val a = reader.readLine()
    val b = reader.readLine()
    val s = "$a#$b"
    val z = IntArray(s.length)
    val ans = BooleanArray(b.length)

    var maxL = 0

    for (i in 1 until a.length + 1) {
        z[i] = min(z[i-maxL], max(0, maxL + z[maxL] - i))

        while (i + z[i] < s.length && s[i + z[i]] == s[z[i]]) {
            z[i]++
        }

        if (i + z[i] > maxL + z[maxL]) {
            maxL = i
        }
    }
    
    var ansSize = 0
    for (i in a.length + 1 until s.length) {
        z[i] = min(z[i-maxL], max(0, maxL + z[maxL] - i))

        while (i + z[i] < s.length && s[i + z[i]] == s[z[i]]) {
            z[i]++
        }
        var zFunny = z[i]
        if (i + z[i] < s.length && s[zFunny] != '#') {
            zFunny++
            while (i + zFunny < s.length && s[i + zFunny] == s[zFunny]) {
                zFunny++
            }
        }

        if (zFunny == a.length) {
            ans[i - a.length-1] = true
            ansSize++
        }

        if (i + z[i] > maxL + z[maxL]) {
            maxL = i
        }
    }

    out.println(ansSize)
    for (i in 0 until b.length) {
        if (ans[i]) {
            out.print("${i+1} ")
        }
    }
    out.close()
}
