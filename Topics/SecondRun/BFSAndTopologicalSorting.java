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
        BFSAndTopologicalSorting bfs = new BFSAndTopologicalSorting();

        int[][] grid573 = {{0,1,0,0,0},{1,0,0,2,1},{0,1,0,0,0}};

        System.out.println(bfs.shortestDistance(grid573));
    }

    /* 573. Build Post Office II */
    // Optimization
    public int shortestDistanceOptimization(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0)   return 0;
        int rLen = grid.length, cLen = grid[0].length, min = Integer.MAX_VALUE;
        Set<PointPost> houses = new HashSet<>();
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 1)    houses.add(new PointPost(i, j, houses.size()));
            }
        }
        int[][][] distance = new int[houses.size()][rLen][cLen];
        for (int i = 0; i < houses.size(); i++) {
            for (int j = 0; j < rLen; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }

        for (PointPost house: houses) {
            getDistance(distance, grid, house);
        }

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                int subsum = 0;
                boolean valid = true;
                for (int k = 0; k < houses.size(); k++) {
                    if (distance[k][i][j] == Integer.MAX_VALUE) {
                        valid = false;
                        break;
                    }
                    subsum += distance[k][i][j];
                }
                if (valid && subsum != 0)    min = Math.min(min, subsum);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    public void getDistance(int[][][] distance, int[][] grid, PointPost cell) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<PointPost> queue = new LinkedList<>();
        queue.offer(cell);
        int len = 0, rLen = grid.length, cLen = grid[0].length;
        boolean[][] visited = new boolean[rLen][cLen];
        visited[cell.row][cell.col] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            len++;
            for (int i = 0; i < size; i++) {
                PointPost cur = queue.poll();
                for (int[] dir : dirs) {
                    int row = cur.row + dir[0];
                    int col = cur.col + dir[1];
                    if (row >= 0 && row < rLen && col >= 0 && col < cLen
                            && !visited[row][col]) {
                        if (grid[row][col] == 0) {
                            distance[cell.id][row][col] = len;
                            queue.offer(new PointPost(row, col, -1));
                            visited[row][col] = true;
                        }
                        else if (grid[row][col] == 2)
                            distance[cell.id][row][col] = Integer.MAX_VALUE;
                    }
                }
            }
        }
    }
    public class PointPost {
        int row;
        int col;
        int id;
        public PointPost(int row, int col, int id) {
            this.row = row;
            this.col = col;
            this.id = id;
        }
    }

    // Brutal Force: Passed 77% Memory Limit Exceeded
    public int shortestDistance(int[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0)   return 0;
        int rLen = grid.length, cLen = grid[0].length, min = Integer.MAX_VALUE, houses = 0;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 1)    houses++;
            }
        }
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 0) {
                    min = Math.min(min, bfs(grid, i, j, rLen, cLen, houses));
                }
            }
        }
        return min;
    }
    public int bfs(int[][] grid, int i, int j, int rLen, int cLen, int houses) {
        Queue<PointPostOffice> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(new PointPostOffice(i, j, 0));
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int sumLen = 0, visitedHouses = 0;
        while (!queue.isEmpty()) {
            PointPostOffice cur = queue.poll();
            set.add(cur.row * cLen + cur.col);
            for (int[] dir : dirs) {
                int row = cur.row + dir[0];
                int col = cur.col + dir[1];
                int len = cur.len + 1;
                int hash = row * cLen + col;
                if (row >= 0 && row < rLen && col >= 0 && col < cLen
                        && grid[row][col] != 2 && !set.contains(hash)) {
                    if (grid[row][col] == 1) {
                        sumLen += len;
                        visitedHouses++;
                    } else if (grid[row][col] == 0) {
                        queue.offer(new PointPostOffice(row, col, len));
                    }
                    set.add(hash);
                }
            }
        }
        return visitedHouses == houses ? sumLen : Integer.MAX_VALUE;
    }
    public class PointPostOffice {
        int row;
        int col;
        int len;
        public PointPostOffice(int row, int col, int len) {
            this.row = row;
            this.col = col;
            this.len = len;
        }
    }

    /* 598. Zombie in Matrix */
    public int zombie(int[][] grid) {
        // write your code here
        if (grid == null || grid[0].length == 0) return 0;
        int rLen = grid.length, cLen = grid[0].length;
        int countPeople = 0, countTurned = 0, lastTurned = -1, days = 0;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 0)
                    countPeople++;
            }
        }
        Queue<Point> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }
        while (!queue.isEmpty()) {
            if (countTurned == countPeople) return days;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                for (int[] dir : dirs) {
                    int row = cur.x + dir[0];
                    int col = cur.y + dir[1];
                    if (row >= 0 && row < rLen && col >= 0 && col < cLen && grid[row][col] == 0) {
                        grid[row][col] = 1;
                        queue.offer(new Point(row, col));
                        countTurned++;
                    }
                }
            }
            days++;
        }
        return countTurned == countPeople ? days : -1;
    }

    /* 605. Sequence Reconstruction */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < seqs.length; i++) {
            if (seqs[i].length == 1) {
                graph.put(seqs[i][0], new HashSet<>());
                inDegree.put(seqs[i][0], 0);
                continue;
            }
            for (int j = 0; j < seqs[i].length - 1; j++) {
                if (!graph.containsKey(seqs[i][j])) {
                    graph.put(seqs[i][j], new HashSet<>());
                    inDegree.put(seqs[i][j], 0);
                }
                if (!graph.containsKey(seqs[i][j + 1])) {
                    graph.put(seqs[i][j + 1], new HashSet<>());
                    inDegree.put(seqs[i][j + 1], 0);
                }
                if (graph.get(seqs[i][j]).add(seqs[i][j + 1]))
                    inDegree.put(seqs[i][j + 1], inDegree.get(seqs[i][j + 1]) + 1);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int node: inDegree.keySet()) {
            if (inDegree.get(node) == 0)    queue.offer(node);
        }
        int visitCount = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 1)    return false;
            int node = queue.poll();
            if (node != org[visitCount])    return false;
            visitCount++;
            for (int next: graph.get(node)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0)    queue.offer(next);
            }
        }
        return visitCount == org.length && visitCount == graph.size();
    }

    /* 323. Number of Connected Components in an Undirected Graph */
    public int countComponents(int n, int[][] edges) {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Set<Integer>> adjs = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) adjs.put(i, new HashSet<>());
        for (int[] edge: edges) {
            adjs.get(edge[0]).add(edge[1]);
            adjs.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            queue.offer(i);
            if (!visited.contains(i)) count++;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    int cur = queue.poll();
                    if (visited.contains(cur))  continue;
                    visited.add(cur);
                    for (int next: adjs.get(cur)) {
                        queue.offer(next);
                        adjs.get(next).remove(cur);
                    }
                }
            }
        }
        return count;
    }

    /* 431. Connected Component in Undirected Graph */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // write your code here
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        List<List<Integer>> list = new LinkedList<>();
        for (UndirectedGraphNode node: nodes) {
            List<Integer> subList = new LinkedList<>();
            queue.offer(node);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    UndirectedGraphNode cur = queue.poll();
                    if (visited.contains(cur))  continue;
                    visited.add(cur);
                    subList.add(cur.label);
                    for (UndirectedGraphNode next: cur.neighbors) {
                        queue.offer(next);
                    }
                }
            }
            Collections.sort(subList);
            if (subList.size() != 0)    list.add(subList);
        }
        return list;
    }

    /* 618. Search Graph Nodes */
    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {
        // write your code here
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            if (visited.contains(cur))  continue;
            visited.add(cur);
            if (values.get(cur) == target)   return cur;
            for (UndirectedGraphNode next: cur.neighbors) {
                queue.offer(next);
            }
        }
        return null;
    }

    /* 892. Alien Dictionary */
    public String alienOrder(String[] words) {
        // Write your code here
        HashMap<Character, Set<Character>> map = constructMap(words);
        HashMap<Character, Integer> inDegree = getInDegree(map);
        Queue<Character> queue = new PriorityQueue<>();
        for (Character ch: inDegree.keySet()) {
            if (inDegree.get(ch) == 0)  queue.offer(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character ch = queue.poll();
            sb.append(ch);
            for (Character next: map.get(ch)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0)    queue.offer(next);
            }
        }
        if (sb.length() != inDegree.size()) return "";
        return sb.toString();
    }
    public HashMap<Character, Integer> getInDegree(HashMap<Character, Set<Character>> map) {
        HashMap<Character, Integer> inDegree = new HashMap<>();
        for (Character ch: map.keySet()) {
            inDegree.put(ch, 0);
        }
        for (Character ch: map.keySet()) {
            for (Character next: map.get(ch)) {
                inDegree.put(next, inDegree.get(next) + 1);
            }
        }
        return inDegree;
    }
    public HashMap<Character, Set<Character>> constructMap(String[] words) {
        HashMap<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!map.containsKey(c))
                    map.put(c, new HashSet<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            int index = 0;
            while (index < words[i].length() && index < words[i + 1].length()) {
                if (words[i].charAt(index) != words[i + 1].charAt(index)) {
                    map.get(words[i].charAt(index)).add(words[i + 1].charAt(index));
                    break;
                }
                index++;
            }
        }
        return map;
    }

    /* 616. Course Schedule II */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
        HashMap<Integer, List<Integer>> preMap = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < numCourses; i ++) {
            preMap.put(i, new LinkedList<>());
            inDegree.put(i, 0);
        }
        for (int[] pre: prerequisites) {
            preMap.get(pre[1]).add(pre[0]);
            inDegree.put(pre[0], inDegree.get(pre[0]) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree.get(i) == 0)   queue.offer(i);
        }
        int[] res = new int[numCourses];
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                for (int next: preMap.get(course)) {
                    inDegree.put(next, inDegree.get(next) - 1);
                    if (inDegree.get(next) == 0)    queue.offer(next);
                }
                res[steps] = course;
                if (++steps == numCourses)  return res;
            }
        }
        return new int[]{};
    }

    /* 615. Course Schedule */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        if (prerequisites.length == 0)  return true;
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        HashMap<Integer, LinkedList<Integer>> preMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new LinkedList<>());
            inDegree.put(i, 0);
        }
        for (int[] prerequisite: prerequisites) {
            inDegree.put(prerequisite[1], inDegree.getOrDefault(prerequisite[1], 0) + 1);
            preMap.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int course: inDegree.keySet()) {
            if (inDegree.get(course) == 0) queue.offer(course);
        }
        int visitCount = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            visitCount++;
            for (int next: preMap.get(course)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0)    queue.offer(next);
            }
        }
        return visitCount == numCourses;
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

    /* 178. Graph Valid Tree */
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++)     map.put(i, new HashSet<>());
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        int visitCount = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited.contains(node)) return false;
            visited.add(node);
            for (int next: map.get(node)) {
                queue.offer(next);
                map.get(next).remove(node);
            }
        }
        return visited.size() == n;
    }

    public boolean validTreeUnionFind(int n, int[][] edges) {
        // write your code here
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        for (int[] edge: edges) {
            int x = find(nums, edge[0]);
            int y = find(nums, edge[1]);
            if (x == y) return false;
            nums[x] = y;
        }
        return edges.length == n - 1;
    }
    public int find(int[] nums, int i) {
        if (nums[i] == -1)  return i;
        return nums[i] = find(nums, nums[i]);
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
