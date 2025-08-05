package com.michaelcherrera;

public class _2_Add_Two_Numbers {

    public static class ListNode {

        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int x) {
            val = x;
        }

        public ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    /**
     * You are given two non-empty linked lists representing two non-negative
     * integers. The digits are stored in reverse order, and each of their nodes
     * contains a single digit. Add the two numbers and return the sum as a
     * linked list. You may assume the two numbers do not contain any leading
     * zero, except the number 0 itself.
     *
     * @param l1 list 1
     * @param l2 list 2
     * @return the sum of the two lists
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    /**
     * Helper method to add two numbers represented by linked lists with carry.
     *
     * @param l1 list 1
     * @param l2 list 2
     * @param carry carry from the previous addition
     * @return the sum of the two lists with carry
     */
    private ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {

        // Base case: if both lists are null and there's no carry, return null
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        // Calculate the sum of the current digits and the carry
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }

        // Create a new node with the digit value of sum % 10
        ListNode resultNode = new ListNode(sum % 10);

        // Recursively call the function for the next digits and the new carry
        resultNode.next = addTwoNumbers(l1, l2, sum / 10);

        return resultNode;
    }

    private static void printList(ListNode node) {
        System.out.print("[");
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(", ");
            }
            node = node.next;
        }
        System.out.print("]");
        System.out.println();
    }

    public static void main(String[] args) {
        _2_Add_Two_Numbers solution = new _2_Add_Two_Numbers();

        // Example 1:
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode result = solution.addTwoNumbers(l1, l2);
        printList(result); // Expected output: 7 -> 0 -> 8

        // Example 2:
        l1 = new ListNode(0);
        l2 = new ListNode(0);
        result = solution.addTwoNumbers(l1, l2);
        printList(result); // Expected output: 0
    }
}
