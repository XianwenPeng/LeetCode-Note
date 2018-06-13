package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class BinaryTree_DivideConquer_DFS_BFS {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /* 144. Binary Tree Preorder Traversal */
    // Recursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        preorderTraversalRecursive(root, list);
        return list;
    }
    public void preorderTraversalRecursive(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        list.add(root.val);
        preorderTraversalRecursive(root.left, list);
        preorderTraversalRecursive(root.right, list);
    }
    // Iterative
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return list;
    }
    // Divide & Conquer
    public List<Integer> preorderTraversalDC(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;

        // Divide
        List<Integer> left = preorderTraversalDC(root.left);
        List<Integer> right = preorderTraversalDC(root.right);

        // Conquer
        list.add(root.val);
        list.addAll(left);
        list.addAll(right);
        return list;
    }
}
