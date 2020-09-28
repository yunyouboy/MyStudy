package cn.readsense.tree.huffman

/**
 *Author:qyg
 *DATE:2020/9/28 14:12
 *Description：
 **/
fun main() {
    HuffmanTree20200928().run {
        buildTree(createNodes())?.run {
            printTree(this)
        }
    }
}

class HuffmanTree20200928 {

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

    internal fun buildTree(nodes: ArrayList<TreeNode<String>>): TreeNode<String>? {
        if (nodes.isEmpty()) return null
        while (nodes.size > 1) {
            sort(nodes)
            var left = nodes[0]
            var right = nodes[1]
            var parent = TreeNode<String>(null, left.weight + right.weight)
            parent.left = left
            parent.right = right
            nodes.remove(left)
            nodes.remove(right)
            nodes.add(parent)
        }
        return nodes[0]
    }

    private fun sort(nodes: ArrayList<TreeNode<String>>) {
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
    }

    internal fun printTree(root: TreeNode<String>) {
        println(root.toString())
        root.left?.run {
            println("left: ");
            printTree(root.left!!)
        }
        root.right?.run {
            println("right: ")
            printTree(root.right!!)
        }

    }

    inner class TreeNode<K> {
        var data: K? = null
        var weight: Int = 0
        var left: TreeNode<K>? = null
        var right: TreeNode<K>? = null

        constructor(data: K?, weight: Int) {
            this.data = data
            this.weight = weight
        }

        override fun toString(): String {
            var sb = StringBuilder()
            sb.append("TreeNode[data=$data,weight=$weight]")
            return sb.toString()
        }
    }
}