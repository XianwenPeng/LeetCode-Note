package LeetCode.Topics.SecondRun;
import java.util.*;

public class CombinationDFS {
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /* 153. Combination Sum II */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(num);
        dfs163(num, target, 0, new LinkedList<>(), list);
        return list;
    }
    private void dfs163(int[] nums, int target, int index,
                     List<Integer> subList, List<List<Integer>> list) {
        if (target < 0) return;
        if (target == 0) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1])    continue;
            subList.add(nums[i]);
            dfs163(nums, target - nums[i], i + 1, subList, list);
            subList.remove(subList.size() - 1);
        }
    }

    /* 135. Combination Sum */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, list, new LinkedList<>());
        return list;
    }
    private void dfs(int[] candidates, int target, int index,
                    List<List<Integer>> list, List<Integer> subList) {
        if (target < 0) return;
        if (target == 0) {
            list.add(new LinkedList<>(subList));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1])    continue;
            subList.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, list, subList);
            subList.remove(subList.size() - 1);
        }
    }


}
