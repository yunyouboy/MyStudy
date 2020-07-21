package cn.readsense.lurcache

/**
 *Author:qyg
 *DATE:2020/7/21 11:24
 *Description：
 **/
fun main() {
    val lru = LruCache<Int, String>(3)

    lru[1] = "a"// 1:a

    println(lru.toString() + lru.getSize())
    lru[2] = "b"// 2:b 1:a

    println(lru.toString() + lru.getSize())
    lru[3] = "c" // 3:c 2:b 1:a

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

class LruCache20200721<K, V> {
    private val defaultCacheSize = 16
    private var cacheSize: Int = defaultCacheSize
    private lateinit var caches: HashMap<K, CacheNode20200721<K, V>>
    private var first: CacheNode20200721<K, V>? = null
    private var last: CacheNode20200721<K, V>? = null

    constructor(size: Int) {
        cacheSize = size
        caches = HashMap(size)
    }

    operator fun set(k: K, v: V) {
        var node = caches[k]
        node ?: run {
            if (caches.size >= cacheSize) {
                caches.remove(last?.key)
                removeLast()
            }
            node = CacheNode20200721()
            node!!.key = k
        }
        node!!.value = v
        moveToFirst(node!!)
        caches[k] = node!!
    }

    operator fun get(k: K): V? {
        val node = caches[k] ?: return null
        moveToFirst(node!!)
        return node.value
    }

    fun remove(k: K) {
        val node = caches[k] ?: return
        if (null != node.next) node.next!!.pre = node.pre
        if (null != node.pre) node.pre!!.next = node.next
        if (first == node) first = node.next
        if (last == node) last = node.pre
        caches.remove(k)
    }

    fun clear() {
        first = null
        last = null
        caches.clear()
    }

    private fun removeLast() {
        last?.run {
            var originLast = last
            last = last!!.pre
            if (last == null) {
                first == null
            } else {
                last!!.next = null
            }
            caches.remove(originLast!!.key)
        }
    }

    private fun moveToFirst(node: CacheNode20200721<K, V>) {
        if (first == node) return
        if (null != node.pre) node.pre!!.next = node.next
        if (null != node.next) node.next!!.pre = node.pre
        if (last == node) last = last!!.pre//node是尾节点，处理尾节点
        if (null == first || null == last) {
            first = node
            last = first
            return
        }
        node.next = first
        first!!.pre = node
        node.pre = null
        first = node
    }

    override fun toString(): String {
        var sb: StringBuilder = StringBuilder()
        var node = first
        while (null != node) {
            sb.append("${node.key} ")
            sb.append("${node.value} ")
            sb.append(",")
            node = node.next
        }
        return sb.toString()
    }

    inner class CacheNode20200721<T, U> {
        var key: T? = null
        var value: U? = null
        var pre: CacheNode20200721<T, U>? = null
        var next: CacheNode20200721<T, U>? = null
    }
}