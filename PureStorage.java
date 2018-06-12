package LeetCode;

import java.util.LinkedList;
import java.util.*;

public class PureStorage {
    public static void main (String[] args) {
        PureStorage ps = new PureStorage();
        /* Pure Storage OA Q2*/
        PSTreeNode root = ps.buildPSTree();
        ps.showTree(root);
        ps.dfsTree(root, 12);

        /* Count palindromes */
        System.out.println(ps.countPalindromes("hellolle"));
        System.out.println(ps.countPalindromes("wowpurerocks"));

//        String[] arr = {"ACQUIRE 346", "ACQUIRE 48", "RELEASE 38", "ACQUIRE 346"};
//        System.out.println(ps.acquireReleaseLock(arr));

        String[] inputs1 = new String[]{"ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364"};
        int res1 = ps.acquireReleaseLock(inputs1);
        System.out.println("Expected output: 0");
        System.out.println("My output: " + res1);
        System.out.println();


        String[] inputs2 = new String[]{"ACQUIRE 364", "ACQUIRE 84", "RELEASE 364", "RELEASE 84"};
        int res2 = ps.acquireReleaseLock(inputs2);
        System.out.println("Expected output: 3");
        System.out.println("My output: " + res2);
        System.out.println();

        String[] inputs3 = new String[]{"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84", "RELEASE 364", "ACQUIRE 456"};
        int res3 = ps.acquireReleaseLock(inputs3);
        System.out.println("Expected output: 7");
        System.out.println("My output: " + res3);
        System.out.println();

        String[] inputs4 = new String[]{"ACQUIRE 123", "ACQUIRE 364", "ACQUIRE 84", "RELEASE 84",
                "RELEASE 364", "ACQUIRE 789", "RELEASE 456", "RELEASE 123"};
        int res4 = ps.acquireReleaseLock(inputs4);
        System.out.println("Expected output: 7");
        System.out.println("My output: " + res4);
        System.out.println();

        String[] inputs5 = new String[]{"ACQUIRE 364", "ACQUIRE 84", "ACQUIRE 364", "RELEASE 364"};
        int res5 = ps.acquireReleaseLock(inputs5);
        System.out.println("Expected output: 3");
        System.out.println("My output: " + res5);
        System.out.println();

        ps.testQ();
    }

    public void testQ() {
        int[] test = {1,2,3,4,5};
        int target = 3;
        if (sorted_search(test, target) == 2)    System.out.println("CORRECT");
        else {
            System.out.println(test.length);
            for (int i : test ){
                System.out.print(i+" ");
            }
            System.out.println();
            System.out.print(target);
        }
    }
    public int sorted_search(int[] temp, int target) {
        if (temp == null || temp.length <= 0)   return -1;
        int left = 0, right = temp.length - 1;
        while (left < right) {
            int middle = (left + right + 1) / 2;
            if (temp[middle] > target) {
                right = middle - 1;
            }
            else {
                left = middle + 1;
            }
        }
        if (temp[right] == target) return right;
        return -1;
    }

