package cn.readsense.tree.huffman

/**
 *Author:qyg
 *DATE:2020/9/16 14:26
 *Description：
 **/
fun main() {
    HuffmanTree20200916().run {
        buildTree(createNodes())?.run {
            printTree(this)
        }
    }
}

class HuffmanTree20200916 {

    internal fun createNodes(): ArrayList<TreeNode<String>> {
        var nodes = ArrayList<TreeNode<String>>()
        nodes.add(TreeNode("a", 10))
        nodes.add(TreeNode("b", 15))
        nodes.add(TreeNode("c", 12))
        nodes.add(TreeNode("d", 3))
        nodes.add(TreeNode("e", 4))
        nodes.add(TreeNode("f", 13))
        nodes.add(TreeNode("g", 1))
        return nodes
    }

    internal fun <T> buildTree(nodes: ArrayList<TreeNode<T>>): TreeNode<T>? {
        if (nodes?.isEmpty()) return null
        while (nodes.size > 1) {
            sort(nodes)
            var left = nodes[0]
            var right = nodes[1]
            var parent = TreeNode<T>(null, left.weight + right.weight)
            parent.left = left
            parent.right = right
            nodes.remove(left)
            nodes.remove(right)
            nodes.add(parent)
        }
        return nodes[0]
    }

    internal fun <T> printTree(root: TreeNode<T>) {
        println(root.toString())
        root.left?.runCatching {
            println("left")
            printTree(root.left!!)
        }
        root.right?.runCatching {
            println("right:")
            printTree(root.right!!)
        }
    }

    private fun <T> sort(nodes: ArrayList<TreeNode<T>>): ArrayList<TreeNode<T>> {
        if (nodes.size > 1) {
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
        return nodes
    }

    inner class TreeNode<T> {
        var data: T? = null
        var weight = 0
        var left: TreeNode<T>? = null
        var right: TreeNode<T>? = null

        constructor(data: T?, weight: Int) {
            this.data
            this.weight = weight
        }

        override fun toString(): String {
            return "TreeNode[data=${data},weight=${weight}]"
        }
    }
}