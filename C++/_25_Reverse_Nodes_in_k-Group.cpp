#include <iostream>

/**
 * Definition for singly-linked list node.
 */
struct ListNode
{
    int val;        ///< Value stored in the node
    ListNode *next; ///< Pointer to the next node in the list

    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

/**
 *  Given the `head` of a linked list, reverse the nodes of the list `k` at a time, and return the modified list.
 *
 *  `k` is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of `k` then left-out nodes, in the end, should remain as it is.
 *
 *  You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *  #### Example 1:
 *    Input: head = [1,2,3,4,5], k = 2
 *    Output: [2,1,4,3,5]
 *
 *  #### Example 2:
 *    Input: head = [1,2,3,4,5], k = 3
 *    Output: [3,2,1,4,5]
 *
 *  #### Constraints:
 *  - The number of nodes in the list is `n`.
 *  - `1 <= k <= n <= 5000`
 *  - `0 <= Node.val <= 1000`
 */
class Solution
{
public:
    /**
     * Reverses nodes in groups of size k and returns the new head of the list.
     *
     * The function only changes node connections, not node values.
     * If fewer than k nodes remain at the end, they are left in place.
     *
     * @param head Pointer to the head of the linked list.
     * @param k    Size of groups to reverse.
     * @return     Pointer to the new head of the modified list.
     */
    ListNode *reverseKGroup(ListNode *head, int k)
    {
        // Edge case: empty list or single node, no reversal needed
        if (!head || k == 1)
            return head;

        ListNode *groupTail = head; // Will become the tail of the reversed group
        ListNode *prev = nullptr;   // Tracks previous node during reversal
        ListNode *curr = head;      // Current node being processed
        ListNode *next = nullptr;   // Temporarily stores the next node

        // Step 1: Ensure there are at least k nodes to reverse
        ListNode *check = head;
        for (int i = 0; i < k; i++)
        {
            if (!check)
                return head; // Not enough nodes, return original head
            check = check->next;
        }

        // Step 2: Reverse exactly k nodes
        for (int i = 0; i < k && curr != nullptr; i++)
        {
            next = curr->next; // Save the next node
            curr->next = prev; // Reverse pointer
            prev = curr;       // Move prev forward
            curr = next;       // Advance curr
        }

        // Step 3: Connect the tail of this group to the result of recursively reversing the rest
        if (next)
        {
            groupTail->next = reverseKGroup(next, k);
        }

        // prev now points to the new head of this reversed group
        return prev;
    }

    /**
     * Deletes all nodes in the linked list and frees memory.
     *
     * @param head Reference to pointer of the head node.
     *             Will be set to nullptr after deletion.
     */
    void deleteList(ListNode *&head)
    {
        ListNode *curr = head;
        while (curr)
        {
            ListNode *next = curr->next; // Store next before deleting
            delete curr;
            curr = next;
        }
        head = nullptr;
    }
};

int main()
{
    // Build linked list: 1 -> 2 -> 3 -> 4 -> 5
    ListNode *list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

    // Reverse nodes in groups of k = 3
    list = Solution().reverseKGroup(list, 3);

    // Print the modified list: expected output -> 3 2 1 4 5
    ListNode *cursor = list;
    while (cursor)
    {
        std::cout << cursor->val << " ";
        cursor = cursor->next;
    }
    std::cout << std::endl;

    // Free allocated memory
    Solution().deleteList(list);

    return 0;
}