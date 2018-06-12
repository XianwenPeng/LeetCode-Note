package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class Google {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static void main (String[] args){
        Google google = new Google();
        /* 4. Median of Two Sorted Arrays */
        int[] arr4_1 = {1,3}, arr4_2 = {2};
        System.out.println(google.findMedianSortedArrays(arr4_1, arr4_2));
        /* 10. Regular Expression Matching */
        System.out.println(google.isMatch2("aa", ".*"));
        /* 388. Longest Absolute File Path */
        String str388 = "\"dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext\"\n";
        System.out.println(google.lengthLongestPath(str388));
        /* 681. Next Closest Time */
        System.out.println(google.nextClosestTime("19:34"));
        /* 298. Binary Tree Longest Consecutive Sequence */
//        System.out.println(google.longestConsecutive();
        /* 418. Sentence Screen Fitting */
        String[] arr418 = {"I", "had", "apple", "pie"};
        System.out.println(google.wordsTyping(arr418, 4, 5));
        /* 394. Decode String */
        System.out.println(google.decodeString("3[a]2[b4[F]c]"));
        /* 361. Bomb Enemy */
        char[][] grid394 = {{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
        System.out.println(google.maxKilledEnemies(grid394));

        /* 215. Kth Largest Element in an Array */
        int[] array = {24,5,45,20,56,75,2,56,99,53,12};
//        google.quickSort(array, 0, 10);
        System.out.println(Arrays.toString(array));
        System.out.println(google.findKthLargest(array, 5));

        /* 340. Longest Substring with At Most K Distinct Characters */
        System.out.println(google.lengthOfLongestSubstringKDistinct("eceeegse", 2));

        /* 128. Longest Consecutive Sequence */
        int[] arr128 = {100,101,1,2,3,4};
        System.out.println(google.longestConsecutive(arr128));

        /* 163. Missing Ranges */
        int[] arr163 = {0, 1, 3, 50, 75};
        System.out.println(google.findMissingRanges(arr163,1,100));

        /* 20. Valid Parentheses */
        System.out.println(google.isValid(""));

        /* 22. Generate Parentheses */
        System.out.println(google.generateParenthesis(3));

        /* 159. Longest Substring with At Most Two Distinct Characters */
        System.out.println(google.lengthOfLongestSubstringTwoDistinct("ecebaaa"));

        /* 259. 3Sum Smaller */
        int[] arr259 = {-2, 0, 1, 3};
        System.out.println(google.threeSumSmaller(arr259, 2));

        /* 挖空岛，仅保留岛边缘题 */
        int[][] arrIsland = {{0,0,0,1,1,1},{0,0,0,1,1,1},{1,1,1,1,1,1}, {1,1,1,1,1,1},{1,1,1,1,1,1}};
        arrIsland = google.removeInsideOfIsland(arrIsland);
        for (int[] i : arrIsland)   System.out.println(Arrays.toString(i));

        /* 15. 3Sum */
        int[] arr15 = {2, -1, -1};
        System.out.println(google.threeSum(arr15));
        System.out.println(google.threeSumTF(arr15));

        /* Road Chunk */
        int[] arrRoad = {2,4,1,3};
        System.out.println(google.roadChunk(arrRoad));
        System.out.println(google.insertRoadChunk(arrRoad));

        /* printClockWise */
        int[][] arrClockWise = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}, {16,17,18,19,20},{21,22,23,24,25}};
        for (int[] i : arrClockWise)    System.out.println(Arrays.toString(i));
        System.out.println(google.printClockWise(arrClockWise, 2, 2));

        /* nthChar */
        System.out.println(google.nthChar("abc", 9));

        /* findTopWindows */
        char[][] matrixWindows = {{'a','a','a','a','a',' ',' ',' ','c','c','c','c','c','c'},
                                  {'a',' ',' ',' ','a',' ',' ',' ','c','c',' ',' ',' ','c'},
                                  {'a',' ',' ',' ','a','b','b','b','c','c','c','c','c','c'},
                                  {'a','a','a','a','a',' ',' ',' ','b',' ',' ',' ',' ',' '},
                                  {' ',' ',' ','b',' ',' ',' ',' ','b',' ',' ',' ',' ',' '},
                                  {' ',' ',' ','b',' ',' ',' ',' ','b',' ',' ',' ',' ',' '},
                                  {' ',' ',' ','b','b','b','b','b','b',' ',' ',' ',' ',' '}};
        System.out.println(google.findTopWindows(matrixWindows));

        /* additionalChar */
        System.out.println(google.additionalChar("abcdefg", "abcdeffg"));

        /* minimumRectangleSize */
        int[][] arrRectangle = {{1,0,1,1,1,0,1},
                                {1,1,0,1,0,0,1},
                                {0,1,0,0,1,0,1}};
        System.out.println(google.minimumRectangleSize(arrRectangle));

        /* 46. Permutations - Backtracking*/
        int[] arr46 = {1,2,3};
        System.out.println(google.permute(arr46));

        /* 207. Course Schedule */
        int[][] arr207 = {{2,0}, {2,1}};
        System.out.println(google.canFinishDFS(3, arr207));

        /* Topological sorting problem - 210. Course Schedule II */
        System.out.println(Arrays.toString(google.findOrderBFS(3, arr207)));

        /* 332. Reconstruct Itinerary */
        String[][] arr332 = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        System.out.println(google.findItinerary( arr332));

        /* 422. Valid Word Square */
        String[] temp422 = {"ball","area","read","lady"};
        List<String> list422 = new ArrayList<>(Arrays.asList(temp422));
        System.out.println(google.validWordSquare(list422));

        /* 425. Word Squares */
        String[] arr425 = {"abaa","aaab","baaa","aaba"};
        System.out.println(google.wordSquares(arr425));

        /* 345. Reverse Vowels of a String - Two Pointers */
        System.out.println(google.reverseVowels("hello"));

        /* tagString */
        List<String> listTag = new ArrayList<>();
        listTag.add("ab");   listTag.add("ca");
        System.out.println(google.tagString("abcab", listTag));

        /* compareDeletedString */
        System.out.println(google.compareDeletedString("abcd//efg", "abcefg", '/'));
    }

    /* 737. Sentence Similarity II */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, String> parents = new HashMap<>();

        for (int i = 0; i < pairs.length; i++) {
            String son = findAreSentencesSimilarTwo(parents, pairs[i][0]);
            String parent = findAreSentencesSimilarTwo(parents, pairs[i][1]);
            parents.put(son, parent);
        }
        for (int i = 0; i < words1.length; i++) {
            String son = findAreSentencesSimilarTwo(parents, words1[i]);
            String parent = findAreSentencesSimilarTwo(parents, words2[i]);
            if (son != parent)    return false;
        }
        return true;
    }
    public String findAreSentencesSimilarTwo(Map<String, String> map, String str) {
        if (!map.containsKey(str))  map.put(str, str);
        return map.get(str) == str ? str : findAreSentencesSimilarTwo(map, map.get(str));
    }

    /* compare 有delete符号的strings */
    /*
    * Use a stack to store the reading chars, when reads the deleting char, poll one from the top, compare them after
    * */
    public boolean compareDeletedString(String s1, String s2, char ch) {
        return deleteString(s1, ch).equals(deleteString(s2, ch));
    }
    public String deleteString(String str, char ch) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == ch && !stack.isEmpty())    stack.pop();
            else    stack.push(c);
        }
        // System.out.println(stack.toString());
        return stack.toString().replaceAll(ch+"", "");
    }

    /* 1. 给一个string  比如说"abc", 再给一个list<String>， 里面都是原来string的substring, 比如 {“ab”, " bc"} 这种的，
    然后让你返回一个string, 把list里面出现过得string 用<b></b> tag 圈起来， 比如这个例子的结果就是 <b>abc</b>
    再个几个例子， "abcab",  {"ab"}  return <b>ab</b>c<b>ab</b>
                       "aaaab "  {"aaa", "aab"}  return <b>aaaab</b>*/
    /*
    * First record the start index and end index of each substring, and then check if it has any overlapped intervals
    * if it does, merge them, and then string builder to link them up.
    * */
    public String tagString(String str, List<String> list) {
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (String substr: list) {
            int index = 0;
            while (index < substr.length()) {
                int i = str.substring(index).indexOf(substr);
                if (i != -1) {
                    start.add(index + i);
                    end.add(index + i + substr.length() - 1);
                }
                index++;
            }
        }
        start.sort((i1, i2) -> Integer.compare(i1, i2)) ;
        end.sort((i1, i2) -> Integer.compare(i1, i2)) ;
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0, j = 0; i < start.size(); i++) {
            if (i == start.size() - 1 || start.get(i + 1) > end.get(i)) {
                intervals.add(new Interval(start.get(j), end.get(i)));
                j = i + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!intervals.isEmpty() && i == intervals.get(0).start){
                sb.append("<b>");
                sb.append(str.charAt(i));
            }
            else if (!intervals.isEmpty() && i == intervals.get(0).end){
                sb.append(str.charAt(i));
                sb.append("</b>");
                intervals.remove(0);
            }
            else    sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /* 345. Reverse Vowels of a String - Two Pointers */
    public String reverseVowels(String s) {
        if(s == null || s.length()==0) return s;
        int left = 0, right = s.length() - 1;
        String vowels = "aeiouAEIOU";
        char[] chs = s.toCharArray();
        while (left < right) {
            while (left < right && !vowels.contains(chs[left]+"")) left++;
            while (left < right && !vowels.contains(chs[right]+"")) right--;
            char temp = chs[left];
            chs[left] = chs[right];
            chs[right] = temp;
            left++;
            right--;
        }
        return new String(chs);
    }

    /* 734. Sentence Similarity */
    /*
    * Build graph with Hash map for each similar pairs
    * Run dfs to check the similarity
    * */
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) return false;
        HashMap<String, List<String>> graph = new HashMap<>();
        for (String[] pair: pairs) {
            if (!graph.containsKey(pair[0])) graph.put(pair[0], new ArrayList<>());
            if (!graph.containsKey(pair[1])) graph.put(pair[1], new ArrayList<>());
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i]))    continue;
            if (!graph.containsKey(words1[i]) || !graph.get(words1[i]).contains(words2[i]))  return false;
        }
        return true;
    }

    /* 687. Longest Univalue Path - DFS */
    public int longestUnivaluePath(TreeNode root) {
        int[] result = new int[1];
        if (root != null)   longestUnivaluePathDFSHelper(root, result);
        return result[0];
    }
    public int longestUnivaluePathDFSHelper(TreeNode root, int[] result) {
        int left = root.left != null ? longestUnivaluePathDFSHelper(root.left, result) : 0;
        int right = root.right != null ? longestUnivaluePathDFSHelper(root.right, result) : 0;
        int resultLeft = root.left != null && root.left.val == root.val ? left + 1 : 0;
        int resultRight = root.right != null && root.right.val == root.val ? right + 1 : 0;
        result[0] = Math.max(result[0], resultLeft + resultRight);
        return Math.max(resultLeft, resultRight);
    }

    /* 516. Longest Palindromic Subsequence - DP */
    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseqDPHelper(s, 0, s.length()-1, new int[s.length()][s.length()]);
    }
    public int longestPalindromeSubseqDPHelper(String s, int i, int j, int[][] dp){
        if (dp[i][j] != 0)   return dp[i][j];
        if (i > j)  return 0;
        if (i == j) return 1;
        if (s.charAt(i) == s.charAt(j)) dp[i][j] =  longestPalindromeSubseqDPHelper(s, i + 1, j - 1, dp) + 2;
        else    dp[i][j] =  Math.max(longestPalindromeSubseqDPHelper(s, i, j - 1, dp),
                longestPalindromeSubseqDPHelper(s, i + 1  , j, dp));
        return dp[i][j];
    }

    /* 409. Longest Palindrome */
    public int longestPalindrome(String s) {
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
                count++;
            }
            else
                set.add(ch);
        }
        if (set.isEmpty())  return count * 2;
        else    return count * 2 + 1;
    }

    /* 425. Word Squares */
    /*
    * First build a Trie as Dictionary, and then use DFS to find out a working square
    * */
    public class TrieNode {
        List<String> items;
        TrieNode[] children;
        public TrieNode(){
            children = new TrieNode[26];
            items = new ArrayList<>();
        }
    }
    public TrieNode root = new TrieNode();
    public void buildTrieWords(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                if (node.children[ch - 'a'] == null) node.children[ch - 'a'] = new TrieNode();
                node.children[ch - 'a'].items.add(s);
                node = node.children[ch - 'a'];
            }
        }
    }
    public List<String> findByPrefix(String word) {
        TrieNode node = root;
        List<String> ans = new ArrayList<>();
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null){
//                System.out.println(ch);

                return ans;
            }
            node = node.children[ch - 'a'];
        }
        ans.addAll(node.items);
        return ans;
    }
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words.length == 0 || words == null) return ans;
        List<String> subList = new ArrayList<>();
        buildTrieWords(words);
        for (String word : words) {
            subList.add(word);
            searchMatchWords(ans, subList, word.length());
            subList.remove(subList.size() - 1);
        }
        return ans;
    }
    public void searchMatchWords(List<List<String>> ans, List<String> subList, int len) {
        if (subList.size() == len)  {
            ans.add(new ArrayList<>(subList));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str: subList)   sb.append(str.charAt(subList.size()));
        List<String> temp = findByPrefix(sb.toString());
        for (String str: temp) {
            subList.add(str);
            searchMatchWords(ans, subList, len);
            subList.remove(subList.size() - 1);
        }
    }

    /* 422. Valid Word Square */
    public boolean validWordSquare(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (String s: words) {
                if (i < s.length())
                    sb.append(s.charAt(i));
            }
            if (!words.get(i).equals(sb.toString())) return false;
        }
        return true;
    }

    /* 332. Reconstruct Itinerary */
    /*
    * Use Hash map <String, List<String>> as a graph to store all the tickets
    * Build the graph first, and then use DFS to find through a way of itinerary,
    * And Sort the list<String> while building the graph, so we can follow lexical order
    * */
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, List<String>> graph = new HashMap<>();
        List<String> ans = new ArrayList<>();
        ans.add("JFK");
        for (int i = 0; i < tickets.length; i++) {
            if (!graph.containsKey(tickets[i][0]))   graph.put(tickets[i][0], new ArrayList<>());
            addToList(graph.get(tickets[i][0]), tickets[i][1]);
        }
        findItineraryDFS("JFK", graph, graph.get("JFK"), ans, new HashSet<>(), tickets.length + 1);
        return ans;
    }
    public boolean findItineraryDFS (String start, HashMap<String, List<String>> graph, List<String> subList,
                                          List<String> ans, Set<String> set, int len) {
        if (ans.size() == len)   return true;
        if (!graph.containsKey(start) || subList.size() == 0)   return false;
        for (int i = 0; i < subList.size(); i++) {
            String next = subList.get(i);
            subList.remove(next);
            ans.add(next);
            if (findItineraryDFS(next, graph, graph.get(next), ans, set, len))  return true;
            ans.remove(ans.size() - 1);
            addToList(subList, next);
        }
        return false;
    }
    public void addToList(List<String> list, String str) {
        int count = 0;
        while (count < list.size()) {
            if (list.get(count).compareTo(str) > 0){
                list.add(count, str);
                return;
            }
            count++;
        }
        list.add(str);
    }

    /* 399. Evaluate Division */
    /*
    * Convert the question into a graph and use DFS to solve it
    * convert graph first in Hash map <String, list<String>> for nodes
    * hash map <String, list<Double>> for edges/values
    * Build a DFS to find the answer for one query first, and then looping queries to find all the answers
    * */
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, List<String>> graph = new HashMap<>();
        HashMap<String, List<Double>> valueMap = new HashMap<>();
        double[] ans = new double[queries.length];
        for (int i = 0; i < equations.length; i++) {
            if (!graph.containsKey(equations[i][0]))    graph.put(equations[i][0], new ArrayList<>());
            if (!graph.containsKey(equations[i][1]))    graph.put(equations[i][1], new ArrayList<>());
            if (!valueMap.containsKey(equations[i][0]))    valueMap.put(equations[i][0], new ArrayList<>());
            if (!valueMap.containsKey(equations[i][1]))    valueMap.put(equations[i][1], new ArrayList<>());

            graph.get(equations[i][0]).add(equations[i][1]);
            graph.get(equations[i][1]).add(equations[i][0]);
            valueMap.get(equations[i][0]).add(values[i]);
            valueMap.get(equations[i][1]).add(1/values[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            ans[i] = calcEquationDFSHelper(queries[i][0], queries[i][1], graph, valueMap, new HashSet<>(), 1);
            if (ans[i] == 0)    ans[i] = -1;
        }
        return ans;
    }
    public double calcEquationDFSHelper(String start, String end, HashMap<String, List<String>> graph,
                                        HashMap<String, List<Double>> valueMap, Set<String> set, double value) {
        if (set.contains(start))    return 0;
        if (!graph.containsKey(start) || !graph.containsKey(end))  return 0;
        if (start.equals(end))  return value;
        set.add(start);
        double temp = 0;
        for (int i = 0; i < graph.get(start).size(); i++) {
            temp = calcEquationDFSHelper(graph.get(start).get(i), end, graph, valueMap, set, value * valueMap.get(start).get(i));
            if (temp != 0)  break;
        }
        set.remove(start);
        return temp;
    }

    /* Topological sorting problem - 210. Course Schedule II */
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjs = new ArrayList<>();
        int[] ans = new int[numCourses];
        int[] prerequiNum = new int[numCourses];
        for (int i = 0; i < numCourses; i ++) adjs.add(new ArrayList<>());
        for (int i = 0; i < prerequisites.length; i ++) {
            adjs.get(prerequisites[i][1]).add(prerequisites[i][0]);
            prerequiNum[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (prerequiNum[i] == 0)    queue.offer(i);
        }
        int index = 0, count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans[index++] = cur;
            count++;
            for (int course: adjs.get(cur)) {
                prerequiNum[course]--;
                if (prerequiNum[course] == 0)   queue.offer(course);
            }
        }
        return count == numCourses ? ans: new int[0];
    }

    /* Topological sorting problem - 207. Course Schedule */
    /*
    * BFS/DFS to solve the problem, by making it in graph first, and then use BFS or DFS to find the topological sorting
    *
    * */
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        // adjs to represents what courses have prerequisites of it, {{1,2,3},{1,2}} means 1,2,3 has prerequisites of 0
        List<List<Integer>> adjs = new ArrayList<>();
        int[] prerequiNum = new int[numCourses];
        for (int i = 0; i < numCourses; i ++) adjs.add(new ArrayList<>());
        for (int i = 0; i < prerequisites.length; i ++) {
            adjs.get(prerequisites[i][1]).add(prerequisites[i][0]);
            prerequiNum[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (prerequiNum[i] == 0)    queue.offer(i);
        }
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int curCourse = queue.poll();
            visitedCount++;
            for (int course: adjs.get(curCourse)) {
                prerequiNum[course]--;
                if (prerequiNum[course] == 0)   queue.offer(course);;
            }
        }
        return visitedCount == numCourses;
    }

    /* DFS - 207. Course Schedule*/
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjs = new ArrayList<>();
        for (int i = 0; i < numCourses; i ++) adjs.add(new ArrayList<>());
        for (int i = 0; i < prerequisites.length; i ++) {
            adjs.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFSHelper(adjs, visited, i))  return false;
        }
        return true;
    }
    public boolean canFinishDFSHelper(List<List<Integer>> adjs, boolean[] visited, int course) {
        if (visited[course])    return false;
        else    visited[course] = true;
        for (int cur: adjs.get(course)) {
            if (!canFinishDFSHelper(adjs, visited, cur))    return false;
        }
        visited[course] = false;
        return true;
    }

    /* clone 一个undirected graph的同时把graph改成directed，directed的定义是一个node的id比parent的id大。
    小姐姐似乎心情很好，面试超时15分钟。*/
    /*
    * DFS: Define a hash map to store all the nodes, label as key and the node as the value
    * BFS: Define a queue to store the reading nodes, hash map to store nodes that has been created
    * */
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        return cloneGraphDFSHelper(map, node);
    }
    public UndirectedGraphNode cloneGraphDFSHelper(HashMap<Integer, UndirectedGraphNode> map, UndirectedGraphNode node) {
        if (node == null)   return null;
        if (map.containsKey(node.label))    return map.get(node.label);
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(newNode.label, newNode);
        for (UndirectedGraphNode n : node.neighbors) {
            newNode.neighbors.add(cloneGraphDFSHelper(map, n));
        }
        return newNode;
    }
    /* Clone Graph BFS */
    public UndirectedGraphNode cloneGraphBFS (UndirectedGraphNode node) {
        if (node == null)   return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        queue.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        while (!queue.isEmpty()) {
            UndirectedGraphNode root = queue.poll();
            for (UndirectedGraphNode n: root.neighbors) {
                if (!map.containsKey(n)){
                    map.put(n, new UndirectedGraphNode(n.label));
                    queue.offer(n);
                }
                map.get(root).neighbors.add(map.get(n));
            }
        }
        return map.get(node);
    }

    /* 46. Permutations - Backtracking*/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        permuteBacktracking(list, new ArrayList<>(), nums);
        return list;
    }
    public void permuteBacktracking(List<List<Integer>> list, List<Integer> sublist, int[] nums) {
        if (sublist.size() == nums.length)  list.add(new ArrayList<>(sublist));
        else {
            for (int i = 0 ; i < nums.length; i++) {
                if (sublist.contains(nums[i]))  continue;
                sublist.add(nums[i]);
                permuteBacktracking(list, sublist, nums);
                sublist.remove(sublist.size() - 1);
            }
        }
    }

    /* 最小四边形面积 */
    /*
    * 1. brutal force, O(n^4), 4 for loop, find two different rows that both has a point on the same col
    *    check on different cols that also has points on these two rows, find the smallest
    * 2. Hash map <rows, List<the num on that row>>, store each row of the matrix into hash map
    *    loop through the hashmap, find two different points in a row and check if they exist on the col map
    *    1,0,1,1,1,1
    *    1,1,0,0,1,1
    *    0,1,0,0,1,0
    *
    *    ex., row mao
    *    0, {0,2,3,4,5}
    *    1, {0,1,4,5}
    *    2, {1,4}
    *    find (0,2), (0,4) on row 0, check if 2, 4 exists on other rows, if not, try 0, 3, O(n^4)
    * 3. Find diagonal, if we find a left-top point and a right-bot point, we can find out if the right top point and
    *    left bot point exists
    *    ex. if we find (0, 2) and (1, 4), then we just need to check (1,2) and (0,4), O(n^3)
    * */
    public int minimumRectangleSize(int[][] matrix) {
        int smallest = Integer.MAX_VALUE, m = matrix.length, n = matrix[0].length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == 1) {
                    if (map.containsKey(i)) map.get(i).add(j);
                    else    {
                        List<Integer> sublist = new ArrayList<>();
                        sublist.add(j);
                        map.put(i, sublist);
                    }
                }
            }
        }
        System.out.println(map);
        for (int key: map.keySet()) {
            for (int i = 0; i < map.get(key).size() - 1; i ++) {
                for (int j = i + 1; j < map.get(key).size(); j++) {
                    int index0 = map.get(key).get(i), index1 = map.get(key).get(j);
                    for (int match: map.keySet()) {
                        if (match != key && map.get(match).contains(index0) && map.get(match).contains(index1)) {
                            smallest = Math.min(smallest, Math.abs(match - key) * Math.abs(index1 - index0));
                        }
                    }
                }
            }
        }
        return smallest;
    }

    /* 一个字符串比另一个多了一个字母，其他一样，怎么找出这个多出来的字母 */
    /*
    * Hashmap to store all the char in the first string
    * Loop through the second string and delete the reading char in the hashmap
    * the char left in the map is the additional one
    * */
    public char additionalChar(String str1, String str2) {
        HashMap<Character, Integer> hashmap = new HashMap<>();
        String shoter = str1.length() > str2.length() ? str2 : str1;
        String longer = str1.length() > str2.length() ? str1 : str2;
        for (char ch: longer.toCharArray()) {
            hashmap.put(ch, hashmap.getOrDefault(ch, 0) + 1);
        }
        for (char ch: shoter.toCharArray()) {
            if (hashmap.containsKey(ch))
                hashmap.put(ch, hashmap.get(ch) - 1);
            if (hashmap.get(ch) == 0)
                hashmap.remove(ch);
        }
        List<Character> list = new ArrayList<>(hashmap.keySet());
        return list.get(0);
    }

    /* interval 变种，一堆interval，找出存在重叠的interval，并把它们标记成overlapped = true。follow up:
    对于每个有重叠的interval，找出都有哪些intervals 跟它重叠。。。*/
    /*
    * Sort the intervals first
    * record the first start as int start, first end as int end
    * run through all intervals, check if the reading interval.start < end
    * add the overlapped part from the start to end, and update the overlapped = true
    * else update the start and end as the reading one
    * */
    public class Interval{
        int start;
        int end;
        boolean overlapped;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            overlapped = overlapped;
        }
    }
    public List<Interval> checkOverlapIntervals(List<Interval> intervals){
        if (intervals == null || intervals.size() == 1) return null;
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        List<Interval> list = new ArrayList<>();
        Interval last = intervals.get(0);
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval: intervals) {
            if (interval.start < end) {
                list.add(new Interval(interval.start, end));
                last.overlapped = true;
                interval.overlapped = true;
                if (interval.end > end) {
                    end = interval.end;
                    last = interval;
                }
            }
            else {
                start = interval.start;
                end = interval.end;
                last = interval;
            }
        }
        return list;
    }


    /* 第二题，给一个matrix来表示电脑屏幕， 如下：
            aaaaa    ccccc
            a   a    c   c
            a   abbbbccccc
            aaaaa    b
               b     b
               bbbbbbb
    这样由a，b围成的两个window，a就处于顶层，然后要求给这样一个表示的matrix， 找到顶层的window(s) */
    /*
    * Run through the matrix, once reads a character, check on right, then down
    * Record the width and height, and then go left with the width and go up with the height
    * check if the character on that position is the same as the original char
    * if yes, add it to the output list
    * */
    public List<Character> findTopWindows(char[][] matrix) {
        List<Character> list = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (Character.isLetter(matrix[i][j]) && !set.contains(matrix[i][j]) && isOnTop(matrix, i, j, set)) {
                    list.add(matrix[i][j]);
                }
            }
        }
        return list;
    }
    public boolean isOnTop(char[][] matrix, int row, int col, Set<Character> set) {
        set.add(matrix[row][col]);
        char origin = matrix[row][col];
        int width = 0, height = 0;
        while (col < matrix[0].length && matrix[row][col] == origin) {
            col++;
            width++;
        }
        col --;
        while (row < matrix.length && matrix[row][col] == origin) {
            row++;
            height++;
        }
        row --;
        // System.out.println(row+" "+col+" "+matrix[row][col]);
        if (width == 1 || height == 1)  return false;
        for (int j = width - 1; j > 0; j--){
            if (matrix[row][col--] != origin) return false;
        }
        for (int j = height - 1; j > 0; j--){
            if (matrix[row--][col] != origin) return false;
        }
        return true;
    }

    /*   1.2 轮 ： 一个华人小哥，题目蛮简单的，第一题，有一串字母，规律如下，“a,b,c, a,a,b,b,c,c, a,a,a,a,b,b,b,b,c,c,c,c,....."
    就是每一轮加倍abc的数量，问你第n个字母是什么*/
    /*
    * use a temp int sum and loop to sum up the total length
    * and a temp times int to store how many times the chars have been duplicated
    * when the sum > the n th char, break the loop, and return str.charAt((index - sum + len * times)/times)
    * */
    public char nthChar(String str, int index) {
        int sum = str.length(), times = 1;
        while (sum < index) {
            times *= 2;
            sum += str.length() * times;
        }
        return str.charAt((index - (sum - str.length() * times) - 1) / times);
    }

    /* 1.1 轮 ： 是一个美国小哥，说话慢条斯理，打字也慢条斯理。。所以只有一题，说的是有一沓传单，每张传单有不同的厚度/高度，
    然后让你写2个function，一个是setHeight()表示把处于位置的传单的厚度改为新的height，
    还有一个getHeight()表示取出处于这个height的是第几张传单*/
    /*
    * We use a list of Integer to store all the data, since list is easy to modify
    * for setHeight, we just modify that on the list
    * for getHeight, loop list, and sum all height for the read heights so far, and return the index
    * */
    private List<Double> heights;
    public void setHeight(int posit, double height) {
        heights.remove(posit);
        heights.add(posit, height);
    }
    public int getHeight(double height) {
        double sum = 0;
        int index = 0;
        while (sum < height) {
            sum += heights.get(index++);
        }
        return index;
    }

    /* 前两天刚面的，也是onsite挂了之后面的，通过google hangout。BQ的话大概就是有什么职业规划，你觉得我们这个项目怎么样。
    题的话是给个matrix 给个出发点 然后螺旋状绕出来，打印出来，应该不难。 */
    /*
    * List of integers to print output
    * 4 temp int to store the out most indexes
    * for looping on the array, from the top in clockwise, check if it hits the border
    * if yes, output, or add it to list
    * */
    public List<Integer> printClockWise(int[][] grid, int startRow, int startCol) {
        List<Integer> list = new ArrayList<>();
        list.add(grid[startRow][startCol]);
        printClockWiseHelper (list, grid, startRow, startCol, 1);
        return list;
    }
    public void printClockWiseHelper(List<Integer> list, int[][] grid, int row, int col, int round) {
        if (row - round < 0 || row - round >= grid.length || col < 0 || col >= grid[0].length) return;
        else    list.add(grid[row - round][col]);;
        if (row - round < 0 || row - round >= grid.length || col + round < 0 || col + round >= grid[0].length)  return;
        else    list.add(grid[row - round][col + round]);
        if (row < 0 || row >= grid.length || col + round < 0 || col + round >= grid[0].length)  return;
        else    list.add(grid[row][col + round]);
        if (row + round < 0 || row + round >= grid.length || col + round < 0 || col + round >= grid[0].length)  return;
        else    list.add(grid[row + round][col + round]);
        if (row + round < 0 || row + round >= grid.length || col < 0 || col >= grid[0].length)  return;
        else    list.add(grid[row + round][col]);;
        if (row + round < 0 || row + round >= grid.length || col - round < 0 || col - round >= grid[0].length)  return;
        else    list.add(grid[row + round][col - round]);
        if (row < 0 || row >= grid.length || col - round < 0 || col - round >= grid[0].length)  return;
        else    list.add(grid[row][col - round]);
        if (row - round< 0 || row - round >= grid.length || col - round < 0 || col - round >= grid[0].length)  return;
        else    list.add(grid[row - round][col - round]);
        printClockWiseHelper(list, grid, row, col, round + 1);
    }

    /* follow up是在第一题的上述基础上(input 一样)，现在我要插入一辆新的车，他的速度是N+1，可以插在任意位置，返回它插在所有位置的情况下
    ，chunk们的大小。比如是[2,4,1,3]这样它就有5个位置可以插，返回的是[ [1,2,2], [3,2], [3,2], [2,3], [2,3] ] */
    public List<List<Integer>> insertRoadChunk (int[] cars) {
        List<List<Integer>> totalList = new ArrayList<>();
        for (int i = 0; i < cars.length + 1; i++){
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < cars.length; j++){
                list.add(cars[j]);
            }
            list.add(i, cars.length + 1);
            totalList.add(list);
        }
        for (int i = 0; i < totalList.size(); i++){
            List<Integer> list = totalList.remove(i);
            int[] ans = new int[list.size()];
            for (int j = 0; j < list.size(); j++){
                ans[j] = list.get(j);
            }
            totalList.add(i, roadChunk(ans));
        }
        return totalList;
    }

    /* 场景是一条公路上，只有一条车道，不能超车，然后有N量车，他们的速度是1 - N，前面的车如果比后面的车慢，那最终快车会被郁闷地
    堵着跟满车一个速度。这样的一撮车成为一个chunk。给你初始状态各个车在什么位置，你要算有多少个这样的chunk，返回每个chunk的大小
    比如[2,4,1,3]这样最后有两个chunk，每个chunk的大小是2.  ==>  [2,2]*/
    /*
    * There must be a int to store the last fastest car
    * Loop through the array, if the car >= last, inner while loop to read all cars that faster than last and count ++
    * if car < last, update last and count ++
    * */
    public List<Integer> roadChunk (int[] cars) {
        if (cars == null || cars.length == 0)   return null;
        List<Integer> list = new ArrayList<>();
        int last = cars[0], cur = 0, count = 0;
        while (cur < cars.length) {
            if (cars[cur] < last) {
                last = cars[cur++];
                list.add(count);
                count = 1;
            }
            else {
                while (cur < cars.length && cars[cur] >= last) {
                    cur++;
                    count++;
                };
            }
        }
        list.add(count);
        return list;
    }

    /* 482. License Key Formatting */
    /*
    * Read the String from the end, because the beginning could be less than K
    * if reads a num or char, add it to the string builder
     *  if the length of string builder is n * K, add a '-' and the char
     *  if the length is not n*K, directly add it to the string builder
     * reverse and to upper case before return out
    * */
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        for (int i = S.length() - 1; i >= 0; i--){
            if (S.charAt(i) != '-')
                sb.append((sb.toString().length() % (K + 1) == K) ? "-" : "").append(S.charAt(i));
        }
        return sb.reverse().toString().toUpperCase();
    }

    /* 686. Repeated String Match */
    public int repeatedStringMatch(String A, String B) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.toString().length() <= B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.toString().contains(B))  return count;
        else if (sb.append(A).toString().contains(B))   return count+1;
        else    return -1;
    }

    /*  3Sum in t/f output */
    public boolean threeSumTF(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i< nums.length - 2; i++){
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[i] + nums[lo] + nums[hi] == 0) return true;
                else if (nums[i] + nums[lo] + nums[hi] < 0) lo++;
                else    hi--;
            }
        }
        return false;
    }

    /* 15. 3Sum */
    /*
    * Use List of List String to store the ans
    * Loop through the array for each number
    * use two pointers in inner loop lo < hi
    * check if target - nums[lo] - nums[hi] == current num
    * if yes, add that to list
    * */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if ((i > 0 && nums[i] != nums[i-1]) || i == 0) {
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi) {
                    if (nums[i] + nums[lo] + nums[hi] == 0) {
                        list.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo + 1] == nums[lo]) lo++;
                        while (lo < hi && nums[hi - 1] == nums[hi]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[i] + nums[lo] + nums[hi] > 0) hi--;
                    else lo++;
                }
            }
        }
        return list;
    }

    /*  http://www.1point3acres.com/bbs/thread-319558-1-1.html
    给一张image，没有定义数据类型，但是遇到的人大概都会用int[][]。里面都是0或1，可以理解为1代表岛，要你写一个method/function，
    把岛除了边缘以外的东西删掉。*/
    /*
    * 2d for loop to run the grid, and check on each cell, if cell == 1, then check neighbors
    * if there is a neighbors 0, then this is on border leave it 1;
    * otherwise, mark it to 2;
    * loop through all cell and mark all marked cells to 0;
    * */
    public int[][] removeInsideOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && i > 0 && i < grid.length -1 && j > 0 && j < grid[0].length -1) {
                    if (!isOnBorder(grid, i, j))
                            grid[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2)    grid[i][j] = 0;
            }
        }
        return grid;
    }
    public boolean isOnBorder(int[][] grid, int row, int col) {
        if (grid[row + 1][col] == 0 || grid[row + 1][col + 1] == 0 || grid[row + 1][col - 1] == 0
                || grid[row - 1][col] == 0 || grid[row - 1][col + 1] == 0 || grid[row - 1][col - 1] == 0
                || grid[row][col + 1] == 0 || grid[row][col - 1] == 0)
            return true;
        return false;
    }

    /*  http://www.1point3acres.com/bbs/thread-319558-1-1.html
    String wrap，地里面经有。已知：1.文本框，已知height & width    2.Font size 范围，min到max已知。  3.两个API
    int widthOf(int fontSize, char c)   int heightOf(int fontSize)  4.一个输入的String 求：最大的能fit in 文本框的font size
    规则：一行填满了就下一行，不用管单词断不断开。for a particular font size, every char has same height,
    but (not necessary) different width. */
    public int width (int fontSize, char c)  {return -1;}
    public int height (int fontSize)    {return -1;}
    /*
    * Binary loop through min font size to max font size with two pointers
    * mid = (lo + hi) / 2, a index for string
    *   int current index for string, cur height = 0, width left = width;
    *   inner loop for each char in the string
    *       if (width left < cur char.width)
    *           width left = width
    *           cur height += cur char.height
    *       width left -= cur char.width
    *   if (cur height <= height)    biggest = Math.max(biggest, cur font size)
    * */
    public int stringWrap(String str, int minFont, int maxFont, int height, int width) {
        int lo = minFont, hi = maxFont, biggest = minFont;
        while (lo <= hi) {
            int mid = (lo + hi) / 2, curHeight = 0, widthLeft = width;
            for (char ch: str.toCharArray()) {
                if (widthLeft < width(mid, ch)) {
                    widthLeft = width;
                    curHeight += height(mid);
                }
                widthLeft -= width(mid, ch);
            }
            if (curHeight <= height) {
                biggest = Math.max(biggest, mid);
                lo = mid + 1;
            }
            else    hi = mid - 1;
        }
        return biggest;
    }

    /* 259. 3Sum Smaller */
    /*
    * First sort the array
    * loop the nums array, and set two pointers from the i + 1 to len - 1
    * inner loop if (nums[lo] + nums[hi] + num < target)    which means all combinations that smaller than hi would do
    * count += hi - lo
    * */
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                if (nums[lo] + nums[hi] + nums[i] < target) count += hi - lo++;
                else    hi--;
            }
        }
        return count;
    }

    /* 159. Longest Substring with At Most Two Distinct Characters */
    /*
    * Use a Hash map to store the chars that read in
    * Use a left int to store the beginning index of substring
    * Loop through the string
    *   inner loop while (map.size() > 2)
    *       delete all beginning char in the map to keep there are only 2 distinct characters in the map
    *       left += map.get(beginning char)
    *   put the char into map
    *   longest = Math.max(longest, current index - left);
    * */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int longest = 0, left = 0;
        for (int i = 0; i < s.length(); i ++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            while (map.size() > 2) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if (map.get(s.charAt(left)) == 0) map.remove(s.charAt(left));
                left++;
            }
            longest = Math.max(longest, i - left + 1);
        }
        return longest;
    }

    /* 22. Generate Parentheses */
    /*
    * Create a list to store any substring that has the length of 2n
    * Helper recursive function
    *   if num of ( < n, add a ( to the substring
    *   if num of ) < (, add a ) to the substring
    *   if the length of ( + ) = 2n, add it to list
    * */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesisHelper(list, "", 0, 0, n);
        return list;
    }
    public void generateParenthesisHelper(List<String> list, String subStr, int left, int right, int n){
        if (left < n)   generateParenthesisHelper(list, subStr + '(', left + 1, right, n);
        if (right < left)  generateParenthesisHelper(list, subStr + ')', left, right + 1, n);
        if (left + right == 2 * n)  list.add(subStr);
    }

    /* 20. Valid Parentheses */
    /*
    * Use a stack to store the parentheses it reads
    * Loop through the string, when reads a (, push a )
    * when reads a [, push a ]
    * when reads a {, push a }
    * when read anything else, compare it with pop, if not the same, it is invalid
    * */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()){
            if (ch == '(')  stack.push(')');
            else if (ch == '[') stack.push(']');
            else if (ch == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != ch) return false;
        }
        return stack.isEmpty();
    }

    /* 163. Missing Ranges */
    /*
    * A list<String> to store the result
    * Loop though the num array, once read a number, check if this number > lower
    *       if this number - 1 > lower  ? add lower -> num - 1 : add lower
    *   if this number >= upper return list
    *   update lower = i;
    * check if lower < upper, add lower + 1 -> upper
    * */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new LinkedList<>();
        for (int num : nums) {
            if (num > lower)    list.add((num - 1 > lower) ? lower + "->" + (num - 1): lower + "");
            if (num >= upper)    return list;
            lower = num + 1;
        }
        if (lower <= upper)  list.add((lower < upper) ? lower + "->" + upper : upper + "");
        return list;
    }

    /* 128. Longest Consecutive Sequence */
    /*
    * Use a set to store the numbers in the array and remove duplicates
    * Loop again in the array and check if the set has the consecutive numbers
    *   if the set has increasing consecutive, remove them and count them
    *   if the set has decreasing consecutive, remove them and count them
    *   longest = Math.max(longest, inc + dec)
    * */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)    set.add(num);
        int longest = 0;
        for (int num : nums) {
            if (set.remove(num)) {
                int inc = 0, dec = 0;
                int val = num;
                while (set.remove(--val))   dec++;
                val = num;
                while (set.remove(++val))   inc++;
                longest = Math.max(longest, inc + dec + 1);
            }
        }
        return longest;
    }

    /* 750. Number Of Corner Rectangles */
    /*
    * 2-d Loop for two rows at a time, and another loop in cols to check if there is a rectangle formed in between
    * */
    public int countCornerRectangles(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                int subCount = 0;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] == '1' && grid[j][k] == '1') subCount++;
                }
                if (subCount > 0) count += subCount * (subCount + 1) / 2;
            }
        }
        return count;
    }

    /* 340. Longest Substring with At Most K Distinct Characters */
    /*
    * Create a hash map to store each appeared character and how many times it appeared in the string
    * Use a integer left index to store where did this substring begin
    * Loop through the string by each character, and add that char to map
    * Run a sub-loop to make sure when map.size > k, it will delete from the beginning char
    *   and move the sliding window to the right
    *   if map.contains key (beginning char) map.get(beginning char)--
    * The length is the current index - the beginning index
    * Use a int max = Math(max, cur - begin) to get the longest length
    * */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, longest = 0;
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            while (map.size() > k) {
                char leftCh = s.charAt(left);
                if (map.containsKey(leftCh))    map.put(leftCh, map.get(leftCh) - 1);
                if (map.get(leftCh) == 0)   map.remove(leftCh);
                left++;
            }
            longest = Math.max(longest, i - left + 1);
        }
        return longest;
    }

    /* 215. Kth Largest Element in an Array */
    public int findKthLargest(int[] a, int k) {
        int len = a.length;
        return a[quickSelect(a, 0, len-1, len - k)];
    }

    // return the index of the kth smallest number
    public int quickSelect(int[] a, int lo, int hi, int k) {
        shuffle(a);
        int left = lo, pivot = a[hi];
        for (int i = lo; i < hi; i++ ){
            if (a[i] <= pivot){
                swap(a, left++, i);
                //  System.out.println("-"+Arrays.toString(a)+" "+i+" "+left);
            }
        }
        swap(a, left, hi);
        //  System.out.println("*"+Arrays.toString(a)+" "+hi+" "+left);
        if (left == k)  return left;
        else if (left > k)  return quickSelect(a, lo, left - 1, k);
        else    return quickSelect(a, left + 1, hi, k);
    }
    public void shuffle(int[] a) {
        Random random = new Random();
        for (int i = 1; i < a.length; i++){
            swap(a, i, random.nextInt(i + 1));
        }
    }
    public void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /* Quick Sort */
    private void quickSort(int[] array, int lowerIndex, int higherIndex) {

        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
//                System.out.println(Arrays.toString(array));
                exchangeNumbers(array, i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }

        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(array, lowerIndex, j);
        if (i < higherIndex)
            quickSort(array, i, higherIndex);
    }
    private void exchangeNumbers(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /* 160. Intersection of Two Linked Lists */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int alen = countLen(headA), blen = countLen(headB);
        while (alen > blen) {
            headA = headA.next;
            alen--;
        }
        while (blen > alen) {
            headB = headB.next;
            blen--;
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
    public int countLen(ListNode headA) {
        int count = 0;
        ListNode temp = headA;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tempA = headA, tempB = headB;
        while (tempA != tempB){
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }

    /* 221. Maximal Square */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)   return 0;
        int max = 0;
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 1; i <= matrix.length; i++){
            for (int j = 1; j <= matrix[0].length; j++){
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    /* 361. Bomb Enemy */
    /*
    * Use 2-d array to store the number of enemies the bomb can kill in each position for DP
    * Looping from top to bot and vice versa and left to right and vice versa to fill the DP array
    * Run a loop for the DP array to find out the max
    * */
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)    return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i ++){
            int count = 0;
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 'E')  count++;
                else if (grid[i][j] == 'W')  count = 0;
                else    dp[i][j] += count;
            }
            count = 0;
            for (int j = grid[0].length - 1; j >= 0; j--){
                if (grid[i][j] == 'E')  count++;
                else if (grid[i][j] == 'W')  count = 0;
                else    dp[i][j] += count;
            }
        }
        for (int i = 0; i < grid[0].length; i ++){
            int count = 0;
            for (int j = 0; j < grid.length; j++){
                if (grid[j][i] == 'E')  count++;
                else if (grid[j][i] == 'W')  count = 0;
                else    dp[j][i] += count;
            }
            count = 0;
            for (int j = grid.length - 1; j >= 0; j--){
                if (grid[j][i] == 'E')  count++;
                else if (grid[j][i] == 'W')  count = 0;
                else    dp[j][i] += count;
            }
        }
        int max = 0;
        for (int i = 0; i < grid.length; i ++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /* 394. Decode String */
    /*
    * Use two stack to store string stack and num stack
    * if reads a digit, read through for that number and store into num stack
    * if reads a [ bracket, read through until not a letter and store into str stack
    * if reads a ] bracket, pop a string from str stack and a number from num stack to form a substring
    *                       if str stack is still not empty, then add that substring to the peek of str stack
    *                       else add substring to the string builder
    * if reads anything else, if str stack is not empty, add it to the peek of str stack
    *                         else add the ch to the string builder
    * */
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        int index = 0;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (Character.isDigit(ch)){
                String num = ch + "";
                while (Character.isDigit(s.charAt(++index))) {
                    num += s.charAt(index);
                }
                numStack.push(Integer.parseInt(num));
            }
            // READ '['
            else if (ch == '[') {
                String str = "";
                while (Character.isLetter(s.charAt(++index))) {
                    str += s.charAt(index);
                }
                strStack.push(str);
            }
            else if (ch == ']') {
                String str = "";
                int num = numStack.pop();
                for (int i = 0; i < num; i++) {
                    str += strStack.peek();
                }
                strStack.pop();
                if (!strStack.isEmpty())    strStack.push(strStack.pop() + str);
                else    sb.append(str);
                index++;
            }
            else{
                if (!strStack.isEmpty())    strStack.push(strStack.pop() + ch);
                else    sb.append(ch);
                index++;
            }
        }
        return sb.toString();
    }

    /* 418. Sentence Screen Fitting */
    /*
    * First run through the sentence array
    * Record the next starting index for each starting string in array in a new row
    * Record how many times can be run for each starting string trial in array in a row
    * Run loop in rows and sum them based on next indexes and times
    * */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int[] nextIndex = new int[sentence.length];
        int[] times = new int[sentence.length];
        for (int i = 0; i < sentence.length; i++) {
            int cur = i, curlen = 0, time = 0;
            while (curlen + sentence[cur].length() <= cols) {
                curlen += sentence[cur++].length() + 1;
                if (cur == sentence.length){
                    cur = 0;
                    time++;
                }
            }
            nextIndex[i] = cur;
            times[i] = time;
        }
        int total = 0, cur = 0;
        for (int i = 0; i < rows; i++) {
            total += times[cur];
            cur = nextIndex[cur];
        }
