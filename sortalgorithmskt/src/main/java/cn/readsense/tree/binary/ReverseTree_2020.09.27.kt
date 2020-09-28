package cn.readsense.tree.binary

import java.util.*

/**
 *Author:qyg
 *DATE:2020/9/27 14:30
 *Description：
 **/
fun main() {
    ReverseTree20200927().run {
        printTree(interval(buildTree())!!)
    }
    ReverseTree20200927().run {
        printTree(interval2(buildTree()))
    }
}


class ReverseTree20200927 {

    internal fun buildTree(): TreeNode<String> {
        var root = TreeNode("a")
        root.left = TreeNode("b")
        root.right = TreeNode("c")
        root.left?.run {
            left = TreeNode("d")
            right = TreeNode("e")
        }
        root.right?.run {
            left = TreeNode("f")
            right = TreeNode("g")
        }
        return root
    }

    internal fun interval(root: TreeNode<String>?): TreeNode<String>? {
        root ?: return null
        var left = interval(root.left)
        var right = interval(root.right)
        root.left = right
        root.right = left
        return root
    }

    internal fun interval2(root: TreeNode<String>): TreeNode<String> {
        var queqe = LinkedList<TreeNode<String>>()
        queqe.add(root)
        while (queqe.isNotEmpty()) {
            val current = queqe.poll()
            var temp = current.left
            current.left = current.right
            current.right = temp
            current.left?.run { queqe.add(current.left!!) }
            current.right?.run { queqe.add(current.right!!) }
        }
        return root
    }

    internal fun printTree(root: TreeNode<String>) {
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

    inner class TreeNode<M> {
        var data: M? = null
        var left: TreeNode<M>? = null
        var right: TreeNode<M>? = null

        constructor(data: M?) {
            this.data = data
        }

        override fun toString(): String {
            return "TreeNode[data=${data}]"
        }
    }
}