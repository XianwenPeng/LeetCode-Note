package LeetCode.Topics.SecondRun;
import java.util.*;

public class DataStructure {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }


    /* 545. Top k Largest Numbers II */
    public class Solution {
        Queue<Integer> pq;
        int size;
        /*
        * @param k: An integer
        */public Solution(int k) {
            // do intialization if necessary
            pq = new PriorityQueue<>();
            size = k;
        }

        /*
         * @param num: Number to be added
         * @return: nothing
         */
        public void add(int num) {
            // write your code here
            if (pq.size() < size) {
                pq.offer(num);
                return;
            }
            if (pq.peek() < num) {
                pq.poll();
                pq.offer(num);
            }
        }

        /*
         * @return: Top k element
         */
        public List<Integer> topk() {
            // write your code here
            List<Integer> res = new ArrayList<>();
            Iterator it = pq.iterator();
            while (it.hasNext()) {
                // int num = ;
                res.add((Integer)it.next());
            }
            Collections.sort(res, Collections.reverseOrder());
            return res;
        }
    }

    /* 612. K Closest Points */
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        HashMap<Point, Double> pointToDistance = new HashMap<>();
        Queue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            double diff = pointToDistance.get(p2) - pointToDistance.get(p1);
            if (diff == 0) {
                if (p1.x != p2.x)   return p2.x - p1.x;
                else    return p2.y - p1.y;
            }
            return diff < 0 ? -1 : 1;
        });
        for (Point point: points) {
            double distance = distance(origin, point);
            if (pq.size() == k) {
                if (distance < pointToDistance.get(pq.peek())) {
                    pointToDistance.remove(pq.poll());
                    pointToDistance.put(point, distance);
                    pq.offer(point);
                }
            }
            else {
                pointToDistance.put(point, distance);
                pq.offer(point);
            }
        }
        Point[] res = new Point[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll();
        }
        return res;
    }
    private double distance(Point origin, Point point) {
        return Math.sqrt((origin.x - point.x) * (origin.x - point.x) + (origin.y - point.y) * (origin.y - point.y));
    }

    /* 104. Merge K Sorted Lists */
    public class SolutionKMerge {
        public ListNode mergeKListsI(List<ListNode> lists) {
            if (lists == null || lists.size() == 0) return null;
            while (lists.size() > 1) {
                List<ListNode> newList = new LinkedList<>();
                for (int i = 0; i < lists.size() - 1; i+=2) {
                    ListNode node = merge(lists.get(i), lists.get(i + 1));
                    newList.add(node);
                }
                if (lists.size() % 2 == 1) {
                    newList.add(lists.get(lists.size() - 1));
                }
                lists = newList;
            }
            return lists.get(0);
        }
        private ListNode merge(ListNode n1, ListNode n2) {
            ListNode dummy = new ListNode(-1);
            ListNode tail = dummy;
            while (n1 != null && n2 != null) {
                if (n1.val < n2.val) {
                    tail.next = n1;
                    n1 = n1.next;
                }
                else {
                    tail.next = n2;
                    n2 = n2.next;
                }
                tail = tail.next;
            }
            if (n1 != null) {
                tail.next = n1;
            }
            else {
                tail.next = n2;
            }
            return dummy.next;
        }

         public ListNode mergeKListsII(List<ListNode> lists) {
             if (lists == null || lists.size() == 0) return null;
             return divideLists(lists, 0, lists.size() - 1);
         }
         private ListNode divideLists(List<ListNode> lists, int start, int end) {
             if (start == end)   return lists.get(start);
             int mid = start + (end - start) / 2;
             ListNode left = divideLists(lists, start, mid);
             ListNode right = divideLists(lists, mid + 1, end);
             return mergeTwoLists(left, right);
         }
         private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
             ListNode dummy = new ListNode(-1);
             ListNode tail = dummy;
             while (l1 != null && l2 != null) {
                 if (l1.val > l2.val) {
                     tail.next = l2;
                     tail = tail.next;
                     l2 = l2.next;
                 }
                 else {
                     tail.next = l1;
                     tail = tail.next;
                     l1 = l1.next;
                 }
             }
             if (l1 != null) {
                 tail.next = l1;
             }
             else {
                 tail.next = l2;
             }
             return dummy.next;
         }

         public ListNode mergeKListsIII(List<ListNode> lists) {
             // write your code here
             Queue<ListNode> queue = new PriorityQueue<>((n1, n2) -> {
                 return n1.val - n2.val;
             });
             for (ListNode node: lists) {
                 if (node != null) {
                     queue.offer(node);
                 }
             }
             ListNode dummy = new ListNode(-1);
             ListNode cur = dummy;
             while (!queue.isEmpty()) {
                 cur.next = queue.poll();
                 cur = cur.next;
                 if (cur.next != null) {
                     queue.offer(cur.next);
                 }
             }
             return dummy.next;
         }
    }

    /* 4. Ugly Number II */
    public int nthUglyNumber(int n) {
        // write your code here
        Queue<Long> pq = new PriorityQueue<>();
        Set<Long> generated = new HashSet<>();
        long[] nums = {2,3,5};
        pq.offer(Long.valueOf(1));
        while (--n > 0) {
            long cur = pq.poll();
            for (int i = 0; i < nums.length; i++) {
                if (!generated.contains(cur * nums[i])) {
                    pq.offer(cur * nums[i]);
                    generated.add(cur * nums[i]);
                }
            }
        }
        return pq.poll().intValue();
    }

    /* 960. First Unique Number in a Stream II */
    public class DataStream {
        private class Node {
            int val;
            Node next;
            public Node(int val) {
                this.val = val;
            }
        }
        Node head, tail;
        Map<Integer, Node> prevs;
        Set<Integer> dup;
        public DataStream(){
            // do intialization if necessary
            head = new Node(-1);
            tail = head;
            prevs = new HashMap<>();
            dup = new HashSet<>();
        }
        /**
         * @param num: next number in stream
         * @return: nothing
         */
        public void add(int num) {
            // write your code here
            if (dup.contains(num))  return;
            if (prevs.containsKey(num)) {
                Node node = prevs.get(num);
                removeNode(node);
                prevs.remove(num);
                dup.add(num);
                return;
            }
            Node node = new Node(num);
            prevs.put(num, tail);
            tail.next = node;
            tail = node;
        }

        public void removeNode(Node node) {
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
            else {
                prevs.put(node.next.val, node);
            }
        }

        /**
         * @return: the first unique number in stream
         */
        public int firstUnique() {
            // write your code here
            if (head == tail)   return -1;
            return head.next.val;
        }
    }

    /* 526. Load Balancer */
    public class LoadBalancer {
        HashMap<Integer, Integer> idToPos;
        List<Integer> ids;
        Random random;
        public LoadBalancer() {
            // do intialization if necessary
            idToPos = new HashMap<>();
            ids = new LinkedList<>();
            random = new Random();
        }

        /*
         * @param server_id: add a new server to the cluster
         * @return: nothing
         */
        public void add(int server_id) {
            // write your code here
            if (idToPos.containsKey(server_id))    return;
            idToPos.put(server_id, ids.size());
            ids.add(server_id);
        }

        /*
         * @param server_id: server_id remove a bad server from the cluster
         * @return: nothing
         */
        public void remove(int server_id) {
            // write your code here
            if (!idToPos.containsKey(server_id))    return;
            int lastId = ids.get(ids.size() - 1);
            int pos = idToPos.get(server_id);
            if (pos < ids.size() - 1) {
                ids.set(pos, lastId);
                idToPos.put(lastId, pos);
            }
            ids.remove(ids.size() - 1);
            idToPos.remove(server_id);
        }

        /*
         * @return: pick a server in the cluster randomly with equal probability
         */
        public int pick() {
            // write your code here
            return ids.get(random.nextInt(ids.size()));
        }
    }

    /* 954. Insert Delete GetRandom O(1) - Duplicates allowed */
    class RandomizedCollection {
        Map<Integer, Set<Integer>> map;
        List<Integer> list;
        Random random;
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            map = new HashMap<>();
            list = new LinkedList<>();
            random = new Random();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            // write your code here
            boolean res = map.containsKey(val);
            if (!res) {
                map.put(val, new HashSet<>());
            }
            map.get(val).add(list.size());
            list.add(val);
            return !res;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            // write your code here
            if (!map.containsKey(val))  return false;
            Set<Integer> pos = map.get(val);
            int id = pos.iterator().next();
            int lastVal = list.get(list.size() - 1);
            Set<Integer> lastPos = map.get(lastVal);

            pos.remove(id);
            if (id < list.size() - 1) {
                lastPos.remove(list.size() - 1);
                list.set(id, lastVal);
                lastPos.add(id);
            }
            list.remove(list.size() - 1);
            if (pos.isEmpty())    map.remove(val);
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            // write your code here
            return list.get(random.nextInt(list.size()));
        }
    }

    /* 657. Insert Delete GetRandom O(1) */
    public class RandomizedSet {
        Map<Integer, Integer> numToId;
        List<Integer> list;
        Random random;

        public RandomizedSet() {
            // do intialization if necessary
            numToId = new HashMap<>();
            list = new LinkedList<>();
            random = new Random();
        }

        /*
         * @param val: a value to the set
         * @return: true if the set did not already contain the specified element or false
         */
        public boolean insert(int val) {
            // write your code here
            if (numToId.containsKey(val))  return false;
            numToId.put(val, list.size());
            list.add(val);
            return true;
        }

        /*
         * @param val: a value from the set
         * @return: true if the set contained the specified element or false
         */
        public boolean remove(int val) {
            // write your code here
            if (!numToId.containsKey(val))  return false;
            int id = numToId.get(val);
            if (val < list.size() - 1) {
                int lastVal = list.get(list.size() - 1);
                numToId.put(id, lastVal);
            }
            numToId.remove(val);
            list.remove(list.size() - 1);
            return true;
        }

        /*
         * @return: Get a random element from the set
         */
        public int getRandom() {
            // write your code here
            return list.get(random.nextInt(list.size()));
        }
    }

    /* 134. LRU Cache */
    public class LRUCache {
        private class Node {
            int key, val;
            Node prev, next;
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        HashMap<Integer, Node> map;
        Node head, tail;
        int capacity, size;
        /*
        * @param capacity: An integer
        */
        public LRUCache(int capacity) {
            // do intialization if necessary
            this.capacity = capacity;
            this.size = 0;
            this.map = new HashMap<>();
            this.head = new Node(-1, - 1);
            tail = head;
        }

        /*
         * @param key: An integer
         * @return: An integer
         */
        public int get(int key) {
            // write your code here
            if (!map.containsKey(key))  return -1;
            Node node = map.get(key);
            removeNode(node);
            insertNode(node);
            return node.val;
        }

        /*
         * @param key: An integer
         * @param value: An integer
         * @return: nothing
         */
        public void set(int key, int value) {
            // write your code here
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = value;
                removeNode(node);
                insertNode(node);
                return;
            }
            Node node = new Node(key, value);
            if (size == capacity) {
                Node temp = map.get(tail.key);
                map.remove(tail.key);
                size--;
                removeNode(temp);
            }
            insertNode(node);
            map.put(key, node);
            size++;
        }

        public void removeNode(Node node) {
            if (node == tail) {
                tail = node.prev;
                tail.next = null;
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        public void insertNode(Node node) {
            node.next = head.next;
            node.prev = head;
            if (head == tail) {
                head.next = node;
                tail = node;
                return;
            }
            head.next.prev = node;
            head.next = node;
        }
    }

    /* 642. Moving Average from Data Stream */
    public class MovingAverage {
        Queue<Integer> queue;
        double sum;
        int size;
        /*
        * @param size: An integer
        */public MovingAverage(int size) {
            // do intialization if necessary
            this.queue = new LinkedList<>();
            this.size = size;
            this.sum = 0;
        }

        /*
         * @param val: An integer
         * @return:
         */
        public double next(int val) {
            // write your code here
            if (queue.size() == this.size)   sum -= queue.poll();
            queue.offer(val);
            sum += val;
            return sum / queue.size();
        }
    }
}
