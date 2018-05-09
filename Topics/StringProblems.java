package LeetCode.Topics;

public class StringProblems {

    public static void main(String[] args) {
        StringProblems sp = new StringProblems();
        /* 14. Longest Common Prefix */
        String[] temp14 = {"flower","flow","flight"};
        System.out.println(sp.longestCommonPrefix(temp14));
    }
    /* 14. Longest Common Prefix */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1)   return "";
        else if (strs.length == 1)  return strs[0];
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j < pre.length(); j++) {
                if (j >= strs[i].length()) {
                    pre = strs[i];
                }
                else if (pre.charAt(j) != strs[i].charAt(j))
                    pre = pre.substring(0, j);
            }
        }
        return pre;
    }
}
