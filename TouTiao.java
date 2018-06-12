package LeetCode;
import java.util.*;

/*
5 2
1 5 3 4 2

4 0
1 1 1 1

6 2
1 5 3 3 4 2

3 3 2
1
3
6

5 3 2
1 2 3 4 6
*/
public class TouTiao {
    /*
1
4 2 3 1
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {//注意while处理多个case
            int rows = in.nextInt();
            for (int i = 0; i < rows; i++) {
                int n = in.nextInt();
                int m = in.nextInt();
                int[] arr = new int[n];
                for (int j = 0; j < n; j++) {
                    arr[j] = in.nextInt();
                }
                System.out.println(p3(m, 0, 0, arr, 0, new boolean[m]));
            }
//        }
    }
    public static int p3 (int m, int pstart, int astart, int[] arr,
                          int round, boolean[] failedPlayers) {
        if (round == m - 1) {
            for (int i = 0; i < failedPlayers.length; i++) {
                if (!failedPlayers[i])  return i;
            }
        }
        int fail = (pstart + arr[astart] - 1) % m;
        failedPlayers[fail] = true;
        pstart = (fail + 1) % m;
        astart = (astart + 1) % arr.length;
        round++;
//        System.out.println(round + " "+ pstart+" " + astart);
        return p3(m, pstart, astart, arr, round, failedPlayers);
    }

    /*
6 2
a
bc
d
eba
ebc
f

ebcc
ebd

6 3
a
bc
d
eba
ebc
f

yuklx
bcc
ff

7 3
a
bc
d
eba
ebc
f
c

ebcc
asd
ebd
     */
    /*
    public class P2Object {
        int m;
        int n;
        List<String> dict;
        List<String> quest;
        public P2Object (int m, int n, List<String> dict, List<String> quest) {
            this.m = m;
            this.n = n;
            this.dict = dict;
            this.quest = quest;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        TouTiao mo = new TouTiao();
//        Main mo = new Main();
        while (in.hasNext()) {//注意while处理多个case
            List<P2Object> res = new LinkedList<>();
            int m = in.nextInt();
            int n = in.nextInt();
            List<String> list = new LinkedList<>();
            List<String> ans = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                list.add(in.next());
            }
            for (int i = 0; i < n; i++) {
                ans.add(in.next());
            }

//            System.out.println(m+" "+n+" "+list+" "+ans);
            P2Object ob = mo.create(m, n, list, ans);
            res.add(ob);
            for (String s: mo.p2(res))   System.out.println(s);
            in.hasNext();
        }

    }
    public P2Object create(int m, int n, List<String> dict, List<String> quest) {
        return new P2Object(m, n, dict, quest);
    }
    public List<String> p2 (List<P2Object> l) {
        List<String> res = new LinkedList<>();
        for (P2Object o: l) {
            List<String> list = o.dict;
            List<String> ans = o.quest;
            for (int i = 0; i < ans.size(); i++) {
                String s = ans.get(i);
                boolean found = false;
                for (String pre : list) {
                    if (s.indexOf(pre) == 0) {
                        res.add("1");
                        found = true;
                    }
                }
                if (!found) res.add("-1");
            }
        }
        return res;
    }
    /*
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list = new LinkedList<>();
        while (in.hasNext()) {//注意while处理多个case
            String s = in.nextLine();
            list.add(s);
            if (s.equals("") || s == null) System.out.println(p1(list));
        }
        System.out.println(p1(list));
    }


    public static int p1 (List<String> list) {
        int count = 0;
        for (String s: list) {
            while (s.indexOf("\"") != -1) {
                int first = s.indexOf("\"");
                String sub = s.substring(first + 1);
                int second = sub.indexOf("\"");
                String rest = s.substring(first + second + 1);
                int third = rest.indexOf("\"");
                if (third == -1) {
                    count += countHelper(rest, "");
                    s = rest;
                    break;
                }
                else {
                    String temp = rest.substring(third + 1);
                    int forth = temp.indexOf("\"");
                    count += countHelper(rest.substring(0, third), );
                    s = rest.substring(forth + 1);
                    break;
                }
            }
            int comma = s.indexOf(";");
            if (comma != -1) {
                s = s.substring(comma + 1);
            }
            count += countHelper(s, "//") > 0 ? 1 : 0;
        }
        return count;
    }
    public static int countHelper(String s, String target) {
        int count = 0;
        while (s.indexOf(target) != -1) {
            count++;
            s = s.substring(s.indexOf(target) + 1);
        }
        return count;
    }*/

    public static int jump(int n, int k, int h, int[] arr) {
        int curHeight = 0;
        int j = arr.length - 1;
        boolean firsttime = false;
        int lastPlate;
        for (int i = 0; i < k; i++) {
            int temp = curHeight;
            while (!firsttime && j >= 0 && temp + h >= arr[j]) {
                j--;
                if(j == 0)  firsttime = true;
            }
            if(firsttime == true) {
                j = 0;
            }
            lastPlate = j;
            curHeight = curHeight + (arr[j] - curHeight) * 2;
            System.out.println(Arrays.toString(arr)+" "+ curHeight +" "+arr[j]+" "+j);
        }
        return curHeight;
    }

    /*public static int prob2(int n) {
        boolean even = n % 2 == 0 ? true: false;
        String s = "a", m = s;
        String[] arr = new String[2];
        arr[0] = s;
        arr[1] = m;
        int count = 0;
        int ans = 0;
        while (count < n) {
            if (even) {
                String[] opt1 = option1(arr);
                if (opt1[0].length() > n) {
                    arr = option2(arr);
                    count = arr[0].length();
                }
                else {
                    arr = option1(arr);
                    count = arr[0].length();
//                    System.out.println("opt1"+Arrays.toString(arr));
                }
                ans++;
            }
            else {
                arr = option2(arr);
                count = arr[0].length();
                ans++;
            }
        }
        return ans;
    }
    public static String[] option1(String[] arr) {
        String[] ans = new String[2];
        ans[0] = arr[0];
        ans[1] = arr[1];

        ans[1] = ans[0];
        ans[0] = ans[0] + ans[0];
        System.out.println("opt1"+Arrays.toString(ans));
        return ans;
    }
    public static String[] option2(String [] arr) {
        String[] ans = new String[2];
        ans[0] = arr[0];
        ans[1] = arr[1];

        ans[0] = ans[0] + ans[1];
        System.out.println("opt2"+Arrays.toString(ans));
//        System.out.println(Arrays.toString(ans));
        return ans;
    }*/

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {//注意while处理多个case
//            int n = in.nextInt();
//            int diff = in.nextInt();
//            int[] arr = new int[n];
//            for (int i = 0; i < n; i++) {
//                arr[i] = in.nextInt();
//            }
//            System.out.println(difference(n, diff, arr));
//        }
//    }
//    public static int difference(int n, int diff, int[] arr) {
//        int count = 0;
//        Arrays.sort(arr);
//        Set<Integer> set = new HashSet<>();
//        List<Integer> list = new ArrayList<>();
//        for (int i : arr) {
//            list.add(i);
//        }
//        for (int i : list) {
//            if (!set.contains(i) && (list.contains(i + diff))) {
//                count++;
//                set.add(i);
//            }
//        }
//        return count;
//    }
}