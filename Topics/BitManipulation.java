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
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((nums[j] >> i) & 1) == 1) {
                    sum ++;
                    sum %= 3;
                }
            }
            if (sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }

    /* 260. Single Number III */
    public int[] singleNumberIII(int[] nums) {
        int bit = 0;
        for (int i : nums) {
            bit ^= i;
        }
        bit &= -bit;
        int[] ans = new int[2];
        for (int i : nums) {
            if ((i & bit) == 0)   ans[0] ^= i;
            else    ans[1] ^= i;
        }
        return ans;
    }
}
