package LeetCode;

import java.util.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {//注意while处理多个case
        while (in.hasNext()) {
            int n = in.nextInt();
            String trash = in.nextLine();
            for (int i = 0; i < n; i++) {
                String numsStr = in.nextLine();
                String[] temp = numsStr.trim().split(" ");

                double[] nums = new double[temp.length - 1];
                int k = Integer.parseInt(temp[0]);

                for (int j = 1; j < temp.length; j++) {
                    nums[j - 1] = Integer.parseInt(temp[j]);
                }
                kthSmall(k, nums);
                System.out.println();
            }
        }
        /* ForUsAll */
        int[] temp = {6,1,4,6,3,2,7,4,5};
//        System.out.println(dp.solution1(temp, 4,3));
        String S= "John Doe, Peter Parker, Mary Jane Watson-Parker, James Doe, John Elvis Doe, Jane Doe, Penny Parker";
        String C = "example";
//        System.out.println(dp.solution(S, C));
    }

    /* ForUsAll */
    public String solution(String S, String C) {
        if (S == null || C == null || S.length() == 0 || C.length() == 0)   return null;
        String[] names = S.split(",");
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < names.length; i ++) {
            String[] temp = names[i].toLowerCase().trim().split(" ");
            StringBuilder sb = new StringBuilder();
            if (temp.length == 3) {
                sb.append(temp[2].replaceAll("-", ""));
                sb.append("_"+temp[0].charAt(0)+"_"+temp[1].charAt(0));
            }
            else if (temp.length == 2) {
                sb.append(temp[1].replaceAll("-", ""));
                sb.append("_"+temp[0].charAt(0));
            }
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            ans.append(sb.toString());
            if (map.get(sb.toString()) > 1)
                ans.append(map.get(sb.toString()));
            ans.append("@"+C.toLowerCase()+".com");
            if (i != names.length - 1)  ans.append(", ");
        }
        return ans.toString();
    }


    public int solution1(int[] A, int K, int L) {
        if (A.length < K + L) return  -1;
        int longer = K >= L ? K : L, shorter = K < L ? K : L;
        int max = 0;
        for (int i = 0; i <= A.length - longer; i++) {
            int[] pos = new int[2];
            int sum = solutionHelper(A, longer, i, i + K, pos);
            sum += Math.max(solutionHelper(A, shorter, pos[1] + 1, A.length - 1, new int[2]),
                    solutionHelper(A, shorter, 0, pos[0] - 1, new int[2]));
            max = Math.max(max, sum);
        }
        return max;
    }
    public int solutionHelper (int[] arr, int k, int left, int right, int[] pos) {
        if (left < 0 || right > arr.length - 1 || arr == null || arr.length == 0 || right - left + 1 < k) return 0;
        int max = 0, curCount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        pos[0] = left;
        pos[1] = right;
        for (int i = left; i <= right; i++) {
            map.put(i, arr[i]);
            curCount += arr[i];
            if (map.size() >= k) {
                if (curCount > max) {
                    max = curCount;
                    pos[0] = i - k + 1;
                    pos[1] = i;
                }
                curCount -= map.get(i - k + 1);
                map.remove(i - k + 1);
            }
        }
        return max;
    }

    /*
3
3 1 2 3 5
4 1 2 3 5
5 1 2 3 5 7 8
     */
    public static void kthSmall(int k, double[] nums){
        int left = 0, right = nums.length - 1;
        int nextl = left + 1, nextr = nums.length - 1;
        int rank = 1, m = left, n = right;
        double num = nums[left] / nums[right], next = nums[nextl] / nums[nextr];
        while (rank != k) {
//            System.out.println(Arrays.toString(nums) +" "+nums[left] +" "+ nums[right] +" "+nums[nextl] +" "+ nums[nextr] );
            if (nums[left] / nums[right - 1] <= next) {
                m = left;
                n = right - 1;
                right--;
                rank++;
            }
            else {
                m = nextl;
                n = nextr;
                nextl++;
                rank++;
            }
        }
        System.out.print((int)nums[m]+" "+(int)nums[n]);
    }
    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {//注意while处理多个case
            String steps = in.nextLine();
            int n = in.nextInt();
            int[][] mat = new int[n][4];
            for (int i = 0; i < n; i++) {
                mat[i][0] = in.nextInt();
                mat[i][1] = in.nextInt();
                mat[i][2] = in.nextInt();
                mat[i][3] = in.nextInt();
            }
            countSteps(steps, mat);
//        }
    }
    /*
uuurrdddddl
4
5 6 3 3
5 6 4 2
6 6 4 2
10 10 5 5
    */
    public static void countSteps(String steps, int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            int row = mat[i][0], col = mat[i][1], posr = mat[i][2], posc = mat[i][3];
            int count = 0;
//            System.out.println(steps + " " + row + " " + col + " " + posr + " " + posc);
            int index = 0;
            char step = steps.charAt(index++);
            int countStep = 1;
            boolean addOne = false;
            while (index < steps.length()) {
                if (steps.charAt(index++) != step) {
                    if (step == 'u') posr -= countStep;
                    else if (step == 'd') posr+=countStep;
                    else if (step == 'l') posc-=countStep;
                    else if (step == 'r') posc+=countStep;
                    count += countStep;
                    addOne = index >= steps.length() ? true : false;
                    if (posr < 1 || posr > row || posc < 1 || posc > col ) {
                        System.out.println(index +" " + steps + " " + row + " " + col + " " + posr + " " + posc+" "+count+" "+countStep);
                        if (posr < 1) count -= Math.abs(0 - posr);
                        else if (posr > row) count -= Math.abs(posr - (row + 1));
                        else if (posc < 1) count -= Math.abs(0 - posc);
                        else if (posc > col) count -= Math.abs(posc - (col + 1));
                        addOne = false;
                        break;
                    }
                    else {
                        countStep = 1;
                        if (index >= steps.length())    break;
                        step = steps.charAt(index);
                    }
                }
                else {
                    countStep++;
                }
            }
            if (addOne){
                count++;
            }
            System.out.println(count);
        }
    }
    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int totalRow = in.nextInt();
            for (int i = 0; i < totalRow; i++) {
                int n = in.nextInt();
                int[] nums = new int[n];
                for (int j = 0; j < n; j++) {
                    nums[j] = in.nextInt();
                }
                System.out.println(minPeriod(nums));
            }

        }
    }
    public static int minPeriod(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums[nums.length - 1]; j++) {
                if (i + j >= nums[0] && nums.length - 1 + j <= nums[nums.length - 1])
                    return j;
            }
        }
        return nums[nums.length - 1];
    }*/

}