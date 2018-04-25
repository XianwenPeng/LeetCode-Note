package LeetCode;

import java.util.*;
import LeetCode.BinaryTree.TreeNode;

public class LeetCode {

    /* 655. Print Binary Tree */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> list = new ArrayList<>();
        int row = getHeight(root);
        int col = (int)Math.pow(2, row) - 1;
        List<String> subList = new ArrayList<>();
        for(int j = 0; j < col; j++)    subList.add("");
        for(int i = 0; i < row; i++)    list.add(new ArrayList<>(subList));
        helper655(root, list, row, 0, 0, col);
        return list;
    }
    public void helper655(TreeNode root, List<List<String>> list, int totelRow, int row, int i, int j){
        if(row == totelRow || root == null) return;
        list.get(row).set((i + j)/2, String.valueOf(root.val));
        helper655(root.left, list, totelRow, row+1, i, (i + j)/2);
        helper655(root.right, list, totelRow, row+1, (i + j)/2, j);
    }
    public int getHeight(TreeNode root){
        if(root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
