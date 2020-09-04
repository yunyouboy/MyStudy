package cn.readsense.stringmatching.bruteforce

/**
 *Author:qyg
 *DATE:2020/8/21 10:35
 *Description：
 **/

fun main() {
    var pString: String = "BBC ABCDAB ABCDABCDABDE"
    var cString: String = "ABCDABD"
    val firstIndex = bruteForce(pString, cString)
    if (-1 == firstIndex) {
        println("未找到匹配子串")
        return
    }
    println("firstIndex:${firstIndex}")
    for (index in firstIndex until firstIndex + cString.length) {
        print(pString[index])
    }
}

private fun bruteForce(pString: String, cString: String): Int {
    var pLength = pString.length
    var cLength = cString.length
    if (cLength > pLength) return -1
    var pIndex = 0
    var cIndex = 0
    while (pIndex < pLength && cIndex < cLength) {
        if (pString[pIndex] == cString[cIndex]) {
            pIndex++
            cIndex++
        } else {
            pIndex = pIndex - cIndex + 1
            cIndex = 0
        }
    }
    return if (cIndex == cLength) pIndex - cIndex else -1
}