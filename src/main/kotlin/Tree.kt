class TreeNode(var value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun main() {
    // Create a binary tree
    val root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left?.left = TreeNode(4)
    root.left?.right = TreeNode(5)
    root.right?.left = TreeNode(6)
    root.right?.right = TreeNode(7)
    root.right?.right?.left = TreeNode(8)

    // Perform operations on the binary tree
    // ...

    // Example: Print the tree values using inorder traversal
    println("Inorder Traversal:")
    inorderTraversal(root)
}

fun inorderTraversal(node: TreeNode?) {
    if (node != null) {
        inorderTraversal(node.left)
        print("${node.value} ")
        inorderTraversal(node.right)
    }
}