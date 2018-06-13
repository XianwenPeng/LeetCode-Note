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

    /* 102. Binary Tree Level Order Traversal */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
    }

    /* 236. Lowest Common Ancestor of a Binary Tree */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)   return root;

        // divide
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // conquer
        if (left != null && right != null)  return root;
        else if (left == null)   return right;
        else if (right == null) return left;
        return root;
    }

    /* 124. Binary Tree Maximum Path Sum */
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return max;
    }
    public int maxPathSumHelper(TreeNode root) {
        if (root == null) return 0;

        // divide
        int left = Math.max(0, maxPathSum(root.left));
        int right = Math.max(0, maxPathSum(root.right));

        // conquer
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    /* 110. Balanced Binary Tree */
    public boolean isBalanced(TreeNode root) {
        if (isBalancedHelper(root) == -1)   return false;
        return true;
    }
    public int isBalancedHelper(TreeNode root) {
        if (root == null)   return 0;

        // divide
        int left = isBalancedHelper(root.left);
        int right = isBalancedHelper(root.right);

        // conquer
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    /* 104. Maximum Depth of Binary Tree */
    public int maxDepth(TreeNode root) {
        if (root == null)   return 0;

        // divide
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        // conquer
        return Math.max(left, right) + 1;
    }

    /* 145. Binary Tree Postorder Traversal */
    // Recursive
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        postorderTraversalRecursive(root, list);
        return list;
    }
    public void postorderTraversalRecursive(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        postorderTraversalRecursive(root.left, list);
        postorderTraversalRecursive(root.right, list);
        list.add(root.val);
    }
    // Iterative
    class ResultNode {
        TreeNode node;
        int action; // 0 for read, 1 for pop
        public ResultNode(TreeNode node, int action) {
            this.node = node;
            this.action = action;
        }
    }
    public List<Integer> postorderTraversalIterative(TreeNode root) {
        Stack<ResultNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;
        stack.push(new ResultNode(root, 0));
        while (!stack.isEmpty()) {
            ResultNode cur = stack.pop();
            if (cur.node == null)    continue;
            if (cur.action == 0) {
                stack.push(new ResultNode(cur.node, 1));
                stack.push(new ResultNode(cur.node.right, 0));
                stack.push(new ResultNode(cur.node.left, 0));
            }
            else {
                list.add(cur.node.val);
            }
        }
        return list;
    }
    // DC
    public List<Integer> postorderTraversalDC(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;

        // Divide
        List<Integer> left = postorderTraversalDC(root.left);
        List<Integer> right = postorderTraversalDC(root.right);

        // Conquer
        list.addAll(left);
        list.addAll(right);
        list.add(root.val);
        return list;
    }

    /* 94. Binary Tree Inorder Traversal */
    // Recursive
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorderTraversalRecursive(root, list);
        return list;
    }
    public void inorderTraversalRecursive(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        inorderTraversalRecursive(root.left, list);
        list.add(root.val);
        inorderTraversalRecursive(root.right, list);
    }
    // Iterative
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }
    // DC
    public List<Integer> inorderTraversalDC(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;

        // Divide
        List<Integer> left = inorderTraversalDC(root.left);
        List<Integer> right = inorderTraversalDC(root.right);

        //Conquer
        list.addAll(left);
        list.add(root.val);
        list.addAll(right);
        return list;
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
