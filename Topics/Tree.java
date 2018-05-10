package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class Tree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main (String[] args) {
        Tree tr = new Tree();
        /* 268. Missing Number */
        int[] num268 = {0,1,3};
        tr.missingNumber(num268);
    }

    /* 572. Subtree of Another Tree */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)  return false;
        if (isSubtreeDFS(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    public boolean isSubtreeDFS(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSubtreeDFS(s.left, t.left) && isSubtreeDFS(s.right, t.right);
    }

    /* 637. Average of Levels in Binary Tree */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                sum += node.val;
            }
            list.add(sum / n);
        }
        return list;
    }

    /* 653. Two Sum IV - Input is a BST */
    public boolean findTarget(TreeNode root, int k) {
        return findTargetDFS(root, k, new HashSet<Integer>());
    }
    public boolean findTargetDFS(TreeNode root, int k, HashSet<Integer> set) {
        if (root == null)   return false;
        if (set.contains(root.val)) return true;
        set.add(k - root.val);
        return findTargetDFS(root.left, k, set) || findTargetDFS(root.right, k, set);
    }

    /* 268. Missing Number */
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int i : nums)  sum ^= i;
        for (int i = 1; i <= nums.length; i++) sum ^= i;
        return sum;
    }

    /* 404. Sum of Left Leaves */
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesHelper(root, 0, false);
    }
    public int sumOfLeftLeavesHelper(TreeNode root, int count, boolean left) {
        if (root == null)   return 0;
        if (root.left == null && root.right == null)    return left ? root.val: 0;
        int temp = count;
        count += sumOfLeftLeavesHelper(root.left, temp, true);
        count += sumOfLeftLeavesHelper(root.right, temp, false);
        return count;
    }
}