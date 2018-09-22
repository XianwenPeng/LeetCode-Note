package LeetCode.Topics.SecondRun;

//                if (totalShares > cur.share) {
//                    totalShares -= cur.share;
//                    bidsPQ.poll();
//                }
//                else if (totalShares == cur.share) {
//                    cur.share -= totalShares;
//                    totalShares -= cur.share;
//                    bidsPQ.poll();
//                }
//                else {
//                    totalShares -= cur.share;
//                    bidsPQ.poll();
//                }
import java.util.*;
public class AkunaOA {
    public static class Bid {
        int id;
        int share;
        int price;
        int timestamp;
        public Bid(int id, int share, int price, int timestamp) {
            this.id = id;
            this.share = share;
            this.price = price;
            this.timestamp = timestamp;
        }
    }
    static List<Integer> GetUnallottedBidIds(List<List<Integer>> bids, int totalShares) {
        PriorityQueue<Bid> bidsPQ = getAllBids(bids);
        List<Integer> res = new LinkedList<>();
        int allotedShare = 0;
        while (!bidsPQ.isEmpty() && totalShares > 0) {
            List<Bid> highBids = getHighestBids(bidsPQ);
            if (highBids.size() == 1) {
                Bid cur = highBids.get(0);
                totalShares -= cur.share;
            }
            else {
                int index = 0;
                Set<Bid> alloted = new HashSet<>();
                while (!highBids.isEmpty() && totalShares > 0) {
                    Bid cur = highBids.get(index);
                    cur.share--;
                    alloted.add(cur);
                    totalShares--;
                    if (cur.share == 0) {
                        highBids.remove(index);
                        break;
                    }
                    index = (index + 1) % highBids.size();
                }
                if (totalShares == 0) {
                    for (Bid bid: highBids) {
                        if (!alloted.contains(bid)) {
                            bidsPQ.offer(bid);
                        }
                    }
                    break;
                }
                for (Bid bid: highBids) {
                    bidsPQ.offer(bid);
                }
            }
        }
        for (Bid bid: bidsPQ) {
            res.add(bid.id);
        }
        Collections.sort(res);
        return res;
    }
    static List<Bid> getHighestBids(PriorityQueue<Bid> bids) {
        List<Bid> res = new LinkedList<>();
        PriorityQueue<Bid> temp = new PriorityQueue<>((b1, b2) -> {
            return b1.timestamp - b2.timestamp;
        });
        int price = -1;
        while (!bids.isEmpty()) {
            Bid next = bids.poll();
            if (price != -1 && next.price != price) {
                bids.offer(next);
                break;
            }
            if (price == -1) {
                price = next.price;
            }
            temp.add(next);
        }
        for (Bid bid: temp) {
            res.add(bid);
        }
        return res;
    }
    static PriorityQueue<Bid> getAllBids(List<List<Integer>> bids) {
        PriorityQueue<Bid> pq = new PriorityQueue<>((b1, b2) -> {
            return b2.price - b1.price;
        });
        for (List<Integer> bid: bids) {
            pq.offer(new Bid(bid.get(0), bid.get(1), bid.get(2), bid.get(3)));
        }
        return pq;
    }
    static void printPQ(PriorityQueue<Bid> bids) {
        Iterator it = bids.iterator();
        while (it.hasNext()) {
            Bid cur = (Bid)it.next();
            System.out.println(cur.id +"," + cur.share + "," + cur.price+","+cur.timestamp);
        }
    }
    static void printList(List<Bid> bids) {
        for (Bid cur: bids) {
            System.out.println(cur.id +"," + cur.share + "," + cur.price+","+cur.timestamp);

        }
    }

