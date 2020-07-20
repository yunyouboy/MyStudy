package cn.readsense.lurcache


/**
 *Author:qyg
 *DATE:2020/7/17 17:30
 *Description：
 **/

fun main() {
    val lru = LruCache<Int, String>(3)

    lru[1] = "a"// 1:a

    println(lru.toString())
    lru[2] = "b"// 2:b 1:a

    println(lru.toString())
    lru[3] = "c" // 3:c 2:b 1:a

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
}


class LruCache<K, V> {
    private val defaultCacheSize: Int = 16

    private var currentCacheSize: Int = 0
    private var cacheSize: Int = defaultCacheSize
    private lateinit var caches: HashMap<K, CacheNode<K, V>>
    private var first: CacheNode<K, V>? = null
    private var last: CacheNode<K, V>? = null

    constructor(size: Int) {
        this.currentCacheSize = 0
        this.cacheSize = size
        caches = HashMap<K, CacheNode<K, V>>(size)
    }

    operator fun set(k: K, v: V) {
        var node = caches[k]
        node ?: run {//缓存中没有该key
            if (caches.size >= cacheSize) {//缓存容量已经达到最大值了，不能装了
                caches.remove(last?.key)//删除HashMap中的Node
                removeLast()//删除双向链表中的尾结点Node
            }
            node = CacheNode()
            node!!.key = k
        }
        node!!.value = v
        moveToFirst(node!!)
        caches[k] = node!!
    }

    operator fun get(k: K): V? {
        val node = caches[k] ?: return null
        moveToFirst(node)
        return node.value
    }

    fun remove(k: K): Unit {
        val node: CacheNode<K, V>? = caches[k]
        if (node != null) {
            if (node.pre != null) {
                node.pre!!.next = node.next
            }
            if (node.next != null) {
                node.next!!.pre = node.pre
            }
            if (node == first) {
                first = node.next
            }
            if (node == last) {
                last = node.pre
            }
        }
    }

    fun clear() {
        first = null
        last = null
        caches.clear()
    }

    private fun removeLast() {
        if (last != null) {
            last = last!!.pre
            if (last == null) {
                first = null
            } else {
                last!!.next = null
            }
        }
    }

    /**
     * @param node 插入的结点
     * put数据，将新数据放到链表头部，这样链表头部就是最新的数据，尾部就是最少访问的数据
     */
    private fun moveToFirst(node: CacheNode<K, V>) {
        if (first == node) {
            return
        }
        if (node.next != null) {
            node.next!!.pre = node.pre
        }
        if (node.pre != null) {
            node.pre!!.next = node.next
        }
        if (node == last) {
            last = last!!.pre
        }
        if (first == null || last == null) {
            last = node
            first = last
            return
        }
        node.next = first
        first!!.pre = node
        first = node
        first!!.pre = null
    }

    override fun toString(): String {
        var sb = StringBuilder()
        var node: CacheNode<K, V>? = first
        while (node != null) {
            sb.append("${node.key} ")
            sb.append("${node.value} ")
            sb.append(", ")
            node = node.next
        }
        return sb.toString()
    }

    inner class CacheNode<K, V> {
        var key: K? = null
        var value: V? = null
        var pre: CacheNode<K, V>? = null
        var next: CacheNode<K, V>? = null
    }

}

