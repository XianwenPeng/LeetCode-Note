package LeetCode.Topics.SecondRun;
import java.util.*;

public class DataStuctureII {
    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /* 840. Range Sum Query - Mutable */
    class NumArray {
        int[] bit, nums;
        public NumArray(int[] nums) {
            int len = nums.length + 1;
            bit = new int[len];
            this.nums = new int[len - 1];
            for (int i = 0; i < len - 1; i++) {
                update(i, nums[i]);
            }

        }

        public void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            for (int j = i + 1; j < bit.length; j += (j & (-j))) {
                bit[j] += diff;
                System.out.println(i+" "+val+" "+Arrays.toString(bit));
            }
        }

        public int sumRange(int i, int j) {
            return sum(j) - sum(i - 1);
        }

        public int sum(int n) {
            int ans = 0;
            for (int i = n + 1; i > 0; i -= (i & (-i))) {
                ans += bit[i];
            }
            return ans;
        }
    }

    /* 41. Maximum Subarray */
    public int maxSubArray(int[] nums) {
        // write your code here
        int[] dp = new int[nums.length + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /* 944. Maximum Submatrix */
    public int maxSubmatrix(int[][] matrix) {
        // write your code here
        if (matrix.length == 0 || matrix[0].length == 0)   return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + matrix[i - 1][j - 1];
            }
        }
        for (int[] arr: dp) System.out.println(Arrays.toString(arr));
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                int sum = 0;
                for (int k = 1; k <= n; k++) {
                    sum += (dp[j][k] - dp[i][k]);
                    max = Math.max(max, sum);
                    sum = Math.max(0, sum);
                }
            }
        }
        return max;
    }

    /* 138. Subarray Sum */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        List<Integer> res = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                res.add(i);
                res.add(map.get(sum) + 1);
                return res;
            }
            map.put(sum, i);
        }
        return res;
    }

    /* 931. Median of K Sorted Arrays */
    public double findMedian(int[][] nums) {
        // write your code here
        int n = 0;
        for (int[] arr: nums) {
            n += arr.length;
        }
        if (n == 0) return 0;
        if (n % 2 == 0) {
            return findKth(nums, n / 2) / 2.0 + findKth(nums, n / 2 + 1) / 2.0;
        }
        return findKth(nums, n / 2 + 1);
    }
    private int findKth(int[][] nums, int k) {
        int left = findMin(nums), right = findMax(nums);
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (countSmallerOrEqual(nums, mid) < k) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (countSmallerOrEqual(nums, left) == k)   return left;
        return right;
    }
    private int countSmallerOrEqual(int[][] nums, int num) {
        int count = 0;
        for (int[] arr: nums) {
            count += countSmallerOrEqual(arr, num);
        }
        return count;
    }
    private int countSmallerOrEqual(int[] nums, int num) {
        if (nums.length == 0)   return 0;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= num) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (nums[left] > num)   return left;
        if (nums[right] > num)  return right;
        return nums.length;
    }
    private int findMin(int[][] nums) {
        int min = 0;
        for (int[] arr: nums) {
            for (int n: arr) {
                min = Math.min(min, n);
            }
        }
        return min;
    }
    private int findMax(int[][] nums) {
        int max = Integer.MAX_VALUE;
        for (int[] arr: nums) {
            for (int n: arr) {
                max = Math.max(max, n);
            }
        }
        return max;
    }

    /* O(log(m + n)) 65. Median of two Sorted Arrays */
    public double findMedianSortedArraysII(int[] A, int[] B) {
        int n = A.length + B.length;
        if (n % 2 == 0) {
            return (findKthII(A, 0, B, 0, n / 2) + findKthII(A, 0, B, 0, n / 2 + 1)) / 2.0;
        }
        return findKthII(A, 0, B, 0, n / 2 + 1);
    }
    public int findKthII(int[] A, int indexA, int[] B, int indexB, int k) {
        if (indexA >= A.length) return B[indexB + k - 1];
        if (indexB >= B.length) return A[indexA + k - 1];

        if (k == 1) {
            return Math.min(A[indexA + k - 1], B[indexB + k - 1]);
        }
        int halfKOfA = indexA + k / 2 - 1 < A.length
                ? A[indexA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKOfB = indexB + k / 2 - 1 < B.length
                ? B[indexB + k / 2 - 1] : Integer.MAX_VALUE;

        if (halfKOfA < halfKOfB) {
            return findKthII(A, indexA + k / 2, B, indexB, k - k / 2);
        }
        return findKthII(A, indexA, B, indexB + k / 2, k - k / 2);
    }

    /* O(log(range) * (log(n) + log(m))) 65. Median of two Sorted Arrays */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int n = A.length + B.length;
        if (n % 2 == 0) {
            return (findKth(A, B, n / 2) + findKth(A, B, n / 2 + 1)) / 2.0;
        }
        return findKth(A, B, n / 2 + 1);
    }
    public int findKth(int[] A, int[] B, int k) {
        if (A.length == 0)  return B[k - 1];
        if (B.length == 0)  return A[k - 1];
        int left = Math.min(A[0], B[0]);
        int right = Math.max(A[A.length - 1], B[B.length - 1]);
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (countSmallOrEqual(A, mid) + countSmallOrEqual(B, mid) < k) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (countSmallOrEqual(A, left) + countSmallOrEqual(B, left) >= k) {
            return left;
        }
        else
            return right;
    }
    public int countSmallOrEqual(int[] arr, int num) {
        int left = 0, right = arr.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= num) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        if (arr[left] > num)   return left;
        if (arr[right] > num)   return right;
        return arr.length;
    }

    /* 654. Sparse Matrix Multiplication */
    public int[][] multiply(int[][] A, int[][] B) {
        // write your code here
        int n = A.length;
        int m = A[0].length;
        int g = B[0].length;
        int[][] res = new int[n][g];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 0)   continue;
                for (int k = 0; k < g; k++) {
                    res[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return res;
    }

    /* Heap O(nklogk + knlogn) 793. Intersection of Arrays */
    private class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int intersectionOfArraysII(int[][] arrs) {
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> {
            return arrs[p1.row][p1.col] - arrs[p2.row][p2.col];
        });
        for (int i = 0; i < arrs.length; i++) {
            Arrays.sort(arrs[i]);
            if (arrs[i].length > 0)
                heap.offer(new Pair(i, 0));
        }
        int count = 0, res = 0, lastVisited = 0;
        while(!heap.isEmpty()) {
            Pair pair = heap.poll();
            if (count == 0 || arrs[pair.row][pair.col] != lastVisited) {
                if (count == arrs.length)   res++;
                count = 1;
                lastVisited = arrs[pair.row][pair.col];
            }
            else {
                count++;
            }

            pair.col++;
            if (pair.col < arrs[pair.row].length) {
                heap.offer(pair);
            }
        }
        if (count == arrs.length)   res++;
        return res;
    }

    /* O(nk) 793. Intersection of Arrays */
    public int intersectionOfArrays(int[][] arrs) {
        // write your code here
        int count = 0;
        int[] indexes = new int[arrs.length];
        for (int[] arr: arrs) Arrays.sort(arr);
        boolean ended = false;
        while (!ended) {
            for (int i = 0; i < indexes.length; i++) {
                if (indexes[i] >= arrs[i].length) {
                    ended = true;
                    return count;
                }
            }
            if (foundSame(indexes, arrs))    count++;
        }
        return count;
    }
    private boolean foundSame(int[] indexes, int[][] arrs) {
        int min = arrs[0][indexes[0]], minPos = 0, first = min;
        boolean same = true;
        for (int i = 0; i < indexes.length; i++) {
            if (arrs[i][indexes[i]] != first)    same = false;
            if (arrs[i][indexes[i]] < min) {
                min = arrs[i][indexes[i]];
                minPos = i;
            }
        }
        if (!same) indexes[minPos]++;
        else {
            for (int i = 0; i < indexes.length; i++) {
                indexes[i]++;
            }
        }
        return same;
    }

    /* 548. Intersection of Two Arrays II */
    public int[] intersectionII(int[] nums1, int[] nums2) {
        // write your code here
        List<Integer> list = new LinkedList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])    i++;
            else if (nums1[i] > nums2[j])   j++;
            else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }

    /* 547. Intersection of Two Arrays */
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])  i++;
            else if (nums1[i] > nums2[j]) j++;
            else {
                if (index == 0 || index > 0 && nums1[i] != temp[index - 1])
                    temp[index++] = nums1[i];
                i++;
                j++;
            }
        }
        int[] res = new int[index];
        for (i = 0; i < index; i++) {
            res[i] = temp[i];
        }
        return res;
    }

    /* O(nklogk) O(k) 577. Merge K Sorted Interval Lists */
    private class PairMerge {
        int row;
        int col;
        public PairMerge(int row, int col) {
            this.col = col;
            this.row = row;
        }
    }
    public List<Interval> mergeKSortedIntervalListsOptimized(List<List<Interval>> intervals) {
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> {
            return intervals.get(p1.row).get(p1.col).start - intervals.get(p2.row).get(p2.col).start;
        });
        int k = intervals.size();
        for (int i = 0; i < k; i++) {
            if (intervals.get(i).size() > 0)
                heap.add(new Pair(i, 0));
        }
        List<Interval> res = new LinkedList<>();
        while (!heap.isEmpty()) {
            Pair pair = heap.poll();
            res.add(intervals.get(pair.row).get(pair.col));
            pair.col++;
            if (pair.col < intervals.get(pair.row).size()) {
                heap.offer(pair);
            }
        }
        return mergeIntervals(res);
    }
    private List<Interval> mergeIntervals(List<Interval> list) {
        List<Interval> res = new LinkedList<>();
        int start = list.get(0).start;
        int end = list.get(0).end;
        for (int i = 0; i < list.size(); i++) {
            Interval interval = list.get(i);
            if (interval.start > end) {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
            else {
                end = Math.max(end, interval.end);
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    /* 577. Merge K Sorted Interval Lists */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        // write your code here
        List<List<Interval>> res = new LinkedList<>(intervals);
        while (res.size() > 1) {
            List<List<Interval>> list = new LinkedList<>();
            for (int i = 0; i < res.size() - 1; i += 2) {
                list.add(mergeTwoLists(res.get(i), res.get(i + 1)));
            }
            if (res.size() % 2 == 1) {
                list.add(res.get(res.size() - 1));
            }
            res = list;
        }
        return res.get(0);
    }
    private List<Interval> mergeTwoLists(List<Interval> l1, List<Interval> l2) {
        List<Interval> res = new LinkedList<>();
        int i = 0, j = 0, index = 0;
        Interval last = null;
        while (i < l1.size() && j < l2.size()) {
            Interval i1 = l1.get(i), i2 = l2.get(j), cur = null;
            if (i1.start < i2.start) {
                cur = i1;
                i++;
            }
            else {
                cur = i2;
                j++;
            }
            last = mergeTwoIntervals(res, last, cur);
        }
        while (i < l1.size()) {
            last = mergeTwoIntervals(res, last, l1.get(i++));
        }
        while (j < l2.size()) {
            last = mergeTwoIntervals(res, last, l2.get(j++));
        }
        res.add(last);
        return res;
    }
    private Interval mergeTwoIntervals(List<Interval> res, Interval last, Interval cur) {
        if (last == null)   return cur;
        if (cur.start > last.end) {
            res.add(last);
            return cur;
        }
        last.end = Math.max(last.end, cur.end);
        return last;
    }

    /* 486. Merge K Sorted Arrays */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> {
            return n2 - n1;
        });
        int size = 0;
        for (int[] arr: arrays) {
            size += arr.length;
        }
        int[] res = new int[size];
        int index = size - 1;
        for (int[] arr: arrays) {
            for (int i = arr.length - 1; i >= 0; i--) {
                if (heap.size() < size) {
                    heap.add(arr[i]);
                    continue;
                }
                if (heap.peek() > arr[i]) {
                    res[index--] = heap.poll();
                    heap.add(arr[i]);
                }
            }
        }
        while (!heap.isEmpty()) {
            res[index--] = heap.poll();
        }
        return res;
    }

    /* 839. Merge Two Sorted Interval Lists */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        int i = 0, j = 0;
        List<Interval> res = new LinkedList<>();
        while (i < list1.size() && j < list2.size()) {
            Interval i1 = list1.get(i), i2 = list2.get(j);
            if (i1.start > i2.start) {
                addInterval(res, i2);
                j++;
            }
            else {
                addInterval(res, i1);
                i++;
            }
        }
        while (i < list1.size()) {
            addInterval(res, list1.get(i++));
        }
        while (j < list2.size()) {
            addInterval(res, list2.get(j++));
        }
        return res;
    }
    public void addInterval(List<Interval> res, Interval cur) {
        if (res.size() == 0 || res.get(res.size() - 1).end < cur.start) {
            res.add(cur);
            return;
        }
        Interval last = res.get(res.size() - 1);
        last.end = Math.max(last.end, cur.end);
    }

    /* 64. Merge Sorted Array */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            }
            else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }

    /* 6. Merge Two Sorted Arrays */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int[] res = new int[A.length + B.length];
        int i = 0, j = 0, index = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                res[index++] = A[i++];
            }
            else {
                res[index++] = B[j++];
            }
        }
        while (i < A.length) {
            res[index++] = A[i++];
        }
        while (j < B.length) {
            res[index++] = B[j++];
        }
        return res;
    }
}
