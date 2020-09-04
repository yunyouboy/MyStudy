package cn.readsense.listalgorithmskt

/**
 *Author:qyg
 *DATE:2020/9/4 10:00
 *Description：
 **/
fun main() {
    var head = ListNode(0)
    head.next = ListNode(1)
    head.next!!.next = ListNode(2)
    head.next!!.next!!.next = ListNode(3)
    var reverse = reverse(head)
    while (null != reverse) {
        println("${reverse.`val`}")
        reverse = reverse.next
    }
}

private fun reverse(head: ListNode?): ListNode? {
    if (null == head?.next) return null
    var pre: ListNode? = null
    var cut = head
    var next: ListNode? = null
    while (null != cut) {
        next = cut.next
        cut.next = pre
        pre = cut
        cut = next
    }
    return pre
}