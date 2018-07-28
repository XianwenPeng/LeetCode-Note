package LeetCode.Topics.SecondRun;
import java.util.*;

public class CombinationDFS {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private boolean res;
    public boolean isBalanced(TreeNode root) {
        // write your code here
        res = true;
        dfs(root);
        return res;
    }
    public int dfs(TreeNode root) {
        if (root == null)   return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (Math.abs(left - right) > 1) res = false;
        return Math.max(left, right) + 1;
    }
}
