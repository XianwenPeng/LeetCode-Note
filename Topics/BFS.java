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
        BFS bfs = new BFS();
        /* 127. Word Ladder */
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(bfs.ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }

    /* 127. Word Ladder */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<WordHop> queue = new LinkedList<>();
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        queue.offer(new WordHop(0, beginWord));
        visited.add(beginWord);
        int l = beginWord.length();
        while (!queue.isEmpty()) {
            WordHop next = queue.poll();
            if (next.word.equals(endWord))   return next.level + 1;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < 26; j++) {
                    String temp = next.word.substring(0, i) + (char)('a' + j) + next.word.substring(i + 1);
                    if (dict.contains(temp) && !visited.contains(temp)) {
                        queue.offer(new WordHop(next.level + 1, temp));
                        visited.add(temp);
                    }
                }
            }
        }
        return 0;
    }
    class WordHop {
        int level;
        String word;
        public WordHop (int level, String word) {
            this.level = level;
            this.word = word;
        }
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
