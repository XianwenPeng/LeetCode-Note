package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class BFS {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    /* 103. Binary Tree Zigzag Level Order Traversal */
    // DFS
    public List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        zigzagLevelOrderDFSHelper(list, 0, root);
        return list;
    }
    public void zigzagLevelOrderDFSHelper(List<List<Integer>> list, int level, TreeNode node) {
        if (node == null)   return;
        if (list.size() <= level) {
            list.add(new LinkedList<>());
        }
        if (level % 2 == 0) list.get(level).add(node.val);
        else list.get(level).add(0, node.val);
        zigzagLevelOrderDFSHelper(list, level + 1, node.left);
        zigzagLevelOrderDFSHelper(list, level + 1, node.right);
    }

    // BFS
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null)   return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean left = false;
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> sub = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                sub.add(node.val);
            }
            list.add(sub);
        }
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 1) Collections.reverse(list.get(i));
        }
        return list;
    }

    /* 314. Binary Tree Vertical Order Traversal */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null)   return new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();

        queue.offer(root);
        level.offer(0);
        int min = 0, max = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int le = level.poll();

            if (!map.containsKey(le)) map.put(le, new LinkedList<>());
            map.get(le).add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                level.offer(le - 1);
                min = Math.min(min, le - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                level.offer(le + 1);
                max = Math.max(max, le + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            list.add(map.get(i));
        }
        return list;
    }

    /* 102. Binary Tree Level Order Traversal */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)   return new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> sub = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                sub.add(node.val);
            }
            list.add(sub);
        }
        return list;
    }
}
