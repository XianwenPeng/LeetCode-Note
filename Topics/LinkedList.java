package LeetCode.Topics;

import java.util.*;
public class LinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        System.out.println(1<<3);

        /* 83. Remove Duplicates from Sorted List */
        int[] arr83 = {1,1,1,2,3,4,4};
        ListNode head83 = ll.deleteDuplicates(ll.buildList(arr83));
        ll.printList(head83);

        /* 82. Remove Duplicates from Sorted List II */
        int[] arr82 = {1,1,1,2,3,3,4,5,5,6,7};
        ListNode head82 = ll.deleteDuplicates2Pointers(ll.buildList(arr82));
        ll.printList(head82);
    }

    /* 19. Remove Nth Node From End of List */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        slow.next = head;
        for (int i = 0; i < n; i++) fast = fast.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }

    /* 86. Partition List */
    public ListNode partition(ListNode head, int x) {
        ListNode queue1 = new ListNode(0), queue2 = new ListNode(0);
        ListNode cur1 = queue1, cur2 = queue2;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                cur1 = head;
            }
            else {
                cur2.next = head;
                cur2 = head;
            }
            head = head.next;
        }
        cur2.next = null;
        cur1.next = queue2.next;
        return queue1.next;
    }

    /* 445. Add Two Numbers II */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        ListNode ans = new ListNode(0), prev = ans;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry == 1) {
            int x = stack1.isEmpty() ? 0 : stack1.pop().val;
            int y = stack2.isEmpty() ? 0 : stack2.pop().val;
            ListNode node = new ListNode((x + y + carry) % 10);
            node.next = ans.next;
            ans.next = node;
            carry = (x + y + carry) / 10;
        }
        return ans.next;
    }

    /* 142. Linked List Cycle II */
    public ListNode detectCycle(ListNode head) {
        if (head == null)   return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)   break;
        }
        if (fast == null || fast.next == null)    return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /* 141. Linked List Cycle */
    public boolean hasCycle(ListNode head) {
        if (head == null)   return false;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)   return true;
        }
        return false;
    }

    /* 82. Remove Duplicates from Sorted List II */
    public ListNode deleteDuplicatesCount(ListNode head) {
        if (head == null)   return head;
        ListNode res = new ListNode(-1), cur = res, temp = head;
        int count = 1;
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                count++;
            }
            else {
                if (count == 1) {
                    cur.next = temp;
                    cur = cur.next;
                }
                count = 1;
            }
            temp = temp.next;
        }
        if (count == 1) cur.next = temp;
        else    cur.next = temp.next;
        return res.next;
    }

    public ListNode deleteDuplicates2Pointers(ListNode head) {
        if (head == null)   return head;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead, cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) cur = cur.next;
            if (pre.next == cur)    pre = cur;
            else    pre.next = cur.next;
            cur = cur.next;
        }
        return fakeHead.next;
    }

    /* 83. Remove Duplicates from Sorted List */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)   return null;
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            }
            else    node = node.next;
        }
        return head;
    }

    /* BUILD LIST AND PRINT LIST */
    public ListNode buildList(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode node = head;
        for (int i = 1; i < arr.length; i++) {
            node.next = new ListNode(arr[i]);
            node = node.next;
        }
        return head;
    }

    public void printList(ListNode head) {
        ListNode node = head;
        while (node != null) {
            System.out.print(node.val+" ");
            node = node.next;
        }
        System.out.println();
    }
}

