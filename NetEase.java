package LeetCode;
import java.util.*;

public class NetEase {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int nRow = sc.nextInt();
//        HashMap<String, Integer> map = new HashMap<>();
//        for (int i = 0; i < nRow; i++) {
//            int nCol = sc.nextInt();
//            for (int j = 0; j < nCol; j++) {
//                String str = sc.next();
//                if (mapContains(str, map)) map.put(str, map.get(str) + 1);
//                else map.put(str, 1);
//            }
//        }
//        int[] arr = new int[map.size()];
//        List<Integer> list = new ArrayList<>(map.values());
//        list.sort((i1, i2) -> Integer.compare(i1, i2));
//        Collections.reverse(list);
//        int outputCount = sc.nextInt();
//        if (outputCount != 0) {
//            int[] strarr = new int[list.get(0) + 1];
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i) > 0) strarr[list.get(i)]++;
//            }
//
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < outputCount; i++) {
//                String str = "";
//                if (strarr[list.get(i)] > 1) str = findTopKey(list.get(i), map);
//                else str = findKey(list.get(i), map);
//
//                if (str != null) {
//                    sb.append(str);
//                    sb.append(" " + map.get(str));
//                    if (i != outputCount - 1) sb.append("\n");
//                }
//
//            }
//            System.out.println(sb.toString());
//        }
//
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        int start = sc.nextInt();
        int end = sc.nextInt();
        int cost = 0;
        int prev = start;

        System.out.print(smallCost(x, y, z, start, end, end - start, 0));
    }
    public static int smallCost(int x, int y, int z, int start, int end, int left, int cost) {
        if (left == 0)  return cost;
        if (left > 0) {
            if (left == 2)  return cost + x;

            int startTimes = start / 2;
            int yxTimes = y / x;


            if (startTimes > 2 && yxTimes < startTimes) {
                start += 2;
                left -= 2;
                cost += x;
            }
            else if (startTimes > 2 && yxTimes > startTimes) {
                start *= 2;
                left /= 2;
                cost += y;
            }
            else{
                start += 2;
                left -= 2;
                cost += x;
            }
            System.out.println(startTimes +" " +yxTimes);
            return smallCost(x, y, z, start, end, left, cost);
        }
        else {
            if (left == -2)  return cost + z;
            start -= 2;
            left -= 2;
            cost += z;
            return smallCost(x, y, z, start, end, left, cost);
        }
    }

    public static boolean mapContains(String str, HashMap<String, Integer> map) {
        for (String s: map.keySet()) {
            if (s.equals(str))  return true;
        }
        return false;
    }
    public static String findKey(int value, HashMap<String, Integer> map) {
        for (String s: map.keySet()) {
            if (map.get(s) == value) {
                return s;
            }
        }
        return null;
    }
    public static String findTopKey(int value, HashMap<String, Integer> map) {
        List<String> strList = new ArrayList<>();
        for (String s: map.keySet()) {
            if (map.get(s) == value) {
                strList.add(s);
            }
        }
        strList.sort((s1,s2) -> s1.compareTo(s2));
        return strList.get(0);
    }
}
