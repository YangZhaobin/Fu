package com.yzb.test.LeetCode;

public class ReverseKGroup {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        System.out.println(reverseKGroup(n1, 2));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return val + "  " + next;
        }
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode ret = head;
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode t = null;

        boolean f = false;
        ListNode[] nodes = new ListNode[2];
        do {
            nodes = reverseK(h.next, k);

            if (!f) {
                f = true;
                ret = nodes[0];
            }
            if (t == null) {
                t = nodes[1];
            }
            else {
                t.next = nodes[0];
                t = nodes[1];
            }

            h = nodes[1];
        } while (nodes[1] != null);

        return ret;
    }

    public static ListNode[] reverseK(ListNode head, int k) {
        ListNode[] ret = new ListNode[2];

        if (head == null) return ret;

        ListNode fast = head;
        ListNode slow = head;
        for (int i = 1; i < k; i++) {
            fast = fast.next;
            if (fast == null) {
                ret[0] = head;
                return ret;
            }
        }

        ListNode prev = fast.next;
        ret[1] = slow;
        while (slow != fast) {
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        slow.next = prev;

        ret[0] = slow;
        System.out.println(ret[0].val + " ==== " + ret[1].val);
        return ret;
    }
}
