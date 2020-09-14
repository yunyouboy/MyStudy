package cn.readsense.tree.binary
import java.util.*

/**
 *Author:qyg
 *DATE:2020/9/14 18:11
 *Description：
 **/
fun main() {
    BinaryTree().run {
        printTree(invertTree(buildTree())!!)
    }
    BinaryTree().run {
        printTree(invertTree2(buildTree())!!)
    }
}

class BinaryTree {
    internal fun buildTree(): Node<String> {
        var node = Node("a")
        node.left = Node("b")
        node.right = Node("c")
        node.left?.left = Node("d")
        node.left?.right = Node("e")
        node.right?.left = Node("f")
        node.right?.right = Node("g")
        return node
    }

    //递归
    internal fun <T> invertTree(root: Node<T>?): Node<T>? {
        root ?: return null
        var left = invertTree(root.left)
        var right = invertTree(root.right)
        root.left = right
        root.right = left
        return root
    }

    //迭代
    internal fun <T> invertTree2(root: Node<T>?): Node<T>? {
        root ?: return null
        var queue = LinkedList<Node<T>>()
        queue.add(root)
        while (!queue.isEmpty()) {
            var cut = queue.poll()
            var temp = cut.left
            cut.left = cut.right
            cut.right = temp
            cut.left?.run { queue.add(cut.left!!) }
            cut.right?.run { queue.add(cut.right!!) }
        }
        return root
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

    inner class Node<T> {
        var value: T? = null
        var left: Node<T>? = null
        var right: Node<T>? = null

        constructor(v: T?) {
            this.value = v
        }

        override fun toString(): String {
            return "BinaryTree[value=${value}]"
        }
    }
}