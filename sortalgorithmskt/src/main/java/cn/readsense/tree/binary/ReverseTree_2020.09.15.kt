package cn.readsense.tree.binary

import java.util.*

/**
 *Author:qyg
 *DATE:2020/9/15 10:42
 *Description：
 **/
fun main() {
    BinaryTree20200915().run {
        printTree(invertTree(buildTree())!!)
        printTree(invertTree2(buildTree())!!)
    }
}

class BinaryTree20200915 {

    internal fun buildTree(): TreeNode<String> {
        var root = TreeNode("a")
        root.run {
            left = TreeNode("b")
            right = TreeNode("c")
            left?.run {
                left = TreeNode("d")
                right = TreeNode("e")
            }
            right?.run {
                left = TreeNode("f")
                right = TreeNode("g")
            }
        }
        return root
    }

    internal fun <T> invertTree(root: TreeNode<T>?): TreeNode<T>? {
        root ?: return null
        var left = invertTree(root.left)
        var right = invertTree(root.right)
        root.left = right
        root.right = left
        return root
    }

    internal fun <T> invertTree2(root: TreeNode<T>?): TreeNode<T>? {
        root ?: return null
        var queue = LinkedList<TreeNode<T>>()
        queue.add(root)
        while (!queue.isEmpty()) {
            val current = queue.poll()
            var temp = current.left
            current.left = current.right
            current.right = temp
            current.left?.run { queue.add(current.left!!) }
            current.right?.run { queue.add(current.right!!) }
        }
        return root
    }

    internal fun <T> printTree(root: TreeNode<T>) {
        println(root.toString())
        root.left?.run {
            println("left:")
            printTree(root.left!!)
        }
        root.right?.run {
            println("right:")
            printTree(root.right!!)
        }
    }

    inner class TreeNode<T> {
        var data: T? = null
        var left: TreeNode<T>? = null
        var right: TreeNode<T>? = null

        constructor(data: T?) {
            this.data = data
        }

        override fun toString(): String {
            return "TreeNode[data=${data}]"
        }
    }
}