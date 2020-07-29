package cn.readsense.lrucache

/**
 *Author:qyg
 *DATE:2020/7/27 14:37
 *Description：
 **/

fun main() {
    val lru = LruCache20200727<Int, String>(3)

    lru[1] = "a"// 1:a
    println(lru.toString())

    lru[2] = "b"// 2:b 1:a
    println(lru.toString())

    lru[3] = "c" // 3:c 2:b 1:a
    println(lru.toString())

    lru.reverse()// 1:a 2:b 3:c
    println(lru.toString())

    lru.reverseBySwapData()// 3:c 2:b 1:a
    println(lru.toString())

    lru[4] = "d" //4:d 3:c 2:b
    println(lru.toString())

    lru[1] = "aa" // 1:aa 4:d 3:c
    println(lru.toString())

    lru[2] = "bb" // 2:bb 1:aa 4:d
    println(lru.toString())

    lru[5] = "e" // 5:e 2:bb 1:aa
    println(lru.toString())

    lru[1] // 1:aa 5:e 2:bb
    println(lru.toString())

    lru.remove(11) // 1:aa 5:e 2:bb
    println(lru.toString())

    lru.remove(1) //5:e 2:bb
    println(lru.toString())

    lru[1] = "aaa" //1:aaa 5:e 2:bb
    println(lru.toString())

    lru.removeLast() //1:aaa 5:e
    println(lru.toString())
}

class LruCache20200727<K, V> {
    private val defaultSize: Int = 16
    private var cacheSize: Int = defaultSize
    private lateinit var caches: HashMap<K, Node<K, V>>
    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null

    constructor(size: Int) {
        this.cacheSize = size
        caches = HashMap(size)
    }

    internal fun getSize() = caches.size

    operator fun get(k: K): V? {
        val node = caches[k] ?: return null
        moveToHead(node)
        return node.value
    }

    operator fun set(k: K, v: V) {
        var node = caches[k]
        node ?: run {
            if (caches.size >= cacheSize) {
                removeLast()
            }
            node = Node(k)
        }
        node!!.value = v
        moveToHead(node!!)
        caches[k] = node!!
    }

    internal fun remove(key: K) {
        val node = caches[key] ?: return
        node.pre?.next = node.next
        node.next?.pre = node.pre
        if (node == tail) tail = tail!!.pre
        if (node == head) head = head!!.next
        caches.remove(key)
    }

    internal fun clear() {
        head = null
        tail = null
        caches.clear()
    }

    fun removeLast() {
        tail?.run {
            caches.remove(tail!!.key)
            tail = tail!!.pre
            if (null == tail) head = null else tail!!.next = null
        }
    }

    private fun moveToHead(node: Node<K, V>) {
        when (node) {
            head -> return
            else -> {
                node.pre?.next = node.next
                node.next?.pre = node.pre
                if (node == tail) tail = node.pre
                if (null == tail || null == head ) {
                    head = node
                    tail = head
                    return
                }
                node.next = head
                head!!.pre = node
                node.pre = null
                head = node
            }
        }
    }

    internal fun reverseBySwapData() {
        var headNode: Node<K, V>? = head
        var tailNode: Node<K, V>? = tail
        while (headNode != tailNode) {
            var key = headNode!!.key
            var value: V? = headNode!!.value
            headNode!!.key = tailNode!!.key
            headNode!!.value = tailNode!!.value
            tailNode.key = key
            tailNode.value = value
            headNode = headNode.next
            if (headNode == tailNode) return
            tailNode = tailNode.pre
        }
    }

    internal fun reverse() {
        var temp: Node<K, V>? = null
        var cut: Node<K, V>? = head
        tail = head//翻转后的尾 为 未反转时的头
        while (null != cut) {
            temp = cut.pre
            cut.pre = cut.next
            cut.next = temp
            cut = cut.pre
        }
        if (null != temp) {//翻转之后temp为倒数第二个节点
            head = temp.pre
        }
    }

    override fun toString(): String {
        var sb = StringBuilder()
        var node = head
        while (null != node) {
            sb.append("${node.key} ")
            sb.append("${node.value} ")
            sb.append(",")
            node = node.next
        }
        sb.append( caches.size)
        sb.append("\n")
        return sb.toString()
    }

    inner class Node<T, U> {
        internal var key: T? = null
        internal var value: U? = null
        internal var pre: Node<T, U>? = null
        internal var next: Node<T, U>? = null

        constructor(key: T) {
            this.key = key
        }
    }
}