package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class DynamicProgramming {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public static void main (String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(1,2);
        System.out.println(map);

        DynamicProgramming dp = new DynamicProgramming();

        /* 152. Maximum Product Subarray */
        int[] arr152 = {-4,-3,-2};
        System.out.println(dp.maxProduct(arr152));

        /* 70. Climbing Stairs */
        System.out.println(dp.climbStairs(4));

        /* 746. Min Cost Climbing Stairs */
        int[] arr746 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(dp.minCostClimbingStairs(arr746));

        int i = 7;
        while (i > 0) {
            i -= i & -i;
            System.out.println("i= "+i);
        }

        /* 96. Unique Binary Search Trees */
        System.out.println(dp.numTrees(4));

        /* 95. Unique Binary Search Trees II */
        for (TreeNode node : dp.generateTrees(4)) {
//            dp.showTree(node);
//            System.out.println();
        }

        /* 673. Number of Longest Increasing Subsequence */
        int[] arr673_1 = {1,2,5,3,7};
        int[] arr673_2 = {2,2,2,2,2};
        int[] arr673_3 = {1,2,3,4,5,6,7,8,9,10,1,1,1,1,1};
        System.out.println(dp.findNumberOfLIS(arr673_1));
        System.out.println(dp.findNumberOfLIS(arr673_2));
//        System.out.println(dp.findNumberOfLIS(arr673_3));

    }

    /* 72. Edit Distance */
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) return 0;
        if (word1.length() == 0)    return word2.length();
        if (word2.length() == 0)    return word1.length();
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= m; i++) {
            int[] temp = new int[n + 1];
            for (int j = 0; j <= n; j++) {
                if (i == 0) temp[j] = j;
                else if (j == 0)    temp[j] = i;
                else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        temp[j] = dp[j - 1];
                    else {
                        temp[j] = Math.min(Math.min(temp[j - 1], dp[j]), dp[j - 1]) + 1;
                    }
                }
            }
            dp = temp;
        }
        return dp[n];
    }

    /* 115. Distinct Subsequences */
    public int numDistinct(String s, String t) {
        int[] dp = new int[t.length() + 1];
        dp[0] = 1;
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) map.put(t.charAt(i), new LinkedList<>());
            map.get(t.charAt(i)).add(i + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            List<Integer> list = map.get(s.charAt(i));
            if (list == null)   continue;
            List<Integer> add = new LinkedList<>();
            for (int j : list) {
                add.add(dp[j - 1]);
            }
            for (int j = 0; j < list.size(); j++) {
                dp[list.get(j)] += add.get(j);
            }
        }
        return dp[t.length()];
    }

    public int numDistinct2(String s, String t) {
        int[] dp = new int[t.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = t.length(); j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[t.length()];
    }

    /* 714. Best Time to Buy and Sell Stock with Transaction Fee */
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) return 0;
        int b0 = -prices[0], b1 = b0;
        int s1 = 0, s0 = 0;
        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s1 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i] - fee);
            b1 = b0;
            s1 = s0;
        }
        return s0;
    }

    /* 309. Best Time to Buy and Sell Stock with Cooldown */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int b0 = -prices[0], b1 = b0;
        int s1 = 0, s2 = 0, s0 = 0;
        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }

    /* 673. Number of Longest Increasing Subsequence */
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        int[] dp = new int[nums.length], dpCount = new int[nums.length + 1];
        int max = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = dpCount[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] == dp[j] + 1) dpCount[i] += dpCount[j];
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        dpCount[i] = dpCount[j];
                    }
                    System.out.println("INTER: " + dp[i] + " " + dpCount[i] + " " + dpCount[j] + " " + Arrays.toString(dp) + " " + Arrays.toString(dpCount));
                }
            }
            if (max == dp[i])    count += dpCount[i];
            else if (max < dp[i]) {
                max = dp[i];
                count = dpCount[i];
            }
             System.out.println(Arrays.toString(dp)+" "+Arrays.toString(dpCount));
        }
        return count;
    }

    /* 95. Unique Binary Search Trees II */
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new LinkedList<>();
        return generateTreesHelper(1, n);
    }
    public List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        List<TreeNode> left, right;
        for (int i = start; i <= end; i++) {
            left = generateTreesHelper(start, i - 1);
            right = generateTreesHelper(i + 1, end);
            for (TreeNode nodel: left) {
                for (TreeNode noder: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = nodel;
                    root.right = noder;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /*public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return generateTreesHelper(1, n);
    }
    public List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start >= end)  {
            if (start > end)    res.add(null);
            else    res.add(new TreeNode(start));
            return res;
        }
        List<TreeNode> left, right;
        for (int i = start; i <= end; i++) {
            left = generateTreesHelper(start, i - 1);
            right = generateTreesHelper(i + 1, end);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    res.add(root);
                }
            }
        }
        return res;
    }*/

    public void showTree(TreeNode root){
        if(root == null)
            return;
        Queue<TreeNode> queue = new java.util.LinkedList<>();
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

    /* 96. Unique Binary Search Trees */
    public int numTrees(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int nodes = 2; nodes < n + 1; nodes++) {
            for (int left = 0; left < nodes; left++) {
                int right = nodes - 1 - left;
                dp[nodes] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }

    /* 304. Range Sum Query 2D - Immutable */
    class NumMatrix {
        int[][] dp;
        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0) return;
            dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
        }
    }


    /* 307. Range Sum Query - Mutable */
    class NumArray {
        int[] arr;
        int[] bit;

        public NumArray(int[] nums) {
            arr = nums;
            bit = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++)
                updateBit(i, nums[i]);
            System.out.println(Arrays.toString(bit));
        }

        public void updateBit(int i, int diff) {
            i++;
            while (i <= arr.length) {
                bit[i] += diff;
                i += i & -i;
            }
        }

        public void update(int i, int val) {
            updateBit(i,  val - arr[i]);
            arr[i] = val;
        }

        public int sumRange(int i, int j) {
            return sum(j) - sum(i);
        }

        public int sum(int i) {
            int ans = 0;
            i++;
            while (i > 0) {
                ans += bit[i];
                i -= i & -i;
            }
            return ans;
        }
    }

    /* 746. Min Cost Climbing Stairs */
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0)   return -1;
        if (cost.length == 1) return cost[0];
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i], dp[i - 1] + cost[i]);
        }
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }

    /* 70. Climbing Stairs */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n];
        dp[0] = 1; dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /* 53. Maximum Subarray */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)   return -1;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }



    /* 152. Maximum Product Subarray */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)   return -1;
        int curMax = nums[0], curMin = nums[0], max = nums[0], preMax = curMax, preMin = curMin;
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(nums[i], Math.max(preMax * nums[i], preMin * nums[i]));
            curMin = Math.min(nums[i], Math.min(preMax * nums[i], preMin * nums[i]));
            max = Math.max(max, curMax);
            preMax = curMax;
            preMin = curMin;
        }
        return max;
    }
}
