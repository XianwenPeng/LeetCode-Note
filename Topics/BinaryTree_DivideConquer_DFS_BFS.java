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

    /* 87. Remove Node in Binary Search Tree */
    public TreeNode removeNode(TreeNode root, int value) {
        // write your code here
        TreeNode node = root;
        while (node != root) {
            node
        }
    }

    /* 173. Binary Search Tree Iterator */
    public class BSTIterator {
        Stack<TreeNode> stack = new Stack<>();
        public BSTIterator(TreeNode root) {
            TreeNode temp = root;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            int ans = node.val;
            if (node.right != null) node = node.right;
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            return ans;
        }
    }


    /* LintCode 11. Search Range in Binary Search Tree */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> list = new LinkedList<>();
        if (root == null)   return list;

        List<Integer> left = searchRange(root.left, k1, k2);
        List<Integer> right = searchRange(root.right, k1, k2);

        list.addAll(left);
        if (root.val >= k1 && root.val <= k2)   list.add(root.val);
        list.addAll(right);
        return list;

    }

    /* LintCode 85. 在二叉查找树中插入节点 */
    // Recursive
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if (root == null)   return node;
        if (root.val > node.val)    root.left = insertNode(root.left, node);
        else    root.right = insertNode(root.right, node);
        return root;
    }
    // Iterative
    public TreeNode insertNodeIterative(TreeNode root, TreeNode node) {
        if (root == null)   return node;
        TreeNode temp = root, last= null;
        while (temp != null) {
            last = temp;
            if (temp.val > node.val)    temp = temp.left;
            else    temp = temp.right;
        }
        if (last != null) {
            if (last.val > node.val)    last.left = node;
            else    last.right = node;
        }
        return root;
    }

    /* 98. Validate Binary Search Tree */
    TreeNode last;
    public boolean isValidBST(TreeNode root) {
        if (root == null)   return true;
        boolean left = isValidBST(root.left);
        if (last != null && root.val <= last.val)  return false;
        else last = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }
    // Inorder
    public boolean isValidBSTInorder(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        isValidBSTInorderHelper(root, list);
        if (list.size() < 2)    return true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1))  return false;
        }
        return true;
    }
    public void isValidBSTInorderHelper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        isValidBSTInorderHelper(root.left, list);
        list.add(root.val);
        isValidBSTInorderHelper(root.right, list);
    }

    /* 103. Binary Tree Zigzag Level Order Traversal */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)   return list;
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> sublist = new LinkedList<>();
            for (int i = 0; i < size; i ++) {
                TreeNode node = queue.poll();
                if (level % 2 == 0) sublist.add(node.val);
                else    sublist.add(0, node.val);
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(sublist);
            level++;
        }
        return list;
    }

    /* 102. Binary Tree Level Order Traversal */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        if (root == null)   return list;
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> sublist = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sublist.add(node.val);
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(sublist);
        }
        return list;
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
