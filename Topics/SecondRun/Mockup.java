package LeetCode.Topics.SecondRun;

import java.util.*;

public class Mockup {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
    public static void main(String[] args) {
        Mockup mu = new Mockup();
        /* 8.11-01 */
        String[] nums = {"5", "3", "7", "1", "4", "6", "9"};
        TreeNode root = mu.buildTree(nums);
        mu.printTreeByLevel(root);
        System.out.println("\n8.11-01 给一颗balanced binary search tree, 找2sum的 pair");
        System.out.println(Arrays.toString(mu.findTwoSumInTree(root, 4)));
        System.out.println(mu.findAllTwoSumPairsInTree(root, 8));

        System.out.println("\n8.11-03 R5 另外一个 hiring manager不知道是不是bar raiser, behavior + 给一颗二叉树，逆时针打印所有边缘Node");
        System.out.println(mu.boundaryOfBinaryTree(root));

        System.out.println("\n8.11-02 2. BQ + 实现一个findKnearest Uber driver的method。这一轮数据结构问的很细。我用priorityQ做的。（这一轮也秒了）");
        List<UberDriver> drivers = mu.buildDrivers("0 0 1 1 0 2 1 0");
        for (UberDriver ub: mu.findNearestUberDriver(drivers, 3, 0, 0)) {
            System.out.print("{"+ub.x +", " + ub.y +"} ");
        }

        System.out.println("\n8.18-03 第三轮 BQ + 找图中两点 是否联通，union find 秒");
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}, {5, 6}};
        System.out.println(mu.connectedInGraph(edges, 1, 5));

        List<String> list = Arrays.asList("MyJavaClass", "MyJava", "MClass", "classMy", "class");
        String test = "Mav";
        System.out.println(test +" " + mu.searchResults(list, test));
    }

    // 9/23 FLAG Mock up Google
    public List<String> searchResults(List<String> list, String pattern) {
        List<String> res = new LinkedList<>(list);
        HashMap<String, Integer> map = new HashMap<>();
        for (String str: res) map.put(str, 0);
        for (int i = 0; i < pattern.length(); i++) {
            List<String> temp = new LinkedList<>(res);
            res.clear();
            for (int j = 0; j < temp.size(); j++) {
                int index = isSubsequent(temp.get(j), map.get(temp.get(j)), pattern.charAt(i));
                if (index >= 0) {
                    map.put(temp.get(j), index + 1);
                    res.add(temp.get(j));
                }
            }
            System.out.println(pattern.charAt(i) + " "+res);
        }
        return res;
    }
    public int isSubsequent(String str, int indexS, char pattern) {
        if (str.indexOf(pattern) == -1) return -1;
        for (int i = indexS; i < str.length(); i++) {
            if (str.charAt(i) == pattern) {
                return i;
            }
        }
        return -1;
    }

    /* 8.20-01 801. Minimum Swaps To Make Sequences Increasing */
    public int minSwap(int[] A, int[] B) {
        int[] dp = {0, 1};  // 0 not swap, 1 swap
        for (int i = 1; i < A.length; i++) {
            int[] cur = new int[2];
            if (A[i] > A[i - 1] && A[i] > B[i - 1] && B[i] > B[i - 1] && B[i] > A[i - 1]) {
                cur[0] = Math.min(dp[0], dp[1]);
                cur[1] = Math.min(dp[0], dp[1]) + 1;
            }
            else if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                cur[0] = dp[0];
                cur[1] = dp[1] + 1;
            }
            else {
                cur[0] = dp[1];
                cur[1] = dp[0] + 1;
            }
            dp = cur;
        }
        return Math.min(dp[0], dp[1]);
    }

    /* 8.19-04 白人小哥+白人大叔，小哥主问，大叔感觉是负责写评价，全程除了最后any question for us环节没有说话。
    BQ是讲述一个你对自己的想法和思路和自信，但最终输掉了和同事的争论的事例。coding是leetcode 117，我一开始给了BFS解法，
    follow up是用constant空间复杂度解，我用了iterative方法； */
    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode head = new TreeLinkNode(-1), end = head;
            while (root != null) {
                if (root.left != null) {
                    end.next = root.left;
                    end = root.left;
                }
                if (root.right != null) {
                    end.next = root.right;
                    end = root.right;
                }
                root = root.next;
            }
            root = head.next;
        }
    }

    /* 8.18-03 第三轮 BQ + 找图中两点 是否联通，union find 秒 */
    public boolean connectedInGraph(int[][] edges, int node1, int node2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge: edges) {
            int son = find(map, edge[0]);
            int parent = find(map, edge[1]);
            map.put(son, parent);
        }
        return find(map, node1) == find(map, node2);
    }
    public int find(Map<Integer, Integer> map, int node) {
        if (!map.containsKey(node)) map.put(node, node);
        return map.get(node) == node ? node : find(map, map.get(node));
    }


    /* 8.11-02 2. BQ + 实现一个findKnearest Uber driver的method。这一轮数据结构问的很细。我用priorityQ做的。（这一轮也秒了）*/
    public class UberDriver {
        int x;
        int y;
        public UberDriver(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public List<UberDriver> findNearestUberDriver(List<UberDriver> drivers, int k, int x, int y) {
        Queue<UberDriver> pq = new PriorityQueue<>((d1, d2) -> {
            return distanceBetween(d1.x, x, d1.y, y) - distanceBetween(d2.x, x, d2.y, y) <= 0 ? 1 : -1;
        });
        for (UberDriver driver: drivers) {
            if (pq.size() < k) {
                pq.offer(driver);
            }
            else {
                if (distanceBetween(driver.x, x, driver.y, y) < distanceBetween(pq.peek().x, x, pq.peek().y, y)) {
                    pq.poll();
                    pq.offer(driver);
                }
            }
        }
        return new LinkedList<>(pq);
    }
    private double distanceBetween(int x1, int x2, int y1, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    private List<UberDriver> buildDrivers(String str) {
        List<UberDriver> res = new LinkedList<>();
        String[] pos = str.split(" ");
        for (int i = 0; i < pos.length; i+=2) {
            res.add(new UberDriver(Integer.parseInt(pos[i]), Integer.parseInt(pos[i + 1])));
        }
        return res;
    }

    /* 8.12-01 4. 里抠而亦柳 + BQ 216. Combination Sum III */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(k, n, 1, res, new LinkedList<>());
        return res;
    }
    public void dfs(int k, int n, int start, List<List<Integer>> res, List<Integer> list) {
        if (n < 0 || k < 0) return;
        if (k == 0 && n == 0) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = start; i <= 9; i++) {
            list.add(i);
            dfs(k - 1, n - i, i + 1, res, list);
            list.remove(list.size() - 1);
        }
    }

    /* 8.11-05 2. 李寇易思玲 + BQ. From 1point 3acres bbs */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        return dfs(s, new HashSet<>(wordDict), map);
    }
    private List<String> dfs(String s, Set<String> dict, Map<String, List<String>> map) {
        if (map.containsKey(s)) return map.get(s);
        List<String> res = new LinkedList<>();
        if (dict.contains(s)) {
            res.add(s);
        }
        for (int i = 0; i < s.length(); i++) {
            String pre = s.substring(0, i + 1);
            String post = s.substring(i + 1);
            if (!dict.contains(pre))    continue;
            List<String> temp = dfs(post, dict, map);
            for (String str: temp) {
                res.add(pre + " " + str);
            }
        }
        map.put(s, res);
        return res;
    }

    /* 8.11-04 1. 利口留其舞 + BQ 675. Cut Off Trees for Golf Event BFS */
    public int cutOffTree(List<List<Integer>> forest) {
        Queue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
            return a1[2] - a2[2];
        });
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1)
                    pq.offer(new int[] {i, j, forest.get(i).get(j)});
            }
        }
        int[] start = new int[2];
        int sumStep = 0;
        while (!pq.isEmpty()) {
            int[] target = pq.poll();
            int step = stepToLowestTree(forest, start, target);
            if (step == -1) return -1;
            sumStep += step;
            start[0] = target[0];
            start[1] = target[1];
            // System.out.println(step);
        }
        return sumStep;
    }
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int stepToLowestTree(List<List<Integer>> forest, int[] start, int[] target) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == target[0] && cur[1] == target[1]) return step;
                for (int j = 0; j < dirs.length; j++) {
                    int row = cur[0] + dirs[j][0];
                    int col = cur[1] + dirs[j][1];
                    if (row < 0 || row >= forest.size() || col < 0 || col >= forest.get(row).size()
                            || forest.get(row).get(col) == 0 || visited[row][col])  continue;
                    queue.offer(new int[] {row, col});
                    visited[row][col] = true;
                }
            }
            step++;
        }
        return -1;
    }

    /* 8.11-03 R5 另外一个 hiring manager不知道是不是bar raiser, behavior + 给一颗二叉树，逆时针打印所有边缘Node. 乐扣上原题。 */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        // write your code here
        List<Integer> res = new LinkedList<>();
        if (root == null)   return res;
        res.add(root.val);
        printLeft(root.left, res);
        printBottom(root.left, res);
        printBottom(root.right, res);
        printRight(root.right, res);
        return res;
    }
    private void printLeft(TreeNode root, List<Integer> res) {
        if (root == null || root.left == null && root.right == null)    return;
        res.add(root.val);
        if (root.left == null)  printLeft(root.right, res);
        else    printLeft(root.left, res);
    }
    private void printRight(TreeNode root, List<Integer> res) {
        if (root == null || root.left == null && root.right == null)    return;
        if (root.right == null)  printRight(root.left, res);
        else    printRight(root.right, res);
        res.add(root.val);
    }
    private void printBottom(TreeNode root, List<Integer> res) {
        if (root == null)    return;
        if (root.left == null && root.right == null)    res.add(root.val);
        printBottom(root.left, res);
        printBottom(root.right, res);
    }

    /* 8.11-02 R3 天竺小哥， 以前的project + LRU cache, 他一直理解不来put是怎么回事，他觉得只有get不到才put。
    语气也很差，最后代码写完有点Bug,但改过来了。*/
    public class LRUCache {
        private class Node {
            int key;
            int val;
            Node prev, next;
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        HashMap<Integer, Node> map;
        Node head, tail;
        int capacity;
        public LRUCache(int capacity) {
            map = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key))  return -1;
            Node node = map.get(key);
            int res = node.val;
            removeNode(node);
            addToHead(node);
            return res;
        }

        public void put(int key, int value) {
            if (capacity == 0)  return;
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = value;
                removeNode(node);
                addToHead(node);
                return;
            }
            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
        }

        public void addToHead(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
        public void removeNode(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }

    /* LFU Cache */
    class LFUCache {
        private class Node {
            int key;
            int val;
            int count;
            Node prev, next;
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                count = 0;
            }
        }
        private class NodeList {
            Node head, tail;
            int size;
            public NodeList() {
                head = new Node(-1, -1);
                tail = new Node(-1, -1);
                head.next = tail;
                size = 0;
            }
            public void removeNode(Node node) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                size--;
            }
            public void addToHead(Node node) {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
                size++;
            }
        }
        Map<Integer, NodeList> countToList;
        Map<Integer, Node> keyToNode;
        int minCount, capacity;
        public LFUCache(int capacity) {
            countToList = new HashMap<>();
            keyToNode = new HashMap<>();
            minCount = -1;
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!keyToNode.containsKey(key))    return -1;
            Node node = keyToNode.get(key);
            int res = node.val;
            removeNode(node);
            node.count++;
            addNode(node);
            return res;
        }

        public void put(int key, int value) {
            if (capacity == 0)  return;
            if (keyToNode.containsKey(key)) {
                Node node = keyToNode.get(key);
                removeNode(node);
                node.count++;
                node.val = value;
                addNode(node);
                return;
            }
            Node node = new Node(key, value);
            node.count = 0;
            if (keyToNode.size() == capacity) {
                removeNode(countToList.get(minCount).tail.prev);
            }
            minCount = node.count;
            addNode(node);
        }
        public void addNode(Node node) {
            if (!countToList.containsKey(node.count))
                countToList.put(node.count, new NodeList());
            countToList.get(node.count).addToHead(node);
            keyToNode.put(node.key, node);
        }
        public void removeNode(Node node) {
            countToList.get(node.count).removeNode(node);
            if (countToList.get(node.count).size == 0) {
                countToList.remove(node.count);
                minCount += minCount == node.count ? 1 : 0;
            }
            keyToNode.remove(node.key);
        }
    }


    /* Follow up: Output all 2 sum pairs
        8.11-01 给一颗balanced binary search tree, 找2sum的 pair
     */
    public class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public List<List<Integer>> findAllTwoSumPairsInTree(TreeNode root, int target) {
        if (root == null)   return null;
        List<List<Integer>> res = new LinkedList<>();
        findAllTwoSumPairsInTreeHelper(root, target, new HashSet<>(), res);
        return res;
    }
    public void findAllTwoSumPairsInTreeHelper(TreeNode root, int target, Set<Integer> set, List<List<Integer>> res) {
        if (root == null)   return;
        if (set.contains(target - root.val)) {
            List<Integer> temp = new LinkedList<>();
            temp.add(root.val);
            temp.add(target - root.val);
            res.add(temp);
        }
        set.add(root.val);
        findAllTwoSumPairsInTreeHelper(root.left, target, set, res);
        findAllTwoSumPairsInTreeHelper(root.right, target, set, res);
    }

    /* Follow up: Output the 2 sum pair */
    public int[] findTwoSumInTree(TreeNode root, int target) {
        if (root == null)   return null;
        findPrintTargetHelper(root, target, new HashSet<>());
        return found ? new int[] {left, right} : null;
    }
    int left = 0, right = 0;
    boolean found = false;
    private void findPrintTargetHelper(TreeNode root, int k, Set<Integer> set) {
        if (root == null)   return;
        if (set.contains(k - root.val)) {
            found = true;
            left = k - root.val;
            right = root.val;
            return;
        }
        set.add(root.val);
        findPrintTargetHelper(root.left, k, set);
        findPrintTargetHelper(root.right, k, set);
    }

    /* No Extra memory used */
    public boolean findTargetDFS(TreeNode root, int k) {
        return dfs(root, root, k);
    }
    public boolean dfs(TreeNode root, TreeNode firstNode, int k) {
        if (root == null)   return false;
        return  search(root, root, k - root.val) || dfs(root.left, root, k) || dfs(root.right, root, k);
    }
    public boolean search(TreeNode root, TreeNode cur, int k) {
        if (root == null)   return false;
        if (root.val == k && root != cur)   return true;
        return root.val > k && search(root.left, cur, k) || root.val < k && search(root.right, cur, k);
    }

    /* LeetCode 653. Two Sum IV - Input is a BST */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)   return false;
        return findTargetHelper(root, k, new HashSet<>());
    }
    private boolean findTargetHelper(TreeNode root, int k, Set<Integer> set) {
        if (root == null)   return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return k - root.val < root.val && findTargetHelper(root.left, k, set)
                || k - root.val > root.val && findTargetHelper(root.right, k, set);
    }

    /* build tree for testing */
    public TreeNode buildTree(String[] nums) {
        return buildTreeHelper(nums, 0);
    }
    private TreeNode buildTreeHelper(String[] nums, int index) {
        if (index >= nums.length || nums[index].equals("null")) return null;
        if (index == nums.length - 1) {
            return nums[index].equals("null") ? null : new TreeNode(Integer.parseInt(nums[index]));
        }
        TreeNode node = nums[index].equals("null") ? null : new TreeNode(Integer.parseInt(nums[index]));
        node.left = buildTreeHelper(nums, index * 2 + 1);
        node.right = buildTreeHelper(nums, index * 2 + 2);
        return node;
    }

    public void printTreeByLevel(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                String val = node == null ? "null" : node.val+"";
                sb.append(val+ " ");
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            System.out.println(sb.toString());
        }
    }
}
