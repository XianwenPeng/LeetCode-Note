package LeetCode.Topics.SecondRun;
import java.util.*;

public class TwoPointers {

    /* 373. Partition Array by Odd and Even */
    public void partitionArray(int[] nums) {
        int left = 0, right = nums.length - 1, i = 0;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1)  left++;
            while (left < right && nums[right] % 2 == 0)   right--;
            swap(nums, left++, right--);
        }
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /* 49. Sort Letters by Case */
    public void sortLetters(char[] chars) {
        int left = 0, right = chars.length - 1, i = 0;
        while (left <= right) {
            while (left <= right && Character.isLowerCase(chars[left]))    left++;
            while (left <= right && Character.isUpperCase(chars[right]))    right--;
            if (left <= right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
    }

    /* 5. Kth Largest Element */
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        return quickSelect(k, nums, 0, nums.length - 1);
    }
    private int quickSelect(int k, int[] nums, int start, int end) {
        if (start == end)   return nums[start];
        int left = start, right = end;
        int pivot = nums[left + (right - left) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot)     left++;
            while (left <= right && nums[right] > pivot)    right--;
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (nums.length - k >= left) return quickSelect(k, nums, left, end);
        else if (nums.length - k <= right)    return quickSelect(k, nums, start, right);
        return nums[right + 1];
    }

    /* 461. Kth Smallest Numbers in Unsorted Array */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        return helper(k - 1, nums, 0, nums.length - 1);
    }
    private int helper(int k, int[] nums, int start, int end) {
        if (start == end)   return nums[start];
        int left = start, right = end;
        int pivot = nums[(right + left) / 2];
        while (left <= right) {
            while (left <= right && nums[left] < pivot)     left++;
            while (left <= right && nums[right] > pivot)    right--;
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (left <= k && left <= end)    return helper(k, nums, left, end);
        else if (right >= k && right >= start)  return helper(k, nums, start, right);
        else    return nums[k];
    }

    /* 31. Partition Array */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0)   return 0;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            while (left <= right && nums[left] < k)     left++;
            while (left <= right && nums[right] >= k)    right--;
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    /* 59. 3Sum Closest */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
        int diff = Integer.MAX_VALUE, sum = 0;
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            int left = i + 1, right = numbers.length - 1;
            while (left < right) {
                int subSum = numbers[i] + numbers[left] + numbers[right];
                if (Math.abs(subSum - target) < diff) {
                    sum = subSum;
                    diff = Math.abs(subSum - target);
                }
                if (subSum < target)   left++;
                else    right--;
            }
        }
        return sum;
    }

    /* 533. Two Sum - Closest to target */
    public int twoSumClosest(int[] nums, int target) {
        // write your code here
        int diff = Integer.MAX_VALUE, left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            diff = Math.min(Math.abs(sum - target), diff);
            if (sum < target)
                left++;
            else    right--;
        }
        return diff;
    }

    /* 443. Two Sum - Greater than target */
    public int twoSum2(int[] nums, int target) {
        // write your code here
        int left = 0, right = nums.length - 1, res = 0;
        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                res += right - left;
                right--;
            }
            else {
                left++;
            }
        }
        return res;
    }

    /* 609. Two Sum - Less than or equal to target */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        int left = 0, right = nums.length - 1, res = 0;
        Arrays.sort(nums);
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum <= target) {
                res += right - left;
                left++;
            }
            else right--;
        }
        return res;
    }

    /* 382. Triangle Count */
    public int triangleCount(int[] S) {
        if (S.length < 3)   return 0;
        int ans = 0;
        Arrays.sort(S);
        for (int i = S.length - 1; i > 0; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                int subsum = S[left] + S[right];
                if (subsum > S[i]) {
                    ans += right - left;
                    right --;
                }
                else left++;
            }
        }
        return ans;
    }

    /* 90. k Sum II */
    public List<List<Integer>> kSumII(int[] numbers, int k, int target) {
        List<List<Integer>> list = new LinkedList<>();
        if (k == 1) {
            for (int num: numbers) {
                if (num == target) {
                    list.add(new LinkedList<>(Arrays.asList(target)));
                    return list;
                }
            }
            return list;
        }
        Arrays.sort(numbers);
        helper(numbers, k, target, 0, 0, new LinkedList<>(), list);
        return list;
    }
    public void helper(int[] numbers, int k, int target,
                       int startIndex, int sum,
                       List<Integer> subList, List<List<Integer>> list) {
        if (k == 2) {
            int left = startIndex, right = numbers.length - 1;
            while (left < right) {
                int subSum = sum + numbers[left] + numbers[right];
                if (subSum == target) {
                    List<Integer> newList = new LinkedList<>(subList);
                    newList.add(numbers[left]);
                    newList.add(numbers[right]);
                    list.add(newList);
                    while (left < right && numbers[left] == numbers[left + 1]) left++;
                    while (left < right && numbers[right] == numbers[right - 1]) right--;
                    left++;
                    right--;
                } else if (subSum < target) left++;
                else right--;
            }
            return;
        }
        for (int i = startIndex; i < numbers.length; i++) {
            subList.add(numbers[i]);
            helper(numbers, k - 1, target, i + 1, sum + numbers[i], subList, list);
            subList.remove(subList.size() - 1);
        }
    }

    /* 57. 3Sum */
    public List<List<Integer>> threeSum(int[] numbers) {
        // write your code here
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 2; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1])  continue;
            int left = i + 1, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];
                if (sum == 0) {
                    list.add(Arrays.asList(numbers[i], numbers[left], numbers[right]));
                    while (left + 1 < right && numbers[left] == numbers[left + 1])
                        left++;
                    while (left < right - 1 && numbers[right] == numbers[right - 1])
                        right--;
                    left++;
                    right--;
                }
                else if (sum < 0)   left++;
                else    right--;
            }
        }
        return list;
    }

    /* 587. Two Sum - Unique pairs */
    public int twoSum6(int[] nums, int target) {
        // write your code here
        int left = 0, right = nums.length - 1;
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                set.add(nums[left]);
                left++;
                right--;
            }
            else if (sum < target)  left++;
            else    right--;
        }
        return set.size();
    }

    /* 608. Two Sum II - Input array is sorted */
    public int[] twoSum(int[] nums, int target) {
        // write your code here
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) return new int[] {left + 1, right + 1};
            if (sum < target)   left++;
            else    right--;
        }
        return new int[] {-1, -1};
    }

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
