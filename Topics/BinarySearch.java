package LeetCode.Topics;

import java.util.*;
public class BinarySearch {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(1<<3);

        /* 153. Find Minimum in Rotated Sorted Array */
        int[] arr153 = {5,1,3};
        System.out.println(bs.findMin(arr153));

        /* 33. Search in Rotated Sorted Array */
        System.out.println(bs.search(arr153, 5));

        /* 367. Valid Perfect Square */
        System.out.println(bs.isPerfectSquare(1));

        /* 29. Divide Two Integers */
        System.out.println(bs.divide(10, 3));

        /* 658. Find K Closest Elements */
        int[] arr658 = {1,3,3,3,5,7,7,8,8,8};
        System.out.println(bs.findClosestElements(arr658, 6,3));

        /* 287. Find the Duplicate Number */
        int[] arr287 = {2,5,1,1,4,3};
        System.out.println(bs.findDuplicateTwoPointers(arr287));

        /* 34. Search for a Range */
        int[] arr34 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(bs.searchRange(arr34, 8)));

    }

    /* 34. Search for a Range */
    public int[] searchRange(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target)     hi = mid - 1;
            else if (nums[mid] < target)    lo = mid + 1;
            else {
                int l = mid, r = mid;
                while (l - 1 >= 0 && nums[l - 1] == target) l--;
                while (r + 1 < nums.length && nums[r + 1] == target) r++;
                return new int[] {l, r};
            }
        }
        return new int[] {-1, -1};
    }

    /* 74. Search a 2D Matrix */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        int lo = 0, hi = row * col - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int r = mid / col, c = mid % col;
            if (matrix[r][c] > target) hi = mid - 1;
            else if (matrix[r][c] < target) lo = mid + 1;
            else    return true;
        }
        return false;
    }

    /* 287. Find the Duplicate Number */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)   return -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2, count = 0;
            for (int i : nums) {
                if (i <= mid)   count ++;
            }
            if (count > mid)    high = mid - 1;
            else    low = mid + 1;
        }
        return low;
    }
    public int findDuplicateTwoPointers(int[] nums) {
        int slow = nums.length, fast = nums.length;
        do{
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);
        slow = nums.length;
        while (slow != fast) {
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }
        return slow;
    }

    /* 658. Find K Closest Elements */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            System.out.println(Arrays.toString(arr) +" "+left+" "+mid+" "+right + " "+k);
            if (x - arr[mid] > arr[mid + k] - x)    left = mid + 1;
            else    right = mid;

        }
        List<Integer> list = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /* 29. Divide Two Integers */
    public int divide(int dividend, int divisor) {
        boolean neg = (divisor < 0 && dividend < 0) || (dividend > 0 && divisor > 0) ? false : true;
        long ldivisor = Math.abs((long)divisor);
        long ldividend = Math.abs((long)dividend);
        long ans = divideHelper(ldividend, ldivisor);
        if (ans > Integer.MAX_VALUE) {
            ans = neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        else    ans = neg ? ans * -1: ans;
        return (int)ans;
    }
    public long divideHelper(long dividend, long divisor) {
        if (dividend < divisor) return 0;
        long sum = divisor;
        long count = 1;
        while ((sum + sum) <= dividend) {
            sum *= 2;
            count *= 2;
        }
        return count + divideHelper(dividend - sum, divisor);
    }


    /* 367. Valid Perfect Square */
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid > num) right = (int)mid - 1;
            else if (mid * mid < num) left = (int)mid + 1;
            else    return true;
        }
        return false;
    }

    public boolean isPerfectSquare2(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /* 35. Search Insert Position */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else if (nums[mid] > target) right = mid - 1;
            else    return mid;
        }
        return left;
    }

    /* 375. Guess Number Higher or Lower II */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return getMoneyAmountDPHelper(dp, 1, n);
    }
    public int getMoneyAmountDPHelper(int[][] dp, int left, int right) {
        if (left >= right)   return 0;
        if (dp[left][right] != 0)   return dp[left][right];
        int res = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            int l = getMoneyAmountDPHelper(dp, left, i - 1);
            int r = getMoneyAmountDPHelper(dp, i + 1, right);
            int temp = i + Math.max(l, r);
            res = Math.min(res, temp);
            System.out.println(l +" "+r+" "+temp+" "+res);
        }
        dp[left][right] = res;
        return res;
    }

    /* 374. Guess Number Higher or Lower */
    int guess(int num){return 0;};
    public int guessNumber(int n) {
        return (int)guessHelper(n, 1, n);
    }
    public long guessHelper(int n, long left, long right) {
        long mid = (left + right) / 2;
        System.out.println(mid);
        if (guess((int)mid) > 0) return guessHelper(n, mid + 1, n);
        else if (guess((int)mid) < 0)    return guessHelper(n, 1, mid - 1);
        else    return mid;
    }

    /* 81. Search in Rotated Sorted Array II */
    public boolean searchInDuplicates(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)    return true;
            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if (nums[mid] < nums[left]){
                if (target <= nums[right] && target > nums[mid])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            else {
                left ++;
            }
        }
        return false;
    }

    /* 33. Search in Rotated Sorted Array */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid])    return mid;
            else if (target > nums[mid]) {
                if (nums[mid] >= nums[left] && nums[mid] > nums[right]) left = mid + 1;
                else {
                    if (target > nums[right])    right = mid - 1;
                    else    left = mid + 1;
                }
            }
            else {
                if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                    if (target >= nums[left])   right = mid - 1;
                    else    left = mid + 1;
                }
                else    right = mid - 1;
            }
        }
        return -1;
    }

    /* 153. Find Minimum in Rotated Sorted Array */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        if (nums.length == 1)   return nums[0];
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1])   return nums[mid];
            if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
//            System.out.println(mid);
        }
        return nums[left];
    }

    /* 29. Divide Two Integers */
//    public int divide(int dividend, int divisor) {
//
//    }

    /* 222. Count Complete Tree Nodes */
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 : height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
                : (1 << h-1) + countNodes(root.left);
    }
    public int height(TreeNode node) {
        return node == null ? -1 : 1 + height(node.left);
    }

    /* 270. Closest Binary Search Tree Value */
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode next = target > a ? root.right : root.left;
        if (next == null) return root.val;
        int b = closestValue(next, target);
        return Math.abs(a - target) > Math.abs(b - target) ? b : a;
    }

    /* 349. Intersection of Two Arrays */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> res = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i))    res.add(i);
        }
        int[] arr = new int[res.size()];
        int pos = 0;
        for (int i : res) {
            arr[pos++] = i;
        }
        return arr;
    }

    /* 350. Intersection of Two Arrays II */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)   return new int[0];
        List<Integer> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i : nums2) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) - 1);
                list.add(i);
                if (map.get(i) == 0)    map.remove(i);
            }
        }
        int[] arr = new int[list.size()];
        int pos = 0;
        for (int i : list) {
            arr[pos++] = i;
        }
        return arr;
    }
}
