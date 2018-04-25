package LeetCode;
import java.util.*;

public class EasyMedium {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        EasyMedium em = new EasyMedium();
        /* 293. Flip Game */
        System.out.println(em.generatePossibleNextMoves("++++"));

        /* 294. Flip Game II */
        System.out.println(em.canWin("++++++"));
        System.out.println(em.canWinBacktracking2("++++++"));

        /* 247. Strobogrammatic Number II */
        System.out.println(em.findStrobogrammatic(3));

        /* 401. Binary Watch */
        System.out.println(em.readBinaryWatch(1));

        /* 267. Palindrome Permutation II */
        System.out.println(em.generatePalindromes("aabbaa"));

        /* 47. Permutations II */
        int[] arr47 = {3,3,0,3};
        System.out.println(em.permuteUnique(arr47));

        /* 31. Next Permutation */
        int[] arr31 = {1,3,2};
        em.nextPermutation(arr31);
        System.out.println(Arrays.toString(arr31));

        /* 389. Find the Difference */
        System.out.println(em.findTheDifferenceBitwise("abcd", "dcbae"));

        /* 415. Add Strings */
        System.out.println(em.addStrings("99", "1"));

        /* 43. Multiply Strings */
        System.out.println(em.multiply("123", "456"));

        System.out.println(em.countHoles(0));

        String[] s1 = {"a",
                "jk",
                "abb",
                "mn",
                "abc"};

        String[] s2 = {"bb",
                "kj",
                "bbc",
                "op",
                "def"};
        System.out.println(Arrays.toString(em.getMinimumDifference(s1, s2)));

        String[] s3 = {"2 3",
                "3 7",
                "4 1"};
        System.out.println(em.countX(s3));

        String[] s4 = {"369/844+256/269"};
//        System.out.println(Arrays.toString(em.reducedFractionSums(s4)));
//        System.out.println(findOver(844, 269));

        /* 400. Nth Digit */
        System.out.println(em.findNthDigit(15));


    }

    /* 400. Nth Digit */
    public int findNthDigit(int n) {
        int start = 1, len = 1;
        long count = 9;
        while (n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }
        start += (n - 1) / len;
        String ans = new String(start+"");
        return ans.charAt((n - 1) % len) - '0';
    }

    /* IEX OA */
    public String[] reducedFractionSums(String[] expressions) {
        /*
         * Write your code here.
         */
        String plus = new String("+");
        String[] ans = new String[expressions.length];
        for (int i = 0; i < expressions.length; i++){
            String[] temp = new String[2];
            StringBuilder sb = new StringBuilder();
            for (char c: expressions[i].toCharArray()) {
                if (c == '+') {
                    temp[0] = sb.toString();
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
            temp[1] = sb.toString();
            int[] nums = new int[temp.length * 2];
            int pos = 0;
            for (int j = 0; j < temp.length; j++) {
                String[] form = temp[j].split("/");
                nums[pos++] = Integer.parseInt(form[0]);
                nums[pos++] = Integer.parseInt(form[1]);
            }
            ans[i] = calc(nums);
        }
        return ans;
    }
    static String calc(int[] nums) {
        StringBuilder sb = new StringBuilder();
        int a = nums[0] * nums[3];
        int c = nums[2] * nums[1];
        int num1 = a + c;
        int num2 = nums[1] * nums[3];
        System.out.println(num1+" "+num2);
        int over = (int)findOver(num1, num2);
        System.out.println(over);
        sb.append(num1 / (num2 / over) + "/" +over);
        return sb.toString();
    }
    static double findOver(double num1, double num2) {
        if (num1 == num2) return 1;
        double small = Math.min(num1, num2);
        double big = Math.max(num1, num2);
        if (big % small == 0)   return big/small;
        for (int i = (int)small - 1; i >= 2; i--) {
            if (big % i == 0 && small % i == 0) return big / i;
        }
        return big * small;
    }

    public long countX(String[] steps) {
        /*
         * Write your code here.
         */

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < steps.length; i++) {
            String[] temp = steps[i].split(" ");
            int[] nums = new int[temp.length];
            for (int j = 0; j < temp.length; j++) {
                nums[j] = Integer.parseInt(temp[0]);
            }
            if (nums.length > 2)    return -1;
            for (int j = 0; j < nums[0]; j++) {
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
        }
        long ans = 0;
//        System.out.println(map.values());
        int max = Integer.MIN_VALUE;
        for (int i : map.values()) {
            max = Math.max(max, i);
            if (i == max) ans++;
        }
        return ans;
    }

    public int[] getMinimumDifference(String[] a, String[] b) {
        /*
         * Write your code here.
         */
        if (a == null || b == null) return null;
        if (a.length != b.length) {
            int[] ans = new int[1];
            ans[0] = -1;
            return ans;
        }
        int[] ans = new int[a.length];
        for (int i = 0; i < ans.length; i++) {
            if (a[i].length() != b[i].length()) ans[i] = -1;
            else    ans[i] = getMinimumDifferenceHelper(a[i], b[i]);
        }
        return ans;
    }
    public int getMinimumDifferenceHelper(String a, String b) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: a.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c: b.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0)    map.remove(c);
            }

        }
        int sum = 0;
        for (int i: map.values()) {
            sum += i;
        }
        return sum;
    }

    public int countHoles(int num) {
        /*
         * Write your code here.
         */
        if (num == 0)   return 1;
        int sum = 0;
        while (num != 0) {
            System.out.println(num);
            int temp = num % 10;
            if ("0469".contains(temp+""))  sum += 1;
            else if ("8".contains(temp +"")) sum += 2;
            num /= 10;
        }
        return sum;
    }

    /* 2. Add Two Numbers */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0), prev = ans;
        int carry = 0;
        while (l1 != null || l2 != null || carry == 1) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            ListNode node = new ListNode((x + y + carry) % 10);
            prev.next = node;
            prev = node;
            carry = (x + y + carry) / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return ans.next;
    }

    /* 43. Multiply Strings */
    public String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int[] pos = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
