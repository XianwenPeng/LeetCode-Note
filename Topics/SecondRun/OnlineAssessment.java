package LeetCode.Topics.SecondRun;

import LeetCode.Topics.Array;

import java.util.*;
//import org.apache.http.client.utils.URLEncodedUtils;

public class OnlineAssessment {
    public static void main(String [] args) {
        OnlineAssessment oa = new OnlineAssessment();
        System.out.println(oa.sortStrArr("how can I sort this"));
        String[] strs = {"Dan XII", "Lucy V", "Dan V"};
        oa.sortStrArr(strs);
        int[] A = {3,5,7,9,3,7,2,23,35,133};
        int[] B = {2,1,8,9,25};
        System.out.println(Arrays.toString(oa.countAinB(A, B)));
        List<String> test = new LinkedList<>();
        test.add("API:\n" +
                "amount=1000&merchant=123456789");
        test.add("API:\n" +
                "amount=800&merchant=123456789&destination[account]=112211&destination[amount]=622");
        test.add("BAL: merchant=123456789");
        test.add("BAL: merchant=112211");
        System.out.println(oa.balance(test));

        int[] movies = {9, -1, -2, 5, 6};
        System.out.println(oa.movieRatesII(movies));
    }

    public int movieRates(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        if (nums.length == 1)   return nums[0];
        int skip = 0, notSkip = 0;
        for (int i = 0; i < nums.length; i++) {
            int curCount = Math.max(skip, notSkip) + nums[i];
            skip = notSkip;
            notSkip = curCount;
        }
        return Math.max(skip, notSkip);
    }

    public int movieRatesII(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        if (nums.length == 1)   return nums[0];
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2]) + nums[i - 1];
        }
        System.out.println(Arrays.toString(dp));
        return Math.max(dp[dp.length - 1], dp[dp.length - 2]);
    }

    /*
   API:
amount=1000&merchant=123456789&destination[account]=111111&destination[
amount]=877
API:
amount=800&merchant=123456789&destination[account]=112211&destination[a
mount]=622
BAL: merchant=123456789
BAL: merchant=112211
*/
    static class Transaction {
        Integer merchant;
        Double amount;
        Integer destAccount;
        Double destAmount;
        public Transaction(String api) {
            String[] splits = api.split("&");
            for (String s: splits) {
                String[] substr = s.split("=");
                if (substr[0].equals("amount"))  amount = Double.parseDouble(substr[1]);
                else if (substr[0].equals("merchant"))  merchant = Integer.parseInt(substr[1]);
                else if (substr[0].equals("destination[account]"))  destAccount = Integer.parseInt(substr[1]);
                else if (substr[0].equals("destination[amount]"))  destAmount = Double.parseDouble(substr[1]);
            }
        }
    }
    static List<Integer> balance(List<String> lines) {
        Map<Integer, Double> map = new HashMap<>();
        List<Integer> res = new LinkedList<>();
//        try {
            for (String str : lines) {
                String[] splits = str.split(":");
//                String uri = "?" + splits[1];

                if (splits[0].equals("API")) {
                    System.out.println(splits[1].trim());
                    Transaction transaction = new Transaction(splits[1].trim());
                    if (transaction.destAccount != null) {
                        double remToMerchant = round(transaction.amount - transaction.amount * 0.029 - 30 - transaction.destAmount, 2);
                        map.put(transaction.merchant, map.getOrDefault(transaction.merchant, 0.0) + remToMerchant);
                        map.put(transaction.destAccount, map.getOrDefault(transaction.destAccount, 0.0) + transaction.destAmount);
                        System.out.println(remToMerchant);
                    } else {
                        double remToMerchant = round(transaction.amount - transaction.amount * 0.029 - 30, 2);
                        map.put(transaction.merchant, map.getOrDefault(transaction.merchant, 0.0) + remToMerchant);
                        System.out.println(remToMerchant);
                    }

//                    List<NameValuePair> uris = URLEncodedUtils.parse(splits);
//                    double rem = 0, toDest = 0;
//                    for (NameValuePair item : uris) {
//                        if (item.getName().equals("destination"))
//                    }
                } else if (splits[0].equals("BAL")) {
                    String[] temp = splits[1].trim().split("=");
                    res.add((int)round(map.get(Integer.parseInt(temp[1])), 0));
                }
            }
//        } catch (Exception e) {
//
//        }
        return res;
    }
    static double round(double num, int pos) {
        double factor = Math.pow(10, pos);
        num = Math.round(num * factor);
        return num / factor;
    }
