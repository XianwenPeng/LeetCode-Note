package LeetCode.Topics.SecondRun;
import java.util.*;

public class PermutationGraphDFS {

    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new LinkedList<>();
        dfs(res, new LinkedList<>(), nums, new boolean[nums.length]);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) continue;
            list.add(nums[i]);
            visited[i] = true;
            dfs(res, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    /* 425. Letter Combinations of a Phone Number */
    public List<String> letterCombinations(String digits) {
        // write your code here
        String[] buttons = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) return res;
        dfs(digits, 0, buttons, res, new StringBuilder());
        return res;
    }
    private void dfs(String digits, int index, String[] buttons, List<String> res, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char[] chs = buttons[digits.charAt(index) - '0'].toCharArray();
        for (int i = 0; i < chs.length; i++) {
            sb.append(chs[i]);
            dfs(digits, index + 1, buttons, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /* 52. Next Permutation */
    public int[] nextPermutation(int[] nums) {
        // write your code here
        int lastIncrease = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                lastIncrease = i;
                break;
            }
        }
        if (lastIncrease == -1) {
            swapBetween(nums, 0, nums.length - 1);
            return nums;
        }
        int greaterPrev = lastIncrease + 1;
        for (int i = nums.length - 1; i > lastIncrease; i--) {
            if (nums[i] > nums[lastIncrease]) {
                greaterPrev = i;
                break;
            }
        }
        swap(nums, greaterPrev, lastIncrease);
        swapBetween(nums, lastIncrease + 1, nums.length - 1);
        return nums;
    }
    public void swapBetween(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /* 862. Next Closest Time */
    String res = "";
    int diff = Integer.MAX_VALUE;
    public String nextClosestTime(String time) {
        // write your code here
        Set<Integer> digits = new HashSet<>();
        for (int i = 0; i < time.length(); i++) {
            if (i == 2) continue;
            digits.add(time.charAt(i) - '0');
        }
        if (digits.size() == 1) return time;
        int min = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        dfs(new LinkedList<>(digits), 0, "", min);
        return res;
    }
    public void dfs(List<Integer> digits, int pos, String cur, int timeMin) {
        if (pos == 4) {
            int min = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(2));
            if (min == timeMin) return;
            int d = min - timeMin > 0 ? min - timeMin : 1440 + min - timeMin;
            if (d < diff) {
                diff = d;
                res = cur.substring(0, 2) + ":" + cur.substring(2);
            }
            return;
        }
        for (int i = 0; i < digits.size(); i ++) {
            if (pos == 0 && digits.get(i) > 2) continue;
            if (pos == 1 && Integer.parseInt(cur.substring(0, pos)) * 10 + digits.get(i) > 23)    continue;
            if (pos == 2 && digits.get(i) > 5) continue;
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59)    continue;
            dfs(digits, pos + 1, cur + digits.get(i), timeMin);
        }
    }

    /* 10. String Permutation II */
    public List<String> stringPermutation2(String str) {
        // write your code here
        List<String> list = new LinkedList<>();
        char[] chs = str.toCharArray();
        Arrays.sort(chs);
        dfs(chs, list, new StringBuilder(), new boolean[str.length()]);
        return list;
    }
    private void dfs(char[] chs, List<String> list, StringBuilder sb, boolean[] visited) {
        if (sb.length() == chs.length) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < chs.length; i++) {
            if (visited[i] || i > 0 && chs[i - 1] == chs[i] && !visited[i - 1])    continue;
            sb.append(chs[i]);
            visited[i] = true;
            dfs(chs, list, sb, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

//    /* 33. N-Queens Iterative */
//    public List<List<String>> solveNQueensIterative(int n) {
//        List<List<String>> list = new LinkedList<>();
//        Stack<Integer> stack = new Stack<>();
//
//    }

    /* 33. N-Queens */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> list = new LinkedList<>();
        dfs(n, list, new LinkedList<>());
        return list;
    }
    private void dfs(int n, List<List<String>> list, List<Integer> sublist) {
        if (sublist.size() == n) {
            list.add(generateResult(sublist, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(sublist, i))
                continue;
            sublist.add(i);
            dfs(n, list, sublist);
            sublist.remove(sublist.size() - 1);
        }
    }
    private boolean isValid(List<Integer> list, int curCol) {
        int row = list.size();
        for (int i = 0; i < row; i++) {
            if (list.get(i) == curCol)  return false;
            if (list.get(i) + i == curCol + row)    return false;
            if (list.get(i) - i == curCol - row)    return false;
        }
        return true;
    }
    private List<String> generateResult(List<Integer> sublist, int n) {
        List<String> res = new LinkedList<>();
        for (int num: sublist) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i++ < num) sb.append(".");
            sb.append("Q");
            while (i++ < n)   sb.append(".");
            res.add(sb.toString());
        }
        return res;
    }
}
