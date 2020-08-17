package cn.readsense.sortalgorithmskt

/**
 *Author:qyg
 *DATE:2020/8/17 9:54
 *Description：
 **/
fun main() {
    val intArray = intArrayOf(86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22)
    sort(intArray, 0, intArray.size - 1)
    intArray.forEach {
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
    var zoneIndex: Int = start - 1
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

private fun swap(array: IntArray, i: Int, j: Int) {
    var temp = array[i]
    array[i] = array[j]
    array[j] = temp
}