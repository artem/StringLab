import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter

private fun maxPrefix(pref: ShortArray, s: String, size: Int): Int {
    val start = reverseIdx(s, size - 1)

    var maxPref = 0
    pref[0] = -1
    for (i in 2 until size + 1) {
        var curLen: Int = pref[i-1].toInt()
        while (curLen != -1) {
            if (s[start + i-1] == s[start + curLen]) {
                break
            }
            curLen = pref[curLen].toInt()
        }
        pref[i] = (curLen + 1).toShort()
        if (pref[i] > maxPref) {
            maxPref = pref[i].toInt()
        }
    }

    return maxPref
}



private fun reverseIdx(s: String, idx: Int): Int {
    return s.length - idx - 1;
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val out = PrintWriter(System.out)
    val s = reader.readLine().reversed()
    val buffer = ShortArray(s.length + 1)
    val n = s.length
    val ansMat = Array(n) {IntArray(n)}
    for (i in 0 until n) {
        ansMat[i][i] = 1;
    }

    for (i in 1 until s.length) {
        var maxPref = 0
        maxPrefix(buffer, s, i + 1)
        for (j in i-1 downTo 0) {
            val len = i - j + 1
            if (buffer[len] > maxPref) {
                maxPref = buffer[len].toInt()
            }
            ansMat[j][i] = ansMat[j][i - 1] + len - maxPref
        }
    }

    val q = reader.readLine().toInt()
    for (i in 0 until q) {
        val (l, r) = reader.readLine().split(" ").map { it.toInt() - 1 }
        out.println(ansMat[l][r])
    }

    out.close()
}