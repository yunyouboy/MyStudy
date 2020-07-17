package cn.readsense.listalgorithmskt

/**
 *Author:qyg
 *DATE:2020/7/17 10:15
 *Description：
 **/

fun main() {
    var header: ListNode? = null
    header = ListNode(0)
    header.next = ListNode(1)
    header.next?.next = ListNode(2)
    header.next?.next?.next = ListNode(3)
    header.next?.next?.next?.next = ListNode(4)
    var reverseList = reverseList(header)
    while (null != reverseList) {
        println(reverseList.`val`)
        reverseList = reverseList.next
    }
}

private fun reverseList(header: ListNode?): ListNode? {
    if (null == header) return header
    var pre: ListNode? = null
    var cur: ListNode? = header
    var next: ListNode? = null
    while (null != cur) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}