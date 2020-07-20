package cn.readsense.listalgorithmskt

/**
 *Author:qyg
 *DATE:2020/7/20 11:19
 *Description：
 **/

fun main() {
    var header: ListNode? = ListNode(0)
    header?.next = ListNode(1)
    header?.next?.next = ListNode(2)
    header?.next?.next?.next = ListNode(3)
    header?.next?.next?.next?.next = ListNode(4)
    var reverseList = reverseList(header)
    while (null != reverseList) {
        println(reverseList.`val`)
        reverseList = reverseList.next
    }


}

private fun reverseList(header: ListNode?): ListNode? {
    if (null == header?.next) return header
    var pre: ListNode? = null
    var cut: ListNode? = header
    var next: ListNode? = null
    while (null != cut) {
        next = cut.next
        cut.next = pre
        pre = cut
        cut = next
    }
    return pre
}