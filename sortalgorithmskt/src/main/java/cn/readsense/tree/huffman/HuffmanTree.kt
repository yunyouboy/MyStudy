package cn.readsense.tree.huffman

/**
 *Author:qyg
 *DATE:2020/9/8 10:32
 *Description：
 **/
fun main() {
    HuffmanTree().run {
        printTree(buildTree(createNodeList()))
    }
}

class HuffmanTree {

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
        val size = nodes.size
        if (size <= 1) return
        for (i in 0 until size) {
            for (j in 0 until size - i - 1) {
                if (nodes[j].weight > nodes[j + 1].weight) run {
                    var tem = nodes[j + 1]
                    nodes[j + 1] = nodes[j]
                    nodes[j] = tem
                }
            }
        }
    }

    /**
    * 递归打印哈夫曼树(先左子树，后右子树打印)
    */
    internal fun <T> printTree(root: Node<T>) {
        println("${root.toString()}")
        if (root.left != null) {
            print("left:")
            printTree(root.left!!)
        }
        if (root.right != null) {
            print("right:")
            printTree(root.right!!)
        }
    }

    inner class Node<N> {
        private var data: N? = null//数据
        var weight = 0//权重
        var left: Node<N>? = null//左子树
        var right: Node<N>? = null//右子书

        constructor(data: N?, weight: Int) {
            this.data = data
            this.weight = weight
        }

        override fun toString(): String {
            return "Node[weight=${weight},data=${data}]"
        }
    }
}
