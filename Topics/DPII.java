package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class DPII {
    public static void main (String[] args) {
        DPII dp = new DPII();
        /* 132. Palindrome Partitioning II */
        boolean[][] arr132 = dp.getPalindromes("bccacc");
        for (boolean[] a : arr132) System.out.println(Arrays.toString(a));
    }

    /* 140. Word Break II */
    public List<String> wordBreakII(String s, List<String> wordDict) {
        List<String> list = new LinkedList<>();
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[i - j] && wordDict.contains(s.substring(i - j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        if (!dp[s.length()])    return list;
        wordBreakIIHelper(s, wordDict, list, new StringBuilder(), 0);
        return list;
    }
    public void wordBreakIIHelper(String s, List<String> wordDict, List<String> list, StringBuilder sb, int start) {
        if (start >= s.length()) {
            list.add(sb.toString().trim());
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (wordDict.contains(str)) {
                int len = sb.length();
                sb.append(str+" ");
                wordBreakIIHelper(s, wordDict, list, sb, i);
                sb.setLength(len);
            }
        }
    }

    /* 139. Word Break */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= i; j++) {
                if (wordDict.contains(s.substring(i - j, i)) && dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /* 132. Palindrome Partitioning II */
    public int minCut(String s) {
        boolean[][] palindromes = getPalindromes(s);
        int[] cut = new int[s.length() + 1];
        cut[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            cut[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if (palindromes[i - j][i - 1])
                    cut[i] = Math.min(cut[i - j] + 1, cut[i]);
            }
        }
        return cut[s.length()] - 1;
    }
    public boolean[][] getPalindromes(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        for (int len = 2; len < s.length(); len++) {
            for (int start = 0; start + len < s.length(); start++) {
                dp[start][start + len] = dp[start + 1][start + len - 1] && s.charAt(start) == s.charAt(start + len);
            }
        }
        return dp;
    }
    public boolean isPalindrome(String s, int l, int r) {
        if (l == r) return true;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    /* 55. Jump Game */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i - j] && nums[i - j] + (i - j) >= i)  dp[i] = true;
                else continue;
            }
        }
        return dp[nums.length - 1];
    }

    /* 70. Climbing Stairs */
    public int climbStairs(int n) {
        if (n < 2)  return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /* 64. Minimum Path Sum */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[j][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /* 120. Triangle */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n ; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        for (int i = n - 2; i >= 0; i--) {
            List<Integer> sublist = triangle.get(i);
            for (int j = 0; j < sublist.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + sublist.get(j);
            }
        }
        return dp[n - 1];
    }

    /* 62. Unique Paths */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // dp[i][j] 表示从起点走到坐标i，j的多少条路
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /* 63. Unique Paths II */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)    break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1)    break;
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1)    continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