    private static class TimeStamp {
        int time;
        int delta;
        public TimeStamp(int time, int delta) {
            this.time = time;
            this.delta = delta;
        }
    }
    public static int getMostVisited(int n, List<Integer> sprints) {
        if (sprints.size() == 0)    return -1;
        if (sprints.size() == 1)    return sprints.get(0);
        int start = sprints.get(0), end = start;
        List<TimeStamp> list = new LinkedList<>();
        for (int i = 1; i < sprints.size(); i++) {
            end = sprints.get(i) > start ? sprints.get(i) : start;
            start = start < sprints.get(i) ? start : sprints.get(i);
            list.add(new TimeStamp(start, 1));
            list.add(new TimeStamp(end, -1));
            start = sprints.get(i);
        }
        Collections.sort(list, (t1, t2) -> {
            if (t1.time == t2.time)
                return t2.delta - t1.delta;
            else
                return t1.time - t2.time;
        });

        int sum = 0;
        int max = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            TimeStamp t = list.get(i);
            sum += t.delta;
            max = Math.max(max, sum);
        }
        int temp = -1;
        sum = 0;
        for (int i = 0; i < list.size(); i++) {
            TimeStamp t = list.get(i);
            sum += t.delta;
            temp = Math.max(temp, sum);
            if (temp == max) {
                res = Math.min(res, t.time);
            }
        }
        return res;
    }

    private static class Sprint {
        int start;
        int end;
        public Sprint(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    private static int max = -1;
    public static int getMostVisitedMap(int n, List<Integer> sprints) {
        // Write your code here
        if (sprints.size() == 0)    return -1;
        if (sprints.size() == 1)    return sprints.get(0);
        Map<Integer, Integer> map = new HashMap<>();
        List<Sprint> list = new LinkedList<>();
        int start = sprints.get(0), end = start;
        for (int i = 1; i < sprints.size(); i++) {
            end = sprints.get(i) > start ? sprints.get(i) : start;
            start = start < sprints.get(i) ? start : sprints.get(i);
            list.add(new Sprint(start, end));
            start = end;
        }
        int max = -1;
        Map<Integer, PriorityQueue<Integer>> maxToIndex = new HashMap<>();
        for (Sprint s: list) {
            start = s.start;
            end = s.end;
            for (int i = start; i < end; i++) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
                max = Math.max(max, map.get(i));
                if (!maxToIndex.containsKey(max))   maxToIndex.put(max, new PriorityQueue<>());
                maxToIndex.get(max).add(i);
            }
        }
        return maxToIndex.get(max).poll();
    }
    private static void run(int left, int right, Map<Integer, Integer> map) {
        int start = left < right ? left : right;
        int end = left > right ? left : right;
        // System.out.println(start +" " + end);
        while (start <= end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            max = Math.max(max, map.get(start));
            start++;
        }
        // System.out.println(map);
    }

    public static void main (String []args) {
        System.out.println(missingWords("I am using hackerrank to improve programming", "am hackerrank to improve"));
        int[] arr = {1,3,5,2,5,9,6,7,8,11};
        System.out.println(Arrays.toString(longestIncrement(arr)));
    }
    static List<String> missingWords(String s, String t) {
        List<String> res = new LinkedList<>();
        String[] swords = s.split("\\W+");
        String[] twords = t.split("\\W+");
        for (int i = 0, j = 0; i < swords.length; i++) {
            if (j >= twords.length || !swords[i].equals(twords[j])) {
                res.add(swords[i]);
            }
            else {
                j++;
            }
        }
        return res;
    }

    static int[] longestIncrement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = -1, first = -1;
        for (int num: nums) {
            int prev = map.getOrDefault(num - 1, 0);
            int post = map.getOrDefault(num + 1, 0);
            int len = prev + post + 1;
            if (len > max) {
                max = len;
                first = num + post - len + 1;
            }
            map.put(num, len - post);
            map.put(num - prev, len);
            map.put(num + post, len);
//            System.out.println(num +" , "+ prev + ", "+ post+", "+"start: " + first + ", "+map);
        }
        System.out.println(map);
        int[] res = new int[map.get(first)];
        for (int i = 0; i < map.get(first); i++) {
            res[i] = i + first;
        }
        return res;
    }
}
