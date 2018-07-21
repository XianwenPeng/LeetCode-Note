package LeetCode.Topics.SecondRun;
import java.util.*;

public class BinarySearch {

    /* 154. Find Minimum in Rotated Sorted Array II */
    public int findMinII(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int left, int right) {
        if (left > right)   return Integer.MAX_VALUE;
        int target = nums[right];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                right = mid;
            }
            else if (nums[mid] > target) {
                left = mid;
            }
            else {
                int leftSmall = helper(nums, left, mid);
                int rightSmall = helper(nums, mid, right);
                return leftSmall < rightSmall ? leftSmall : rightSmall;
            }
        }
        return nums[left] < nums[right] ? nums[left] : nums[right];
    }

    /* 159. Find Minimum in Rotated Sorted Array */
    public int findMinOptimization(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0)   return -1;
        int start = 0, end = nums.length - 1;
        int target = nums[end];
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (nums[start] <= nums[end])    return nums[start];
        return nums[end];
    }
    public int findMin(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0)   return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[start]) {
                end = mid;
            }
            else if (nums[mid] < nums[end]) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (nums[start] < nums[end])    return nums[start];
        return nums[end];
    }

    /* 447. Search in a Big Sorted Array */
    public int searchBigSortedArrayExponential(ArrayReader reader, int target) {
        // write your code here
        int index = 1;
        while (reader.get(index - 1) < target)  index *= 2;

        int start = 0, end = index;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target)  {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (reader.get(start) == target)   return start;
        if (reader.get(end) == target)     return end;
        return -1;
    }

    public int searchBigSortedArray(ArrayReader reader, int target) {
        // write your code here
        int start = 0, end = start + 2;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target)  {
                start = mid;
                end *= 2;
            }
            else {
                end = mid;
            }
        }
        if (reader.get(start) == target)   return start;
        if (reader.get(end) == target)     return end;
        return -1;
    }
    public class ArrayReader {
        public int get(int x)   {return x;}
    }
    /* 460. Find K Closest Elements */
    public int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        if (A == null || A.length == 0) return null;
        int lastLower = findLastLower(A, target);
        int left = lastLower, right = left + 1;
        int[] res = new int[k];

        for (int i = 0; i < k; i++) {
            if (isLeftCloser(A, target, left, right)) {
                res[i] = A[left];
                left--;
            }
            else {
                res[i] = A[right];
                right++;
            }
        }
        return res;
    }
    public boolean isLeftCloser(int[] A, int target, int left, int right) {
        if (left < 0)   return false;
        if (right >= A.length)  return true;
        if (A[left] != A[right])    return target - A[left] <= A[right] - target;
        return true;
    }
    public int findLastLower(int[] A, int target) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (A[end] < target)    return end;
        return start;
    }

    /* 74. First Bad Version */
    public int findFirstBadVersion(int n) {
        // write your code here
        if (n == 0) return -1;
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid))  {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if (isBadVersion(start))    return start;
        if (isBadVersion(end))  return end;
        return -1;
    }
    public boolean isBadVersion(int k) {return false;};

    /* 458. Last Position of Target */
    public int lastPosition(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0)   return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            }
            else if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (nums[end] == target)    return end;
        if (nums[start] == target)  return start;
        return -1;
    }

    /* 14. First Position of Target */
    public int binarySearch(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0)   return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            }
            else if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if (nums[start] == target)  return start;
        if (nums[end] == target)    return end;
        return -1;
    }

    /* 457. Classical Binary Search */
    public int findPosition(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length == 0)   return -1;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)    return mid;
            if (nums[mid] < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
}
