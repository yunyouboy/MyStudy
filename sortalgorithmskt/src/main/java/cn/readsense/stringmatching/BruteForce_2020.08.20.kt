package cn.readsense.stringmatching

/**
 *Author:qyg
 *DATE:2020/8/20 18:47
 *Description：
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

private fun bruteForce(parent: String, child: String): Int {
    var pLength = parent.length
    var cLength = child.length
    if (cLength > pLength) return -1
    var i: Int = 0
    var j: Int = 0
    while (i < pLength && j < cLength) {
        if (parent[i] == child[j]) {
            i++
            j++
        } else {
            i = i - j + 1
            j = 0
        }
    }
    return if (j >= cLength - 1) i - j else -1
}