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

        /* 51. N-Queens */
        System.out.println(bt.solveNQueens(4));

        /* 52. N-Queens II */
        System.out.println(bt.totalNQueens(4));

        /* 60. Permutation Sequence */
        System.out.println(bt.getPermutation(3,3));

        /* 93. Restore IP Addresses */
        System.out.println(bt.restoreIpAddresses("25525511135"));

    }

    /* 93. Restore IP Addresses */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new LinkedList<>();
        restoreIpAddressesBacktracking(list, s, "", 0, 0);
        return list;
    }
    public void restoreIpAddressesBacktracking(List<String> list, String s, String ip, int index, int count) {
        if (count > 4)  return;
        if (count == 4 && s.length() == index) {
            list.add(ip);
            return;
        }
        for (int i = 1; i < 4; i++) {
            if (index + i > s.length()) break;
            String temp = s.substring(index, index + i);
            if ((temp.startsWith("0") && temp.length() > 1) || (Integer.parseInt(temp) > 255))    continue;
            restoreIpAddressesBacktracking(list, s, ip + temp + (count == 3 ? "": "."), index + i, count + 1);
        }
    }

    /* 60. Permutation Sequence */
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        int sum = 1;
        List<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
            numbers.add(i);
        }
        k--;
        for (int i = 1; i <= n; i++) {
            int fac = factorial[n - i];
            int index = k / fac;
            sb.append(numbers.get(index));
            k -= index * fac;
            numbers.remove(index);
        }
        return sb.toString();
    }

    /* 52. N-Queens II */
    int count = 0;
    public int totalNQueens(int n) {
        totalNQueensDFS(n, new boolean[n], new HashSet<>(), new HashSet<>(), 0);
        return count;
    }
    public void totalNQueensDFS(int n, boolean[] visitedCols, Set<Integer> diagL, Set<Integer> diagR, int row) {
        if (row >= n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visitedCols[i] || diagL.contains(row - i) || diagR.contains(row + i))   continue;
            visitedCols[i] = true;
            diagL.add(row - i);
            diagR.add(row + i);
            totalNQueensDFS(n, visitedCols, diagL, diagR, row + 1);
            visitedCols[i] = false;
            diagL.remove(row - i);
            diagR.remove(row + i);
        }
    }

    /* 51. N-Queens */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new LinkedList<>();
        solveQueensBacktracking(n, list, new LinkedList<>(), new boolean[n], new HashSet<>(), new HashSet<>(), 0);
        return list;
    }
    public void solveQueensBacktracking(int n, List<List<String>> list, List<String> temp, boolean[] visitedCols,
                                        Set<Integer> diagL, Set<Integer> diagR, int row) {
        if (temp.size() == n) {
            list.add(new LinkedList<>(temp));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visitedCols[i] || diagL.contains(row - i) || diagR.contains(row + i))    continue;
            visitedCols[i] = true;
            diagL.add(row - i);
            diagR.add(row + i);
            temp.add(generateRes(i, n));
            solveQueensBacktracking(n, list, temp, visitedCols, diagL, diagR, row + 1);
            temp.remove(temp.size() - 1);
            visitedCols[i] = false;
            diagL.remove(row - i);
            diagR.remove(row + i);
        }
    }
    public String generateRes (int j, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < j; i++) {
            sb.append(".");
        }
        sb.append("Q");
        for (int i = j + 1; i < n; i++) {
            sb.append(".");
        }
        return sb.toString();
    }

    /* 131. Palindrome Partitioning */
    public List<List<String>> partition(String s) {
        List<List<String>> list = new LinkedList<>();
        partitionBacktracking(s, list, new LinkedList<>(), 0);
        return list;
    }
    public void partitionBacktracking(String s, List<List<String>> list, List<String> subList, int start) {
        if (start >= s.length() && subList.size() > 0) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                subList.add(s.substring(start, i + 1));
                partitionBacktracking(s, list, subList, i + 1);
                subList.remove(subList.size() - 1);
            }
        }
    }
    public boolean isPalindrome(String s, int l, int r) {
        if (l == r) return true;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    /* 77. Combinations */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        combineHelper(res, new LinkedList<>(), n, k, 0);
        return res;
    }
    public void combineHelper(List<List<Integer>> res, List<Integer> list, int n, int k, int start) {
        if (list.size() == k) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            combineHelper(res, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /* 40. Combination Sum II */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(candidates);
        combinationSum2Helper(res, new LinkedList<>(), 0, candidates, target, 0);
        return res;
    }
    public void combinationSum2Helper(List<List<Integer>> res, List<Integer> list, int curSum,
                                      int[] candidates, int target, int start) {
        if (curSum > target)    return;
        if (curSum == target)   {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1])    continue;
            list.add(candidates[i]);
            combinationSum2Helper(res, list, curSum + candidates[i], candidates, target, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /* 39. Combination Sum */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<>();
        combinationSumHelper(list, new LinkedList<>(), 0, candidates, target, 0);
        return list;
    }
    public void combinationSumHelper(List<List<Integer>> res, List<Integer> list,
                                     int curSum, int[] candidates, int target, int start) {
        if (curSum > target)    return;
        if (curSum == target)   {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            combinationSumHelper(res, list, curSum + candidates[i], candidates, target, i);
            list.remove(list.size() - 1);
        }
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
