package cn.readsense.sortalgorithmskt

/**
 *Author:qyg
 *DATE:2020/8/24 16:46
 *Description：
 **/
fun main() {
    val array = intArrayOf(86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22)
    sort(array, 0, array.size - 1)
    array.forEach {
        println(it)
    }
}

private fun sort(array: IntArray, start: Int, end: Int) {
    if (array.isEmpty() || start < 0 || end >= array.size || start > end) return
    val partition = partition(array, start, end)
    if (partition > start) sort(array, start, partition - 1)
    if (partition < end) sort(array, partition + 1, end)
}

private fun partition(array: IntArray, start: Int, end: Int): Int {
    var pivot: Int = (start + Math.random() * (end - start + 1)).toInt()
    var zoneIndex = start - 1
    swap(array, pivot, end)
    for (index in start..end) {
        if (array[index] <= array[end]) {
            zoneIndex++
            if (zoneIndex < index) {
                swap(array, zoneIndex, index)
            }
        }
    }
    return zoneIndex
}

private fun swap(array: IntArray, i: Int, j: Int) {
    var temp = array[i]
    array[i] = array[j]
    array[j] = temp
}