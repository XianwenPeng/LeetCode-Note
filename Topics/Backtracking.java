package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class Backtracking {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Backtracking bt = new Backtracking();

        /* 784. Letter Case Permutation */
        System.out.println(bt.letterCasePermutation("a1b2"));
        /* 90. Subsets II */
        int[] arr90 = {4,4,4,1,4};
        System.out.println(bt.subsetsWithDup(arr90));
    }

    /* 90. Subsets II */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new LinkedList<>();
        subsetsWithDupBacktracking(list, new LinkedList<>(), nums, 0);
        return list;
    }
    public void subsetsWithDupBacktracking(List<List<Integer>> list, List<Integer> temp, int[] nums, int pos) {
        list.add(new LinkedList<>(temp));
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i - 1])    continue;
            temp.add(nums[i]);
            subsetsWithDupBacktracking(list, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /* 784. Letter Case Permutation */
    public List<String> letterCasePermutation(String S) {
        LinkedList<String> list = new LinkedList<>();
        letterCasePermutationBacktracking(S, list, S.toCharArray(), 0);
        return list;
    }
    public void letterCasePermutationBacktracking(String s, List<String> list, char[] chs, int pos) {
        if (pos == s.length()) {
            list.add(new String(chs));
            return;
        }
        if (Character.isLetter(chs[pos])) {
            chs[pos] = Character.toUpperCase(chs[pos]);
            letterCasePermutationBacktracking (s, list, chs, pos + 1);
            chs[pos] = Character.toLowerCase(chs[pos]);
        }
        letterCasePermutationBacktracking (s, list, chs, pos + 1);
    }
}