    /* Acquire / Release LOCK*/
    public int acquireReleaseLock2(String[] commands) {
        if (commands == null || commands.length == 0)   return -1;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < commands.length; i++) {
            String[] temp = commands[i].toLowerCase().split(" ");
            System.out.println(commands[i]);
            if (temp[0].toLowerCase().equals("acquire")) {
                if (stack.contains(commands[i].toLowerCase()))    return i + 1;
                else    stack.push(commands[i].toLowerCase());
            }
            else {
                String str = stack.pop();
                String[] tempStr = commands[i].toLowerCase().split(" ");
                if (stack.contains(commands[i].toLowerCase())){
                    System.out.println("SDFGHFDSAFGDEFGBGFDEWDF");
                    return i + 1;
                }
                else if (!str.equals("acquire "+tempStr[1]))    return i + 1;
            }
        }
        if (!stack.isEmpty())   return commands.length + 1;
        return 0;
    }
    public int acquireReleaseLock(String[] commands) {
        if (commands == null || commands.length == 0)   return -1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < commands.length; i++) {
            System.out.println(commands[i]);
            String[] temp = commands[i].toLowerCase().split(" ");
            if (temp[0].equals("acquire")) {
                if (stack.contains(Integer.parseInt(temp[1])))  return i + 1;
                else    stack.push(Integer.parseInt(temp[1]));
            }
            else {
                if (stack.isEmpty() || stack.pop() != Integer.parseInt(temp[1]))    return i + 1;
            }
        }
        if (!stack.isEmpty())   return commands.length + 1;
        return 0;
    }
    /* Count palindromes */
    public int countPalindromes (String str) {
        if (str == null || str.length() == 0) return 0;
        List<String> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < str.length(); i ++) {
            count += countPalindromesHelper(str, i, i, 0, list);
            count += countPalindromesHelper(str, i, i + 1, 0, list);
        }
        System.out.println(list);
        return count;
    }
    public int countPalindromesHelper (String str, int l, int r, int count, List<String> list) {
        if (l < 0 || r >= str.length()) return count;
        if (str.charAt(l) != str.charAt(r)) return count;
        list.add(str.substring(l, r+1));
        return countPalindromesHelper(str, l - 1, r + 1, count + 1, list);
    }

    /* Pure Storage OA Q2*/
    public PSTreeNode buildPSTree () {
        PSTreeNode root = new PSTreeNode(5);

        // LEFT
        PSTreeNode one = new PSTreeNode(1);
        one.parent = root;
        root.right = one;

        PSTreeNode eight = new PSTreeNode(8);
        eight.parent = one;
        one.left = eight;

        PSTreeNode eleven = new PSTreeNode(11);
        eleven.parent = eight;
        eight.left = eleven;

        PSTreeNode five = new PSTreeNode(5);
        five.parent = eight;
        eight.right = five;

        PSTreeNode four = new PSTreeNode(4);
        four.parent = eleven;
        eleven.left = four;

        PSTreeNode five2 = new PSTreeNode(5);
        five2.parent = eleven;
        eleven.right = five2;

        // RIGHT
        PSTreeNode four2 = new PSTreeNode(4);
        four2.parent = root;
        root.left = four2;

        PSTreeNode seven = new PSTreeNode(7);
        seven.parent = four2;
        four2.left = seven;

        PSTreeNode eight2 = new PSTreeNode(8);
        eight2.parent = seven;
        seven.left = eight2;

        PSTreeNode one2 = new PSTreeNode(1);
        one2.parent = seven;
        seven.right = one2;

        PSTreeNode three = new PSTreeNode(3);
        three.parent = four2;
        four2.right = three;

        PSTreeNode nine = new PSTreeNode(9);
        nine.parent = three;
        three.right = nine;

        PSTreeNode two = new PSTreeNode(2);
        two.parent = nine;
        nine.left = two;

        PSTreeNode twelve = new PSTreeNode(12);
        twelve.parent = two;
        two.right = twelve;

        return eleven;
    }
    public class PSTreeNode {
        int val;
        PSTreeNode parent;
        PSTreeNode left;
        PSTreeNode right;
        boolean visited;
        public PSTreeNode (int val) {
            this.val = val;
            visited = false;
        }
    }
    public PSTreeNode dfsTree(PSTreeNode node, int target) {
        System.out.println(node.val);
        node.visited = true;
        if (node.val == target) return node;
        PSTreeNode[] nodes = {node.right, node.parent, node.left};
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null && !nodes[i].visited) {
                PSTreeNode result = dfsTree(nodes[i], target);
                if (result != null) return result;
            }
        }
        return null;
    }

    public void showTree(PSTreeNode root){
        if(root == null)
            return;
        Queue<PSTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            PSTreeNode node = queue.poll();
            System.out.print(node.val);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
        System.out.println();
    }

}
