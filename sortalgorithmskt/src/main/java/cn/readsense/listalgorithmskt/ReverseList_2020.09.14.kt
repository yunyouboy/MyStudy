package cn.readsense.listalgorithmskt

/**
 *Author:qyg
 *DATE:2020/9/14 10:05
 *Description：
 **/
fun main() {
    var node = ListNode(0)
    node.next = ListNode(1)
    node.next?.next = ListNode(2)
    node.next?.next?.next = ListNode(3)
    var reverse = reverse(node)
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