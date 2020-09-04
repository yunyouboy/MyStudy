package cn.readsense.stringmatching.bruteforce

/**
 *Author:qyg
 *DATE:2020/8/19 14:35
 *Description：BF算法是最原始、最暴力的求解过程，但也是其他匹配算法的基础
 **/

fun main() {
    var parent: String = "BBC ABCDAB ABCDABCDABDE"
    var child: String = "ABCDABD"
    val firstIndex = bruteForce(parent, child)
    if (-1 == firstIndex) {
        println("未找到匹配子串")
        return
    }
    println("firstIndex:${firstIndex}")
    for (index in firstIndex until firstIndex + child.length) {
        print(parent[index])
    }
}

private fun bruteForce(p: String, c: String): Int {
    var pLength = p.length
    var cLength: Int = c.length
    if (pLength < cLength) return -1

    var i: Int = 0
    var j: Int = 0
    while (i < pLength && j < cLength) {
        if (p[i] == c[j]) {
            i++
            j++
        } else {
            i = i - j + 1
            j = 0
        }
    }
    return if (j >= cLength) i - j else -1
}