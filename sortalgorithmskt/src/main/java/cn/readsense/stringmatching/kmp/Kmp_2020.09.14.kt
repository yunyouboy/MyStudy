package cn.readsense.stringmatching.kmp

/**
 *Author:qyg
 *DATE:2020/9/14 14:01
 *Description：
 **/
fun main() {
    var pString: String = "BBC ABCDAB ABCDABCDABDE"
    var cString: String = "ABCDABD"
    val firstIndex = kmp(pString, cString)
    if (-1 == firstIndex) {
        println("未找到匹配子串")
        return
    }
    println("firstIndex:${firstIndex}")
    for (index in firstIndex until firstIndex + cString.length) {
        print(pString[index])
    }
}

private fun kmp(parent: String, child: String): Int {
    var pLength = parent.length
    var cLength = child.length
    if (cLength > pLength) return -1
    val next = buildNext(child)
    var pIndex = 0
    var cIndex = 0
    while (pIndex < pLength && cIndex < cLength) {
        if (-1 == cIndex || parent[pIndex] == child[cIndex]) {
            pIndex++
            cIndex++
        } else {
            cIndex = next[cIndex]
        }
    }
    return if (cIndex == cLength) pIndex - cLength else -1
}

private fun buildNext(child: String): IntArray {
    var length = child.length
    var next: IntArray = IntArray(length)
    next[0] = -1
    var m = 0
    var n = -1
    while (m < length - 1) {
        if (-1 == n || child[m] == child[n]) {
            m++
            n++
            next[m] = n
        } else {
            n = next[n]
        }
    }
    return next
}