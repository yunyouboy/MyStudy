package cn.readsense.stringmatching

/**
 *Author:qyg
 *DATE:2020/8/19 15:16
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

private fun kmp(p: String, c: String): Int {
    var pLength = p.length
    var cLength: Int = c.length
    if (cLength > pLength) return -1
    val next = buildNext(c)

    var i: Int = 0
    var j: Int = 0
    while (i < pLength && j < cLength) {
        if (j == -1 || p[i] == c[j]) {
            i++
            j++
        } else {
            j = next[j]
        }
    }
    return if (j >= cLength) i - j else -1
}


fun buildNext(child: String): IntArray {
    //构建next表就是查找真前缀 == 真后缀的最大长度，以获取模式串尽量多地往右移动
    val length = child.length
    val next = IntArray(length)
    next[0] = -1
    var j = 0 //主串位置
    var k = -1 //子串位置
    while (j < length - 1) {
        if (k < 0 || child[j] == child[k]) {
            j++
            k++
            next[j] = k
        } else { //失配
            k = next[k]
        }
    }
    return next
}