//        System.out.println(Arrays.toString(nextIndex));
        return total;
    }

    /* 549. Binary Tree Longest Consecutive Sequence II */
    /*
    * Use int[]{inc, dec} to store
    * if left != null, left.val != root.val + 1, left[0] ++
    *                  left.val != root.val - 1, left[1] ++
    * if right != null, right.val != root.val + 1, right[0] ++
    *                   right.val != root.val - 1, right[1] ++
    * inc = Math.max(left[0], right[0])
    * dec = Math.max(left[1], right[1])
    * max = Math.max(inc + dec - 1, max)
    * */
    int max549 = 0;
    public int longestConsecutive2(TreeNode root) {
        if (root == null)   return 0;
        longestConsecutive2DFS(root);
        return max549;
    }
    public int[] longestConsecutive2DFS(TreeNode root) {
        if (root == null)   return new int[2];
        int inc = 1, dec = 1;
        int[] left = longestConsecutive2DFS(root.left), right = longestConsecutive2DFS(root.right);
        if (root.left != null) {
            if (root.left.val == root.val + 1)  inc = Math.max(inc, ++left[0]);
            if (root.left.val == root.val - 1)  dec = Math.max(dec, ++left[1]);
        }
        if (root.right != null) {
            if (root.right.val == root.val + 1) inc = Math.max(inc, ++right[0]);
            if (root.right.val == root.val - 1) dec = Math.max(dec, ++right[1]);
        }
        max549 = Math.max(inc + dec - 1, max549);
        return new int[]{inc, dec};
    }

    /* 298. Binary Tree Longest Consecutive Sequence */
    public int longestConsecutive(TreeNode root) {
        if (root == null)   return 0;
        return longestConsecutiveDFS(root, 0, root.val);
    }
    public int longestConsecutiveDFS(TreeNode root, int cur, int target) {
        if (root == null)   return cur;
        if (root.val == target) cur++;
        else    cur = 1;
        int left = longestConsecutiveDFS(root.left, cur, root.val+1);
        int right = longestConsecutiveDFS(root.right, cur, root.val+1);

        return Math.max(Math.max(left, right), cur);
    }

    /* 681. Next Closest Time */
    private int diff = Integer.MAX_VALUE;
    private String result;
    public String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0, 1)));
        set.add(Integer.parseInt(time.substring(1, 2)));
        set.add(Integer.parseInt(time.substring(3, 4)));
        set.add(Integer.parseInt(time.substring(4, 5)));

        if (set.size() == 1)    return time;
        List<Integer> list = new ArrayList<>(set);
        int min = Integer.parseInt(time.substring(0,2))*60 + Integer.parseInt(time.substring(3,5));
        nextClosestTimeDFS(list, "", 0, min);

        return result;
    }
    public void nextClosestTimeDFS(List<Integer> digits, String cur, int pos, int target){
        if (pos == 4) {
            int min = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2));
            int d = (min > target) ? min - target : 1440 + min - target;
            if (d < diff) {
                diff = d;
                result = cur.substring(0, 2) + ":" + cur.substring(2);
            }
            return;
        }
        for (int i = 0; i < digits.size(); i++){
            if (pos == 0 && digits.get(i) > 2)   continue;
            if (pos == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23)    continue;
            if (pos == 2 && digits.get(i) > 5)  continue;
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59)   continue;
            nextClosestTimeDFS(digits, cur + digits.get(i), pos + 1, target);
        }
    }

    /* 388. Longest Absolute File Path */
    public int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int max = 0;
        for (String str : input.split("\n")){
            int level = str.lastIndexOf("\t") + 1;
            while (level + 1 < stack.size())    stack.pop();
            int curLength = stack.peek() + str.length() - level + 1;
//            System.out.println(stack+" "+level +" " + stack.peek());

            if (str.contains("."))  max = Math.max(max, curLength - 1);
            stack.push(curLength);
        }
        return max;
    }

    /* 10. Regular Expression Matching */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        boolean dp[][] = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*')
                dp[0][i+1] = dp[0][i-1];
        }
        for (int i = 0; i < s.length(); i ++) {
            for (int j = 0; j < p.length(); j ++) {
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))
                    dp[i+1][j+1] = dp[i][j];
                if (p.charAt(j) == '*'){
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.')
                        dp[i+1][j+1] = dp[i+1][j-1];
                    else
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch2(String s, String p) {
        if (p.isEmpty())    return s.isEmpty();

        if (p.length() > 1 && p.charAt(1) == '*')
            return isMatch2(s, p.substring(2)) ||
                    (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch2(s.substring(1), p));

        if (p.charAt(0) == '.')
            return !s.isEmpty() && isMatch2(s.substring(1), p.substring(1));

        return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch2(s.substring(1), p.substring(1));
    }

    /* 4. Median of Two Sorted Arrays */
    /*
    * arr1 = {# 1 # 2 # ( 3 / 3 ) # 4 # 5 #}  n = 5
    * arr2 = {# 1 # 1 / 1 # 1}  n = 4
    * 2m + 2n + 2 = (5 + 4) * 2 + 2 '/'
    * c2 = k, c1 = m + n - k
    * l1 = arr1[(c1 - 1)/2], r1 = arr1[c1/2]
    * l2 = arr2[(c2 - 1)/2], r2 = arr2[c2/2]
    * if (l1 > r2)  move c1 to left, c2 to right
    * if (l2 > r1)  move c1 to right, c1 to left
    * else  median = (max(l1, l2) + min(r1, r2))/2
    * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m < n)  return findMedianSortedArrays(nums2, nums1);
        int lo = 0, hi = n * 2;
        while (lo <= hi) {
            int cut2 = (lo + hi) / 2;
            int cut1 = m + n - cut2;

            double l1 = (cut1 == 0) ? Double.MIN_VALUE : nums1[(cut1-1)/2];
            double l2 = (cut2 == 0) ? Double.MIN_VALUE : nums2[(cut2-1)/2];
            double r1 = (cut1 == m * 2) ? Double.MAX_VALUE : nums1[cut1/2];
            double r2 = (cut2 == n * 2) ? Double.MAX_VALUE : nums2[cut2/2];

            if (l1 > r2)    lo = cut2 + 1;
            else if (l2 > r1)   hi = cut2 - 1;
            else    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
        }
        return -1;
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
}
