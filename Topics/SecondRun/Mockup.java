package LeetCode.Topics.SecondRun;

import java.util.*;

public class Mockup {
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
