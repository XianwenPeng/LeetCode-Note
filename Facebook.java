package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import LeetCode.BinaryTree.TreeNode;
import javafx.util.Pair;

public class Facebook {
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }

    public static void main(String[] args){
        Facebook facebook = new Facebook();
        /* 33. Search in Rotated Sorted Array */
        int[] arr33 = {4,5,6,1,2,3};
        System.out.println(facebook.search(arr33, 2));

        /* 43. Multiply Strings */
        System.out.println(facebook.multiply("123", "456"));

        /* 50. Pow(x, n) */
        System.out.println(facebook.myPow(3, 3));

        /* 75. Sort Colors */
        int[] arr75 = {0,0,1,0,0,1};
        facebook.sortColors(arr75);
        System.out.println(Arrays.toString(arr75));

        /* 79. Word Search */
        char[][] ch79 = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(facebook.exist(ch79, "SEE"));

        /* 91. Decode Ways */
        System.out.println(facebook.numDecodings("1234"));

        /* 621. Task Scheduler */
        char[] ch621 = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(facebook.leastInterval(ch621, 2));

        /* 67. Add Binary */
        System.out.println(facebook.addBinary("11", "1"));

        /* 325. Maximum Size Subarray Sum Equals k */
        int[] nums325 = {-2, -1, 2, 1};
        System.out.println(facebook.maxSubArrayLen(nums325, 1));

        /* 314. Binary Tree Vertical Order Traversal */
        int[] arr314 = {3,9,20,15,7};
        BinaryTree tree = new BinaryTree();
        TreeNode root314 = tree.buildTree(arr314);
        System.out.println(facebook.verticalOrder(root314));

        /* 311. Sparse Matrix Multiplication */
        int[][] A311 = {{1, 0, 0}, {-1, 0, 3}};
        int[][] B311 = {{7, 0, 0}, { 0, 0, 0}, { 0, 0, 1}};
        System.out.println(facebook.multiply(A311, B311));

        /* 161. One Edit Distance */
        System.out.println(facebook.isOneEditDistance("abed", "abce"));

        /* 636. Exclusive Time of Functions */
        String[] arr636 = {"0:start:0","1:start:2","2:start:3","2:end:4","1:end:6","0:end:7"};
        List<String> list636 = Arrays.asList(arr636);
        System.out.println(Arrays.toString(facebook.exclusiveTime(3,list636)));

        /* 670. Maximum Swap */
        System.out.println(facebook.maximumSwap(2736));

        /* 523. Continuous Subarray Sum */
        int[] nums523 = {23, 2, 4, 6, 7};
        System.out.println(facebook.checkSubarraySum(nums523, 27));

        /* 494. Target Sum */
        int[] nums494 = {1,1,1,1,1};
        System.out.println(facebook.findTargetSumWays(nums494, 3));

        /* 477. Total Hamming Distance */
        int[] nums477 = {4,14,2};
        System.out.println(facebook.totalHammingDistance(nums477));

        /* 438. Find All Anagrams in a String */
        System.out.println(facebook.findAnagrams("cbaebabacd", "abc"));
    }

    /* 721. Accounts Merge */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> graph = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, Set<String>> unions = new HashMap<>();
        List<List<String>> res = new LinkedList<>();

        // Create the graph and parents
        // Initialize(): Make each node the parent of their own
        for (List<String> subList : accounts) {
            for (int i = 1; i < subList.size(); i++) {
                graph.put(subList.get(i), subList.get(0));
                parents.put(subList.get(i), subList.get(i));
            }
        }
        // Find(): Make the first one the parent of the rest
        for (List<String> subList : accounts) {
            String parentFirst = find(parents, subList.get(1));
            for (int i = 2; i < subList.size(); i++) {
                String parentRest = find(parents, subList.get(i));
                parents.put(parentRest, parentFirst);
            }
        }
        // Union(): Unions the nodes into map union
        for (List<String> subList : accounts) {
            String parent = find(parents, subList.get(1));
            if (!unions.containsKey(parent)) unions.put(parent, new TreeSet<>());
            Set<String> emails = unions.get(parent);
            for (int i = 1; i < subList.size(); i++) {
                emails.add(subList.get(i));
            }
        }
        // Add unions to the result list
        for (String parent: unions.keySet()) {
            List<String> subList = new LinkedList<>();
            subList.add(graph.get(parent));
            subList.addAll(new LinkedList<>(unions.get(parent)));
            res.add(subList);
        }
        return res;
    }
    public String find(Map<String, String> map, String str) {
        return map.get(str) == str ? str : find(map, map.get(str));
    }

    /* 438. Find All Anagrams in a String */
    public List<Integer> findAnagrams(String s, String p) {
        int[] chars = new int[26];
        List<Integer> list = new ArrayList<>();
        for(char c: p.toCharArray()){
            chars[c - 'a']++;
        }
        int start = 0, end = 0, count = p.length();
        while(end < s.length()){
            if(end - start == p.length() && chars[s.charAt(start++) - 'a']++ >= 0)
                count++;
            if(--chars[s.charAt(end++) - 'a'] >= 0)
                count--;
            if(count == 0)
                list.add(start);
//            System.out.println(start +" "+end+" "+count+" "+Arrays.toString(chars));
        }
        return list;
    }

    /* 286. Walls and Gates */
    public void wallsAndGates(int[][] rooms) {
        for(int i = 0 ; i < rooms.length; i++){
            for (int j = 0; j < rooms[0].length; j ++){
                if(rooms[i][j] == 0)   wallsAndGatesHelper(rooms, i, j, 0);
            }
        }
    }
    public void wallsAndGatesHelper(int[][] rooms, int x, int y, int distance){
        if(x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] < distance)   return;
        rooms[x][y] = distance;
        wallsAndGatesHelper(rooms, x + 1, y, distance + 1);
        wallsAndGatesHelper(rooms, x - 1, y, distance + 1);
        wallsAndGatesHelper(rooms, x, y + 1, distance + 1);
        wallsAndGatesHelper(rooms, x, y - 1, distance + 1);
    }

    /* 477. Total Hamming Distance */
    public int totalHammingDistance(int[] nums) {
        int count = 0;
        for(int i = 0; i < 32; i++){
            int bit = 0;
            for(int j = 0; j < nums.length; j++){
                bit += (nums[j] >> i) & 1;
            }
            count += bit *(nums.length - bit);
        }
        return count;
    }

    /* 494. Target Sum */
    public int findTargetSumWays(int[] nums, int S) {
        int[] count = new int[1];
        findTargetSumWaysHelper(nums, S, 0, 0, count);
        return count[0];
    }
    public void findTargetSumWaysHelper(int[] nums, int S, int sum, int index, int[] count){
        if(index == nums.length){
            if(sum == S)    count[0]++;
            return;
        }
        findTargetSumWaysHelper(nums, S, sum + nums[index], index+1, count);
        findTargetSumWaysHelper(nums, S, sum - nums[index], index+1, count);
    }

    /* 523. Continuous Subarray Sum */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(k != 0)  sum %= k;
            Integer prev = map.get(sum);
            if(prev != null){
                if(i - prev > 1)    return true;
            }
            else
                map.put(sum, i);
        }
        return false;
    }

    /* 670. Maximum Swap */
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] bucket = new int[10];
        for(int i = 0; i < digits.length; i++){
            bucket[digits[i] - '0'] = i;
        }
        for(int i = 0; i < digits.length; i++){
            for(int j = 9; j > digits[i] - '0'; j-- ){
                if(bucket[j] > i){
                    char temp = digits[i];
                    digits[i] = digits[bucket[j]];
                    digits[bucket[j]] = temp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }

    /* 133. Clone Graph */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
        return cloneGraphHelper(node, map);
    }
    public UndirectedGraphNode cloneGraphHelper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map){
        if(node == null)    return null;
        if(map.containsKey(node.label))
            return map.get(node.label);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone);
        for(UndirectedGraphNode neighbor: node.neighbors){
            clone.neighbors.add(cloneGraphHelper(neighbor, map));
        }
        return clone;
    }

    /* 636. Exclusive Time of Functions */
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<Integer> stack = new Stack<>();
        int res[] = new int[n];
        int time = 0;
        for(int i = 0; i < logs.size(); i++){
            String[] str = logs.get(i).split(":");
            if(!stack.isEmpty()) {
                System.out.println(stack.peek() +" " +Integer.parseInt(str[2]) +" "+time);
                res[stack.peek()] += Integer.parseInt(str[2]) - time;
            }
            time = Integer.parseInt(str[2]);
            if(str[1].equals("start")){
                stack.push(Integer.parseInt(str[0]));
            }
            else{
                res[stack.pop()]++;
                time++;
            }
        }
        return res;
    }

    /* 161. One Edit Distance */
    public boolean isOneEditDistance(String s, String t) {
        for(int i = 0; i < Math.min(s.length(), t.length()); i++){
            if(s.charAt(i) != t.charAt(i)){
                if(s.length() == t.length())
                    return s.substring(i+1).equals(t.substring(i+1));
                else if(s.length() < t.length())
                    return s.substring(i).equals(t.substring(i+1));
                else
                    return s.substring(i+1).equals(t.substring(i));
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }

    /* 125. Valid Palindrome */
    public boolean isPalindrome(String s) {
        String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String after = new StringBuilder(str).reverse().toString();
        return str.equals(after);
    }

    /* 277. Find the Celebrity */
    public int findCelebrity(int n) {
        int start = 0, end = n, found = 0;
        for(int i = 1; i < n; i++){
            if(knows(found, i)){
                found = i;
            }
        }
        for(int i = 0; i < n; i++){
            if(i != found && (!knows(i, found) || knows(found, i)))    return -1;
        }
        return found;
    }
    boolean knows(int a, int b){return false;}; // The knows API is defined in the parent class Relation.

    /* 311. Sparse Matrix Multiplication */
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] arr = new int[A.length][B[0].length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B[0].length; j++){
                int sum = 0;
                for(int k = 0; k < B.length; k++){
                    sum += A[i][k] * B[k][j];
                }
                arr[i][j] = sum;
            }
        }
        return arr;
    }

    /* 314. Binary Tree Vertical Order Traversal */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)    return list;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<TreeNode, Integer> index = new HashMap<TreeNode, Integer>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        index.put(root, 0);
        int min = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            int pos = index.get(node);
            if(!map.containsKey(pos))
                map.put(pos, new ArrayList<>());
            map.get(pos).add(node.val);
            if(node.left != null) {
                queue.offer(node.left);
                index.put(node.left, pos-1);
            }
            if(node.right != null) {
                queue.offer(node.right);
                index.put(node.right, pos+1);
            }
            min = Math.min(min, pos);
        }
        while(map.containsKey(min)){
            list.add(map.get(min++));
        }
        return list;
    }

    /* 253. Meeting Rooms II */
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length < 2)   return intervals.length;
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++){
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int count = intervals.length;
        for(int i = 0, j = 0; i < intervals.length; i++){
            if(i + 1 < intervals.length && start[i + 1] >= end[j]){
                count--;
                j++;
            }
        }
        return count;
    }
    public int minMeetingRooms2(Interval[] intervals) {
        if(intervals.length < 2)   return intervals.length;
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start >= heap.peek())   heap.poll();
            heap.offer(intervals[i].end);
        }
        return heap.size();
    }

    /* 325. Maximum Size Subarray Sum Equals k */
    public int maxSubArrayLen(int[] nums, int k) {
        int max = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(sum == k) max = i + 1;
            else if(map.containsKey(sum - k))   max = Math.max(max, i - map.get(sum-k));
            if(!map.containsKey(sum))   map.put(sum, i);
        }
        return max;
    }

    /* 67. Add Binary */
    public String addBinary(String a, String b) {
        int alen = a.length()-1, blen = b.length()-1, carry = 0;
        StringBuilder ans = new StringBuilder();
        while(alen >= 0 || blen >= 0){
            if(alen >= 0)   carry += a.charAt(alen--) - '0';
            if(blen >= 0)   carry += b.charAt(blen--) - '0';
            ans.append(carry % 2);
            carry = carry / 2;
        }
        if(carry > 0)   ans.append(1);
        return ans.reverse().toString();
    }

    /* 621. Task Scheduler */
    public int leastInterval(char[] tasks, int n) {
        int[] ch = new int[26];
        int max = 0, count = 0;
        for(char c: tasks) {
            ch[c - 'A']++;
            if(ch[c - 'A'] > max){
                max = ch[c - 'A'];
                count = 1;
            }
            else if(ch[c - 'A'] == max)  count++;
        }
        return Math.max(tasks.length, (n + 1) * (max - 1) + count);
    }

    /* 91. Decode Ways */
    public int numDecodings(String s) {
        int len = s.length();
        if(len == 0) return 0;
        int[] arr = new int[len+1];
        arr[len] = 1;
        arr[len-1] = s.charAt(len-1) == '0' ? 0 : 1;

        for(int i = len-2; i >= 0; i--){
            if(s.charAt(i) == '0') continue;
            else if(Integer.parseInt(s.substring(i, i+2)) <= 26)    arr[i] = arr[i+1]+arr[i+2];
            else
                arr[i] = arr[i+1];
        }
        return arr[0];
    }

    /* 79. Word Search */
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(existHelper(board, word, 0, i, j))   return true;
            }
        }
        return false;
    }
    public boolean existHelper(char[][] board, String word, int index, int x, int y){
        if(index == word.length())  return true;
        if(x < 0 || x == board.length || y < 0 || y == board[0].length) return false;
        if(board[x][y] != word.charAt(index))   return false;
        board[x][y] = '-';
        boolean found = existHelper(board, word, index+1, x, y+1)
                || existHelper(board, word, index+1, x, y-1)
                || existHelper(board, word, index+1, x+1, y)
                || existHelper(board, word, index+1, x-1, y);
        board[x][y] = word.charAt(index);
        return found;
    }

    /* 75. Sort Colors */
    public void sortColors(int[] nums) {
        int low = 0; int high = nums.length-1;
        for(int i = low; i <= high;){
            if(nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[low];
                nums[low] = temp;
                low++;
                i++;
            }
            else if(nums[i] == 2){
                int temp = nums[i];
                nums[i] = nums[high];
                nums[high] = temp;
                high--;
            }
            else{
                i++;
            }
        }
    }

    /* 56. Merge Intervals */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        if(intervals.size() == 0)   return list;
        intervals.sort((r1, r2) -> Integer.compare(r1.start, r2.start));
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for(Interval i : intervals){
            if(i.start > end){
                list.add(new Interval(start, end));
                start = i.start;
                end = i.end;
            }
            else {
                end = Math.max(end, i.end);

            }
        }
        list.add(new Interval(start, end));
        return list;
    }
    public List<Interval> merge2(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        if(intervals.size() == 0)   return list;
        int start[] = new int[intervals.size()];
        int end[] = new int[intervals.size()];
        for(int i = 0; i < intervals.size(); i++){
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for(int i = 0, j = 0; i < intervals.size(); i++){
            if(i == intervals.size() - 1 || start[i + 1] > end[i]) {
                list.add(new Interval(start[j], end[i]));
                j = i + 1;
            }
        }
        return list;
    }

    /* 50. Pow(x, n) */
    public double myPow(double x, int n) {
        if(n < 0){
            n *= -1;
            x = 1 / x;
        }
        if(n == Integer.MIN_VALUE){
            x *= x;
            n--;
        }
        if(n == 0)
            return 1;
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    /* 43. Multiply Strings */
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int pos[] = new int[len1 + len2];
        for(int i = len1 - 1; i >= 0; i--){
            for(int j = len2 - 1; j >= 0; j--){
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int pos1 = i + j, pos2 = i + j + 1;
                int sum = mul + pos[pos2];

                pos[pos1] += sum/10;
                pos[pos2] = sum%10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int s : pos){
            if(!(sb.length() == 0 && s == 0))
                sb.append(s);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /* 33. Search in Rotated Sorted Array */
    public int search(int[] nums, int target) {
        if(nums.length == 0)    return -1;
        int start = 0, end = nums.length-1;
        while(start < end){
            int mid = (start + end) / 2;
            if(nums[mid] > nums[end]){
                if(target > nums[mid] || target <= nums[end])
                    start = mid + 1;
                else
                    end = mid;
            }
            else{
                if(target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid;
            }
        }
        if(target != nums[start]) return -1;
        return start;
    }


}
