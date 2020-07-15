package cn.readsense.sortalgorithmskt

fun main() {
    val array = intArrayOf(86, 11, 77, 23, 32, 45, 58, 63, 93, 4, 37, 22)
    sort(array, 0, array.size - 1)
    for (index in array.indices) {
        println(array[index])
    }
}

/**
 * 快排
 */
private fun sort(array: IntArray, start: Int, end: Int) {
    if (array.isEmpty() || start < 0 || end >= array.size || start > end) {
        return
    }
    var zoneIndex = partition(array, start, end)
    if (zoneIndex > start) sort(array, start, zoneIndex - 1)
    if (zoneIndex < end) sort(array, zoneIndex + 1, end)
}

/**
 *选取切分点
 */
private fun partition(array: IntArray, start: Int, end: Int): Int {
    //TODO 1.选取基准位置
    var pivot: Int = (start + Math.random() * (end - start + 1)).toInt()
    var zoneIndex = start - 1//分割指示器
    swap(array, pivot, end)//交换基准位置和末尾位置
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
 * 交换数组中指定位置的值
 */
private fun swap(array: IntArray, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}