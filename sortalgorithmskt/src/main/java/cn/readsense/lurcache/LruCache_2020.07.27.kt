package cn.readsense.lurcache

/**
 *Author:qyg
 *DATE:2020/7/27 14:37
 *Description：
 **/

fun main() {
    val lru = LruCache20200725<Int, String>(3)

    lru[1] = "a"// 1:a
    println(lru.toString() + lru.getSize())

    lru[2] = "b"// 2:b 1:a
    println(lru.toString() + lru.getSize())

    lru[3] = "c" // 3:c 2:b 1:a
    println(lru.toString() + lru.getSize())

    lru.reverseList()// 1:a 2:b 3:c
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
                caches.remove(tail?.key)
            }
            node = Node<K, V>(k)
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

    private fun removeLast() {
        tail?.run {
            var originTail = tail
            tail = tail!!.pre
            if (null == tail) head = null else tail!!.next = null
            caches.remove(originTail!!.key)
        }
    }

    private fun moveToHead(node: Node<K, V>) {
        when (node) {
            head -> return
            else -> {
                node.pre?.next = node.next
                node.next?.pre = node.pre
                if (node == tail) tail = node.pre
                if (null == head || tail == head) {
                    head = node
                    tail = head
                    return
                }
                head!!.pre = node
                node.next = head
                node.pre = null
                head = node
            }
        }
    }

    private fun reverseBySwap() {
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

    private fun reverse() {
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