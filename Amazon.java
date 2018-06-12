package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import LeetCode.BinaryTree.TreeNode;

public class Amazon {
    public class TreeNode {
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

    public static void main(String[] args){
        Amazon amazon = new Amazon();
        /* 139. Word Break */
        String s = "ccbb";
        String[] sa = {"bc", "cb"};
        List<String> sList = Arrays.asList(sa);
        System.out.println(amazon.wordBreak(s, sList));

        /* 238. Product of Array Except Self */
        int[] arr = {2,3,4,5};
        System.out.println(Arrays.toString(amazon.productExceptSelf(arr)));

        /* 240. Search a 2D Matrix II */
        int[][] arr240 = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(amazon.searchMatrix2(arr240, 5));

        /* 118. Pascal's Triangle */
        System.out.println(amazon.generate(5));

        /* 451. Sort Characters By Frequency */
        System.out.println(amazon.frequencySort("tree"));
        System.out.println(amazon.frequencySort("eeeee"));

        /* 89. Gray Code */
        System.out.println(amazon.grayCode(3));

        /* 127. Word Ladder */
        String[] arr127 = {"hot","cog","dog","tot","hog","hop","pot","dot"};
        List<String> list127 = Arrays.asList(arr127);
        System.out.println(amazon.ladderLength("hot", "dog", list127));

        /* 682. Baseball Game */
        String[] arr682 = {"5","2","C","D","+"};
        System.out.println(amazon.calPoints(arr682));

        /* 560. Subarray Sum Equals K */
        int[] arr560 = {1,2,3,1,2,-1,4};
        System.out.println(amazon.subarraySum(arr560, 3));

        /* 713. Subarray Product Less Than K */
        int[] arr713 = {1,2,3};
        System.out.println(amazon.numSubarrayProductLessThanK(arr713, 0));

        /* 273. Integer to English Words */
        System.out.println(amazon.numberToWords(123123));

        /* 647. Palindromic Substrings */
        System.out.println(amazon.countSubstrings("aaa"));

        /* Search in BST for target, print the path */
        TreeNode root = amazon.buildTree();
        amazon.searchBSTPath(root, 10);

        /* 338. Counting Bits */
        System.out.println("\n" + Arrays.toString(amazon.countBits(10)));

        /* 152. Maximum Product Subarray */
        int[] arr152 = {0, 2};
        System.out.println(amazon.maxProduct(arr152));
    }