//    private Map<String, String> decodeExtras(String extras) {
//        Map<String, String> results = new HashMap<String, String>();
//        try {
//            URI rawExtras = new URI("?" + extras);
//            List<NameValuePair> extraList = URLEncodedUtils.parse(rawExtras, "UTF-8");
//            for (NameValuePair item : extraList) {
//                String name = item.getName();
//                int i = 0;
//                while (results.containsKey(name)) {
//                    name = item.getName() + ++i;
//                }
//                results.put(name, item.getValue());
//            }
//        } catch (URISyntaxException e) {
//            Log.w(TAG, "Invalid syntax error while decoding extras data from server.");
//        }
//        return results;
//    }

    public String sortStrArr(String str) {
        String[] splits = str.split("\\W+");
        List<String> strs = Arrays.asList(splits);
        Collections.sort(strs, (s1, s2) -> {
            int i = 0, j = 0;
            while (i < s1.length() && j < s2.length()) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    i++;
                    j++;
                }
                return s1.charAt(i) - s2.charAt(j);
            }
            return i >= s1.length() ? -1 : 1;
        });
        System.out.println(strs);
        return strs.get(0);
    }

    public void sortStrArr(String[] strings) {
        List<String> strs = Arrays.asList(strings);
        Collections.sort(strs, (s1, s2) -> {
            String[] name1 = s1.split("\\W+");
            String[] name2 = s2.split("\\W+");
            if (name1[0].equals(name2[0])) {
                return romanToNum(name1[1]) - romanToNum(name2[1]);
            }
            else {
                int i = 0, j = 0;
                while (i < name1[0].length() && j < name2[0].length()) {
                    if (name1[0].charAt(i) == name2[0].charAt(j)) {
                        i++;
                        j++;
                    }
                    return name1[0].charAt(i) - name2[0].charAt(j);
                }
                return i >= name1[0].length() ? -1 : 1;
            }
        });
        System.out.println(strs);
    }
    private int romanToNum(String roman) {
        int res = 0;
        for (int i = 0; i < roman.length(); i++) {
            int r1 = valueOf(roman.charAt(i));
            if (i + 1 < roman.length()) {
                int r2 = valueOf(roman.charAt(i + 1));
                if (r1 >= r2) {
                    res += r1;
                }
                else {
                    res += r2 - r1;
                    i++;
                }
            }
            else {
                res += r1;
                i++;
            }
        }
        return res;
    }
    private int valueOf(char roman) {
        if (roman == 'I')   return 1;
        if (roman == 'V')   return 5;
        if (roman == 'X')   return 10;
        if (roman == 'L')   return 50;
        if (roman == 'C')   return 100;
        if (roman == 'D')   return 500;
        if (roman == 'M')   return 1000;
        return -1;
    }

    public int[] countAinB(int[] A, int[] B) {
        Arrays.sort(A);
        int[] res = new int[B.length];
        System.out.println(Arrays.toString(A));
        for (int i = 0; i < B.length; i++) {
            int left = 0, right = A.length - 1;
            boolean found = false;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (B[i] > A[mid]) {
                    left = mid;
                }
                else if (B[i] < A[mid]) {
                    right = mid;
                }
                else {
                    res[i] = mid + 1;
                    found = true;
                    break;
                }
            }
            if (found)  continue;
            if (B[i] >= A[right])    res[i] = right + 1;
            else if (B[i] >= A[left])    res[i] = left + 1;
            else  res[i] = 0;
        }
        return res;
    }

    public int uniquePath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (matrix[m][0] == 1)  break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][n] == 1)  break;
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1)  continue;
                dp[i][j] = (dp[i - 1][j] % 100000007 + dp[i][j - 1] % 100000007) % 100000007;
            }
        }
        return dp[m - 1][n - 1];
    }



}
