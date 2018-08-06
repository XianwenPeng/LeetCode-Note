package LeetCode.Topics.SecondRun;
import java.util.*;

public class JiuZhangFinal {
    public class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        JiuZhangFinal jz = new JiuZhangFinal();

        int[] start = {1,2,3};
        int[] end = {2,3,4};
        System.out.println(jz.isCyclicGraph(start, end));


        System.out.println(jz.tupleMultiply("(1,2,3),(4,5,6),(7,8,9)", 2));


        char[] ch ={'c','b','a','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String[] str ={"cab","cba","abc"};
        System.out.println(Arrays.toString(jz.wordSort(ch, str)));

        String[] s1 = {"a","b","d","e","f"};
        String[] s2 = {"b","c","e","g","g"};
        System.out.println(jz.maximumAssociationSet(s1, s2));
    }

    /* 805. Maximum Association Set */
    public List<String> maximumAssociationSet(String[] ListA, String[] ListB) {
        // Write your code here
        List<String> res = new LinkedList<>();
        if (ListA == null || ListB == null || ListA.length == 0 || ListB.length == 0)   return res;
        HashMap<String, Integer> nameId = new HashMap<>();
        HashMap<Integer, String> idMap = new HashMap<>();
        int n = 0;
        for (int i = 0; i < ListA.length; i++) {
            if (!nameId.containsKey(ListA[i])) {
                nameId.put(ListA[i], n);
                idMap.put(n, ListA[i]);
                n++;
            }
            if (!nameId.containsKey(ListB[i])) {
                nameId.put(ListB[i], n);
                idMap.put(n, ListB[i]);
                n++;
            }
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int i = 0; i < ListA.length; i++) {
            int parentA = find(parent, nameId.get(ListA[i]));
            int parentB = find(parent, nameId.get(ListB[i]));
            if (parentA != parentB) {
                parent[parentA] = parentB;
            }
        }
        int[] sum = new int[n];
        int max = 0, resId = -1;
        for (int i = 0; i < n; i++) {
            parent[i] = find(parent, i);
            sum[parent[i]]++;
            if (sum[parent[i]] > max) {
                max = sum[parent[i]];
                resId = parent[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (parent[i] == resId) res.add(idMap.get(i));
        }
        return res;
    }
    public int find(int[] ids, int id) {
        return ids[id] == id ? id : find(ids, ids[id]);
    }

    /* 819. Word Sorting */
    public String[] wordSort(char[] alphabet, String[] words) {
        // Write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            map.put(Character.valueOf(alphabet[i]), i);
        }
        Arrays.sort(words, (s1, s2) -> {
            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (map.get(c1) == map.get(c2)) continue;
                return map.get(c1) - map.get(c2);
            }
            return 0;
        });
        return words;
    }

    /* 820. Rectangle */
    public class PointResult {
        public int x;
        public int y;
        public PointResult(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)  return true;
            if (!(o instanceof PointResult))  return false;
            PointResult p = (PointResult)o;
            if (p.x != this.x)  return false;
            return p.y == this.y;
        }

        @Override
        public int hashCode() {
            long temp;
            int result;
            temp = x >>> 32;
            result = (int)(temp ^ (temp >>> 32));
            temp = y >>> 32;
            result += result ^ (int)(temp ^ (temp >>> 32));
            return result;
        }
    }
    public String rectangle(Point[] pointSet) {
        // Write your code here
        Set<PointResult> set = new HashSet<>();
        int size = pointSet.length;
        for (int i = 0; i < size; i++) {
            set.add(new PointResult(pointSet[i].x, pointSet[i].y));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || pointSet[i].x == pointSet[i].y
                        || pointSet[j].x == pointSet[j].y) continue;
                PointResult x = new PointResult(pointSet[i].x, pointSet[j].y);
                PointResult y = new PointResult(pointSet[i].y, pointSet[j].x);
                if (set.contains(x) && set.contains(y)) return "YES";
            }
        }
        return "NO";
    }
    private boolean searchInSet(Point[] pointSet, Point point) {
        for (Point p: pointSet) {
            if (p.x == point.x && p.y == point.y)   return true;
        }
        return false;
    }

    /* 933. Tuple Multiply */
    public long tupleMultiply(String tuple, int n) {
        // Write your code here
        long res = 1;
        String[] strs = tuple.split("\\(");
        if (strs.length == 0 || tuple == null)  return 0;
        List<String> tuples = new LinkedList<>();
        for (String str: strs) {
            String[] temp = str.split("\\)");
            if (temp[0].length() != 0)  tuples.add(temp[0]);
        }
        System.out.println(tuples);
        for (String str: tuples) {
            String[] nums = str.split(",");
            if (n > nums.length)    return res;
////            for (int i = 0 ; i < nums.length; i ++) {
//                System.out.println(Long.parseLong(nums[n - 1] ));
                res *= Long.parseLong(nums[n - 1]);
//            }
        }
        return res;
    }

    /* 1366. Directed Graph Loop */
    public boolean isCyclicGraph(int[] start, int[] end) {
        // Write your code here
        if (start == null || end == null || start.length == 0 || end.length == 0)
            return false;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        HashMap<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < start.length; i++) {
            if (!map.containsKey(start[i])) map.put(start[i], new LinkedList<>());
            if (!map.containsKey(end[i])) map.put(end[i], new LinkedList<>());
            if (!indegree.containsKey(start[i])) indegree.put(start[i], 0);
            map.get(start[i]).add(end[i]);
            indegree.put(end[i], indegree.getOrDefault(end[i], 0) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int node: indegree.keySet()) {
            if (indegree.get(node) == 0)    queue.offer(node);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next: map.get(node)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0)
                    queue.offer(next);
            }
        }
        for (int node: indegree.keySet()) {
            if (indegree.get(node) > 0)    return true;
        }
        return false;
    }

}
