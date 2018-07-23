package LeetCode.Topics.SecondRun;
import java.util.*;

public class TwoPointers {

    /* 607. Two Sum III - Data structure design */
    public class TwoSum {
        HashMap<Integer, Integer> map = new HashMap<>();
        /*
         * @param number: An integer
         * @return: nothing
         */
        public void add(int number) {
            // write your code here
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        /*
         * @param value: An integer
         * @return: Find if there exists any pair of numbers which sum is equal to the value.
         */
        public boolean find(int value) {
            // write your code here
            for (int num: map.keySet()) {
                int remain = value - num;
                if (remain == num && map.get(num) > 1
                        || remain != num && map.containsKey(remain))    return true;
            }
            return false;
        }
    }

    /* 891. Valid Palindrome II */
    public boolean validPalindrome(String s) {
        // Write your code here
        return helper(s, 0, s.length() - 1, false);
    }
    public boolean helper(String s, int left, int right, boolean deleted) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }
            else if (deleted)   return false;
            else {
                return helper(s, left + 1, right, true)
                        || helper(s, left, right - 1, true);
            }
        }
        return true;
    }

    /* 539. Move Zeroes */
    public void moveZeroes(int[] nums) {
        // write your code here
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
}