//                System.out.println(Arrays.toString(pos));
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
//                System.out.println(Arrays.toString(pos));
            }
        }
        for (int i: pos) {
            if (! (sb.length() == 0 && i == 0)) sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /* 415. Add Strings */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0; i >= 0 || j >= 0 || carry == 1; i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }

    /* 389. Find the Difference */
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c: s.toCharArray()) {
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0)
                map.remove(c);
        }
        char ans = 0;
        for (char c: map.keySet()) ans = c;
        return ans;
    }

    public char findTheDifferenceBitwise(String s, String t) {
        char ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            ans ^= t.charAt(i);
        }
        return ans;
    }

    /* 31. Next Permutation */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }
    public void swap(int[] nums, int lo, int hi) {
        int temp = nums[lo];
        nums[lo] = nums[hi];
        nums[hi] = temp;
        return;
    }
    public void reverse(int[] nums, int lo, int hi) {
        while (lo < hi) swap(nums, lo++, hi--);
    }

    /* 47. Permutations II */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        permuteUniqueHelper(nums, new boolean[nums.length], ans, new ArrayList<>());
        return ans;
    }
    public void permuteUniqueHelper(int[] nums, boolean[] used, List<List<Integer>> ans, List<Integer> subAns) {
        if (subAns.size() == nums.length) {
            ans.add(new ArrayList<>(subAns));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            if (!used[i]) {
                used[i] = true;
                subAns.add(nums[i]);
                permuteUniqueHelper(nums, used, ans, subAns);
                subAns.remove(subAns.size() - 1);
                used[i] = false;
            }
        }
    }

    /* 267. Palindrome Permutation II */
    public List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0 ) + 1);
        }
        int count = 0;
        for (int i : map.values()) {
            if (i % 2 == 1) count ++;
        }
        if (count > 1)  return ans;
        List<Character> perms = new ArrayList<>();
        String mid = "";
        for (char ch : map.keySet()) {
            if (map.get(ch) % 2 == 1)   mid += ch;
            for (int i = 0; i < map.get(ch) / 2;i ++) {
                perms.add(ch);
            }
        }
        generatePalindromesHelper(perms, new boolean[perms.size()], mid, ans, new StringBuilder());
        return ans;
    }
    public void generatePalindromesHelper(List<Character> perms, boolean[] used, String mid,
                                          List<String> ans, StringBuilder sb) {
//        System.out.println(perms +" " +sb.toString() + " " + ans);
        if (sb.toString().length() == perms.size()) {
            ans.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }
        for (int i = 0; i < perms.size(); i++) {
            if (i > 0 && perms.get(i) == perms.get(i - 1) && !used[i - 1])  continue;
//            System.out.println(perms.get(i));
            if (!used[i]) {
                used[i] = true;
                sb.append(perms.get(i));
                generatePalindromesHelper(perms, used, mid, ans, sb);
                sb.deleteCharAt(sb.toString().length() - 1);
                used[i] = false;
            }
        }
    }

    public boolean canPermutePalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0 ) + 1);
        }
        int count = 0;
        for (int i : map.values()) {
            if (i % 2 == 1) count ++;
        }
        return count == 1? true : false;
    }

    /* 362. Design Hit Counter */
    class HitCounter {
        List<Integer> list;
        /** Initialize your data structure here. */
        public HitCounter() {
            list = new LinkedList<>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            list.add(timestamp + 300);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int count = 0;
            for (int i : list) {
                if (timestamp < i) count++;
            }
            return count;
        }
    }

    /* 359. Logger Rate Limiter */
    class Logger {
        HashMap<String, Integer> map;
        /** Initialize your data structure here. */
        public Logger() {
            map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (timestamp < map.getOrDefault(message, 0))
                return false;
            map.put(message, timestamp + 10);
            return true;
        }
    }

    /* 401. Binary Watch */
    public List<String> readBinaryWatch(int num) {
        int[] hours = {1, 2, 4, 8};
        int[] mins = {1, 2, 4, 8, 16, 32};
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            List<Integer> hourList = new ArrayList<>();
            List<Integer> minList = new ArrayList<>();
            readBinaryWatchHelper(hours, i, 0, 0, hourList);
            readBinaryWatchHelper(mins, num - i, 0, 0, minList);
            for (int hour : hourList) {
                if (hour >= 12) continue;
                for (int min : minList) {
                    if (min >= 60)  continue;
                    ans.add(hour + ":" + (min < 10 ? "0" + min : min));
                }
            }
        }
        return ans;
    }
    public void readBinaryWatchHelper(int[] arr, int count, int pos, int sum, List<Integer> list) {
        if (count == 0) {
            list.add(sum);
            return;
        }
        for (int i = pos; i < arr.length; i++) {
            readBinaryWatchHelper(arr, count - 1, i + 1, sum + arr[i], list);
        }
    }

    /* 463. Island Perimeter */
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (j > 0 && grid[i][j - 1] == 1) result -= 2;
                    if (i > 0 && grid[i - 1][j] == 1) result -= 2;
                }
            }
        }
        return result;
    }

    /* 198. House Robber */
    public int robDP(int[] nums) {
        int rob = 0, notrob = 0;
        for (int i = 0; i < nums.length; i++) {
            int currob = notrob + nums[i];
            notrob = Math.max(rob, notrob);
            rob = currob;
        }
        return Math.max(rob, notrob);
    }

    /* 213. House Robber II */
    public int rob2DP(int[] nums) {
        if (nums.length == 1)   return nums[0];
        return Math.max(rob2DPHelper(nums, 0, nums.length - 1), rob2DPHelper(nums, 1, nums.length));
    }
    public int rob2DPHelper(int[] nums, int left, int right) {
        int rob = 0, notrob = 0;
        for (int i = left; i < right; i++) {
            int currob = notrob + nums[i];
            notrob = Math.max(rob, notrob);
            rob = currob;
        }
        return Math.max(rob, notrob);
    }

    /* 337. House Robber III */
    public int rob3DFS(TreeNode root) {
        int[] res = rob3DFSHelper(root);
        return Math.max(res[0], res[1]);
    }
    public int[] rob3DFSHelper(TreeNode root) {
        if (root == null)   return new int[2];
        int[] left = rob3DFSHelper(root.left);
        int[] right = rob3DFSHelper(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    /* 256. Paint House */
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(Math.min(costs[costs.length - 1][0], costs[costs.length - 1][1]), costs[costs.length - 1][2]);
    }

    /* 276. Paint Fence */
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) return 0;
        if (n == 1) return k;
        int[] same = new int[n];
        int[] diff = new int[n];
        same[0] = same[1] = k;
        diff[0] = k;
        diff[1] = k * (k - 1);
        for (int i = 2; i < n; i++) {
            same[i] = diff[i - 1];
            diff[i] = (same[i - 1] + diff[i - 1]) * (k - 1);
        }
        return same[n - 1] + diff[n - 1];
    }

    /* 766. Toeplitz Matrix */
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1])   return false;
            }
        }
        return true;
    }

    /* 246. Strobogrammatic Number */
    public boolean isStrobogrammatic(String num) {
        int left = num.length() % 2 == 0 ? num.length() / 2 - 1: num.length()/2;
        int right = num.length()/2;
        return isStrobogrammaticHelper(num, left, right);
    }
    public boolean isStrobogrammaticHelper(String num, int left, int right) {
        if (left < 0 && right > num.length() - 1)   return true;
        else if (num.charAt(left) == num.charAt(right) && num.charAt(left) != '0'
                && num.charAt(left) != '1' && num.charAt(left) != '8')  return false;
        else if (num.charAt(left) != num.charAt(right) && !((num.charAt(left) == '6' && num.charAt(right) == '9')
                || (num.charAt(left) == '9' && num.charAt(right) == '6')))  return false;
        return isStrobogrammaticHelper(num, left--, right++);
    }

    /* 247. Strobogrammatic Number II */
    public List<String> findStrobogrammatic(int n) {
        List<String> list = new ArrayList<>();
        findStrobogrammaticHelper(list, new char[n], 0, n - 1);
        return list;
    }
    public void findStrobogrammaticHelper(List<String> list, char[] chs, int left, int right) {
        if (left > right) {
            list.add(new String(chs));
            return;
        }
        else if (left == right) {
            chs[left] = '0';
            list.add(new String(chs));
            chs[left] = '1';
            list.add(new String(chs));
            chs[left] = '8';
            list.add(new String(chs));
            return;
        }
        if (left != 0) {
            chs[left] = '0';
            chs[right] = '0';
            findStrobogrammaticHelper(list, chs, left + 1, right - 1);
        }
        chs[left] = '1';
        chs[right] = '1';
        findStrobogrammaticHelper(list, chs, left + 1, right - 1);
        chs[left] = '8';
        chs[right] = '8';
        findStrobogrammaticHelper(list, chs, left + 1, right - 1);
        chs[left] = '6';
        chs[right] = '9';
        findStrobogrammaticHelper(list, chs, left + 1, right - 1);
        chs[left] = '9';
        chs[right] = '6';
        findStrobogrammaticHelper(list, chs, left + 1, right - 1);
    }

    /* 292. Nim Game */
    /*
    * win: 1,2,3,5,6,7,9,10,11
    * lose: 4,8,12
    * */
    public boolean canWinNim(int n) {
        return n < 4 ? true : n % 4 == 0 ? false : true;
    }

    /* 293. Flip Game */
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '+' && s.charAt(i) == '+')
                list.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
        }
        return list;
    }

    /* 294. Flip Game II */
    //  Backtracking way 1 - 199ms
    public boolean canWin(String s) {
        if (s == null || s.length() < 2)    return false;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String temp = s.substring(0, i) + "--" + s.substring(i + 2);
                if(!canWin(temp))   return true;
            }
        }
        return false;
    }
    //  Backtracking way 2 - 18ms - Add a hash map to store all the combinations has appeared
    public boolean canWinBacktracking2(String s) {
        if (s == null || s.length() < 2)    return false;
        HashMap<String, Boolean> map = new HashMap<>();
        return canWinBacktracking2Helper(s, map);
    }
    public boolean canWinBacktracking2Helper(String s, HashMap<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.startsWith("++", i)) {
                String temp = s.substring(0, i) + "--" + s.substring(i + 2);
                if(!canWinBacktracking2Helper(temp, map)){
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }

    public boolean canWinBacktracking(String s, int index, boolean result) {
        System.out.println(index + " "+result);
        if (s.indexOf("++", index) < 0
                && s.indexOf("+++", index) < 0
                && s.indexOf("++++", index) < 0)   return !result;
        if (s.indexOf("++++", index) >= 0
                || s.indexOf("+++", index) >= 0)    return canWinBacktracking(s, index + s.indexOf("+++", index) + 3, !result);
        else if (s.indexOf("++", index) >= 0)    return canWinBacktracking(s, index + s.indexOf("++", index) + 2, !result);
        return !result;
    }
}
