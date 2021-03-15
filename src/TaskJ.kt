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

    var ans = 1
    out.println(ans)
    for (i in 2..s.length) {
        ans += i - maxPrefix(buffer, s, i)
        out.println(ans)
    }

    out.close()
}