package LeetCode.Topics.SecondRun;
import java.util.*;

public class DataStructure {

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
