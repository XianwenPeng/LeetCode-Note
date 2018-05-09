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