    /* 152. Maximum Product Subarray */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)   return -1;
        int maxpre = nums[0], minpre = nums[0], result = nums[0];
        for (int i = 1; i < nums.length; i++){
            int max = Math.max(Math.max(maxpre * nums[i], minpre * nums[i]), nums[i]);
            int min = Math.min(Math.min(maxpre * nums[i], minpre * nums[i]), nums[i]);
            result = Math.max(result, max);
            maxpre = max;
            minpre = min;
        }
        return result;
    }

    /* 338. Counting Bits */
    public int[] countBits(int num) {
        int[] arr = new int[num + 1];
        for (int i = 1; i < num + 1; i++){
            arr[i] = arr[i >> 1] + (i & 1);
        }
        return arr;
    }

    /* Search in BST for target, print the path */
    public TreeNode buildTree(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        return root;
    }
    public ArrayList<TreeNode> searchBSTPath(TreeNode root, int target){
        ArrayList<TreeNode> list = new ArrayList<>();
        searchBSTPathHelper(root, target, list);
        for (int i = list.size() - 1; i >= 0 ; i --) {
            System.out.print(list.get(i).val + " ");
        }
        return list;
    }
    public boolean searchBSTPathHelper(TreeNode root, int target, ArrayList<TreeNode> list){
        if (root == null)   return false;
        if(root.val == target || searchBSTPathHelper(root.left, target, list)
                || searchBSTPathHelper(root.right, target, list)) {
            list.add(root);
            return true;
        }
        return false;
    }
    
    /* 647. Palindromic Substrings */
    int count = 0;
    public int countSubstrings(String s) {
        if (s.length() == 0 || s == null)   return 0;
        for (int i = 0; i < s.length(); i++){
            countSubstringsHelper(s, i, i);
            countSubstringsHelper(s, i, i + 1);
        }
        return count;
    }
    public void countSubstringsHelper(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
            count++;
        }
    }

    /* 273. Integer to English Words */
    private final String[] DIGITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety", "Hundred"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if(num == 0)    return "Zero";
        int count = 0;
        String words = "";
        while(num > 0){
            if(num % 1000 != 0){
                words = numberToWordsHelper(num % 1000) + THOUSANDS[count] + " " + words;
            }
            num /= 1000;
            count++;
        }
        return words.trim();
    }
    public String numberToWordsHelper(int num){
        if (num < 20)   return DIGITS[num]+" ";
        else if (num < 100) return TENS[num / 10] + " " + numberToWordsHelper(num % 10);
        else    return DIGITS[num / 100] + " Hundred " + numberToWordsHelper(num % 100);
    }

    /* 713. Subarray Product Less Than K */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int product = 1, count = 0;
        int start = 0, end = 0, index = 0;
        while(index < nums.length){
            product *= nums[index];
            while(start <= index && product >= k){
                product /= nums[start];
                start++;
            }
            count += index - start + 1;
            index++;
        }
        return count;
    }

    /* 560. Subarray Sum Equals K */
    public int subarraySum(int[] nums, int k) {
        int sum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int num : nums){
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /* 725. Split Linked List in Parts */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] list = new ListNode[k];
        int count = 0;
        ListNode node = root;
        while(node != null){
            count++;
            node = node.next;
        }
        int left = count%k, remain = count/k;
        node = root;
        int i = 0;
        while(i < k){
            if(node != null){
                list[i] = node;
                int size = left-- > 0 ? remain + 1 : remain;
                System.out.println(size);
                while(--size > 0){
                    node = node.next;
                    if(node == null)    break;
                }
                if(node != null){
                    ListNode temp = node.next;
                    node.next = null;
                    node = temp;
                }
            }
            i++;
        }
        return list;
    }

    /* 449. Serialize and Deserialize BST */
    private final String SEP = ",";
    private final String NULL = "null";
    public String serialize(TreeNode root) {
        if(root == null)    return NULL;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop().val + SEP);
            if(root.right != null)  stack.push(root.right);
            if(root.left != null)   stack.push(root.left);
        }
        return sb.toString();
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("null") || data.equals("")) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        String arr[] = data.split(SEP);
        for(String str : arr){
            queue.offer(new TreeNode(Integer.parseInt(str)));
        }
        return deserializeHelper(queue);
    }
    public TreeNode deserializeHelper(Queue<TreeNode> queue){
        if(queue.isEmpty()) return null;
        TreeNode root = queue.poll();
        Queue<TreeNode> smaller = new LinkedList<TreeNode>();
        while(!queue.isEmpty() && queue.peek().val < root.val){
            smaller.offer(queue.poll());
        }
        root.left = deserializeHelper(smaller);
        root.right = deserializeHelper(queue);
        return root;
    }

    /* 682. Baseball Game */
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for(int i = 0; i < ops.length; i++){
            if(ops[i].equals("C")){
                sum -= stack.pop();
            }
            else if(ops[i].equals("D")){
                stack.push(stack.peek() * 2);
                sum += stack.peek();
            }
            else if(ops[i].equals("+")){
                int prev = stack.pop(), pprev = stack.pop();
                stack.push(pprev);
                stack.push(prev);
                sum += prev + pprev;
                stack.push(prev + pprev);
            }
            else{
                stack.push(Integer.parseInt(ops[i]));
                sum+=Integer.parseInt(ops[i]);
            }
        }
        return sum;
    }

    /* 127. Word Ladder */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        queue.add(null);
        set.add(beginWord);
        int level = 1;
        while(!queue.isEmpty()){
            String str = queue.poll();
            if(str != null){
                for(int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String substr = new String(chars);
                        if (!set.contains(substr) && wordList.contains(substr)) {
                            queue.add(substr);
                            set.add(substr);

                            if(substr.equals(endWord)) {
                                return level + 1;
                            }
                        }
                    }
                }
            }
            else{
                level++;
                if(!queue.isEmpty())
                    queue.add(null);
            }
        }
        return 0;
    }

    /* 89. Gray Code */
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < Math.pow(2, n); i++){
            list.add(i ^ (i >> 1));
        }
        return list;
    }

    /* 451. Sort Characters By Frequency */
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        if(s.length() == 0) return "";
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);

            else
                map.put(ch, 0);
        }
        String[] chs = new String[s.length()+1];
        for(char ch : map.keySet()){
            int index = map.get(ch);
            if(chs[index] == null)  chs[index] = "";
            chs[index] += ch+"";
        }
        StringBuilder ans = new StringBuilder();
        for(int i = chs.length - 1; i >= 0; i--){
            if(chs[i] != null) {
                for(int j = 0; j < chs[i].length(); j++) {
                    for(int k = 1; k <= i+1; k ++){
                        ans.append(chs[i].charAt(j));
                    }
                }
            }
        }
        return ans.toString();
    }

    /* 118. Pascal's Triangle */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            List<Integer> sub = new ArrayList<>();
            for(int j = 0; j < i+1; j++){
                if(j == 0 || j == i)
                    sub.add(1);
                else
                    sub.add(list.get(i-1).get(j-1) + list.get(i-1).get(j));
            }
            list.add(sub);
        }
        return list;
    }

    /* 240. Search a 2D Matrix II */
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++){
            List<Integer> list = Arrays.stream(matrix[i]).boxed().collect(Collectors.toList());
            if(list.contains(target))
                return true;
        }
        return false;
    }
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)   return false;
        int row = 0;
        int col = matrix[0].length-1;
        while(col >= 0 && row < matrix.length){
            if(matrix[row][col] == target)
                return true;
            else if(target > matrix[row][col])
                row++;
            else if(target < matrix[row][col])
                col--;
        }
        return false;
    }

    /* 238. Product of Array Except Self */
    public int[] productExceptSelf(int[] nums) {
        int[] arr = new int[nums.length];
        arr[0] = 1;
        for (int i = 1; i < nums.length; i++){
            arr[i] = arr[i-1] * nums[i-1];
        }
        int mult = 1;
        for(int i = nums.length-1; i >= 0; i--){
            arr[i] *= mult;
            mult *= nums[i];
        }
        return arr;
    }

    /* 139. Word Break */
    public boolean wordBreak(String s, List<String> wordDict){
        boolean[] ans = new boolean[s.length()+1];
        ans[0] = true;
        for(int i = 1; i < s.length()+1; i++){
            for(int j = 0; j < i; j++){
                if(ans[j] && wordDict.contains(s.substring(j, i))){
                    ans[i] = true;
                    break;
                }
            }
        }
        return ans[s.length()];
    }

    /* 199. Binary Tree Right Side View */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)    return list;
        return helper199(list, root, 0);
    }
    public List<Integer> helper199(List<Integer> list, TreeNode root, int level){
        if(root != null && level >= list.size())
            list.add(root.val);
        if(root.right != null)
            list = helper199(list, root.right, level+1);
        if(root.left != null)
            list = helper199(list, root.left, level+1);
        return list;
    }

    /* 236. Lowest Common Ancestor of a Binary Tree */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)    return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        return (left != null) ? left : right;
    }
}
