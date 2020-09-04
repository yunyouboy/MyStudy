package cn.readsense.lrucache

/**
 *Author:qyg
 *DATE:2020/9/4 10:37
 *Description：
 **/
fun main() {
    val lru = LruCache20200904<Int, String>(3)

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

    lru.removeTail() //1:aaa 5:e
    println(lru.toString())

    lru.clear()
    println(lru.toString())
}

private class LruCache20200904<K, V> {
    private val defaultSize = 16
    private var cacheSize = defaultSize
    private var caches: HashMap<K, Node<K, V>> = HashMap(cacheSize)
    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null

    constructor(size: Int) {
        this.cacheSize = size
        this.caches = HashMap(cacheSize)
    }

    operator fun get(k: K): V? {
        var node = caches[k] ?: return null
        move2Head(node)
        return node.value
    }

    operator fun set(k: K, v: V) {
        var node = caches[k]
        node ?: run {
            if (caches.size >= cacheSize) removeTail()
            node = Node(k)
        }
        node!!.value = v
        move2Head(node!!)
        caches[k] = node!!
    }

    internal fun move2Head(node: Node<K, V>) {
        when (node) {
            head -> return
            else -> {
                node.pre?.next = node.next
                node.next?.pre = node.pre
                if (node == tail) tail = tail?.pre
                if (null == head || null == tail) {
                    head = node
                    tail = head
                    return
                }
                node.next = head
                head?.pre = node
                node.pre = null
                head = node
            }
        }
    }

    internal fun removeTail() {
        tail?.run {
            caches.remove(this.key)
            tail = this.pre
            if (null == tail) head = tail else tail?.next = null
        }
    }

    internal fun remove(k: K): V? {
        val node = caches[k] ?: return null
        caches.remove(k)
        node.pre?.next = node.next
        node.next?.pre = node.pre
        if (tail == node) tail = tail?.pre
        if (head == node) head = head?.next
        return node.value
    }

    internal fun clear() {
        caches.clear()
        head = null
        tail = null
    }

    internal fun reverse() {
        var temp: Node<K, V>? = null
        var cut = head
        tail = head
        while (null != cut) {
            temp = cut.pre
            cut.pre = cut.next
            cut.next = temp
            cut = cut.pre
        }
        if (null != temp) head = temp.pre
    }

    internal fun reverseBySwapData() {
        var headNode = head
        var tailNode = tail
        while (headNode !== tailNode && null != headNode && null != tailNode) {
            var key = headNode.key
            var value = headNode.value
            headNode.key = tailNode.key
            headNode.value = tailNode.value
            tailNode.key = key
            tailNode.value = value
            headNode = headNode.next
            if (headNode === tailNode) return else tailNode = tailNode.pre
        }
    }

    override fun toString(): String {
        var node = head
        var sp = StringBuilder()
        while (null != node) {
            sp.append("${node.key} : ${node.value}, ")
            node = node.next
        }
        sp.append(" ${caches.size}\n")
        return sp.toString()
    }

    private inner class Node<U, T> {
        internal var key: U? = null
        internal var value: T? = null
        internal var pre: Node<U, T>? = null
        internal var next: Node<U, T>? = null

        constructor(k: U) {
            this.key = k
        }
    }
}