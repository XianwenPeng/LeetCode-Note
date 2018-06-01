package LeetCode.Topics;

import java.io.FileOutputStream;
import java.util.*;
import java.util.LinkedList;

public class Array {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public static void main (String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(1,2);
        System.out.println(map);

        Array ar = new Array();
        /* 280. Wiggle Sort */
        int[] temp280 = {3, 5, 2, 1, 6, 4};
        ar.wiggleSort(temp280);
        System.out.println(Arrays.toString(temp280));

        /* 228. Summary Ranges */
        int[] temp228 = {0,1,2,4,5,7};
        System.out.println(ar.summaryRanges(temp228));
    }

    /* 325. Maximum Size Subarray Sum Equals k */
    public int maxSubArrayLen(int[] nums, int k) {
        int preSum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (map.containsKey(preSum - k))
                max = Math.max(max, i - map.get(preSum - k));
            if (!map.containsKey(preSum))
                map.put(preSum, i);
        }
        return max;
    }

    /* 525. Contiguous Array */
    public int findMaxLength(int[] nums) {
        int preSum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 0 ? -1 : 1;
            if (preSum == 0)    max += 1;
            else if (map.containsKey(preSum))
                max = Math.max(max, i - map.get(preSum));
            else
                map.put(preSum, i);
        }
        return max;
    }

    /* 275. H-Index II */
    public int hIndexII(int[] citations) {
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length - i)    return i;
        }
        return 0;
    }

    /* 274. H-Index */
    public int hIndex(int[] citations) {
        int bucket[] = new int[citations.length];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= bucket.length)  bucket[bucket.length - 1]++;
            else if (citations[i] >= 1)    bucket[citations[i] - 1]++;
        }
        int preSum = 0;
        for (int i = bucket.length; i > 0; i ++) {
            preSum += bucket[i - 1];
            if (preSum >= i)     return i;
        }
        return 0;
    }

    /* 334. Increasing Triplet Subsequence */
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3)    return false;
        int curmax = -1, curmin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > curmin) {
                if (curmax != -1 && nums[i] > curmax)
                    return true;
                else
                    curmax = nums[i];
            }
            else {
                curmin = nums[i];
            }
        }
        return false;
    }

    /* 209. Minimum Size Subarray Sum */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)   return 0;
        int left = 0, right = 0, min = Integer.MAX_VALUE, subsum = nums[0];
        while (left <= right && right < nums.length) {
            if (subsum < s) {
                right++;
                if (right == nums.length)   break;
                subsum += nums[right];
            }
            else {
                min = Math.min(min, right - left + 1);
                subsum -= nums[left];
                left ++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /* 825. Friends Of Appropriate Ages */
    public int numFriendRequests(int[] ages) {
        int[] count = new int[121];
        for (int i: ages)   count[i]++;
        int ans = 0;
        for (int i = 0; i <= 120; i++) {
            if (i > 14) {
                ans += count[i] * (count[i] - 1 + count[i - 1] - count[i / 2 + 7]);
            }
            count[i] += count[i - 1];
        }
        return ans;
    }

    /* 80. Remove Duplicates from Sorted Array II */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2)    return nums.length;
        int ans = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[ans - 1] || nums[i] != nums[ans - 2]) {
                nums[ans++] = nums[i];
            }
        }
        return ans + 1;
    }

    /* 45. Jump Game II */
    public int jump(int[] nums) {
        int max = 0, count = 0, end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(i + nums[i], max);
            if (i == end) {
                count ++;
                end = max;
            }
        }
        return count;
    }

    /* 57. Insert Interval */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (newInterval == null) {
                res.add(cur);
                continue;
            }
            if (cur.end < newInterval.start) {
                res.add(cur);
            }
            else if (cur.start > newInterval.end) {
                res.add(newInterval);
                res.add(cur);
                newInterval = null;
            }
            else {
                newInterval.start = Math.min(cur.start, newInterval.start);
                newInterval.end = Math.max(cur.end, newInterval.end);
            }
        }
        if (newInterval != null) res.add(newInterval);
        return res;
    }

    /* 41. First Missing Positive */
    public int firstMissingPositive(int[] nums) {
        int max = nums.length + 1, min = 1;
        for (int i: nums) {
            if (i > 0 && i < nums.length) {
                nums[i] = 0 - nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0)  return i + 1;
        }
        return nums.length + 1;
    }

    /* 18. 4Sum */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4)   return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])  continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])  continue;
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[i] + nums[j] + nums[left] + nums[right] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[right] == nums[right - 1])  right--;
                        while (left < right && nums[left] == nums[left + 1])    left++;
                        right--;
                        left++;
                    }
                    else if (nums[i] + nums[j] + nums[left] + nums[right] > target) right--;
                    else    left++;
                }
            }
        }
        return res;
    }

    /* 63. Unique Paths II */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)    return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        dp[1][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1;  j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 1)    continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /* 62. Unique Paths */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) dp[i][j] = 1;
                else    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }

    /* 120. Triangle */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null)   return 0;
        int len = triangle.size();
        int[] dp = new int[triangle.get(len - 1).size()];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = triangle.get(len - 1).get(i);
        }
        for (int i = len - 2; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                dp[j] = list.get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    /* 228. Summary Ranges */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new LinkedList<>();
        if (nums == null || nums.length == 0)   return list;
        StringBuilder sb = new StringBuilder();
        int next = nums[0];
        sb.append(next);
        for (int i = 0; i < nums.length; i++) {
            if (next == nums[i]) {
                next++;
                if (i != 0 && i == nums.length - 1)   sb.append("->" + nums[i]);
            }
            else {
                next = nums[i] + 1;
                if (!String.valueOf(nums[i - 1]).equals(sb.toString()) )
                    sb.append("->"+nums[i - 1]);
                list.add(sb.toString());
                sb = new StringBuilder();
                sb.append(nums[i]);
            }
        }
        list.add(sb.toString());
        return list;
    }
    public List<String> summaryRangesInnerWhile(int[] nums) {
        List<String> list = new LinkedList<>();
        if (nums == null || nums.length == 0)   return list;
        for (int i = 0; i < nums.length; i++) {
            int start = i;
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1)   i++;
            if (start != i)     list.add(nums[start] + "->" + nums[i]);
            else    list.add(String.valueOf(nums[start]));
        }
        return list;
    }

    /* 59. Spiral Matrix II */
    public int[][] generateMatrix(int n) {
        if (n == 1) return new int[][] {{1}};
        int[][] mat = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int r = 0, c = 0;
        String move = "right";
        for (int i = 1; i <= n * n; i++) {
            mat[r][c] = i;
            if (c == right && move.equals("right")) {
                move = "down";
                top++;
            }
            else if (c == left && move.equals("left")) {
                move = "up";
                bottom--;
            }
            else if (r == top && move.equals("up")) {
                move = "right";
                left++;
            }
            else if (r == bottom && move.equals("down")){
                move = "left";
                right--;
            }
            switch (move) {
                case "right":
                    c++;
                    break;
                case "left":
                    c--;
                    break;
                case "up":
                    r--;
                    break;
                case "down":
                    r++;
                    break;
            }
        }
        return mat;
    }

    /* 54. Spiral Matrix */
    /*
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9,10,11,12],
    [13,14,15,16],
    [17,18,19,20]

    [0,1,2,3],
    [13,14,15,4],
    [12,19,16,5],
    [11,18,17,6],
    [10,9,8,7]

    [1,2,3,4,8, - 12,16,20,19,18, - 17,13,9,5,6, - 7,11,15,14,10]
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix == null || matrix.length == 0)   return list;
        int r = 0, c = 0;
        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        String move = "right";
        for (int i = 0; i < matrix[0].length * matrix.length; i++) {
            list.add(matrix[r][c]);
            if (c == right && move.equals("right")) {
                move = "down";
                top++;
            }
            else if (c == left && move.equals("left")) {
                move = "up";
                bottom--;
            }
            else if (r == top && move.equals("up")) {
                move = "right";
                left++;
            }
            else if (r == bottom && move.equals("down")) {
                move = "left";
                right--;
            }
            switch (move) {
                case "left":
                    c--;
                    break;
                case "right":
                    c++;
                    break;
                case "up":
                    r--;
                    break;
                case "down":
                    r++;
                    break;
            }
        }
        return list;
    }

    /* 16. 3Sum Closest */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i])    continue;
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[low] + nums[high] + nums[i];
                closest = Math.abs(sum - target) > Math.abs(closest - target) ? closest: sum;
                if (sum == target)   return target;
                else if (sum > target)  high--;
                else    low++;
            }
        }
        return closest;
    }

    /* 324. Wiggle Sort II */
    public void wiggleSortII(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            if (i % 2 == 1 && nums[i] )
//        }
    }

    /* 280. Wiggle Sort */
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1 && nums[i] < nums[i - 1]) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
            else if ((i != 0 && i % 2 == 0) && (nums[i - 1] < nums[i])) {
                int temp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = temp;
            }
        }
    }

    /* 289. Game of Life */
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int[] temp = new int[2];
                gameOfLifeHelper(board, temp, i, j);
                if (board[i][j] == 1 && (temp[1] == 2 || temp[1] == 3)) board[i][j] = 3;
                else if (board[i][j] == 0 && temp[1] == 3)  board[i][j] = 2;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++){
                board[i][j] >>= 1;
            }
        }
    }
    public void gameOfLifeHelper(int[][] board, int[] temp, int i, int j) {
        for (int row = -1; row < 2; row++) {
            for (int col = -1; col < 2; col++) {
                if (i + row >= 0 && i + row < board.length && j + col >= 0 && j + col < board[0].length
                        && !(row == 0 && col == 0)) {
                    if ((board[i + row][j + col] & 1) == 1)    temp[1]++;
                }
            }
        }
    }

    /* 27. Remove Element */
    public int removeElement(int[] nums, int val) {
        int savePos = 0, len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[savePos] = nums[i];
                savePos++;
            }
            else    len--;
        }
        for (int i = len; i < nums.length; i++) nums[i] = 0;
        return len;
    }

    /* 229. Majority Element II */
    public List<Integer> majorityElementII(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if (nums == null || nums.length == 0)   return list;
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (i + n/3 < n && nums[i + n/3] == temp) {
                list.add(temp);
                i += n/3;
            }
            while (i + 1 < n && nums[i + 1] == temp)    i++;
        }
        return list;
    }

    /* 169. Majority Element */
    public int majorityElement(int[] nums) {
        if (nums.length == 0)  return -1;
        int num = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) count++;
            else {
                count --;
                if (count == 0) {
                    num = nums[i];
                    count = 1;
                }
            }
        }
        return num;
    }

    /* 122. Best Time to Buy and Sell Stock II */
    public int maxProfit122(int[] prices) {
        if (prices.length <= 1) return 0;
        int min = prices[0], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) min = prices[i];
            profit += prices[i] - min;
            min = prices[i];
        }
        return profit;
    }

    /* 121. Best Time to Buy and Sell Stock */
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices == null)   return 0;
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }

    /* 674. Longest Continuous Increasing Subsequence */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)   return -1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] > nums[i - 1] ? dp[i - 1] + 1 : 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /* 220. Contains Duplicate III */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long value = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = value / (t + 1);
//            System.out.println(bucket);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket + 1) && Math.abs(value - map.get(bucket + 1)) <= t)
                    || (map.containsKey(bucket - 1) && Math.abs(value - map.get(bucket - 1)) <= t)) return true;
            if (map.size() >= k) {
                map.remove(((long)nums[i - k] - Integer.MIN_VALUE) / (t + 1));
            }
            map.put(bucket, value);
        }
        return false;
    }

    /* 219. Contains Duplicate II */
    // Time O(N) Space O(N)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(nums[i]) && Math.abs(map.get(nums[i]) - i) <= k) return true;
            map.put(nums[i], i);
        }
        return false;
    }

    /* 217. Contains Duplicate */
    // Time O(N) Space O(N)
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i: nums) {
            if (set.contains(i))    return true;
            set.add(i);
        }
        return false;
    }

    // Time O(N logN) Space O(1)
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    // Time O(N^2) Space O(1)
    public boolean containsDuplicate3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }
}
