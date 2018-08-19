package LeetCode.Topics.SecondRun;
import java.util.*;

public class FourWordsAndMisc {

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        List<List<String>> res = new LinkedList<>();
        Map<String, List<String>> nexts = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        bfs(start, end, dict, nexts, distances);
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
