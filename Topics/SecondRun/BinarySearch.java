package LeetCode.Topics.SecondRun;
import java.util.*;

public class BinarySearch {
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
