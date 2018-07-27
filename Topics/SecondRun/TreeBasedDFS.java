package LeetCode.Topics.SecondRun;
import java.util.*;

public class TreeBasedDFS {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /* LogN 901. Closest Binary Search Tree Value II */
    public List<Integer> closestKValuesLogN(TreeNode root, double target, int k) {
        // write your code here
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> right = new Stack<>();
        Stack<TreeNode> left = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            left.push(node);
            if (node.val > target) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        right.addAll(left);
        if (target < left.peek().val) {
            goGreater(left);
        }
        else {
            goSmaller(right);
        }

        while (list.size() < k) {
            if (right.isEmpty() ||
                    !left.isEmpty() && Math.abs(left.peek().val - target) < Math.abs(right.peek().val - target)) {
                list.add(left.peek().val);
                goGreater(left);
            }
            else {
                list.add(right.peek().val);
                goSmaller(right);
            }
        }
        return list;

    }
    private void goGreater(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.left == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().left == node)
                node = stack.pop();
            return;
        }
        node = node.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }
    private void goSmaller(Stack<TreeNode> stack) {
        TreeNode node = stack.peek();
        if (node.right == null) {
            node = stack.pop();
            while (!stack.isEmpty() && stack.peek().right == node)
                node = stack.pop();
            return;
        }
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /* 901. Closest Binary Search Tree Value II */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        List<Integer> list = new LinkedList<>();
        dfs(root, target, k, list);
        return list;
    }
    public void dfs(TreeNode root, double target, int k, List<Integer> list) {
        if (root == null)   return;
        dfs(root.left, target, k, list);
        if (list.size() < k) {
            list.add(root.val);
        }
        else if (Math.abs(root.val - target) < Math.abs(list.get(0) - target)) {
            list.remove(0);
            list.add(root.val);
        }
        dfs(root.right, target, k, list);
    }

    /* 900. Closest Binary Search Tree Value */
    TreeNode resClosestBinary;
    public int closestValue(TreeNode root, double target) {
        // write your code here
        if (root == null)   return res.val;
        if (resClosestBinary == null
                || Math.abs(resClosestBinary.val - target) > Math.abs(root.val - target))
            resClosestBinary = root;
        if (root.val < target) {
            return closestValue(root.right, target);
        }
        else {
            return closestValue(root.left, target);
        }
    }

    /* 67. Binary Tree Inorder Traversal */
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            list.add(node.val);
            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            }
            else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return list;
    }

    /* 448. Inorder Successor in BST */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null || p == null)   return null;
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left != null ? left : root;
        }
    }

    /* 86. Binary Search Tree Iterator */
    public class BSTIterator {
        Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            // do intialization if necessary
            stack = new Stack<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
        public boolean hasNext() {
            // write your code here
            return !stack.isEmpty();
        }
        public TreeNode next() {
            // write your code here
            TreeNode node = stack.peek();
            TreeNode res = node;
            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            }
            else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return res;
        }
    }

    /* Follow up, 很多增删改查 902. Kth Smallest Element in a BST */
    public int kthSmallestFollowUp(TreeNode root, int k) {
        Map<TreeNode, Integer> map = new HashMap<>();
        countChilder(root, map);
        return quickSelectKthSmallest(root, k, map);
    }
    private int countChilder(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null)   return 0;
        int left = countChilder(root.left, map);
        int right = countChilder(root.right, map);
        int count = left + right + 1;
        map.put(root, count);
        return count;
    }
    private int quickSelectKthSmallest(TreeNode root, int k, Map<TreeNode, Integer> map) {
        if (root == null)   return -1;
        int left = root.left == null ? 0 : map.get(root.left);
        if (left >= k)  return quickSelectKthSmallest(root.left, k, map);
        if (left == k - 1)  return root.val;
        return quickSelectKthSmallest(root.right, k - left - 1, map);
    }

    /* 902. Kth Smallest Element in a BST */
    public int kthSmallest(TreeNode root, int k) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        int count = 0;
        while (!stack.isEmpty()) {
            if (count == k - 1) break;
            TreeNode node = stack.peek();
            if (node.right == null) {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            } else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            count++;
        }
        return stack.peek().val;
    }
    int count = 0, kthSmallestRecursiveres = 0;
    public void kthSmallestRecursive(TreeNode root, int k) {
        if (root == null)   return;
        kthSmallestRecursive(root.left, k);
        count++;
        if (count == k) kthSmallestRecursiveres = root.val;
        kthSmallestRecursive(root.right, k);
    }

    /* 453. Flatten Binary Tree to Linked List */
    TreeNode last = null;
    public void flatten(TreeNode root) {
        // write your code here
        if (root == null)   return;
        if (last != null) {
            last.left = null;
            last.right = root;
        }
        last = root;
        TreeNode temp = root.right;
        flatten(root.left);
        flatten(temp);
    }

    /* 88. Lowest Common Ancestor of a Binary Tree */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null)   return null;
        if (root == A)  return A;
        if (root == B)  return B;
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        if (left != null && right != null)  return root;
        else if (left == null)  return right;
        else return left;
    }

    /* 480. Binary Tree Paths */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> list = new LinkedList<>();
        if (root == null)   return list;
        binaryTreePathsDFS(root, new LinkedList<>(), list);
        return list;
    }
    public void binaryTreePathsDFS(TreeNode root, List<Integer> nums, List<String> list) {
        if (root == null)   return;
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.size(); i++) {
                sb.append(nums.get(i) +"->");
            }
            sb.append(root.val);
            list.add(sb.toString());
            return;
        }
        nums.add(root.val);
        binaryTreePathsDFS(root.left, nums, list);
        binaryTreePathsDFS(root.right, nums, list);
        nums.remove(nums.size() - 1);
    }

    /* 596. Minimum Subtree */
    int sum = Integer.MAX_VALUE;
    TreeNode res = null;
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        if (root == null)   return null;
        helper(root);
        return res;
    }
    public int findSubtreeDFS(TreeNode root) {
        if (root == null)   return 0;
        int tempSum = findSubtreeDFS(root.left) + findSubtreeDFS(root.right) + root.val;
        if (tempSum < sum) {
            res = root;
            sum = tempSum;
        }
        return tempSum;
    }

    /* 95. Validate Binary Search Tree */
    public boolean isValidBSTIterative(TreeNode root) {
        // write your code here
        if (root == null)   return true;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode last = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (last != null && last.val >= node.val) {
                return false;
            }
            last = node;
            if (node.right == null)  {
                node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            }
            else {
                node = node.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
        return true;
    }
    private boolean isValidBSTDFS (TreeNode root, long leftBound, long rightBound) {
        if (root == null)   return true;
        if (root.val <= leftBound || root.val >= rightBound)  return false;
        boolean left = isValidBSTDFS(root.left, leftBound, root.val);
        boolean right = isValidBSTDFS(root.right, root.val, rightBound);
        return left && right;
    }

    /* 175. Invert Binary Tree */
    public void invertBinaryTreeIterative(TreeNode root) {
        // write your code here
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);

        }
    }
    public void invertBinaryTreeDFS(TreeNode root) {
         if (root == null)   return;
        invertBinaryTreeDFS(root.left);
        invertBinaryTreeDFS(root.right);
         TreeNode temp = root.left;
         root.left = root.right;
         root.right = temp;
    }

    /* 597. Subtree with Maximum Average */
    TreeNode subtree = null;
    ResultType subtreeResult = null;
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        if (root == null) return null;
        helper(root);
        return subtree;
    }
    public ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        ResultType result = new ResultType(
                left.sum + right.sum + root.val,
                left.size + right.size + 1
        );
        if (subtree == null ||
                (double) result.sum / result.size > (double) subtreeResult.sum / subtreeResult.size) {
            subtree = root;
            subtreeResult = result;
        }
        return result;
    }
    public class ResultType {
        int sum;
        int size;
        public ResultType(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
    }
}
