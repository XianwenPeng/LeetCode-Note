package LeetCode.Topics.SecondRun;
import java.util.*;

public class BFSAndTopologicalSorting {

    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }

    class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }

    class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;
        DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
    }

    public static void main(String[] args) {

    }

    /* 127. Topological Sorting */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode, Integer> inDegree = new HashMap<>();
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        ArrayList<DirectedGraphNode> list = new ArrayList<>();

        for (DirectedGraphNode node: graph) {
            if (!inDegree.containsKey(node)) inDegree.put(node, 0);
            for (DirectedGraphNode neighbor: node.neighbors) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        for (DirectedGraphNode node: inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                DirectedGraphNode node = queue.poll();
                list.add(node);
                for (DirectedGraphNode neighbor: node.neighbors) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return list;
    }

    /* 611. Knight Shortest Path */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid == null || grid.length == 0)  return 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        int[][] dirs = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2},
                {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
        int rLen = grid.length, cLen = grid[0].length, res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                if (cur.x == destination.x && cur.y == destination.y)   return res;
                for (int[] dir: dirs) {
                    int row = cur.x + dir[0];
                    int col = cur.y + dir[1];
                    if (row >= 0 && row < rLen && col >= 0 && col < cLen && !grid[row][col]) {
                        Point next = new Point(row, col);
                        queue.offer(next);
                        grid[row][col] = true;
                    }
                }
            }
            res++;
        }
        return -1;
    }

    /* 433. Number of Islands */
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0)   return 0;
        Queue<Cell> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int rlen = grid.length, clen = grid[0].length, res = 0;
        for (int i = 0; i < rlen; i++) {
            for (int j = 0; j < clen; j++) {
                if (grid[i][j] == true) {
                    queue.offer(new Cell(i, j));
                    while (!queue.isEmpty()) {
                        Cell cell = queue.poll();
                        int row = cell.row, col = cell.col;
                        grid[row][col] = false;
                        for (int[] dir: dirs) {
                            if (row + dir[0] >= 0  && row + dir[0] < rlen && col + dir[1] >= 0 && col + dir[1] < clen && grid[row + dir[0]][col + dir[1]])
                                queue.offer(new Cell(row + dir[0], col + dir[1]));
                        }
                    }
                    res++;
                }
            }
        }
        return res;
    }
    public class Cell {
        int row;
        int col;
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


    /* 120. Word Ladder */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        if (start.equals(end))  return 1;
        Queue<ResultType> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(new ResultType(start, 1));
        int len = start.length(), min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            ResultType cur = queue.poll();
            String curStr = cur.str;
            for (int i = 0; i < len; i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j == curStr.charAt(i))  continue;
                    String str = String.valueOf(curStr.substring(0, i) + j + curStr.substring(i + 1));
                    if (str.equals(end)) return cur.length + 1;
                    if (!dict.contains(str) || set.contains(str))  continue;
                    queue.offer(new ResultType(str, cur.length + 1));
                    set.add(str);
                }
            }
        }
        return min;
    }
    public class ResultType {
        String str;
        int length;
        public ResultType(String str, int length) {
            this.str = str;
            this.length = length;
        }
    }

    /* 137. Clone Graph */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if (node == null)   return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        queue.offer(node);
        map.put(node, new UndirectedGraphNode(node.label));
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            for (UndirectedGraphNode neighbor: cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    queue.offer(neighbor);
                }
            }
        }
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            UndirectedGraphNode newCur = map.get(cur);
            for (UndirectedGraphNode neighbor: cur.neighbors) {
                if (!newCur.neighbors.contains(neighbor))
                    newCur.neighbors.add(map.get(neighbor));
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return map.get(node);
    }

    /* 71. Binary Tree Zigzag Level Order Traversal */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        if (root == null)   return list;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (list.size() % 2 == 1)   subList.add(0, node.val);
                else    subList.add(node.val);
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(subList);
        }
        return list;
    }

    /* 70. Binary Tree Level Order Traversal II */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(0, subList);
        }
        return list;
    }

    /* 7. Serialize and Deserialize Binary Tree */
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sb.append(node.val + ",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                else
                    sb.append("null,");
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        // write your code here
        if (data.length() == 0 || data == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        String[] nodes = data.split(",");
        TreeNode root = nodes[0].equals("null") ? null : new TreeNode(Integer.parseInt(nodes[0]));
        queue.offer(root);
        for (int i = 1; i < nodes.length; i++) {
            TreeNode node = queue.poll();
           if (!nodes[i].equals("null")) {
               node.left = new TreeNode(Integer.parseInt(nodes[i]));
               queue.offer(node.left);
           }
           i++;
           if (!nodes[i].equals("null")) {
               node.right = new TreeNode(Integer.parseInt(nodes[i]));
               queue.offer(node.right);
           }
        }
        return root;
    }

    /* 69. Binary Tree Level Order Traversal */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> list = new LinkedList<>();
        if (root == null)   return list;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null)  queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            list.add(subList);
        }
        return list;
    }
}
