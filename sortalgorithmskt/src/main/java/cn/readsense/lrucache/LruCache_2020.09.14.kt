package cn.readsense.lrucache

/**
 *Author:qyg
 *DATE:2020/9/14 10:14
 *Description：
 **/
fun main() {
    val lru = LruCache20200914<Int, String>(3)

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

private class LruCache20200914<K, V> {
    val defaultSize = 16
    var cacheSize = defaultSize
    var caches: HashMap<K, Node<K, V>> = HashMap(cacheSize)
    var head: Node<K, V>? = null
    var tail: Node<K, V>? = null

    constructor(size: Int) {
        cacheSize = size
        caches = HashMap(cacheSize)
    }

    operator fun get(k: K): V? {
        var node = caches[k] ?: return null
        move2Head(node)
        return node.v
    }

    operator fun set(k: K, v: V) {
        var node = caches[k]
        node ?: run {
            if (caches.size >= cacheSize) removeTail()
            node = Node(k)
        }
        node!!.v = v
        move2Head(node!!)
        caches[k] = node!!
    }

    internal fun move2Head(node: Node<K, V>) {
        when (node) {
            head -> return
            else -> {
                node.pre?.next = node.next
                node.next?.pre = node.pre
                if (node === tail) tail = node.pre
                if (null == tail || null == head) {
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

    internal fun removeTail() {
        if (null == tail) return
        caches.remove(tail!!.k)
        tail = tail!!.pre
        if (null == tail) head = null else tail!!.next = null
    }

    internal fun remove(k: K): V? {
        var node = caches[k] ?: return null
        node.pre?.next = node.next
        node.next?.pre = node.pre
        if (node === tail) tail = node.pre
        if (node === head) head = node.next
        caches.remove(k)
        return node.v
    }

    internal fun clear() {
        head = null
        tail = null
        caches.clear()
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
        while (headNode !== tailNode) {
            var k = headNode?.k
            var v = headNode?.v
            headNode?.k = tailNode?.k
            headNode?.v = tailNode?.v
            tailNode?.k = k
            tailNode?.v = v
            headNode = headNode?.next
            return if (headNode === tailNode) return else tailNode = tailNode?.pre
        }
    }

    override fun toString(): String {
        var headNode = head
        var sb = StringBuilder()
        while (null != headNode) {
            sb.append("key= ${headNode.k} val = ${headNode.v},")
            headNode = headNode.next
        }
        sb.append("size= ${caches.size}\n")
        return sb.toString()
    }

    internal class Node<N, M> {
        var k: N? = null
        var v: M? = null
        var pre: Node<N, M>? = null
        var next: Node<N, M>? = null

        constructor(k: N) {
            this.k = k
        }
    }
}