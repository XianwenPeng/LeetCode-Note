package LeetCode.Topics.SecondRun;
import java.util.*;

public class FirstLessonProblems {
    public static void main(String[] args) {

    }

    /* 200. Longest Palindromic Substring */
    public String longestPalindrome200(String s) {
        // write your code here
        int start = 0, len = 0;
        for (int i = 0; i < s.length(); i++) {
            int temp = getPalindrome(s, i, i);
            if (temp > len) {
                start = i - temp / 2;
                len = temp;
            }
            temp = getPalindrome(s, i, i + 1);
            if (temp > len) {
                start = i - temp / 2 + 1;
                len = temp;
            }
        }
        return s.substring(start, start + len);
    }
    public int getPalindrome(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            len += left == right ? 1: 2;
            left --;
            right ++;
        }
        return len;
    }

    /* 13. Implement strStr() */
    public int strStr(String source, String target) {
        // write your code here
        if (source == null || target == null)   return -1;
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            int j = 0;
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j))   break;
            }
            if (j == target.length())   return i;
        }
        return -1;
    }

    /* 415. Valid Palindrome */
    public boolean isPalindrome(String s) {
        // write your code here
        s = s.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        int j = s.length() - 1;
        for (int i = 0; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    /* 627. Longest Palindrome */
    public int longestPalindrome(String s) {
        // write your code here
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                set.remove(c);
                count++;
            }
            else {
                set.add(c);
            }
        }
        if (set.isEmpty())  return count * 2;
        return count * 2 + 1;
    }
}
