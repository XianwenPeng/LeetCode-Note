package LeetCode.Topics;
import java.util.*;
import java.util.LinkedList;

public class StringProblems {

    public static void main(String[] args) {
        StringProblems sp = new StringProblems();
        /* 14. Longest Common Prefix */
        String[] temp14 = {"flower","flow","flight"};
        System.out.println(sp.longestCommonPrefix(temp14));
    }

    /* 71. Simplify Path */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] strs = path.split("/");
        for (String s: strs) {
            if (s.length() != 0 && !s.equals(".")) {
                if (s.equals("..")) {
                    if (!stack.isEmpty())   stack.pop();
                }
                else    stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s: new LinkedList<>(stack)) sb.append("/" + s);
        return sb.toString().length() == 0 ? "/" : sb.toString();
    }

    /* 58. Length of Last Word */
    public int lengthOfLastWord(String s) {
        int len = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                len = count > 0 ? count : len;
                count = 0;
            }
            else    count++;
        }
        len = count;
        return count == 0 ? len : count;
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
