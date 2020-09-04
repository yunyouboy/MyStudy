package cn.readsense.stringmatching.kmp

/**
 *Author:qyg
 *DATE:2020/8/24 16:23
 *Description：
 **/
fun main() {
    var parent: String = "BBC ABCDAB ABCDABCDABDE"
    var child: String = "ABCDABD"
    val firstIndex = kmp(parent, child)
    if (-1 == firstIndex) {
        println("未找到匹配子串")
        return
    }
    println("firstIndex:${firstIndex}")
    for (index in firstIndex until firstIndex + child.length) {
        print(parent[index])
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
        if (cIndex == -1 || parent[pIndex] == child[cIndex]) {
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
    var m = 0//主串位置
    var n = -1//子串位置
    while (m < length - 1) {
        if (n < 0 || child[m] == child[n]) {
            m++
            n++
            next[m] = n
        } else {
            n = next[n]
        }
    }
    return next
}