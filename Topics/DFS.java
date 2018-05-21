package LeetCode.Topics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;
import java.util.Queue;

public class DFS {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        /* 108. Convert Sorted Array to Binary Search Tree */
        int[] arr108 = {-10,-3,-1,0,5,9};
        TreeNode node108 = dfs.sortedArrayToBST(arr108);
        dfs.printList(node108);
    }

    /* 99. Recover Binary Search Tree */
    TreeNode firstNode = null, secondNode = null, preNode = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        recoverTreeTraversal(root);
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }
    public void recoverTreeTraversal(TreeNode root) {
        if (root == null)   return;
        recoverTreeTraversal(root.left);
        if (firstNode == null && preNode.val >= root.val)   firstNode = preNode;
        if (firstNode != null && preNode.val >= root.val)   secondNode = root;
        preNode = root;
        recoverTreeTraversal(root.right);
    }
    // Stack Iterative
    public void recoverTreeII(TreeNode root) {
        Stack<Element> stack = new Stack<>();
        TreeNode firstNode = null, secondNode = null, preNode = new TreeNode(Integer.MIN_VALUE);
        stack.push(new Element(root, 0));
        while (!stack.empty()) {
            Element cur = stack.pop();
            if (cur.node == null)   continue;
            if (cur.type == 0)  {
                stack.push(new Element(cur.node.right, 0));
                stack.push(new Element(cur.node, 1));
                stack.push(new Element(cur.node.left, 0));
            }
            else {
                if (preNode == null)    preNode = cur.node;
                if (firstNode == null && preNode.val >= cur.node.val)
                    firstNode = preNode;
                if (preNode.val >= cur.node.val)
                    secondNode = cur.node;
                preNode = cur.node;
            }
        }
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }
    public class Element {
        int type; // 0 visit, 1 do something
        TreeNode node;
        public Element(TreeNode node, int type) {
            this.node = node;
            this.type = type;
        }
    }


    /* 117. Populating Next Right Pointers in Each Node II */
    public void connectII(TreeLinkNode root) {
        if (root == null || (root.left == null && root.right == null))   return;
        TreeLinkNode last = null;
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
            last = root.right;
        }
        else {
            last = root.left == null ? root.right : root.left;
        }
        TreeLinkNode node = root.next;
        while (node != null) {
            if (node.left != null || node.right != null) {
                if (node.left != null)  last.next = node.left;
                else    last.next = node.right;
                break;
            }
            node = node.next;
        }
        connectII(root.right);
        connectII(root.left);
    }

    /* 111. Minimum Depth of Binary Tree */
    public int minDepth(TreeNode root) {
        if (root == null)   return 0;
        int left = 1 + minDepth(root.left);
        int right = 1 + minDepth(root.right);
        return left == 1 || right == 1 ? Math.max(left, right) : Math.min(left, right);
    }

    /* 116. Populating Next Right Pointers in Each Node */
    public void connect(TreeLinkNode root) {
        if (root == null || root.left == null || root.right == null)   return;
        root.left.next = root.right;
        if (root.next != null)  root.right.next = root.next.left;
        connect(root.right);
        connect(root.left);
    }

    /* 114. Flatten Binary Tree to Linked List */
    public void flatten(TreeNode root) {
        if (root == null)   return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flatten(left);
        flatten(right);
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }

    /* 695. Max Area of Island */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0)   return 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++ ) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, maxAreaOfIslandDFS(grid, i, j, 0));
                }
            }
        }
        return max;
    }
    public int maxAreaOfIslandDFS(int[][] grid, int i, int j, int area) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return area;
        grid[i][j] = 0;
        area++;
        area += maxAreaOfIslandDFS(grid, i + 1, j, 0);
        area += maxAreaOfIslandDFS(grid, i - 1, j, 0);
        area += maxAreaOfIslandDFS(grid, i, j + 1, 0);
        area += maxAreaOfIslandDFS(grid, i, j - 1, 0);
        return area;
    }

    /* 109. Convert Sorted List to Binary Search Tree */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)   return null;
        ArrayList<ListNode> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null){
            list.add(temp);
            temp = temp.next;
        }
        return sortedListToBSTHelper(list, 0, list.size() - 1);
    }
    public TreeNode sortedListToBSTHelper(ArrayList<ListNode> list, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left == right)  return new TreeNode(list.get(left).val);
        if (left == mid) {
            TreeNode temp = new TreeNode(list.get(right).val);
            temp.left = new TreeNode(list.get(left).val);
            return temp;
        }
        TreeNode root = new TreeNode(list.get(mid).val);
        root.left = sortedListToBSTHelper(list, left, mid - 1);
        root.right = sortedListToBSTHelper(list, mid + 1, right);
        return root;
    }

    // Two pointers to solve 109
    public TreeNode sortedListToBSTTwoPointers(ListNode head) {
        if (head == null)   return null;
        if (head.next == null)  return new TreeNode(head.val);
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (prev != null)  prev.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBSTTwoPointers(head);
        root.right = sortedListToBSTTwoPointers(slow.next);
        return root;
    }

    /* 108. Convert Sorted Array to Binary Search Tree */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)   return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }
    public TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right || left < 0 || right > nums.length - 1)    return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return root;
    }

    /* 100. Same Tree */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if ((p != null && q == null) || (p == null && q != null) || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /* 110. Balanced Binary Tree */
    public boolean isBalanced(TreeNode root) {
        return countLevel(root) == -1 ? false : true;
    }
    public int countLevel(TreeNode root) {
        if (root == null)   return 0;
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    /* PRINT TREE */
    public void printList(TreeNode head) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                System.out.print(node.val + " ");
            }
            System.out.println();
        }
    }
}
