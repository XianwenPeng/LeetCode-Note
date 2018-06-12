package LeetCode;

import java.util.*;
import java.lang.*;
public class PocketGem {
    public class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { value = x; }
    }

    public static void main(String[] args){
        PocketGem pg = new PocketGem();
        /*  LC 210. Course Schedule II */
        int[][] temp = {};
        System.out.println("LC 210. Course Schedule II: " + Arrays.toString(pg.findOrder(2, temp)));

        /*  LC 305. Number of Islands II */
        int[][] temp1 = {{0,0},{1,1},{0,1}};
        System.out.println("LC 305. Number of Islands II: " + pg.numIslands2(2, 2, temp1));
        int[][] temp2 = {{7,0}};
        System.out.println("LC 305. Number of Islands II: " + pg.numIslands2(8, 2, temp2));

        /*  LC 547. Friend Circles:  */
        int[][] temp3 = {{1,0,0,1}, {0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println("LC 547. Friend Circles: " + pg.findCircleNum(temp3));

        /*  LC 200. Number of Islands:  */
        char[][] temp4 = {{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}};
        System.out.println("LC 200. Number of Islands: " + pg.numIslands(temp4));

        /*  UnionFind Friend Total*/
        String[] temp5 = {"Friend", "Friend", "Total", "Friend", "Total"};
        int[] student1 = {0, 1, 2, 5, 0};
        int[] student2 = {2, 2, 1, 4, 5};
        System.out.println("UnionFind Friend Total: " + Arrays.toString(pg.friendcircle(6, temp5, student1, student2)));

        /* 347. Top K Frequent Elements*/
        int[] temp6 = {4,1,-1,2,-1,2,3};
        System.out.println("LC 347. Top K Frequent Elements: " + pg.topKFrequent(temp6, 2));
    }

    /* 347. Top K Frequent Elements*/
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if(nums.length == 0) return list;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num: nums){
            if(map.containsKey(num)){
                int val = map.get(num);
                map.put(num, ++val);
            }
            else
                map.put(num, 1);
        }
        System.out.println(map);
        list = new ArrayList<>(map.values());
        Collections.sort(list);
        Collections.reverse(list);
        System.out.println(list);
        List<Integer> ans = new ArrayList<>();
        while(k > 0){
            for(int key: map.keySet()){
                if(map.get(key) == list.get(0)) {
                    ans.add(key);
                    map.remove(key);
                    list.remove(0);
                    k--;
                    break;
                }
            }
        }

        return ans;
    }

    /*
    * LeetCode.LeetCode 129. Sum Root to Leaf Numbers
    * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
        An example is the root-to-leaf path 1->2->3 which represents the number 123.
        Find the total sum of all root-to-leaf numbers. */
    public int sumNumbers(TreeNode root) {
        return sum(root, 0);
    }
    public int sum(TreeNode root, int sub){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return sub*10 + root.value;
        return sum(root.left, sub*10 + root.value) + sum(root.right, sub*10 + root.value);
    }

    /*  实现canReach：给出两个点(x1,y1), (x2, y2)，问是否能从前者到达后者；
    移动的规则是(x, y) -> (x+y, x) or (x, x+y); x，y均为正数，所以dfs／recursive solution非常好写。 */
    public boolean canReach(int x1, int y1, int x2, int y2){
        if(x1 > x2 && y1 > y2)  return false;
        if(x1 == x2 && y1 == y2)    return true;
        return canReach(x1 + y1, x1, x2, y2) || canReach(x1, x1 + y1, x2,y2);
    }

    /*  UnionFind题目，参考题目就是LC的Union Find tag下的题目。题目大意是给定n个学生，和一系列Friend和Total操作：
     hackerrank中给的输入是，(int n, String[] queryType, int[] students1, int[]students2)，n是指总共有n个学生，
     queryType中要么是"Friend"要么是“Total”，“Friend"操作内容是把学生 i 和学生 j 连接起来，
     对应union find里的union操作，"Total"是内容计算出学生 i 和学生 j 总共的朋友圈个数， students1里是q个学生（
     可以理解为操作中的 i 学生）students2里是q个学生（可以理解为操作中的 j 学生）。要求的返回值居然是个int[]，
     这个数组里存着每一次"Total"操作的结果。*/
    public int[] friendcircle(int n, String[] queryType, int[] students1, int[] students2) {
        List<Integer> list = new ArrayList<>();
        int id[] = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        for (int i = 0; i < queryType.length; i++) {
            if(queryType[i].equals("Friend"))
                friendUnion(id, students1[i], students2[i]);
            else if(queryType[i].equals("Total"))
                list.add(friendSize(id, students1[i], students2[i]));
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    public int friendSize(int[] id, int stu1, int stu2){
        int index1 = friendFind(id, stu1);
        int index2 = friendFind(id, stu2);
        int count = 0;
//        System.out.println(Arrays.toString(id));
        for (int i = 0; i < id.length; i++) {
            if(friendFind(id, i) == index1 || friendFind(id, i) == index2){
                count++;
            }
        }
        return count;
    }
    public void friendUnion(int[] id, int stu1, int stu2){
        int index1 = friendFind(id, stu1);
        int index2 = friendFind(id, stu2);
        if(index1 != index2)
            id[index1] = index2;
    }
    public int friendFind(int[] id, int index){
        while(index != id[index]){
            id[index] = id[id[index]];
            index = id[index];
        }
        return index;
    }

    /*  LeetCode.LeetCode 200. Number of Islands */
    int[][] dir200 = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public int numIslands(char[][] grid) {
        int count = 0;
        int row = grid.length;
        if(row == 0)    return count;
        int col = grid[0].length;
        int id[] = new int[grid.length * grid[0].length];
        Arrays.fill(id, -1);
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++) {
                if(grid[i][j] == '1') {
                    int index = col * i + j;
                    id[index] = index;
                    count++;
                    for (int[] dir : dir200) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        int temp = x * col + y;
                        if (x < 0 || x >= row || y < 0 || y >= col || id[temp] == -1) continue;
                        int root = root200(temp, id);
                        if(root != index){
                            id[index] = root;
                            index = root;
                            count--;
                        }
                    }
                }
            }
        }
        return count;
    }
    public int root200(int index, int[] id){
        while(index != id[index]){
            id[index] = id[id[index]];
            index = id[index];
        }
        return index;
    }

    /*  LeetCode.LeetCode 547. Friend Circles*/
    int[][] dirs547 = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
    public int findCircleNum(int[][] M) {
        int count = M.length;
        int id[] = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            id[i] = i;
        }
        for (int i = 0; i < M.length; i++) {
            int lastJ = -1;
            for (int j = i + 1; j < M[0].length; j++) {
                if(M[i][j] == 1) {
                    if(findRoot(j, id) != findRoot(i, id))
                        count--;
                    while(findRoot(j, id) != findRoot(i, id)) {
                        int root = findRoot(j, id);
                        id[j] = findRoot(i, id);
                        j = root;
                    }
                }
            }
        }
        return count;
    }
    public int findRoot(int index, int[] id){
        while(index != -1 && index != id[index]){
            id[index] = id[id[index]];
            index = id[index];
        }
        return index;
    }

    /*  305. Number of Islands II */
    int[][] dirs305 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<>();
        int[] id = new int[m*n];
        Arrays.fill(id, -1);
        int count = 0;
        for(int[] posit: positions){
            int index = posit[0] * n + posit[1];
            id[index] = index;
            count++;
            for(int[] dir: dirs305){
                int x = posit[0] + dir[0];
                int y = posit[1] + dir[1];
                int tempIndex = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || id[tempIndex] == -1)    continue;
                int newRoot = root(tempIndex, id);
                if(newRoot != index) {
                    id[index] = newRoot;
                    index = newRoot;
                    count--;
                }
            }
            list.add(count);
        }
        return list;
    }
    public int root(int i, int[] id){
        while(i != -1 && i != id[i]){
//            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    /*  Binary search tree minimum sum from root to leaf
    *   跟BST没啥关系，不要看到BST就以为是最左边的路径之和（左边路径可以很长，右边路径可以很短），用递归做很简单 */
    public int pathSum(TreeNode root){
        if(root == null) return 0;
        if(root.left != null && root.right == null)
            return pathSum(root.left) + root.value;
        if(root.left == null && root.right != null)
            return pathSum(root.right) + root.value;
        return Math.min(pathSum(root.left), pathSum(root.right)) + root.value;
    }

    /*   题目是binary tree找和最长的path，并输出path */                 //  IN PROGRESS
    public int pathMaxSum(TreeNode root){
        if(root == null) return 0;
        if(root.left != null && root.right == null)
            return pathSum(root.left) + root.value;
        if(root.left == null && root.right != null)
            return pathSum(root.right) + root.value;
        return Math.max(pathSum(root.left), pathSum(root.right)) + root.value;
    }

    /*  把related的string归到一个group里面，算一算总共多少group，这个是面经的？
        related就是一个string是另一个string的substring就可以
        一个group要包含尽可能多的element
        比如[“o","r","ring","string","ringo"] 就是有3，因为["r","ring","string"],["r","ring","ringo"],["o","ringo"]
        比如["r","rin","ring","string"]就是1，因为["r","rin","ring","string"]就是一个group */
    public int stringGroup(String[] strs){
        return 0;
    }

    /*  LeetCode.LeetCode 124. Binary Tree Maximum Path Sum
    * For this problem, a path is defined as any sequence of nodes
    * from some starting node to any node in the tree along the parent-child connections.
    * The path must contain at least one node and does not need to go through the root.*/
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root){
        sum(root);
        return max;
    }
    public int sum(TreeNode root){
        if(root == null) return 0;
        int left = Math.max(0, sum(root.left)) + root.value;
        int right = Math.max(0, sum(root.right)) + root.value;
        max = Math.max(max, left + right + root.value);
        return Math.max(sum(root.left), sum(root.right)) + root.value;
    }

    /*  LeetCode.LeetCode 210. Course Schedule II - BFSn*/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int order[] = new int[numCourses], pre[] = new int[numCourses], index = 0;
        // Count how many prerequisites.
        for(int i = 0; i < prerequisites.length; i++){
            pre[prerequisites[i][0]] ++;
        }
        //  Add no requisite courses to order.
        Queue queue = new ArrayDeque();
        for(int i = 0; i < numCourses; i++){
            if(pre[i] == 0){
                queue.offer(i);
                order[index++] = i;
            }
        }
        //  How many do not need prerequisites
        while(!queue.isEmpty()){
            int temp = (int)queue.poll();
            for(int i = 0; i < prerequisites.length; i++){
                if(prerequisites[i][1] == temp){
                    pre[prerequisites[i][0]]--;
                    if(pre[prerequisites[i][0]] == 0){
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        return (index == numCourses) ? order : new int[0];
    }
}
