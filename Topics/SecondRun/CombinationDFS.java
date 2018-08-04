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

    /* 154. Regular Expression Matching */
    public boolean isMatchREMDP(String s, String p) {
        // write your code here
        int n = s.length();
        int m = p.length();
        boolean[][] match = new boolean[n + 1][m + 1];
        match[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (p.charAt(i - 1) == '*' && i >= 2 && match[0][i - 2]) match[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*' && j - 2 >= 0) {
                    if (j >= 2 && p.charAt(j - 2) != '.' && p.charAt(j - 2) != s.charAt(i - 1))
                        match[i][j] = match[i][j - 2];
                    else
                        match[i][j] = match[i - 1][j] || match[i][j - 2] || match[i][j - 1];
                } else {
                    match[i][j] = (p.charAt(j - 1) == s.charAt(i - 1)
                            || p.charAt(j - 1) == '.')
                            && match[i - 1][j - 1];
                }
            }
        }
        return match[n][m];
    }

    public boolean isMatchRegularExpressionMatching(String s, String p) {
        // write your code here
        return dfsREM(s, p, 0, 0, new boolean[s.length()][p.length()], new boolean[s.length()][p.length()]);
    }
    private boolean dfsREM(String s, String p, int sIndex, int pIndex, boolean[][] visited, boolean[][] matched) {
        if (pIndex == p.length())
            return sIndex == s.length();
        if (sIndex == s.length())
            return allStarREM(p, pIndex);
        if (visited[sIndex][pIndex])
            return matched[sIndex][pIndex];
        char chS = s.charAt(sIndex);
        char chP = p.charAt(pIndex);
        boolean match = false;
        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            match = dfsREM(s, p, sIndex, pIndex + 2, visited, matched)
                    || ((chS == chP || chP == '.') && dfsREM(s, p, sIndex + 1, pIndex, visited, matched));
        }
        else {
            match = (chS == chP || chP == '.') && dfsREM(s, p, sIndex + 1, pIndex + 1, visited, matched);
        }
        matched[sIndex][pIndex] = match;
        visited[sIndex][pIndex] = true;
        return match;

    }
    private boolean allStarREM(String s, int index) {
        while (index < s.length()) {
            if (index + 1 >= s.length() || s.charAt(index + 1) != '*') {
                return false;
            }
            index += 2;
        }
        return true;
    }


    /* 192 Wildcard Matching - DP version */
    public boolean isMatchDP(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] match = new boolean[n + 1][m + 1];
        match[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (p.charAt(i - 1) == '*' && match[0][i - 1]) match[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    match[i][j] = match[i - 1][j] || match[i][j - 1];
                }
                else {
                    match[i][j] = (s.charAt(i - 1) == p.charAt(j - 1)
                            || p.charAt(j - 1) == '?')
                            && match[i - 1][j - 1];
                }
            }
        }
        return match[n][m];
    }

    /* 192. Wildcard Matching */
    public boolean isMatch(String s, String p) {
        // write your code here
        int n = s.length(), m = p.length();
        boolean[][] match = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        return dfs(s, p, 0, 0, match, visited);
    }
    public boolean dfs(String s, String p, int sIndex, int pIndex,
                       boolean[][] matched, boolean[][] visited) {
        if (pIndex == p.length())
            return sIndex == s.length();
        if (sIndex == s.length())
            return allStar(p, pIndex);
        if (visited[sIndex][pIndex])
            return matched[sIndex][pIndex];
        char cs = s.charAt(sIndex);
        char cp = p.charAt(pIndex);
        boolean match = false;
        if (cp == '*') {
            match = dfs(s, p, sIndex, pIndex + 1, matched, visited)
                    || dfs(s, p, sIndex + 1, pIndex, matched, visited);
        }
        else {
            match = (cs == cp || cp == '?') &&
                    dfs(s, p, sIndex + 1, pIndex + 1, matched, visited);
        }
        matched[sIndex][pIndex] = match;
        visited[sIndex][pIndex] = true;
        return match;
    }
    public boolean allStar(String s, int index) {
        while (index < s.length()) {
            if (s.charAt(index++) != '*') return false;
        }
        return true;
    }

    /* 680. Split String */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> list = new LinkedList<>();
        dfs(s, 0, list, new LinkedList<>());
        return list;
    }
    public void dfs(String s, int index, List<List<String>> list, List<String> subList) {
        if (index == s.length()) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < index + 2 && i < s.length(); i++) {
            String str = s.substring(index, i + 1);
            subList.add(str);
            dfs(s, i + 1, list, subList);
            subList.remove(subList.size() - 1);
        }
    }

    /* 136. Palindrome Partitioning */
    public List<List<String>> partition(String s) {
        // write your code here
        List<List<String>> list = new LinkedList<>();
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }

        dfs(s, 0, list, new LinkedList<>(), isPalindrome);
        return list;
    }
    private void dfs(String s, int index,
                     List<List<String>> list, List<String> subList, boolean[][] isPalindrome) {
        if (index == s.length()) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (!isPalindrome[index][i]) continue;
            subList.add(s.substring(index, i + 1));
            dfs(s, i + 1, list, subList, isPalindrome);
            subList.remove(subList.size() - 1);
        }
    }
    private boolean isPalindrome(String s, int left, int right) {
        if (left == right)  return true;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--))  return false;
        }
        return true;
    }

    /* 90. k Sum II */
    public List<List<Integer>> kSumII(int[] A, int k, int targer) {
        // write your code here
        List<List<Integer>> list = new LinkedList<>();
        dfs(A, k, targer, 0, list, new LinkedList<>());
        return list;
    }
    private void dfs(int[] A, int k, int target, int index,
                     List<List<Integer>> list, List<Integer> subList) {
        if (target < 0 || k < 0)    return;
        if (k == 0 && target == 0) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < A.length; i++) {
            if (i > index && A[i] == A[i - 1])  continue;
            subList.add(A[i]);
            dfs(A, k - 1, target - A[i], i + 1, list, subList);
            subList.remove(subList.size() - 1);
        }
    }

    /* 153. Combination Sum II */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(num);
        dfs163(num, target, 0, new LinkedList<>(), list);
        return list;
    }
    private void dfs163(int[] nums, int target, int index,
                     List<Integer> subList, List<List<Integer>> list) {
        if (target < 0) return;
        if (target == 0) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1])    continue;
            subList.add(nums[i]);
            dfs163(nums, target - nums[i], i + 1, subList, list);
            subList.remove(subList.size() - 1);
        }
    }

    /* 135. Combination Sum */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, list, new LinkedList<>());
        return list;
    }
    private void dfs(int[] candidates, int target, int index,
                    List<List<Integer>> list, List<Integer> subList) {
        if (target < 0) return;
        if (target == 0) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1])    continue;
            subList.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, list, subList);
            subList.remove(subList.size() - 1);
        }
    }


}
