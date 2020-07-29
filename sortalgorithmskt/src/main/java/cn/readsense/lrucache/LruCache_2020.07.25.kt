package cn.readsense.lrucache

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

class LruCache20200725<K, V> {
    private val defaultCacheSize: Int = 16
    private var cacheSize = defaultCacheSize
    private lateinit var caches: HashMap<K, Node<K, V>>
    private var head: Node<K, V>? = null
    private var tail: Node<K, V>? = null

    constructor(size: Int) {
        this.cacheSize = size
        caches = HashMap(cacheSize)
    }

    fun getSize(): Int = caches.size

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

    fun remove(k: K) {
        var node: Node<K, V> = caches[k] ?: return
        node.pre?.next = node.next
        node.next?.pre = node.pre
        if (node == tail) tail = node.pre
        if (node == head) head = node.next
        caches.remove(k)
    }

    private fun clear() {
        head = null
        tail = null
        caches.clear()
    }

    private fun moveToHead(node: Node<K, V>) {
        when (node) {
            head -> return
            else -> {
                node.pre?.next = node.next
                node.next?.pre = node.pre
                if (tail == node) tail = node.pre
                if (null == tail || null == head) {
                    head = node
                    tail = head
                    return
                }
                node.next = head
                head!!.pre = node
                head = node
                node.pre = null
            }
        }

    }

    fun removeLast() {
        tail?.run {
            caches.remove(tail!!.key)
            tail = tail!!.pre
            if (null == tail) head = null else tail!!.next = null
        }
    }

    fun reverseList() {
        var temp: Node<K, V>? = null
        var cur: Node<K, V>? = head
        tail = head
        while (null != cur) {
            temp = cur.pre
            cur.pre = cur.next
            cur.next = temp
            cur = cur.pre
        }
        if (null != temp) {
            head = temp.pre
        }
    }

    fun reverseListBySwapData() {
        var headNode: Node<K, V>? = head
        var tailNode: Node<K, V>? = tail
        while (headNode != tailNode) {
            var `key`: K? = headNode?.key
            var `val`: V? = headNode?.value
            headNode?.key = tailNode?.key
            headNode?.value = tailNode?.value
            tailNode?.key = `key`
            tailNode?.value = `val`
            headNode = headNode?.next
            if (headNode == tailNode) return
            tailNode = tailNode?.pre
        }
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