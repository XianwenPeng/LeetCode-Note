package LeetCode.Topics;

import java.util.*;
import java.util.LinkedList;

public class HashTable {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main (String[] args) {
        HashTable ht = new HashTable();

    }

    /* 30. Substring with Concatenation of All Words */
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        if (s.length() == 0 || words.length == 0)   return list;
        for (String str: words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int len = words[0].length(), n = words.length, slen = s.length();
        for (int i = 0; i <= slen - n * len; i++) {
            HashMap<String, Integer> temp = new HashMap<>();
            int j = 0;
            while (j < n) {
                String sub = s.substring(i + j * len, i + (j + 1) * len);
                if (map.containsKey(sub)) {
                    temp.put(sub, temp.getOrDefault(sub, 0) + 1);
                    if (temp.get(sub) > map.get(sub))   break;
                }
                else    break;
                j++;
            }
            if (j == n) list.add(i);
        }
        return list;
    }

    /* 288. Unique Word Abbreviation */
    class ValidWordAbbr {
        HashMap<String, String> dict;
        public ValidWordAbbr(String[] dictionary) {
            dict = new HashMap<>();
            for (String str: dictionary) {
                if (str.length() <= 2)  continue;
                StringBuilder sb = new StringBuilder();
                sb.append(""+ str.charAt(0) + (str.length() - 2) + str.charAt(str.length() - 1));
                if (dict.containsKey(sb.toString()))    dict.put(sb.toString(), "");
                else    dict.put(sb.toString(), str);
            }
        }

        public boolean isUnique(String word) {
            if (word.length() <= 2) return true;
            StringBuilder sb = new StringBuilder();
            sb.append(""+ word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1));
            return !dict.containsKey(sb.toString()) || dict.get(sb.toString()).equals(word);
        }
    }


}
