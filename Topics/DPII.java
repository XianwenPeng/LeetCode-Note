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

    /* 97. Interleaving String */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())   return false;
        if (s1.length() == 0)   return s2.equals(s3);
        if (s2.length() == 0)   return s1.equals(s3);
        int m = s1.length(), n = s2.length(), l = s3.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[m][n];
    }

    /* 72. Edit Distance */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
            }
        }
        return dp[m][n];
    }

    /* 115. Distinct Subsequences */
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[m][n];
    }

    /* Longest Common Subsequence */
    public int lengthOfLCS(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    /* 673. Number of Longest Increasing Subsequence */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length < 1)    return 0;
        int[] dp = new int[nums.length], dpCount = new int[nums.length];
        int maxCount = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = dpCount[i] = 1;
            int num = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < num) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        dpCount[i] = dpCount[j];
                    }
                    else if (dp[i] == dp[j] + 1) {
                        dpCount[i] += dpCount[j];
                    }
                }
            }
            if (maxCount < dp[i]) {
                maxCount = dp[i];
                res = dpCount[i];
            }
            else if (maxCount == dp[i]) res += dpCount[i];
        }
        return res;
    }

    /* 300. Longest Increasing Subsequence */
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0, num = nums[i];
            for (int j = 0; j < i; j++) {
                if (nums[j] < num)  max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /* 639. Decode Ways II */
    public int numDecodingsII(String s) {
        if (s.length() < 1) return 1;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : s.charAt(0) == '*' ? 9 : 1;
        for (int i = 2; i <= s.length(); i++) {
            char first = s.charAt(i - 1);
            char second = s.charAt(i - 2);
            if (first == '*') {
                dp[i] += dp[i - 1] * 9;
            }
            else if (first > '0') {
                dp[i] += dp[i - 1];
            }
            if (second == '*') {
                if (first == '*') {
                    dp[i] += dp[i - 2] * 15;
                }
                else if (first <= '6') {
                    dp[i] += dp[i - 2] * 2;
                }
                else    dp[i] += dp[i - 2];
            }
            else if (second == '1' || second == '2') {
                if (first == '*') {
                    if (second == '1')  dp[i] += dp[i - 2] * 9;
                    else    dp[i] += dp[i - 2] * 6;
                }
                else if ((second - '0') * 10 + (first - '0') <= 26) dp[i] += dp[i - 2];

            }
            dp[i] %= 1000000007;
        }
        return dp[s.length()];
    }

    /* 91. Decode Ways */
    public int numDecodings(String s) {
        if (s.length() < 1) return 1;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int first = s.charAt(i - 1) - '0';
            int second = (s.charAt(i - 2) - '0') * 10 + first;

            if (first > 0 && first <= 9)   dp[i] += dp[i - 1];
            if (second >= 10 && second <= 26)    dp[i] += dp[i - 2];
        }
        return dp[s.length()];
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
