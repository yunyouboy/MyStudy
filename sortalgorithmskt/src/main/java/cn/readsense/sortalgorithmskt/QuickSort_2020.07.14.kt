package cn.readsense.sortalgorithmskt

/**
 *Author:qyg
 *DATE:2020/7/14 10:37
 *Description：
 **/

fun main() {
    val array = intArrayOf(86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22)
    sort(array, 0, array.size - 1)
    array.map {
        println(it)
    }
}

private fun sort(array: IntArray, start: Int, end: Int) {
    if (array.isEmpty() || start < 0 || end >= array.size || end < start) return
    val partition = partition(array, start, end)
    if (start < partition) sort(array, start, partition - 1)
    if (end > partition) sort(array, partition + 1, end)
}

private fun partition(array: IntArray, start: Int, end: Int): Int {
    var pivot = (start + Math.random() * (end - start + 1)).toInt()
    var zoneIndex = start - 1
    swap(array, pivot, end)
    for (index in start..end) {
        if (array[index] <= array[end]) {
            zoneIndex++
            if (index > zoneIndex) {
                swap(array, index, zoneIndex)
            }
        }
    }
    return zoneIndex
}

/**
 * 交换
 */
private fun swap(array: IntArray, i: Int, j: Int) {
    var temp = array[i]
    array[i] = array[j]
    array[j] = temp
}