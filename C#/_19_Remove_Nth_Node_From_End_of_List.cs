using System;

var list = new _19_Remove_Nth_Node_From_End_of_List.ListNode(1);
list.next = new _19_Remove_Nth_Node_From_End_of_List.ListNode(2);
list.next.next = new _19_Remove_Nth_Node_From_End_of_List.ListNode(3);
list.next.next.next = new _19_Remove_Nth_Node_From_End_of_List.ListNode(4);
list.next.next.next.next = new _19_Remove_Nth_Node_From_End_of_List.ListNode(5);

var sol = new _19_Remove_Nth_Node_From_End_of_List.Solution();
list = sol.RemoveNthFromEnd(list, 2);

var temp = list;
Console.Write("[");
while (temp != null)
{
    Console.Write(temp.val + ",");
    temp = temp.next;
}
Console.Write("\b]");


public class _19_Remove_Nth_Node_From_End_of_List
{
    /// <summary>
    /// Definition for a singly-linked list node.
    /// </summary>
    public class ListNode
    {
        // Value stored in the node
        public int val;
        // Reference to the next node in the list (or null if this is the last node)
        public ListNode? next;
        
        /// <summary>
        /// Initialize a node with the given value and a reference to the next node.
        /// </summary>
        /// <param name="val">Integer value to store in this node</param>
        /// <param name="next">The next node in the list (or <c>null</c> if none</param>
        public ListNode(int val = 0, ListNode? next = null)
        {
            this.val = val;
            this.next = next;
        }
    }

    /// <summary>
    /// Given the head of a linked list, remove the nth node from the end of the list and return its head.
    /// </summary>
    /// <example>
    /// Example 1:
    /// - Input: head = [1,2,3,4,5], n = 2
    /// - Output: [1,2,3,5]
    /// </example>
    /// <example>
    /// Example 2:
    /// - Input: head = [1], n = 1
    /// - Output: [] 
    /// </example>
    /// <example>
    /// Example 3:
    /// - Input: head = [1,2], n = 1
    /// - Output: [1]
    /// </example>
    /// <remarks>
    /// Constraints:
    /// - The number of nodes in the list is `sz`.
    /// - `1 <= sz <= 30`
    /// - `0 <= Node.val <= 100`
    /// - `1 <= n <= sz`
    /// </remarks>
    public class Solution
    {
        /// <summary>
        /// Removes the n-th node from the end of a singly linked list.
        /// </summary>
        /// <param name="head">The head of the linked list</param>
        /// <param name="n">The position from the end (1-based index) of the node to remove</param>
        /// <returns>The head of the modified linked list</returns>
        public ListNode? RemoveNthFromEnd(ListNode head, int n)
        {
            ListNode fast = head;
            ListNode slow = head;

            // Move `fast` pointer n steps ahead.
            for (int i = 0; i < n; i++)
            {
                fast = fast.next!;
            }
            // If `fast` is null, that means the head itself should be removed.
            if (fast == null)
            {
                return head.next;
            }

            // Move both `fast` and `slow` until `fast` reaches the end.
            // At that point, `slow` will be just before the node to remove.
            while (fast.next != null)
            {
                fast = fast.next;
                slow = slow.next!;
            }
            // If slow.next is nil, it means there was no node to remove.
            if (slow.next == null)
            {
                return null;
            }

            // Skip the target node by linking slow to the node after slow.next.
            slow.next = slow.next.next;

            return head;
        }
    }
}