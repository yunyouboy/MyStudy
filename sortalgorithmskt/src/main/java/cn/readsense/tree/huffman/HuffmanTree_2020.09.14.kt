package cn.readsense.tree.huffman

/**
 *Author:qyg
 *DATE:2020/9/14 14:16
 *Description：
 **/
fun main() {
    HuffmanTree20200914().run {
        printTree(buildTree(createNotes()))
    }
}

private class HuffmanTree20200914 {
    internal fun createNotes(): ArrayList<Node<String>> {
        var nodes = ArrayList<Node<String>>()
        nodes.add(Node("a", 10))
        nodes.add(Node("b", 15))
        nodes.add(Node("c", 12))
        nodes.add(Node("d", 3))
        nodes.add(Node("e", 4))
        nodes.add(Node("f", 13))
        nodes.add(Node("g", 1))
        return nodes
    }

    internal fun <T> buildTree(nodes: ArrayList<Node<T>>): Node<T> {
        while (nodes.size > 1) {
            sort(nodes)
            var left = nodes[0]
            var right = nodes[1]
            var parent = Node<T>(null, left.weight + right.weight)
            parent.left = left
            parent.right = right
            nodes.remove(left)
            nodes.remove(right)
            nodes.add(parent)
        }
        return nodes[0]
    }

    internal fun <T> printTree(root: Node<T>) {
        println("${root.toString()}")
        root.left?.run {
            println("left:")
            printTree(root.left!!)
        }
        root.right?.run {
            println("right:")
            printTree(root!!.right!!)
        }
    }

    private fun <T> sort(nodes: ArrayList<Node<T>>) {
        for (i in 0 until nodes.size) {
            for (j in 0 until nodes.size - i - 1) {
                if (nodes[j].weight > nodes[j + 1].weight) {
                    var temp = nodes[j]
                    nodes[j] = nodes[j + 1]
                    nodes[j + 1] = temp
                }
            }
        }
    }

    inner class Node<N> {
        var data: N? = null
        var weight = 0
        var left: Node<N>? = null
        var right: Node<N>? = null

        constructor(data: N?, weight: Int) {
            this.data = data
            this.weight = weight
        }

        override fun toString(): String {
            return "node[data=${data},weight=${weight}]"
        }
    }
}