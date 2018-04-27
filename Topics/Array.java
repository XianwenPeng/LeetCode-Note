package LeetCode.Topics;

import java.io.FileOutputStream;
import java.util.*;
import java.util.LinkedList;

public class Array {
    public static void main (String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(1,2);
        System.out.println(map);
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
