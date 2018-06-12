package LeetCode;

import java.util.*;
public class Meituan {

    /*
5 100
20 30 40 50 60

    public static void main (String[] args) {
        Scanner in = new Scanner (System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int seats = in.nextInt();
            int[] request = new int[n];
            for (int i = 0; i < n; i++) {
                request[i] = in.nextInt();
            }
            System.out.println(isPerfect(seats, request));
        }
    }
    public static String isPerfect(int seats, int[] requests) {
        boolean perfect = false;
        HashSet<Integer> set = new HashSet<>();
        int left = seats;

        for (int i = 0; i < requests.length; i++) {
            if (requests[i] == seats) {
                perfect = true;
                break;
            }
            for (int k = 0; k < requests.length; k++) {
                set.add(requests[k]);
            }
            System.out.println(set);
            left = seats - requests[i];
            set.remove(requests[i]);
            int index = i + 1;
            while (!set.isEmpty() && index < requests.length) {
                System.out.println(set + " " +left+" "+index);
                if (set.contains(left - requests[index])) {
                    perfect = true;
                    break;
                }
                set.remove(requests[index]);
                left -= requests[index++];
            }
            if (perfect) break;
//            left = seats - requests[i];
//            for (int j = i + 1; j < requests.length; j++) {
//                left -= requests[j];
//                if (requests[i] == left) {
//                    perfect = true;
//                    break;
//                }
//            }
        }

        if (perfect)    return "perfect";
        else    return "good";
    }*/

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int places = in.nextInt();
            int colors = in.nextInt();
            System.out.println(combine(places, colors));

        }
    }
    public static int combine(int n, int k){
        if (k == 1) return 1;
        LinkedList<Integer> list = new LinkedList<>();
        if (k > n)  return 0;
        if (n == k) return factorial(k);
//        int count = k;
//
//        for (int i = 1; i < n - k ; i ++) {
//            count *= k;
//        }
//        for (int i = k; i > 0 ; i --) {
//            count *= i;
//        }
//        int subSum = k;
//        for (int i = 1; i < n - k ; i ++) {
//            subSum *= k;
//        }
//        return (count + subSum) % 772235;
        int count = k;
//
        for (int i = 1; i < n ; i ++) {
            count *= k;
        }
        return count - k;
    }
    public static int factorial(int k) {
        int sum = 1;
        for (int i = k ; i > 0; i--) {
            sum *= i;
        }
        return sum % 772235;
    }


    /*
2
2 5 4 0 4
4 5 9 2 1
    */

    /*
    public static void main (String[] args) {
        Scanner in = new Scanner (System.in);

        while (in.hasNext()) {
            int n = in.nextInt();
            String trash = in.nextLine();
            int[][] data = new int[n][5];
            for (int i = 0;i < n; i++) {
                String[] arr = in.nextLine().split(" ");
                int index = 0;
                for (String num : arr) {
                    if (num != null && num != "" && num.length() != 0)
                        data[i][index++] = Integer.parseInt(num.trim());
                }
            }
            for (int i = 0; i < n; i++) {

                if (i != n - 1)
                    System.out.println(checkCoinLose(data[i]));
                else
                    System.out.print(checkCoinLose(data[i]));
            }
        }
    }

    public static int checkCoinLose(int[] data){
        int sum = 0;
        for (int i : data) {
            sum += i;
        }
        if (sum % 5 == 0 && sum != 0)   return sum / 5;
        else return -1;
    }
    /*
    public static boolean smallestSwap(String curWord, String target, HashSet<String> set,
                                    int index, List<String> list, List<String> ans) {
        System.out.println(curWord +" "+target+" "+list+" "+ans+" "+index+" "+set);
        if (curWord.equals(target)) {
            return true;
        }
        if (set.contains(curWord)) {
            set.remove(curWord);
            ans.remove(ans.size()-1);
            return false;
        }
        set.add(curWord);
        for (String str: list) {
            if (str != null && str.length() != 0 && countDiff(str, curWord) == 1 && !set.contains(str)) {
                ans.add(str);
                if(smallestSwap(str, target, set,index + 1, list, ans)) return true;
                ans.add(str);
            }
        }
        return false;
    }
    public static int countDiff(String s1, String s2) {
        HashSet<Character> set = new HashSet<>();
        char[] arr1 = new char[s1.length()];
        char[] arr2 = new char[s2.length()];
        List<Character> list2 = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            arr1[i] = s1.charAt(i);
        }
        for (int i = 0; i < s2.length(); i++) {
            arr2[i] = s2.charAt(i);
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) count++;
        }
        return count;
    }





    /*
    public static int minimumCannotForm(String nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            map.put(i, 0);
        }
        int ans = 0;
        for (char c: nums.toCharArray()) {
            map.put(c - '0', map.get(c - '0') + 1);
        }
        int min = Integer.MAX_VALUE;
        for (int count: map.values()) {
            min = Math.min(min, count);
        }
        int minAdd1 = Integer.MAX_VALUE;
        for (int count: map.values()) {
            if (count > min)     minAdd1 = Math.min(minAdd1, count);
        }

        HashMap<Integer, Integer> minMap = new HashMap<>();
        for (int key: map.keySet()) {
            if (map.get(key) == min) minMap.put(key, map.get(key));
        }
        HashMap<Integer, Integer> minAdd1Map = new HashMap<>();
        for (int key: map.keySet()) {
            if (map.get(key) == minAdd1) minAdd1Map.put(key, map.get(key));
        }
//        System.out.println(map+" "+minAdd1+" "+min+" "+minMap+" "+minAdd1Map);

        List<Integer> minList = new ArrayList<>(minMap.keySet());
        List<Integer> minAdd1List = new ArrayList<>(minAdd1Map.keySet());


        StringBuilder sb = new StringBuilder();
        if (minList.size() == 1 && map.get(minList.get(0)) == 0 && minAdd1List.size() == 1 && map.get((minAdd1List.get(0))) == 0){
            sb.append(10);
            return Integer.parseInt(sb.toString());
        }
        else if (minList.size() == 1 && map.get(minList.get(0)) == 0) {
            sb.append(minAdd1List.get(0));
            sb.append(minList.get(0));
            return Integer.parseInt(sb.toString());
        }
        else if (minList.size() == 1 && map.get(minList.get(0)) != 0) {
            sb.append(minAdd1List.get(0));
            for (int i = 0; i < minMap.get(minList.get(0)); i++){
                sb.append(minList.get(0));
            }
            sb.append(minList.get(0));
            return Integer.parseInt(sb.toString());
        }
        for (int i = 0; i < minMap.get(minList.get(0)); i++){
            sb.append(minAdd1List.get(0));
        }
        sb.append(minList.get(0));
        if (Integer.parseInt(sb.toString()) == 0 && minList.size() == 1)
            return minAdd1List.get(0);
        else if (Integer.parseInt(sb.toString()) == 0 && minList.size() != 1)
            return minList.get(1);
        return Integer.parseInt(sb.toString());

    }*/
}
