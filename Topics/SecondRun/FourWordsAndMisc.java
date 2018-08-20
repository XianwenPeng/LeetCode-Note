package LeetCode.Topics.SecondRun;
import java.util.*;

public class FourWordsAndMisc {

    public static void main(String[] args) {
        FourWordsAndMisc fw = new FourWordsAndMisc();
        /* 121. Word Ladder II */
        String[] words = {"hot","dot","dog","lot","log"};
        System.out.println(fw.findLadders("hit", "cog", new HashSet<>(Arrays.asList(words))));
    }

    /* 322. Coin Change */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin: coins) {
                if (i - coin >= 0 && dp[i - coin] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin]);
                }
            }
            dp[i] = dp[i] == Integer.MAX_VALUE ? -1 : dp[i] + 1;
        }
        return dp[amount];
    }

    /* 33. Search in Rotated Sorted Array */
    public int search(int[] nums, int target) {
        if (nums.length == 0)   return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid;
                }
                else {
                    right = mid;
                }
            }
            else {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid;
                }
                else {
                    left = mid;
                }
            }
        }
        if (nums[left] == target)   return left;
        else if (nums[right] == target)  return right;
        return -1;
    }

    /* 829. Word Pattern II */
    public boolean wordPatternMatch(String pattern, String str) {
        return dfs(pattern, str, 0, 0, new HashMap<>(), new HashSet<>());
    }
    public boolean dfs(String pattern, String str, int indexP, int indexS,
                       Map<Character, String> patToStr, Set<String> visited) {
        if (indexP == pattern.length()) return indexS == str.length();
        if (indexS == str.length()) return indexP == pattern.length();
        char p = pattern.charAt(indexP);
        if (patToStr.containsKey(p)) {
            String temp = str.substring(indexS);
            String pat = patToStr.get(p);
            if (temp.startsWith(pat))
                return dfs(pattern, str, indexP + 1, indexS + pat.length(), patToStr, visited);
            else
                return false;
        }
        for (int i = indexS; i < str.length(); i++) {
            String s = str.substring(indexS, i + 1);
            if (visited.contains(s))    continue;
            visited.add(s);
            patToStr.put(p, s);
            if (dfs(pattern, str, indexP + 1, indexS + s.length(), patToStr, visited))
                return true;
            visited.remove(s);
            patToStr.remove(p);
        }
        return false;
    }

    /* 132. Word Search II */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        Set<String> res = new HashSet<>();
        TrieNode root = new TrieNode();
        for (String word: words) {
            insert(root, word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return new LinkedList<>(res);
    }
    private void dfs(char[][] board, int i, int j, TrieNode root, Set<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] == '-'
                || root.nodes[board[i][j] - 'a'] == null)    return;
        root = root.nodes[board[i][j] - 'a'];
        if (root.wordEnd)   res.add(root.word);
        char backup = board[i][j];
        board[i][j] = '-';
        dfs(board, i + 1, j, root, res);
        dfs(board, i - 1, j, root, res);
        dfs(board, i, j + 1, root, res);
        dfs(board, i, j - 1, root, res);
        board[i][j] = backup;
    }
    private void insert(TrieNode root, String word) {
        TrieNode node = root;
        char[] chs = word.toCharArray();
        for (char ch : chs) {
            if (node.nodes[ch - 'a'] == null) node.nodes[ch - 'a'] = new TrieNode();
            node = node.nodes[ch - 'a'];
        }
        node.wordEnd = true;
        node.word = word;
    }
    private class TrieNode {
        TrieNode[] nodes;
        boolean wordEnd;
        String word;
        public TrieNode() {
            nodes = new TrieNode[26];
            wordEnd = false;
        }
    }

    /* 582. Word Break II */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // write your code here
        List<String> res = new LinkedList<>();
        boolean[] valid = new boolean[s.length()];
        Arrays.fill(valid, true);
        dfsWordBreak(s, wordDict, res, new StringBuilder(), 0, valid);
        return res;
    }
    private boolean dfsWordBreak(String s, Set<String> wordDict, List<String> res,
                                 StringBuilder sb, int index, boolean[] valid) {
        if (index == s.length()) {
            res.add(sb.toString().trim());
            return true;
        }
        if (!valid[index])  return false;
        boolean indexValid = false;
        for (int i = index; i < s.length(); i++) {
            String str = s.substring(index, i + 1);
            if (!wordDict.contains(str))    continue;
            sb.append(str + " ");
            indexValid = dfsWordBreak(s, wordDict, res, sb, i + 1, valid);
            sb.delete(sb.length() - str.length() - 1, sb.length());
        }
        valid[index] = indexValid;
        return indexValid;
    }

    /* 121. Word Ladder II */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> nexts = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        bfs(start, end, dict, nexts, distances);
        System.out.println(nexts);
        dfs(start, end, res, new LinkedList<>(), nexts, distances);
        return res;
    }
    public void bfs(String start, String end, Set<String> dict,
                    Map<String, List<String>> nexts, Map<String, Integer> distances) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distances.put(start, 0);
        dict.add(end);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (!nexts.containsKey(cur))
                nexts.put(cur, new LinkedList<>());
            for (int i = 0; i < cur.length(); i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    String next = cur.substring(0, i) + j + cur.substring(i + 1);
                    if (!dict.contains(next) || next.equals(cur))   continue;
                    nexts.get(cur).add(next);
                    if (!distances.containsKey(next)) {
                        distances.put(next, distances.get(cur) + 1);
                        queue.offer(next);
                    }
                }
            }
        }
    }
    public void dfs(String start, String end,
                    List<List<String>> res, List<String> list,
                    Map<String, List<String>> nexts, Map<String, Integer> distances) {
        if (start.equals(end)) {
            res.add(new LinkedList<>(list));
            return;
        }
        if (list.size() == 0)   list.add(start);
        for (String next: nexts.get(start)) {
            if (distances.get(next) == distances.get(start) + 1) {
                list.add(next);
                dfs(next, end, res, list, nexts, distances);
                list.remove(list.size() - 1);
            }
        }
    }

}
