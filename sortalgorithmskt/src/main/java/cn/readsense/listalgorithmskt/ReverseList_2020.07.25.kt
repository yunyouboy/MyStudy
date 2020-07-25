package cn.readsense.listalgorithmskt

fun main() {
    var head: ListNode = ListNode(1)
    head.next = ListNode(2)
    head.next?.next = ListNode(3)
    head.next?.next?.next = ListNode(4)
    var reverseList = reverseList(head)
    while (null != reverseList) {
        println(reverseList.`val`)
        reverseList = reverseList.next
    }
}

private fun reverseList(head: ListNode?): ListNode? {
    head?.next ?: return head
    var pre: ListNode? = null
    var cut: ListNode? = head
    var next: ListNode? = null
    while (null != cut) {
        next = cut.next
        cut.next = pre
        pre = cut
        cut = next
    }
    return pre
}