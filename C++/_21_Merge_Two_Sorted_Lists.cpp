#include <iostream>

/**
 * Definition for singly-linked list.
 */
struct ListNode
{
    int val;        ///< Value stored in the node
    ListNode *next; ///< Pointer to the next node

    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

/**
 * You are given the heads of two sorted linked lists `list1` and `list2`.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1:
 *  - Input: list1 = [1,2,4], list2 = [1,3,4]
 *  - Output: [1,1,2,3,4,4]
 *
 * Example 2:
 *  - Input: list1 = [], list2 = []
 *  - Output: []
 *
 * Example 3:
 *  - Input: list1 = [], list2 = [0]
 *  - Output: [0]
 *
 * Constraints:
 *  - The number of nodes in both lists is in the range `[0, 50]`.
 *  - `-100 <= Node.val <= 100`
 *  - Both `list1` and `list2` are sorted in non-decreasing order.
 */
class Solution
{
public:
    /**
     * @brief Merges two sorted singly linked lists into one sorted list.
     *
     * Given the heads of two sorted linked lists (list1 and list2), this method
     * merges them by splicing together nodes into a single sorted list.
     *
     * The new list is built by copying node values into newly allocated nodes,
     * preserving the non-decreasing order.
     *
     * @param list1 Pointer to the head of the first sorted list.
     * @param list2 Pointer to the head of the second sorted list.
     * @return Pointer to the head of the newly merged sorted list.
     *
     * @note
     *  - The input lists are not modified; a new list is created.
     *  - The caller is responsible for freeing the memory of the returned list.
     */
    ListNode *mergeTwoLists(ListNode *list1, ListNode *list2)
    {
        // Handle base cases where either list is empty.
        if (list1 == nullptr && list2 == nullptr)
            return nullptr;
        if (list1 == nullptr)
            return list2;
        if (list2 == nullptr)
            return list1;

        // Create a dummy head node to simplify list construction.
        ListNode *result = new ListNode();
        ListNode *cursor = result; // Tracks the end of the merged list

        // Traverse both lists until one runs out.
        while (list1 != nullptr && list2 != nullptr)
        {
            if (list1->val > list2->val)
            {
                // Pick value from list2 if it is smaller.
                cursor->val = list2->val;
                list2 = list2->next;
            }
            else if (list1->val < list2->val)
            {
                // Pick value from list1 if it is smaller.
                cursor->val = list1->val;
                list1 = list1->next;
            }
            else
            {
                // If values are equal, take one from each list.
                cursor->val = list1->val;
                list1 = list1->next;

                cursor->next = new ListNode();
                cursor = cursor->next;

                cursor->val = list2->val;
                list2 = list2->next;
            }

            // Prepare the next node if there are still elements to merge.
            if (list1 != nullptr || list2 != nullptr)
            {
                cursor->next = new ListNode();
                cursor = cursor->next;
            }

            // If list1 is exhausted, append the remainder of list2.
            if (list1 == nullptr && list2 != nullptr)
            {
                while (list2 != nullptr)
                {
                    cursor->val = list2->val;
                    list2 = list2->next;

                    if (list2 != nullptr)
                    {
                        cursor->next = new ListNode();
                        cursor = cursor->next;
                    }
                }
            }

            // If list2 is exhausted, append the remainder of list1.
            if (list2 == nullptr && list1 != nullptr)
            {
                while (list1 != nullptr)
                {
                    cursor->val = list1->val;
                    list1 = list1->next;

                    if (list1 != nullptr)
                    {
                        cursor->next = new ListNode();
                        cursor = cursor->next;
                    }
                }
            }
        }

        // Return the head of the merged list.
        return result;
    }
};

class BetterSolution
{
public:
    ListNode *mergeTwoLists(ListNode *list1, ListNode *list2)
    {
        // Dummy node acts as a placeholder for the new head
        ListNode dummy;
        ListNode *tail = &dummy; // tail always points to last node in result

        // Merge while both lists still have nodes
        while (list1 != nullptr && list2 != nullptr)
        {
            if (list1->val < list2->val)
            {
                tail->next = list1; // attach list1 node
                list1 = list1->next;
            }
            else
            {
                tail->next = list2; // attach list2 node
                list2 = list2->next;
            }
            tail = tail->next; // advance tail
        }

        // Attach the remainder of whichever list is not empty
        tail->next = (list1 != nullptr) ? list1 : list2;

        // The merged list starts after the dummy
        return dummy.next;
    }
};

/**
 * @brief Utility function to free all nodes in a linked list.
 *
 * @param head Reference to the head pointer of the list.
 *        After deletion, head will be set to nullptr.
 */
void deleteList(ListNode *&head)
{
    ListNode *current = head;
    ListNode *next;

    while (current != nullptr)
    {
        next = current->next;
        delete current;
        current = next;
    }

    head = nullptr; // prevent dangling pointer
};

int main()
{
    ListNode *list1 = new ListNode(1);
    list1->next = new ListNode(2);
    list1->next->next = new ListNode(4);

    ListNode *list2 = new ListNode(1);
    list2->next = new ListNode(3);
    list2->next->next = new ListNode(4);

    Solution sol = Solution();
    BetterSolution bSol = BetterSolution();
    ListNode *output = sol.mergeTwoLists(list1, list2);
    ListNode *output1 = bSol.mergeTwoLists(list1, list2);

    // Print merged list
    ListNode *temp = output;
    std::cout << "[";
    while (temp != nullptr)
    {
        std::cout << temp->val << ",";
        temp = temp->next;
    }
    std::cout << "\b]" << std::endl;

    temp = output1;
    std::cout << "[";
    while (temp != nullptr)
    {
        std::cout << temp->val << ",";
        temp = temp->next;
    }
    std::cout << "\b]";

    // Clean up memory
    deleteList(output);
    deleteList(output1);

    return 0;
}