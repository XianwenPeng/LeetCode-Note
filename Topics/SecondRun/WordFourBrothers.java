package LeetCode.Topics.SecondRun;
import java.util.*;

public class WordFourBrothers {

    /* Trie: 132. Word Search II */
    public List<String> wordSearchIITrie(char[][] board, List<String> words) {
        // write your code here
        TrieNode root = new TrieNode();
        for (String str: words) {
            root.insert(root, str);
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(res, root, board, i, j, "");
            }
        }
        return new LinkedList<>(res);
    }
    private void dfs(Set<String> res, TrieNode root, char[][] board,
                     int row, int col, String str) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ||
                board[row][col] == '-' || root.nodes[board[row][col] - 'a'] == null) return;
        char temp = board[row][col];
        board[row][col] = '-';
        root = root.nodes[temp - 'a'];
        if (root.wordEnd)   res.add(root.str);
        dfs(res, root, board, row + 1, col, str + temp);
        dfs(res, root, board, row - 1, col, str + temp);
        dfs(res, root, board, row, col + 1, str + temp);
        dfs(res, root, board, row, col - 1, str + temp);
        board[row][col] = temp;
    }
    private class TrieNode {
        boolean wordEnd;
        TrieNode[] nodes;
        String str;
        public TrieNode() {
            this.wordEnd = false;
            this.nodes = new TrieNode[26];
        }
        public void insert(TrieNode root, String str) {
            TrieNode node = root;
            char[] chs = str.toCharArray();
            for (char ch: chs) {
                if (node.nodes[ch - 'a'] == null)    node.nodes[ch - 'a'] = new TrieNode();
                node = node.nodes[ch - 'a'];
            }
            node.wordEnd = true;
            node.str = str;
        }
    }

    /* 132. Word Search II */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        Set<String> res = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (String str: words) set.add(str);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(res, "", board, set, i, j);
            }
        }
        return new LinkedList<>(res);
    }
    private void dfs(Set<String> res, String str, char[][] board, Set<String> dic,
                     int row, int col) {
        if (dic.contains(str))  res.add(str);
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || board[row][col] == '-')    return;
        char temp = board[row][col];
        board[row][col] = '-';
        dfs(res, str + temp, board, dic, row + 1, col);
        dfs(res, str + temp, board, dic, row - 1, col);
        dfs(res, str + temp, board, dic, row, col + 1);
        dfs(res, str + temp, board, dic, row, col - 1);
        board[row][col] = temp;
    }

    /* 121. Word Ladder II */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        Map<String, List<String>> neighbors = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        List<List<String>> res = new LinkedList<>();
        bfs(neighbors, distances, start, end, dict);
        dfs(neighbors, distances, res, new LinkedList<>(), start, end);
        return res;
    }
    private void dfs(Map<String, List<String>> neighbors, Map<String, Integer> distances,
                     List<List<String>> res, List<String> path,
                     String start, String end) {
        path.add(start);
        if (start.equals(end)) {
            res.add(new LinkedList<>(path));
        }
        else {
            List<String> nexts = neighbors.get(start);
            for (String next: nexts) {
                if (distances.get(next) == distances.get(start) + 1) {
                    dfs(neighbors, distances, res, path, next, end);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    private void bfs(Map<String, List<String>> neighbors, Map<String, Integer> distances,
                     String start, String end, Set<String> dict) {
        for (String str: dict) {
            neighbors.put(str, new LinkedList<>());
        }
        neighbors.put(start, new LinkedList<>());
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distances.put(start, 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(end))    break;
                List<String> nextStrs = findNexts(cur, end, dict);
                for (String next: nextStrs) {
                    neighbors.get(cur).add(next);
                    if (!distances.containsKey(next)) {
                        distances.put(next, distances.get(cur) + 1);
                        queue.offer(next);
                    }
                }
            }
        }
    }
    private List<String> findNexts(String str, String end, Set<String> dict) {
        List<String> res = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == str.charAt(i)) continue;
                String temp = str.substring(0, i) + j + str.substring(i + 1);
                if (dict.contains(temp) || temp.equals(end)) {
                    res.add(temp);
                }
            }
        }
        return res;
    }

    /* 829. Word Pattern II */
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        Map<Character, String> patToStr = new HashMap<>();
        Set<String> visitedPattern = new HashSet<>();
        return dfs(pattern, str, patToStr, visitedPattern);
    }
    public boolean dfs(String pattern, String str,
                       Map<Character, String> patToStr, Set<String> visitedPattern) {
        if (pattern.length() == 0) return str.length() == 0;
        char p = pattern.charAt(0);
        if (patToStr.containsKey(p)) {
            if (!str.startsWith(patToStr.get(p)))
                return false;
            else
                return dfs(pattern.substring(1), str.substring(patToStr.get(p).length()), patToStr, visitedPattern);
        }
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(0, i + 1);
            if (visitedPattern.contains(temp)) continue;
            patToStr.put(p, temp);
            visitedPattern.add(temp);
            if (dfs(pattern.substring(1), str.substring(i + 1), patToStr, visitedPattern))
                return true;
            patToStr.remove(p);
            visitedPattern.remove(temp);
        }
        return false;
    }
}
