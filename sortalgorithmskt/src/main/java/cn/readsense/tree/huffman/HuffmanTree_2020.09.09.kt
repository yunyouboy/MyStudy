package cn.readsense.tree.huffman

/**
 *Author:qyg
 *DATE:2020/9/9 18:42
 *Description：
 **/
fun main() {
    HuffmanTree20200909().run {
        printTree(buildTree(createNodeList()))
    }
}

class HuffmanTree20200909 {

    /**
     * 构造测试数据
     */
    internal fun createNodeList(): ArrayList<Node<String>> {
        var nodes: ArrayList<Node<String>> = ArrayList<Node<String>>()
        //把节点加入至list中
        //把节点加入至list中
        nodes.add(Node<String>("a", 10))
        nodes.add(Node<String>("b", 15))
        nodes.add(Node<String>("c", 12))
        nodes.add(Node<String>("d", 3))
        nodes.add(Node<String>("e", 4))
        nodes.add(Node<String>("f", 13))
        nodes.add(Node<String>("g", 1))
        return nodes
    }

    /**
     * 构造哈夫曼树
     *
     * @param nodes 节点集合
     * @return 构造出来的哈夫曼树的根节点
     */
    internal fun <T> buildTree(nodes: ArrayList<Node<T>>): Node<T> {
        while (nodes.size >= 2) {
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

    /**
     * 冒泡排序，用于对节点进行排序（增序排序）
     */
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

    /**
     * 递归打印哈夫曼树(先左子树，后右子树打印)
     * 中序遍历
     */
    internal fun <T> printTree(root: Node<T>) {
        println("${root.toString()}")
        root.left?.run {
            println("left:")
            printTree(root.left!!)
        }
        root.right?.run {
            println("right:")
            printTree(root.right!!)
        }
    }


    inner class Node<T> {
        var data: T? = null
        var weight: Int = 0
        var left: Node<T>? = null
        var right: Node<T>? = null

        constructor(data: T?, weight: Int) {
            this.data = data
            this.weight = weight
        }

        override fun toString(): String {
            return "Node[weight=${weight},data=${data}]"
        }
    }
}