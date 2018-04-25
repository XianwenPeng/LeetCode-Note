package LeetCode;

import java.util.*;

public class BinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        int[] arr = {1,2,3,4,5};
        TreeNode root = tree.buildTree(arr);
        tree.showTree(root);
    }

    public TreeNode buildTree(int[] array){
        if(array.length == 0)
            return null;
        TreeNode root = new TreeNode(-1);
        return buildTreeHelper(array, root, 0);
    }
    public TreeNode buildTreeHelper(int[] array, TreeNode node, int index){
        if(index < array.length){
            TreeNode temp = new TreeNode(array[index]);
            node = temp;
            node.left = buildTreeHelper(array, node.left, index * 2 + 1);
            node.right = buildTreeHelper(array, node.right, (index + 1 ) * 2);
        }
        return node;
    }
    public void showTree(TreeNode root){
        if(root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.val);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }
}
