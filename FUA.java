package LeetCode;

import java.util.*;
public class FUA {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        FUA fua = new FUA();

        /* 49. Group Anagrams */
        String[] arr49 = {"chi","nip","lab","mud","fan","yak","kid","lox","joy","rob","cad","hug","ken","oaf",
                "pus","hos","ton","any","sac","mid","nip","ron","tux","set","jug","axe","ago","sob","ode","dot",
                "nit","pug","sue","new","rub","sup","ohs","ski","oaf","don","cob","kin","ark","gay","jay","bur",
                "dot","eat","rca","wad","maj","luz","gad","dam","eon","ark","del","sin","tat"};
        System.out.println(fua.groupAnagramsFollow(arr49));

        /* 261. Graph Valid Tree */
        int[][] arr261 = {{0,1},{0,2},{1,3},{1,4}};
        System.out.println(fua.validTreeDFS(5, arr261));

        /* 490. The Maze */
        int[] start = {0,4};
        int[] dest = {4,4};
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        System.out.println(fua.hasPathBFS(maze, start, dest));
    }

    /* 49. Group Anagrams */
    // O (N * k log k) k 是每一个string的最大长度
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String s = String.valueOf(temp);
            if (!map.containsKey(s))    map.put(s, new LinkedList<>());
            map.get(s).add(str);
        }
        return new LinkedList<>(map.values());
    }

    /* Follow up :
        1. 输出的每个group里string相对位置和输入一样
        用了一个set记录见过的string，输出变为list of lists
        2. what if string length is around 5000, array size < 5
        换了一个线性的hash方法
        3. what if characters choose from 100 million different characters
        */
    public List<List<String>> groupAnagramsFollow(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            String temp = groupAnagramsFollowHash(str);
            if (!map.containsKey(temp)) map.put(temp, new LinkedList<>());
            map.get(temp).add(str);
        }
        return new LinkedList<>(map.values());
    }
    public String groupAnagramsFollowHash(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c: map.keySet()) {
            sb.append(c + "" + map.get(c));
        }
        return sb.toString();
    }

    /* 287. Find the Duplicate Number */
    // 暴力解就是先sort看是否有一样的最简单，如果都是正数 1 to n
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int temp = Math.abs(nums[i]);
            if (nums[temp] > 0) nums[temp] = - nums[temp];
            else return temp;
        }
        return -1;
    }

    // 如果可以有负数，那就用two pointers
    public int findDuplicateTwoPointers(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /* 261. Graph Valid Tree */
    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer, Integer> graph = new HashMap<>();
        for (int[] edge : edges) {
            int son = find(graph, edge[0]);
            int parent = find(graph, edge[1]);
            if (son == parent)  return false;
            graph.put(son, parent);
        }
        return edges.length == n - 1;
    }
    public int find(HashMap<Integer, Integer> graph, int n) {
        if (!graph.containsKey(n))  graph.put(n, n);
        return graph.get(n) != n ? find(graph, graph.get(n)) : n;
    }

    // BFS
    public boolean validTreeBFS(int n, int[][] edges) {
        if (n == 0) return false;
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (visited.contains(v))    return false;
            visited.add(v);
            for (int i : graph.get(v)) {
                queue.offer(i);
                graph.get(i).remove(v);
            }
        }
        return visited.size() == n;
    }

    // DFS
    public boolean validTreeDFS(int n, int[][] edges) {
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new HashSet<>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        if (!validTreeDFSHelper(0, graph, visited))   return false;
        for (boolean v: visited) {
            if (!v) return false;
        }
        return true;
    }
    public boolean validTreeDFSHelper(int n, HashMap<Integer, Set<Integer>> graph, boolean[] visited) {
        if (visited[n]) return false;
        else    visited[n] = true;
        for (int i: graph.get(n)) {
            graph.get(i).remove(n);
            if (validTreeDFSHelper(i, graph, visited) == false)   return false;
        }
        return true;
    }

    /* 281. Zigzag Iterator */
    public class ZigzagIterator {
        Queue<Iterator> queue;
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            queue = new LinkedList<>();
            if (!v1.isEmpty())  queue.offer(v1.iterator());
            if (!v2.isEmpty())  queue.offer(v2.iterator());
        }

        public int next() {
            Iterator<Integer> iterator = queue.poll();
            int next = iterator.next();
            if (iterator.hasNext()) queue.offer(iterator);
            return next;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    /* Clarification for the follow up question - Update (2015-09-18):
    The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
    If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
    For example, given the following input:

    [1,2,3]
    [4,5,6,7]
    [8,9]*/
    public class ZigzagIteratorFollowUp {
        Queue<Iterator> queue;
        public ZigzagIteratorFollowUp(List<List<Integer>> v) {
            queue = new LinkedList<>();
            for (int i = 0; i < v.size(); i++) {
                if (!v.get(i).isEmpty())  queue.offer(v.get(i).iterator());
            }
        }

        public int next() {
            Iterator<Integer> iterator = queue.poll();
            int next = iterator.next();
            if (iterator.hasNext()) queue.offer(iterator);
            return next;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }

    /* 53. Maximum Subarray */
    public int maxSubArray(int[] nums) {
        if (nums.length < 1)   return 0;
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /* 490. The Maze */
    public boolean hasPathDFS(int[][] maze, int[] start, int[] destination) {
        boolean visited[][] = new boolean[maze.length][maze[0].length];
        return hasPathDFSHelper(maze, start, destination, visited);
    }
    public boolean hasPathDFSHelper(int[][] maze, int start[], int[] dest, boolean[][] visited) {
        if (visited[start[0]][start[1]])    return false;
        else if (start[0] == dest[0] && start[1] == dest[1])    return true;
        else {
            visited[start[0]][start[1]] = true;
            return hasPathDFSHelper(maze, left(maze, start[0], start[1]), dest, visited)
                    || hasPathDFSHelper(maze, right(maze, start[0], start[1]), dest, visited)
                    || hasPathDFSHelper(maze, up(maze, start[0], start[1]), dest, visited)
                    || hasPathDFSHelper(maze, down(maze, start[0], start[1]), dest, visited);
        }
    }
    public int[] left(int[][] maze, int row, int col) {
        while (col > 0 && maze[row][col - 1] == 0)  col--;
        return new int[] {row, col};
    }
    public int[] right(int[][] maze, int row, int col) {
        while (col < maze[0].length - 1 && maze[row][col + 1] == 0) col++;
        return new int[] {row, col};
    }
    public int[] up(int[][] maze, int row, int col) {
        while (row > 0 && maze[row - 1][col] == 0)  row --;
        return new int[] {row, col};
    }
    public int[] down(int[][] maze, int row, int col) {
        while (row < maze.length - 1 && maze[row + 1][col] == 0)    row++;
        return new int[] {row, col};
    }

    // BFS
    public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[] x = {0,0,1,-1};
        int[] y = {1,-1,0,0};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == destination[0] && cur[1] == destination[1])  return true;
            visited[cur[0]][cur[1]] = true;
            for (int i = 0; i < 4; i++) {
                int row = cur[0];
                int col = cur[1];
                while (row >= 0 && row <= maze.length - 1 && col >= 0 && col <= maze[0].length - 1
                        && maze[row][col] == 0) {
                    row += x[i];
                    col += y[i];
                }
                row -= x[i];
                col -= y[i];
                if (!visited[row][col]) {
                    int[] next = {row, col};
                    queue.offer(next);
                }
            }
        }
        return false;
    }


    /* 460. LFU Cache */
    class LFUCache {
        class Node {
            int key;
            int val;
            Node next;
            Node prev;
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                next = null;
                prev = null;
            }
        }
        class LRUList {
            Node head, tail;
            int size;
            public LRUList() {
                head = new Node(0,0);
                tail = new Node(0,0);
                head.next = tail;
                tail.prev = tail;
                size = 0;
            }
            public void add(Node node){
                node.prev = head;
                node.next = head.next;
                head.next.prev = node;
                head.next = node;
                size++;
            }
            public void remove(Node node) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                size--;
            }
            public Node removeTail() {
                if (size > 0) {
                    Node node = tail.prev;
                    remove(node);
                    return node;
                }
                else return null;
            }
        }
        HashMap<Integer, LRUList> countMap;
        HashMap<Integer, Node> nodes;
        HashMap<Node, Integer> counts;
        int capacity, used, min;
        public LFUCache(int capacity) {
            countMap = new HashMap<>();
            nodes = new HashMap<>();
            counts = new HashMap<>();
            this.capacity = capacity;
            this.used = 0;
            this.min = 0;
        }

        public int get(int key) {
            if (nodes.containsKey(key)) {
                Node node = nodes.get(key);
                updateCountMap(node);
                counts.put(node, counts.get(node) + 1);
                return node.val;
            }
            else return -1;
        }
        public void put(int key, int value) {
            if (capacity == 0)  return;
            Node node = new Node(key, value);
            if (nodes.containsKey(key)) {
                Node temp = nodes.get(key);
                temp.val = value;
                updateCountMap(temp);
                counts.put(temp, counts.get(temp) + 1);
            }
            else {
                if (used == capacity) {
                    LRUList temp = countMap.get(min);
                    Node remove = temp.removeTail();
                    counts.remove(remove);
                    nodes.remove(remove.key);
                    used--;
                }
                used++;
                nodes.put(key, node);
                counts.put(node, 1);
                if (!countMap.containsKey(1))   countMap.put(1, new LRUList());
                countMap.get(1).add(node);
                min = 1;
            }
        }
        public void updateCountMap(Node node) {
            int count = counts.get(node);
            LRUList temp = countMap.get(count);
            temp.remove(node);
            if (temp.size == 0 && count == min) min++;
            if (temp.size == 0) countMap.remove(count);
            if (!countMap.containsKey(count + 1)) {
                countMap.put(count + 1, new LRUList());
            }
            LRUList next = countMap.get(count + 1);
            next.add(node);
        }
    }

    /* 94. Binary Tree Inorder Traversal */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }
    // Recursive
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorderTraversalRecursiveHelper(root, list);
        return list;
    }
    public void inorderTraversalRecursiveHelper(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        if (root.left != null)  inorderTraversalRecursiveHelper(root.left, list);
        list.add(root.val);
        if (root.right != null) inorderTraversalRecursiveHelper(root.right, list);
    }

    /* 144. Binary Tree Preorder Traversal */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        while (root != null) {
            list.add(root.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            root = root.left;
            if (root == null && !stack.isEmpty()) {
                stack.pop();
            }
        }
        return list;
    }
    // Recursive
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        preorderTraversalRecursiveHelper(root, list);
        return list;
    }
    public void preorderTraversalRecursiveHelper(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        list.add(root.val);
        if (root.left != null)  preorderTraversalRecursiveHelper(root.left, list);
        if (root.right != null)  preorderTraversalRecursiveHelper(root.right, list);
    }

    /* 173. Binary Search Tree Iterator */
    public class BSTIterator {
        Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            TreeNode node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                TreeNode temp = node.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return node.val;
        }
    }

    /* 769. Max Chunks To Make Sorted */
    public int maxChunksToSorted(int[] arr) {
        int count = 0, index = 0, max = arr[0];
        while (index < arr.length) {
            max = Math.max(max, arr[index]);
            if (index++ == max)   count++;
        }
        return count;
    }

    /* 768. Max Chunks To Make Sorted II */
    public int maxChunksToSortedII(int[] arr) {
        int count = 1;
        int[] minOfRight = new int[arr.length];
        int[] maxOfLeft = new int[arr.length];
        minOfRight[arr.length - 1] = arr[arr.length - 1];
        maxOfLeft[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1])  count++;
        }
        return count;
    }

    /* 230. Kth Smallest Element in a BST */
    int num = 0;
    int count = 0;
    public int kthSmallestDFSRecursive(TreeNode root, int k) {
        count = k;
        kthSmallestInorder(root);
        return num;
    }
    public void kthSmallestInorder(TreeNode root) {
        if (root == null)   return;
        kthSmallestInorder(root.left);
        count--;
        if (count == 0){
            num = root.val;
            return;
        }
        kthSmallestInorder(root.right);
    }
    // DFS Iterative
    public int kthSmallestDFSIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            k--;
            if (k == 0) return node.val;
            node = node.right;
        }
        return -1;
    }
    // Binary Search
    public int kthSmallestBinarySearch(TreeNode root, int k) {
        int count = count(root.left);
        if (k < count + 1)     return kthSmallestBinarySearch(root.left, k);
        else if (k > count + 1)     return kthSmallestBinarySearch(root.right, k - count - 1);
        return root.val;
    }
    public int count(TreeNode root) {
        if (root == null)   return 0;
        return 1 + count(root.left) + count(root.right);
    }

    /* 200. Number of Islands */
    public int numIslandsDFSIterative(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)    return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    Stack<int[]> stack = new Stack<>();
                    int[] pos = {i, j};
                    stack.push(pos);
                    while (!stack.isEmpty()) {
                        int[] cell = stack.pop();
                        pushToStack(grid, stack, cell[0] + 1, cell[1]);
                        pushToStack(grid, stack, cell[0] - 1, cell[1]);
                        pushToStack(grid, stack, cell[0], cell[1] + 1);
                        pushToStack(grid, stack, cell[0], cell[1] - 1);
                    }
                    count++;
                }
            }
        }
        return count;
    }
    public void pushToStack(char[][] grid, Stack<int[]> stack, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')  return;
        grid[i][j] = '0';
        int[] temp = {i, j};
        stack.push(temp);
    }
}
