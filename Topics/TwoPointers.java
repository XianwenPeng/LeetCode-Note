package LeetCode.Topics;

import java.util.Arrays;
import java.util.HashMap;

public class TwoPointers {

    public static void main(String[] args) {
        TwoPointers tp = new TwoPointers();

        /* 76. Minimum Window Substring */
        System.out.println(tp.minWindow("ADOBECODEBANC", "ABC"));
    }
    /* 76. Minimum Window Substring */
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE, end = 0, begin = 0, count = t.length();
        String ans = "";
        int[] cnt = new int[256];
        for (char c: t.toCharArray()) cnt[c]++;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (--cnt[c] >= 0)  count--;
            while (count == 0 && begin <= end) {
                if (min > end - begin) {
                    min = end - begin;
                    ans = s.substring(begin, end + 1);
                }
                c = s.charAt(begin);
                if (++cnt[c] > 0)   count++;
                begin++;
            }
            end++;
        }
        return ans;
    }
}
