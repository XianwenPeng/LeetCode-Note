package LeetCode.Topics.SecondRun;
import java.util.*;

public class DataStuctureII {
    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /* O(nklogk) O(k) 577. Merge K Sorted Interval Lists */
    private class Pair {
        int row;
        int col;
        public Pair(int row, int col) {
            this.col = col;
            this.row = row;
        }
    }
    public List<Interval> mergeKSortedIntervalListsOptimized(List<List<Interval>> intervals) {
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> {
            return intervals.get(p1.row).get(p1.col).start - intervals.get(p2.row).get(p2.col).start;
        });
        int k = intervals.size();
        for (int i = 0; i < k; i++) {
            if (intervals.get(i).size() > 0)
                heap.add(new Pair(i, 0));
        }
        List<Interval> res = new LinkedList<>();
        while (!heap.isEmpty()) {
            Pair pair = heap.poll();
            res.add(intervals.get(pair.row).get(pair.col));
            pair.col++;
            if (pair.col < intervals.get(pair.row).size()) {
                heap.offer(pair);
            }
        }
        return mergeIntervals(res);
    }
    private List<Interval> mergeIntervals(List<Interval> list) {
        List<Interval> res = new LinkedList<>();
        int start = list.get(0).start;
        int end = list.get(0).end;
        for (int i = 0; i < list.size(); i++) {
            Interval interval = list.get(i);
            if (interval.start > end) {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
            else {
                end = Math.max(end, interval.end);
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    /* 577. Merge K Sorted Interval Lists */
    public List<Interval> mergeKSortedIntervalLists(List<List<Interval>> intervals) {
        // write your code here
        List<List<Interval>> res = new LinkedList<>(intervals);
        while (res.size() > 1) {
            List<List<Interval>> list = new LinkedList<>();
            for (int i = 0; i < res.size() - 1; i += 2) {
                list.add(mergeTwoLists(res.get(i), res.get(i + 1)));
            }
            if (res.size() % 2 == 1) {
                list.add(res.get(res.size() - 1));
            }
            res = list;
        }
        return res.get(0);
    }
    private List<Interval> mergeTwoLists(List<Interval> l1, List<Interval> l2) {
        List<Interval> res = new LinkedList<>();
        int i = 0, j = 0, index = 0;
        Interval last = null;
        while (i < l1.size() && j < l2.size()) {
            Interval i1 = l1.get(i), i2 = l2.get(j), cur = null;
            if (i1.start < i2.start) {
                cur = i1;
                i++;
            }
            else {
                cur = i2;
                j++;
            }
            last = mergeTwoIntervals(res, last, cur);
        }
        while (i < l1.size()) {
            last = mergeTwoIntervals(res, last, l1.get(i++));
        }
        while (j < l2.size()) {
            last = mergeTwoIntervals(res, last, l2.get(j++));
        }
        res.add(last);
        return res;
    }
    private Interval mergeTwoIntervals(List<Interval> res, Interval last, Interval cur) {
        if (last == null)   return cur;
        if (cur.start > last.end) {
            res.add(last);
            return cur;
        }
        last.end = Math.max(last.end, cur.end);
        return last;
    }

    /* 486. Merge K Sorted Arrays */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> {
            return n2 - n1;
        });
        int size = 0;
        for (int[] arr: arrays) {
            size += arr.length;
        }
        int[] res = new int[size];
        int index = size - 1;
        for (int[] arr: arrays) {
            for (int i = arr.length - 1; i >= 0; i--) {
                if (heap.size() < size) {
                    heap.add(arr[i]);
                    continue;
                }
                if (heap.peek() > arr[i]) {
                    res[index--] = heap.poll();
                    heap.add(arr[i]);
                }
            }
        }
        while (!heap.isEmpty()) {
            res[index--] = heap.poll();
        }
        return res;
    }

    /* 839. Merge Two Sorted Interval Lists */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        int i = 0, j = 0;
        List<Interval> res = new LinkedList<>();
        while (i < list1.size() && j < list2.size()) {
            Interval i1 = list1.get(i), i2 = list2.get(j);
            if (i1.start > i2.start) {
                addInterval(res, i2);
                j++;
            }
            else {
                addInterval(res, i1);
                i++;
            }
        }
        while (i < list1.size()) {
            addInterval(res, list1.get(i++));
        }
        while (j < list2.size()) {
            addInterval(res, list2.get(j++));
        }
        return res;
    }
    public void addInterval(List<Interval> res, Interval cur) {
        if (res.size() == 0 || res.get(res.size() - 1).end < cur.start) {
            res.add(cur);
            return;
        }
        Interval last = res.get(res.size() - 1);
        last.end = Math.max(last.end, cur.end);
    }

    /* 64. Merge Sorted Array */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            }
            else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }

    /* 6. Merge Two Sorted Arrays */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int[] res = new int[A.length + B.length];
        int i = 0, j = 0, index = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                res[index++] = A[i++];
            }
            else {
                res[index++] = B[j++];
            }
        }
        while (i < A.length) {
            res[index++] = A[i++];
        }
        while (j < B.length) {
            res[index++] = B[j++];
        }
        return res;
    }
}
