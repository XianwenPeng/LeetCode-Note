package LeetCode.Topics.SecondRun;
import java.util.*;

public class BinarySearch {

    /* 428. Pow(x, n) */
    public double myPow(double x, int n) {
        // write your code here
        boolean neg = false;
        if (n < 0) {
            x = 1 / x;
            n = - (n + 1);
            neg = true;
        }

        double ans = 1, base = x;
        while (n > 0) {
            if (n % 2 == 1) {
                ans *= base;
            }
            base *= base;
            n /= 2;
        }

        if (neg)    ans *= x;
        return ans;
    }

    /* 140. Fast Power */
    public int fastPower(int a, int b, int n) {
        // write your code here
        long base = a, ans = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                ans = ans * base % b;
            }
            n /= 2;
            base = base * base % b;
        }
        return (int) ans % b;
    }

    /* 845. Greatest Common Divisor */
    public int gcd(int a, int b) {
        // write your code here
        if (b != 0) {
            return gcd(b, a % b);
        }
        else
            return a;
    }

    /* 62. Search in Rotated Sorted Array */
    public int search(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return -1;
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target)   return mid;
            if (A[mid] < A[start]) {
                if (target <= A[end] && target > A[mid]){
                    start = mid;
                }
                else {
                    end = mid;
                }
            }
            else {
                if (target >= A[start] && target < A[mid]) {
                    end = mid;
                }
                else {
                    start = mid;
                }
            }
        }
        if (A[start] == target) return start;
        if (A[end] == target)   return end;
        return -1;
    }

    /* 75. Find Peak Element */
    public int findPeak(int[] A) {
        // write your code here
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid + 1]) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        return A[start] > A[end] ? start : end;
    }

    /*
    public int minArea(char[][] image, int x, int y) {
        // write your code here
        int[][] bounds = new int[2][2];
        bounds[0][0] = bounds[0][1] = x;
        bounds[1][0] = bounds[1][1] = y;
        bounds = dfs(image, x, y, new boolean[image.length],
                new boolean[image[0].length], bounds);
        int row = -1, col = -1;
        // if (bounds[0][0] == -1 || bounds[0][1] == -1
        //     || bounds[1][0] == -1 || bounds[1][1] == -1)
        //     return 0;
        row = bounds[0][1] - bounds[0][0] + 1;
        col = bounds[1][1] - bounds[1][0] + 1;
        return row * col;
    }
    public int[][] dfs(char[][] image, int x, int y,
                       boolean[] visitedX, boolean[] visitedY, int[][] bounds) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length
                || (visitedX[x] && visitedY[y]) || image[x][y] == '0')
            return bounds;
        visitedX[x] = true;
        visitedY[y] = true;
        int[] row = rangeInRow(image, x, y);
        int[] col = rangeInCol(image, x, y);
        bounds[0][0] = bounds[0][0] == -1 ? row[0] : Math.min(bounds[0][0], row[0]);
        bounds[0][1] = bounds[0][1] == -1 ? row[1] : Math.max(bounds[0][1], row[1]);

        bounds[1][0] = bounds[1][0] == -1 ? col[0] : Math.min(bounds[1][0], col[0]);
        bounds[1][1] = bounds[1][1] == -1 ? col[1] : Math.max(bounds[1][1], col[1]);

        // System.out.println(Arrays.toString(bounds[0])+ ", "+Arrays.toString(bounds[1]));
        // System.out.println(Arrays.toString(row)+ ": "+Arrays.toString(col));

        bounds = dfs(image, x + 1, y, visitedX, visitedY, bounds);
        bounds = dfs(image, x - 1, y, visitedX, visitedY, bounds);
        bounds = dfs(image, x, y + 1, visitedX, visitedY, bounds);
        bounds = dfs(image, x, y - 1, visitedX, visitedY, bounds);
        return bounds;
    }
    public int[] rangeInCol(char[][] image, int x, int y) {
        int row = x, upperBound = x, lowerBound = x;
        while (image[row + 1][y] == '1')   {
            lowerBound++;
        }
        row = x;
        while (image[row - 1][y] == '1')   {
            upperBound--;
        }
        return new int[] {upperBound, lowerBound};
    }
    public int[] rangeInRow(char[][] image, int x, int y) {
        int col = x, leftBound = y, rightBound = x;
        while (image[x][col + 1] == '1')   {
            rightBound++;
        }
        col = x;
        while (image[x][col - 1] == '1')   {
            leftBound--;
        }
        return new int[] {leftBound, rightBound};
    }*/

    /* 462. Total Occurrence of Target */
    public int totalOccurrence(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return 0;
        int left = 0, right = A.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] >= target) {
                right = mid;
            }
            else {
                left = mid;
            }
        }
        int leftBound = 0;
        if (A[left] == target)  leftBound = left;
        else if (A[right] == target) leftBound = right;
        else    return 0;

        left = 0;
        right = A.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] <= target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (A[right] == target) return right - leftBound + 1;
        else if (A[left] == target) return left - leftBound + 1;
        return 0;
    }

    /* 38. Search a 2D Matrix II */
    public int searchMatrixII(int[][] matrix, int target) {
        // write your code here
        int r = matrix.length - 1;
        int c = 0;
        int count = 0;
        while (r >= 0 && c < matrix[0].length) {
            if (matrix[r][c] == target) {
                count++;
                r--;
                c++;
                continue;
            }
            if (matrix[r][c] < target) {
                c++;
            }
            else {
                r--;
            }
        }
        return count;
    }

    /* 28. Search a 2D Matrix */
    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix == null || matrix.length == 0)   return false;
        int left = 0, right = matrix.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (matrix[mid][0] == target)   return true;
            if (matrix[mid][0] < target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        int level = matrix[0].length;
        if (target < matrix[right][0])  level = left;
        else level = right;

        left = 0;
        right = matrix[level].length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (matrix[level][mid] == target)   return true;
            if (matrix[level][mid] < target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (matrix[level][left] == target || matrix[level][right] == target)
            return true;
        return false;
    }

    /* 61. Search for a Range */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) return new int[] {-1, -1};
        int left = 0, right = A.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] >= target) {
                right = mid;
            }
            else{
                left = mid;
            }
        }
        int leftBound = -1, rightBound = -1;
        if (A[left] == target)
            leftBound = left;
        else if (A[right] == target)
            leftBound = right;

        left = 0;
        right = A.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] <= target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (A[right] == target)  rightBound = right;
        else if (A[left] == target) rightBound = left;
        return new int[] {leftBound, rightBound};
    }

    /* 162. Find Peak Element */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }

    /* 585. Maximum Number in Mountain Sequence */
    public int mountainSequence(int[] nums) {
        // write your code here
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[left] > nums[right] ? nums[left] : nums[right];
    }

    public int mountainSequenceTwoMid(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid1 = left + (right - left) / 2;
            int mid2 = right - (right - mid1) / 2;
            if (nums[mid1] > nums[mid2]) {
                right = mid2 - 1;
            }
            else if (nums[mid1] < nums[mid2]) {
                left = mid1 + 1;
            }
            else {
                left = mid1;
                right = mid2;
            }
        }
        return nums[left] > nums[right] ? nums[left] : nums[right];
    }

    public int mountainSequenceWorstON(int[] nums) {
        return helperMountain(nums, 0, nums.length - 1);
    }
    public int helperMountain(int[] nums, int left, int right) {
        int target = nums[right];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                int leftHeighest = helper(nums, left, mid);
                int rightHeighest = helper(nums, mid, right);
                return leftHeighest > rightHeighest ? leftHeighest : rightHeighest;
            } else {
                left = mid;
            }
        }
        return nums[left] > nums[right] ? nums[left] : nums[right];
    }

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
