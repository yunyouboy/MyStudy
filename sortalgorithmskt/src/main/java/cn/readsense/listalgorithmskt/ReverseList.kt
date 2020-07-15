package cn.readsense.listalgorithmskt

/**
 *Author:qyg
 *DATE:2020/7/14 11:37
 *Description 单链表反转
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

private fun reverseList(head: ListNode?): ListNode? {
    if (head?.next == null) {
        return head
    }
    var pre: ListNode? = null
    var cur: ListNode? = head
    var next: ListNode? = null
    while (null != cur) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}