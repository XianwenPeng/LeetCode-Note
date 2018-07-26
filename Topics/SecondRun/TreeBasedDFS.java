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
