package LeetCode.Topics;

import java.util.*;
public class Array {
    public static void main (String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(1,2);
        System.out.println(map);
    }

    /* 121. Best Time to Buy and Sell Stock */

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
