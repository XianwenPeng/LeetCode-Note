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

        /* 351. Android Unlock Patterns */
        System.out.println(bt.numberOfPatterns(1,2));
    }

    /* 351. Android Unlock Patterns */
    public int numberOfPatterns(int m, int n) {
        if (m > n)  return 0;
        int[] ans = new int[1];
        boolean[][] visited = new boolean[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                visited[i][j] = true;
                numberOfPatternsHelper(1, visited, i, j, ans, m, n);
                visited[i][j] = false;
            }
        }
        return ans[0];
    }
    public void numberOfPatternsHelper(int count, boolean[][] visited, int i, int j, int[] ans, int m, int n) {
        if (count > n)    return;
        if (count >= m && count <= n)   ans[0]++;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (visited[row][col])  continue;
                int diffx = Math.abs(i - row);
                int diffy = Math.abs(j - col);
                if ((diffx * diffy == 0 && diffx + diffy == 2) || (diffx * diffy == 4))   {
                    if (!visited[(i + row) / 2][(j + col) / 2]) continue;
                }
                visited[row][col] = true;
                numberOfPatternsHelper(count + 1, visited, row, col, ans, m, n);
                visited[row][col] = false;
            }
        }

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