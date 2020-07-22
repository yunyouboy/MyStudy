package cn.readsense.lurcache

/**
 *Author:qyg
 *DATE:2020/7/22 10:56
 *Description：
 **/

fun main() {
    val lru = LurCache20200722<Int, String>(3)

    lru[1] = "a"// 1:a
    println(lru.toString() + lru.getSize())

    lru[2] = "b"// 2:b 1:a
    println(lru.toString() + lru.getSize())

    lru[3] = "c" // 3:c 2:b 1:a
    println(lru.toString() + lru.getSize())

    lru.reverserList()// 1:a 2:b 3:c
    println(lru.toString() + lru.getSize())

    lru.reverseListBySwapData()// 3:c 2:b 1:a
    println(lru.toString() + lru.getSize())

    lru[4] = "d" //4:d 3:c 2:b
    println(lru.toString() + lru.getSize())

    lru[1] = "aa" // 1:aa 4:d 3:c
    println(lru.toString() + lru.getSize())

    lru[2] = "bb" // 2:bb 1:aa 4:d
    println(lru.toString() + lru.getSize())

    lru[5] = "e" // 5:e 2:bb 1:aa
    println(lru.toString() + lru.getSize())

    lru[1] // 1:aa 5:e 2:bb
    println(lru.toString() + lru.getSize())

    lru.remove(11) // 1:aa 5:e 2:bb
    println(lru.toString() + lru.getSize())

    lru.remove(1) //5:e 2:bb
    println(lru.toString() + lru.getSize())

    lru[1] = "aaa" //1:aaa 5:e 2:bb
    println(lru.toString() + lru.getSize())

    lru.removeLast() //1:aaa 5:e
    println(lru.toString() + lru.getSize())
}

class LurCache20200722<K, V> {

    private val defaultSize: Int = 16
    private var cacheSize = defaultSize
    private lateinit var caches: HashMap<K, Node<K, V>>
    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null

    constructor() {
        caches = HashMap(cacheSize)
    }

    constructor(size: Int) {
        cacheSize = size
        caches = HashMap(cacheSize)
    }

    fun getSize() = caches.size

    operator fun set(k: K, v: V) {
        var node = caches[k]
        node ?: run {
            if (caches.size >= cacheSize) {
                caches.remove(tail?.key)
                removeLast()
            }
            node = Node(k)
        }
        node!!.value = v
        moveToHead(node!!)
        caches[k] = node!!
    }

    operator fun get(k: K): V? {
        val node = caches[k] ?: return null
        moveToHead(node)
        return node.value
    }

    fun remove(k: K) {
        val node = caches[k] ?: return
        node.pre?.next = node.next
        node.next?.pre = node.pre
        if (head == node) head = node.next
        if (tail == node) tail = node.pre
        caches.remove(k)
    }

    fun clear() {
        head = null
        tail = null
        caches.clear()
    }

    fun removeLast() {
        tail?.run {
            var origTail = tail
            tail = tail!!.pre
            if (null == tail) head = null else tail!!.next = null
            caches.remove(origTail!!.key)
        }
    }

    private fun moveToHead(node: Node<K, V>) {
        if (node == head) return
        node.pre?.next = node.next
        node.next?.pre = node.pre
        if (tail == node) tail = node.pre
        if (null == head || tail == null) {
            head = node
            tail = head
            return
        }
        node.next = head
        head!!.pre = node
        node.pre = null
        head = node
    }

    override fun toString(): String {
        var sb: StringBuilder = StringBuilder()
        var node = head
        while (null != node) {
            sb.append("${node.key} ")
            sb.append("${node.value} ")
            sb.append(",")
            node = node.next
        }
        return sb.toString()
    }

    fun reverserList() {
        //定义临时节点指针，初始化值为null
        var temp: Node<K, V>? = null
        //定义当前节点指针，初始化指向head节点
        var cut = head
        //tail指针指向head，原链表的head即为反转后的tail
        tail = head
        while (null != cut) {
            //temp指向当前节点的前一个节点（初始时head的前一个节点为null）
            temp = cut.pre
            //当前节点的前向指针指向当前节点的下一个节点
            cut.pre = cut.next
            //当前节点的next指针指向临时节点指针（temp初始时为null）
            cut.next = temp
            //当前节点指针current指向下一个节点（往后移动）
            cut = cut.pre
        }
        //循环结束后temp指向原链表倒数第二个节点，利用temp的前向指针（pre）指向原链表倒数第一个节点，将head指向反转后链表的第一个节点
        if (null != temp) {
            head = temp.pre
        }
    }

    fun reverseListBySwapData() {
        var headNode = head
        var tailNode = tail
        while (headNode != tailNode) {
            val key = headNode?.key
            var value = headNode?.value
            headNode?.key = tailNode?.key
            headNode?.value = tailNode?.value
            tailNode?.key = key
            tailNode?.value = value
            headNode = headNode?.next
            if (headNode == tailNode) {
                break
            }
            tailNode = tailNode?.pre
        }
    }

    inner class Node<U, T> {
        var key: U? = null
        var value: T? = null
        var pre: Node<U, T>? = null
        var next: Node<U, T>? = null

        constructor(u: U) {
            this.key = u
        }

        constructor(u: U, t: T) {
            this.key = u
            this.value = t
        }
    }
}