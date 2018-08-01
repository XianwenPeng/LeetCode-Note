package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class PureStorage {
    public static void main(String[] args) {
        PureStorage ps = new PureStorage();
        System.out.println(ps.countWays(26) +"\n");

        System.out.println(ps.countWaysDfs(26));
    }

    /* 应用题：ｆｏｏｔｂａｌｌ比赛有多种得分方式，ｔｏｕｃｈｄｏｗｎ　６分，之后加踢罚球　１分，再ｔｏｕｃｈｄｏｗｎ　３分，
　　　　　给一个比分，问有几种得分方式，　还有算法复杂度 */
    public int countWays(int points) {
        if (points < 0)     return 0;
        if (points == 0)    return 1;
        return countWays(points - 6) + countWays(points - 7) + countWays(points - 10);
    }

    public List<List<Integer>> countWaysDfs(int points) {
        List<List<Integer>> list = new LinkedList<>();
        dFSHelper(points, list, new LinkedList<>());
        return list;
    }
    public void dFSHelper(int points, List<List<Integer>> list, List<Integer> subList) {
        if (points < 0)     return;
        if (points == 0)    {
            list.add(new LinkedList<>(subList));
        }
        if (subList.size() > 0 && subList.get(subList.size() - 1) == 6) {
            subList.add(1);
            dFSHelper(points - 1, list, subList);
            subList.remove(subList.size() - 1);
        }
        if (subList.size() > 0 && subList.get(subList.size() - 1) == 1) {
            subList.add(3);
            dFSHelper(points - 3, list, subList);
            subList.remove(subList.size() - 1);
        }
        subList.add(6);
        dFSHelper(points - 6, list, subList);
        subList.remove(subList.size() - 1);
    }

    /* 202. Happy Number */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int temp = 0;
            while (n > 0) {
                temp += (n % 10) * (n % 10);
                n /= 10;
            }
            n = temp;
            if (set.contains(n))    return false;
            set.add(n);
        }
        return true;
    }

    /* Q: Implement set with integers in range {1…N}, implements five operations, add, remove, contains, clear, iterate.
     There are 3 versions to solve this 0, 1, 2
        v1: using only bucket array with size N+1, the operation costs are: O(1),O(1), O(1), O(N), O(N) for example:
            if we add, 2, 0, 1, it will become list:[1, 1, 1]
        v2: using only sequential array(store them sequentially in array): O(1),O(count),O(count),O(1), O(count)
            for example: if we add, 2, 0, 1, it will become map: [2, 0, 1] you need to keep an count
        v3: achieve best of v1 and v2’s performance: O(1),O(1) O(1), O(1), O(count)*/
    // add  remove  contains    clear   iterate
    public class SetPS {
        int[] indexes;
        int[] nums;
        int size;

        public SetPS(int size) {
            this.size = size;
            this.indexes = new int[size];
            this.nums = new int[size];
        }

        public void add(int val) {
            indexes[val - 1] = size;
            nums[size] = val;
            size++;
        }

        public int remove(int val) {
            int index = indexes[val - 1];
            if (nums[index] != val || index >= size)  return -1;
            int res = nums[index];
            nums[index] = nums[size - 1];
            size--;
            return  res;
        }

        public boolean contains(int val) {
            int index = indexes[val - 1];
            return index >= size ? false : nums[index] == val;
        }

        public void clear() {
            size = 0;
        }

        public void iterate() {
            for (int i = 0; i < size; i++) {
                System.out.println(nums[i]);
            }
        }
    }
    // LinkedList + Array
    public class SetPSLA {
        private class Node {
            int num;
            Node next;
            public Node(int num, Node next) {
                this.num = num;
                this.next = next;
            }
        }
        Node head, tail;
        Node[] prevs;
        int size;

        public SetPSLA(int size) {
            this.size = size;
            head = new Node(-1, null);
            tail = head;
            Node[] prevs = new Node[size];
        }

        public void add(int val) {
            tail.next = new Node(val, null);
            prevs[val - 1] = tail;
            tail = tail.next;
            size++;
        }

        public int remove(int val) {
            Node prev = prevs[val - 1];
            if (prev.next.num != val)   return -1;
            int res = prev.next.num;
            if (prev.next.next != null)
                prevs[prev.next.next.num] = prev;
            prev.next = prev.next.next;
            size--;
            return res;
        }

//        public boolean contains(int val) {
//            int index = indexes[val - 1];
//            return index >= size ? false : nums[index] == val;
//        }
//
//        public void clear() {
//            size = 0;
//        }
//
//        public void iterate() {
//            for (int i = 0; i < size; i++) {
//                System.out.println(nums[i]);
//            }
//        }
    }


    /* Follow up, input n points to count how many square is can form */
    public int countSquare(int[][] grid) {
        int n = grid.length, m = grid[0].length, count = 0;
        HashSet<Point> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)    continue;
                set.add(new Point(i, j));
            }
        }
        Point[] points = (Point[]) set.toArray();
        int size = set.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) continue;
                if (points[i].x - points[j].x != points[i].y - points[j].y) continue;
                Point newA = new Point(points[j].x, points[i].y);
                Point newB = new Point(points[i].x, points[j].y);
                if (set.contains(newA) && set.contains(newB))   count++;
            }
        }
        return count;
    }
    private class Point {
        double x;
        double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)  return true;
            if (!(o instanceof Point))  return false;
            Point point = (Point) o;
            if (point.x != this.x)  return false;
            return point.y == this.y;
        }

        @Override
        public int hashCode() {
            int result = 0;
            long temp = 0;
            temp = Double.doubleToLongBits(x);
            result += (int)(temp ^ temp >>> 32);
            temp = Double.doubleToLongBits(y);
            result += 31 ^ result + (int)(temp ^ temp >>> 32);
            return result;
        }
    }
    // grid, 1 for there is a point
    public int countSquareGridN3(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        HashMap<Integer, Set<Integer>> pointsOnRow = new HashMap<>();
        HashMap<Integer, Set<Integer>> pointsOnCol = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pointsOnRow.put(i, new HashSet<>());
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)    continue;
                pointsOnRow.get(i).add(j);
                if (!pointsOnCol.containsKey(j))    pointsOnRow.put(j, new HashSet<>());
                pointsOnRow.get(j).add(i);
            }
        }
        int count = 0;
        for (int row: pointsOnRow.keySet()) {
            Set<Integer> cols = pointsOnRow.get(row);
            for (int col: cols) {
                for (int row2: pointsOnCol.get(col)) {
                    if (row2 == row)    continue;
                    Set<Integer> rows = pointsOnRow.get(row2);
                    if (cols.contains(col + (row2 - row)) && rows.contains(col + (row2 - row))) count++;
                }
            }
        }
        return count;
    }
    // O(n ^ 4);
    public int countSquareGridN4(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0)    continue;
                for (int k = i + 1; k < n; k++) {
                    for (int l = j + 1; l < m; l++) {
                        if (grid[k][l] == 0)    continue;
                        if (grid[k][j] == 1 && grid[i][l] == 1 && k - i == l - j) count++;
                    }
                }
            }
        }
        return count;
    }

    /* 593. Valid Square */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double d1 = distanceBetween(p1, p2);
        if (d1 == 0 || d1 != distanceBetween(p3, p4)) {
            return false;
        }
        double d2 = distanceBetween(p1, p3);
        if (d2 == 0 || d2 != distanceBetween(p2, p4)) {
            return false;
        }
        double d3 = distanceBetween(p1, p4);
        if (d3 == 0 || d3 != distanceBetween(p2, p3)) {
            return false;
        }
        if (d1 == d2 || d2 == d3 || d1 == d3) {
            return true;
        }
        return false;
    }
    private double distanceBetween(int[] p1, int[] p2) {
        int num = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
        return Math.sqrt(num);
    }
}
