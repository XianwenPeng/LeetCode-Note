package LeetCode;

import java.util.*;
public class PracticesForJune25 {
    class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    };
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

    public static void main(String[] args) {
        PracticesForJune25 aoa = new PracticesForJune25();

        System.out.println("Most Frequently Used WordsS");
        String test1_1 = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food";
        List<String> test1_2 = new LinkedList<>(Arrays.asList("Jack", "Jill", "and", "he", "the", "to", "is"));
        System.out.println(aoa.mostFrequentlyUsed(test1_1, test1_2));

        String test2_1 = null;
        List<String> test2_2 = new LinkedList<>();
        System.out.println(aoa.mostFrequentlyUsed(test2_1, test2_2));

        System.out.println("\nLogFiles Reorder");
        int test3_1 = 6;
        List<String> test3_2 = new LinkedList<>(Arrays.asList("a1 9 2 3 1", "g1 Act car", "a7 act zoo", "zo4 4 7", "ab1 off KEY dog", "a8 act zoo"));
        System.out.println(aoa.logOrderLines(test3_1, test3_2));
        int test4_1 = 0;
        List<String> test4_2 = new LinkedList<>(Arrays.asList("a1 9 2 3 1", "g1 Act car", "a7 act zoo", "zo4 4 7", "ab1 off KEY dog", "a8 act zoo"));
        System.out.println(aoa.logOrderLines(test4_1, test4_2));

        PriorityQueue pq = new PriorityQueue<String>((o1, o2) -> {
            return o2.compareTo(o1);
        });
        for (int i = 0; i < 10; i++) {
            pq.add(i+"");
//            if (pq.size() > 5)  pq.poll();
        }
        pq.remove("3");
        System.out.println(pq);
        while (!pq.isEmpty())   System.out.println(pq.poll());

        // 108 Follow up
        TreeNode root = aoa.testBuildTree();
        System.out.println(Arrays.toString(aoa.BSTToSortedArray(root)));
        System.out.println(aoa.findKthLargestInList(root, 2));

//        String s = "abc";
//        StringBuilder sb = new StringBuilder(s);
//        System.out.println(sb.reverse().toString());
    }

    /* 109. Convert Sorted List to Binary Search Tree */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)   return null;
        if (head.next == null)  return new TreeNode(head.val);
        ListNode pre = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre.next != null)   pre.next = null;
        TreeNode root = new TreeNode(head.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }

    /* 108 Follow up, BST to Sorted Array */
    public TreeNode testBuildTree() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-3);
        root.left.left = new TreeNode(-10);
        root.right = new TreeNode(9);
        root.right.left = new TreeNode(5);
        return root;
    }

    public int[] BSTToSortedArray(TreeNode root) {
       List<Integer> list = new LinkedList<>();
       BSTInorderList(root, list);
       int[] res = new int[list.size()];
       for (int i = 0; i < list.size(); i++) {
           res[i] = list.get(i);
       }
       return res;
    }
    public int findKthLargestInList(TreeNode root, int k) {
        int[] arr = BSTToSortedArray(root);
        return findKthLargestInListHelper(arr, k, 0, arr.length - 1);
    }
    public int findKthLargestInListHelper(int[] arr, int k, int left, int right) {
        int pivot = arr[right], i = left, low = left;
        while (i < right) {
            if (arr[i] <= pivot) swap(arr, low++, i);
            i++;
        }
        swap(arr, low, right);
        if (arr.length - low == k)  return arr[low];
        else if (arr.length - low < k)  return findKthLargestInListHelper(arr, k, left, low - 1);
        else    return findKthLargestInListHelper(arr, k, low + 1, right);
    }
    public void BSTInorderList(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        BSTInorderList(root.left, list);
        list.add(root.val);
        BSTInorderList(root.right, list);
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
//    public ListNode BSTToSortedArrayHelper(TreeNode root, HashSet<TreeNode> set) {
//        if (root == null)   return null;
//        TreeNode temp = root, pre = null;
//        while (temp != null) {
//
//            if (!set.contains(temp) && temp.left != null)   temp = temp.left;
//            else    temp = temp.right;
//        }
//        set.add(temp);
//        ListNode head = new ListNode(temp.val);
//        head.next = BSTToSortedArrayHelper(root, set);
//        return head;
//    }


    /* 108. Convert Sorted Array to Binary Search Tree */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        return sortedArrayToBSTHelper(root, nums, 0, nums.length - 1);
    }
    public TreeNode sortedArrayToBSTHelper(TreeNode root, int[] nums, int left, int right) {
        if (left > right || left < 0 || right >= nums.length)  return null;
        int mid = left + (right - left) / 2;
        root = new TreeNode(nums[mid]);

        TreeNode lNode = sortedArrayToBSTHelper(root.left, nums, left, mid - 1);
        TreeNode rNode = sortedArrayToBSTHelper(root.right, nums, mid + 1, right);

        root.left = lNode;
        root.right = rNode;
        return root;
    }


    /* 138. Copy List with Random Pointer */
    public RandomListNode copyRandomListII(RandomListNode head) {
        if (head == null)   return null;
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode temp = new RandomListNode(cur.label);
            temp.next = cur.next;
            cur.next = temp;
            cur = cur.next.next;
        }

        cur = head;
        RandomListNode dummy = new RandomListNode(-1);
        dummy.next = cur.next;
        while (cur != null) {
            RandomListNode temp = cur.next;
            if (cur.random != null) {
                temp.random = cur.random.next;
            }
            cur = temp.next;
        }
        cur = head;
        while (cur != null) {
            RandomListNode temp = cur.next;
            cur.next = temp.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /* 33. Search in Rotated Sorted Array */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;
            if (nums[mid] > nums[right]) {
                if (target < nums[mid] && target >= nums[left])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }


    /* 138. Copy List with Random Pointer*/
    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
    public RandomListNode copyRandomListNoHashMap(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode node = head, start = dummy;
        while (node != null) {
            start.next = new RandomListNode(node.label);
            if (node.random != null) {
                start.next.random = new RandomListNode(node.random.label);
            }
            node = node.next;
            start = start.next;
        }
        return dummy.next;
    }

    /* 212. Word Search II */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> list = new LinkedList<>();
        if (board == null || board.length == 0 || words == null || words.length == 0)   return list;
        TrieNode root = new TrieNode();
        Set<String> resSet = new HashSet<>();

        for (String s: words) {
            root.insert(root, s);
        }
        int row = board.length, col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                findWordsDFS(board, root, i, j, row, col, resSet);
            }
        }
        list = new LinkedList<>(resSet);
        return list;
    }
    public void findWordsDFS(char[][] board, TrieNode root, int i, int j, int row, int col,
                             Set<String> resSet) {
        if (board[i][j] == '-' || root.nodes[board[i][j] - 'a'] == null) return;
        char tempBoard = board[i][j];
        board[i][j] = '-';
        root = root.nodes[tempBoard - 'a'];
        if (root.wordEnd)   resSet.add(root.word);

        if (i + 1 < row)    findWordsDFS(board, root, i + 1, j, row, col, resSet);
        if (i - 1 >= 0)    findWordsDFS(board, root, i - 1, j, row, col, resSet);
        if (j + 1 < col)    findWordsDFS(board, root, i, j + 1, row, col, resSet);
        if (j - 1 >= 0)    findWordsDFS(board, root, i, j - 1, row, col, resSet);

        board[i][j] = tempBoard;
    }
    class TrieNode {
        TrieNode[] nodes = new TrieNode[26];
        boolean wordEnd = false;
        String word = "";
        public void insert(TrieNode root, String str) {
            char[] chs = str.toLowerCase().toCharArray();
            TrieNode node = root;
            for (char c: chs) {
                if (node.nodes[c - 'a'] == null)    node.nodes[c - 'a'] = new TrieNode();
                node = node.nodes[c - 'a'];
            }
            node.wordEnd = true;
            node.word = str;
        }
    }

    /* 677. Map Sum Pairs */
    class MapSum {
        class TrieNode {
            TrieNode[] nodes = new TrieNode[26];
            int value = 0;
        }
        /** Initialize your data structure here. */
        TrieNode root;
        Set<String> set;
        public MapSum() {
            root = new TrieNode();
            set = new HashSet<>();
        }

        public void insert(String key, int val) {
            char[] chs = key.toLowerCase().toCharArray();
            TrieNode node = root;
            for (char c: chs) {
                if (node.nodes[c - 'a'] == null)
                    node.nodes[c - 'a'] = new TrieNode();
                node = node.nodes[c - 'a'];
                if (!set.contains(key))  node.value += val;
                else    node.value = val;
            }
            set.add(key);
        }

        public int sum(String prefix) {
            char[] chs = prefix.toLowerCase().toCharArray();
            TrieNode node = root;
            int count = 0;
            for (char c: chs) {
                node = node.nodes[c - 'a'];
                if (node == null)   return 0;
                count = node.value;
            }
            return count;
        }
    }

    /* 745. Prefix and Suffix Search */
    class WordFilter {

        class TrieNode{
            TrieNode[] nodes = new TrieNode[26];
            int weight = -1;
            String word = "";

            public void insert(TrieNode node, String str, int weight) {
                char[] chs = str.toLowerCase().toCharArray();
                TrieNode cur = node;
                for (char c: chs) {
                    if (cur.nodes[c - 'a'] == null)     cur.nodes[c - 'a'] = new TrieNode();
                    cur = cur.nodes[c - 'a'];
                }
                cur.word = str;
                cur.weight = weight;
            }
        }

        TrieNode root;
        public WordFilter(String[] words) {
            root = new TrieNode();
            for (int i = 0; i < words.length; i++) {
                root.insert(root, words[i], i);
            }
        }

        public int f(String prefix, String suffix) {
            char[] chs = prefix.toLowerCase().toCharArray();
            TrieNode node = root;
            for (char c: chs) {
                if (node.nodes[c - 'a'] == null)    return -1;
                node = node.nodes[c - 'a'];
            }
            return fHelperDFS(node, -1, suffix);
        }

        public int fHelperDFS(TrieNode node, int maxWeight, String suffix) {
            if (node == null)   return -1;
            if (node.weight >= 0 && node.word.endsWith(suffix)) return node.weight;
            for (TrieNode cur: node.nodes) {
                int curWeight = fHelperDFS(cur, maxWeight, suffix);
                maxWeight = Math.max(maxWeight, curWeight);
            }
            return maxWeight;
        }
    }

    /* 720. Longest Word in Dictionary */
    public String longestWord(String[] words) {
        TrieTempNode root = new TrieTempNode();
        root.str = "-";
        for (String s: words) {
            root.insert(root, s);
        }
        return longestWordDFS(root, "");
    }
    public String longestWordDFS(TrieTempNode node, String resWord) {
        if (node == null || node.str.length() == 0) return "";
        if (!node.str.equals("-"))  resWord = node.str;
        for (TrieTempNode curNode: node.nodes) {
            String curRes = longestWordDFS(curNode, resWord);
            if (curRes.length() > resWord.length() || (curRes.length() == resWord.length() && curRes.compareTo(resWord) < 0))
                resWord = curRes;
        }
        return resWord;
    }
    class TrieTempNode {
        TrieTempNode[] nodes = new TrieTempNode[26];
        String str = "";

        public void insert(TrieTempNode node, String str) {
            char[] chr = str.toLowerCase().toCharArray();
            TrieTempNode root = node;
            for (char c : chr) {
                if (root.nodes[c - 'a'] == null)    root.nodes[c - 'a'] = new TrieTempNode();
                root = root.nodes[c - 'a'];
            }
            root.str = str;
        }
    }

    /* 208. Implement Trie (Prefix Tree) */
    class Trie {
        class TrieNode {
            TrieNode[] nodes;
            boolean wordEnd;
            public TrieNode() {
                nodes = new TrieNode[26];
                wordEnd = false;
            }
        }
        TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] chs = word.toLowerCase().toCharArray();
            TrieNode node = root;
            for (char c: chs) {
                if (node.nodes[c - 'a'] == null)  node.nodes[c - 'a'] = new TrieNode();
                node = node.nodes[c - 'a'];
            }
            node.wordEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] chs = word.toLowerCase().toCharArray();
            TrieNode node = root;
            for (char c: chs) {
                if (node.nodes[c - 'a'] == null)    return false;
                node = node.nodes[c - 'a'];
            }
            return node.wordEnd;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] chs = prefix.toLowerCase().toCharArray();
            TrieNode node = root;
            for (char c: chs) {
                if (node.nodes[c - 'a'] == null)    return false;
                node = node.nodes[c - 'a'];
            }
            return true;
        }
    }


    // 23280666870106
    public List<String> mostFrequentlyUsed(String str, List<String> excludeWords) {
        List<String> list = new LinkedList<>();
        if (str == null || str.length() == 0)   return list;
        List<String> dic = new LinkedList<>();
        int max = 0;
        HashMap<String, Integer> map = new HashMap<>();

        String[] strs = str.split("[^A-Za-z]");
        for (String s: excludeWords)    dic.add(s.toLowerCase());

        for (String s: strs) {
            s = s.toLowerCase();
            if (!dic.contains(s)) {
                map.put(s, map.getOrDefault(s, 0) + 1);
                max = Math.max(max, map.get(s));
            }
        }
        for (String s: map.keySet()) {
            if (max == map.get(s)) {
                list.add(s);
            }
        }
        return list;
    }

    public List<String> logOrderLines(int logSize, List<String> logLines) {
        List<String> listStrs = new LinkedList<>();
        List<String> listNums = new LinkedList<>();
        if (logLines == null || logSize == 0 || logSize != logLines.size() || logLines.size() == 0) return listStrs;
        for (String s: logLines) {
            String line = s.trim().substring(s.indexOf(" ") + 1).trim();
            if (Character.isDigit(line.charAt(0)))
                listNums.add(s);
            else    listStrs.add(s);
        }

        Collections.sort(listStrs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1;
                String s2 = o2;
                String id1 = s1.substring(0, s1.trim().indexOf(" "));
                String line1 = s1.substring(s1.trim().indexOf(" ") + 1);
                String id2 = s2.substring(0, s2.trim().indexOf(" "));
                String line2 = s2.substring(s2.trim().indexOf(" ") + 1);

                if (line1.equals(line2))    return compareTwoStr(id1, id2);

                else
                    return compareTwoStr(line1, line2);
            }

            public int compareTwoStr(String s1, String s2) {
                for (int i = 0; i < s1.length() && i < s2.length(); i++) {
                    if (s1.charAt(i) == s2.charAt(i))   continue;
                    else {
                        return s1.charAt(i) - s2.charAt(i);
                    }
                }
                if (s1.length() < s2.length())  return s1.charAt(s2.length());
                else if (s1.length() > s2.length())    return s1.charAt(s2.length());
                return 0;
            }
        });
        listStrs.addAll(listNums);
        return listStrs;
    }
}
