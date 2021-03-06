package LeetCode.Topics;

import java.util.Arrays;
import java.util.*;
import java.util.LinkedList;

public class TwoPointers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TwoPointers tp = new TwoPointers();

        /* 76. Minimum Window Substring */
        System.out.println(tp.minWindow("ADOBECODEBANC", "ABC"));

        /* 42. Trapping Rain Water */
        int[] arr42 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(tp.trap(arr42));
    }

    /* 142. Linked List Cycle II */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)   break;
        }
        if (fast == null || fast.next == null)  return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /* 141. Linked List Cycle */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /* 340. Longest Substring with At Most K Distinct Characters */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, max = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > k) {
                char cleft = s.charAt(left++);
                map.put(cleft, map.get(cleft) - 1);
                if (map.get(cleft) == 0) map.remove(cleft);
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    /* 3. Longest Substring Without Repeating Characters */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, subLen = 0, max = 0, count = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                count++;
                max = Math.max(count, max);
                set.add(c);
            }
            else {
                while (left < s.length()) {
                    char cleft = s.charAt(left++);
                    if (cleft != c) {
                        count--;
                        set.remove(cleft);
                    }
                    else
                        break;
                }
            }
        }
        return max;
    }

    /* 632. Smallest Range */
    class Node {
        int val;
        int index;
        int list;
        public Node (int list, int index, int val) {
            this.list = list;
            this.index = index;
            this.val = val;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> pq = new PriorityQueue<>((i1, i2) -> {return i1.val - i2.val;});
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i).get(0);
            pq.offer(new Node(i, 0, num));
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int diff = max - min, left = min, right = max;
        while (pq.size() == nums.size()){
            Node cur = pq.poll();
            if (diff > max - cur.val) {
                diff = max - cur.val;
                left = cur.val;
                right = max;
            }
            if (cur.index < nums.get(cur.list).size() - 1) {
                cur.index++;
                cur.val = nums.get(cur.list).get(cur.index);
                pq.offer(cur);
                if (cur.val > max)
                    max = cur.val;
            }
        }
        return new int[] {left, right};
    }


    /* 209. Minimum Size Subarray Sum */
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, subsum = 0, min = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            subsum += nums[right];
            while (left < nums.length) {
                if (subsum >= s) {
                    min = Math.min(min, right - left);
                    subsum -= nums[left++];
                }
                else
                    break;
            }
        }
        return min;
    }

    /* 19. Remove Nth Node From End of List */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1), start = dummy, end = dummy;
        dummy.next = head;
        for (int i = 0; i < n; i++) {
            end = end.next;
        }
        while (end.next != null) {
            end = end.next;
            start = start.next;
        }
        start.next = start.next.next;
        return dummy.next;
    }

    /* 215. Kth Largest Element in an Array */
    public int findKthLargest(int[] a, int k) {
        return quickSelect(a, k, 0, a.length - 1);
    }
    public int quickSelect(int[] a, int k, int left, int right) {
        int pivot = a[right], low = left, i = left;
        while (i < right) {
            if (a[i] <= pivot) swap(a, low++, i);
            i++;
        }
        swap(a, low, right);
        if (a.length - low == k) return a[low];
        else if (a.length - low < k)    return quickSelect(a, k, left, low - 1);
        else return quickSelect(a, k, low + 1, right);
    }

    /* LintCode 49. Sort Letters by Case */
    public void sortLetters(char[] chars) {
        if (chars.length < 2)   return;
        int left = 0, right = chars.length - 1, i = 0;
        while (i <= right) {
            if (chars[i] >= 'a') {
                char temp = chars[i];
                chars[i++] = chars[left];
                chars[left++] = temp;
            }
            else {
                char temp = chars[i];
                chars[i] = chars[right];
                chars[right--] = temp;
            }
        }
    }

    /* LintCode 373. Partition Array by Odd and Even */
    public void partitionArray(int[] nums) {
        if (nums.length < 2)    return;
        int even = nums.length - 1, odd = 0, i = 0;
        while (i <= even) {
            if (nums[i] % 2 == 1) {
                swap(nums, i++, odd++);
            }
            else {
                swap(nums, i, even--);
            }
        }
    }

    /* 75. Sort Colors*/
    public void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1, i = 0;
        while (i <= right) {
            if (nums[i] == 1)   i++;
            else if (nums[i] == 0)  swap(nums, left++, i++);
            else    swap(nums, right--, i);
        }
    }

    /* LintCode 31. Partition Array */
    public int partitionArray(int[] nums, int k) {
        return partitionArrayHelper(nums, k, 0, nums.length - 1);
    }
    public int partitionArrayHelper(int[] nums, int k, int l, int r) {
        if (nums.length < 2)    return 0;
        int left = l, right = r;
        while (left <= right) {
            while (left <= right && nums[left] < k) left++;
            while (left <= right && nums[right] >= k) right--;
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /* 11. Container With Most Water */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = 0;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[right], height[left]));
            if (height[left] > height[right]) right--;
            else left++;
        }
        return max;
    }

    /* 407. Trapping Rain Water II */
    class Cell {
        int row;
        int col;
        int height;
        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0 || heightMap[0].length == 0)  return 0;
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>((c1, c2) -> c1.height - c2.height);
        int n = heightMap.length, m = heightMap[0].length, sum = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    visited[i][j] = true;
                    pq.offer(new Cell(i, j, heightMap[i][j]));
                }
            }
        }
        int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int[] dir: dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >= 0 && row < n && col >= 0 && col < m && !visited[row][col]) {
                    visited[row][col] = true;
                    sum += heightMap[row][col] < cell.height ? cell.height - heightMap[row][col] : 0;
                    pq.offer(new Cell(row, col, Math.max(cell.height, heightMap[row][col])));
                }
            }
        }
        return sum;
    }

    /* 42. Trapping Rain Water */
    public int trap(int[] height) {
        if (height.length < 3)  return 0;
         int left = 0, right = height.length - 1, sum = 0;
         int leftHigh = height[left], rightHigh = height[height.length - 1];
         while (left < right) {
             if (height[left] <= height[right]) {
                 left++;
                 leftHigh = Math.max(leftHigh, height[left]);
                 if (height[left] < leftHigh) {
                     sum += leftHigh - height[left];
                 }
             }
             else {
                 right--;
                 rightHigh = Math.max(rightHigh, height[right]);
                 if (height[right] < rightHigh) {
                     sum += rightHigh - height[right];
                 }
             }
         }
         return sum;
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

    /* 1. Two Sum */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        if (nums.length < 2)    return ans;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) return new int[] {map.get(nums[i]), i};
            map.put(target - nums[i], i);
        }
        return ans;
    }

    /* LintCode 89. k Sum */
    int ans = 0;
    public int kSum(int[] A, int k, int target) {
        if (A.length < k)   return 0;
        if (k == 1) {
            int count = 0;
            for (int i : A) if (i == target) count++;
            return count;
        }
        Arrays.sort(A);
        kSumHelper(A, target, k, 0, 0);
        return ans;
    }
    public void kSumHelper(int[] A, int target, int layer, int sum, int start) {
        if (layer == 2) {
            int left = start, right = A.length - 1;
            while (left < right) {
                int newsum = A[left] + A[right] + sum;
                if (newsum == target) {
                    ans++;
                    while (left < right && A[left] == A[left + 1])  left++;
                    while (left < right && A[right] == A[right - 1])  right--;
                    left++;
                    right--;
                }
                else if (newsum > target)   right--;
                else    left++;
            }
            return;
        }
        for (int i = start; i < A.length; i++) {
            kSumHelper(A, target, layer - 1, sum + A[i], i + 1);
        }
    }

    /* 15. 3Sum */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();
        if (nums.length < 3)    return list;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])    continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1])    left++;
                    while (left < right && nums[right] == nums[right - 1])    right--;
                    left++;
                    right--;
                }
                else if (sum > 0)   right--;
                else    left++;
            }
        }
        return list;
    }

    /* 454. 4Sum II */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sum = A[i] + B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (map.containsKey(C[i] + D[j])) ans+= map.get(C[i] + D[j]);
            }
        }
        return ans;
    }
    public int fourSumCountBrutalForce(int[] A, int[] B, int[] C, int[] D) {
        Set<String> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    for (int n = 0; n < D.length; n++) {
                        int subsum = A[i] + B[j] + C[k] + D[n];
                        if (subsum == 0) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(i + j + k + n);
                            if (!set.contains(sb.toString())) {
                                set.add(sb.toString());
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /* 18. 4Sum */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4)    return new LinkedList<>();
        Arrays.sort(nums);
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int subsum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (subsum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1])  left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    }
                    else if (subsum > target) right--;
                    else left++;
                }
                while (j < nums.length - 2 && nums[j] == nums[j + 1])  j++;
            }
            while (i < nums.length - 3 && nums[i] == nums[i + 1])  i++;
        }
        return list;
    }

    /* 16. 3Sum Closest */
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3)    return -1;
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE, sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int subsum = nums[i] + nums[left] + nums[right];
                if (Math.abs(subsum - target) < closest) {
                    closest = Math.abs(subsum - target);
                    sum = subsum;
                }
                if (subsum > target)    right--;
                else if (subsum < target)   left++;
                else    return subsum;
            }
        }
        return sum;
    }

    /* 76. Minimum Window Substring */
    public String minWindow(String s, String t) {
        int right = 0, min = Integer.MAX_VALUE, left = 0, count = 0;
        String str = "", minstr = "";
        int[] chs = new int[256];
        for (int i = 0; i < t.length(); i++)    chs[t.charAt(i)]++;
        while (right < s.length()) {
            char cright = s.charAt(right);
            chs[cright]--;
            count += chs[cright] >= 0 ? 1 : 0;
            str += cright;
            while (count == t.length() && left < s.length()) {
                if (str.length() < min) {
                    min = str.length();
                    minstr = str;
                }
                char cleft = s.charAt(left);
                chs[cleft]++;
                str = str.substring(1);
                if (chs[cleft] > 0) count--;
                left++;
            }
            right++;
        }
        return minstr;
    }
}
