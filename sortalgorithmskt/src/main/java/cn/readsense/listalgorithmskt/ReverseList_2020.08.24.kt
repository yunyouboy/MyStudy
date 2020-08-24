package cn.readsense.listalgorithmskt

/**
 *Author:qyg
 *DATE:2020/8/24 16:40
 *Description：
 **/

fun main() {
    var head = ListNode(0)
    head.next = ListNode(1)
    head.next?.next = ListNode(2)
    head.next?.next?.next = ListNode(3)
    var reverse = reverse(head)
    while (null != reverse) {
        println(reverse.`val`)
        reverse = reverse.next
    }
}

private fun reverse(node: ListNode?): ListNode? {
    if (null == node?.next) return node
    var pre: ListNode? = null
    var cut = node
    var next: ListNode? = null
    while (null != cut) {
        next = cut.next
        cut.next = pre
        pre = cut
        cut = next
    }
    return pre
}