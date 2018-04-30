package LeetCode.Topics;

import java.util.*;
public class BitManipulation {
    public static void main(String[] args) {
        BitManipulation bm = new BitManipulation();
        /* 136. Single Number */
        int[] arr136 = {1,2,1,2,4};
        System.out.println(bm.singleNumber(arr136));
    }

    /* 136. Single Number */
    public int singleNumber(int[] nums) {
        int bit = 0;
        for (int i : nums) {
            bit ^= i;
            System.out.println(bit);
        }
        return bit;
    }

    /* 137. Single Number II */
    public int singleNumberII(int[] nums) {

    }
}
