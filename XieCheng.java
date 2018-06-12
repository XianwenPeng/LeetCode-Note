package LeetCode;
import java.util.*;

/*
1 4 7
2 5 8
3 6 9
*/
public class XieCheng {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        while (in.hasNext()) {
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int adult = in.nextInt();
                int child = in.nextInt();
                int price = in.nextInt();
                List<Integer> temp = new ArrayList<>();
                temp.add(adult);
                temp.add(child);
                temp.add(price);
                map.put(i, temp);
            }
            int adult = in.nextInt();
            int child = in.nextInt();
            int night = in.nextInt();
            System.out.println(pro3(map, adult, child, night));
        }
    }
    public static String pro3(HashMap<Integer, List<Integer>> map, int a, int c, int n) {
        return null;
    }
    public static int[][] rotateMatrix(int[][] matrix, int n) {
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = matrix[n-1-j][i];
            }
        }
        return ans;
    }
    public static List<Integer> helper(String str, List<Integer> list) {
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && temp != "" && temp != "　") {
                temp = temp.trim().replaceAll(" ", "");
                if (temp.length() != 0 && temp != "" && temp != "\n") list.add(Integer.parseInt(temp));
                temp = "";
            }
            else temp += str.charAt(i);
        }
        temp = temp.trim().replaceAll(" ", "");
        if (temp.length() != 0 && temp != "" && temp != "\n") list.add(Integer.parseInt(temp));
        return list;
    }
    public static int[] moveZeros(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0){
                arr[index] = arr[i];
                index++;
            }
        }
        for (int i = index; i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

}
