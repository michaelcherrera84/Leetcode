from typing import List, Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val = 0, next = None):
        self.val = val
        self.next = next


class Solution:
    """
    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

    _Merge all the linked-lists into one sorted linked-list and return it._

    **Example 1:**
      **Input:** lists = [[1,4,5],[1,3,4],[2,6]]
      **Output:** [1,1,2,3,4,4,5,6]
      **Explanation:** The linked-lists are:
    ```
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    ```
      merging them into one sorted linked list:  
      1->1->2->3->4->4->5->6  

    **Example 2:**
      **Input:** lists = []
      **Output:** []

    **Example 3:**
      **Input:** lists = [[]]
      **Output:** []
    
    **Constraints:**
    - `k == lists.length`
    - `0 <= k <= 104`
    - `0 <= lists[i].length <= 500`
    - `-104 <= lists[i][j] <= 104`
    - `lists[i]` is sorted in **ascending order**.
    - The sum of `lists[i].length` will not exceed 104.
    """

    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        """
        Merge two sorted linked lists into a single sorted linked list.

        This method uses a dummy head node to simplify pointer manipulation.
        It iterates through both lists, always attaching the smaller node to
        the merged list, until one list is exhausted. Any remaining nodes are
        then appended.

        Args:
            list1 (Optional[ListNode]): Head of the first sorted linked list.
            list2 (Optional[ListNode]): Head of the second sorted linked list.

        Returns:
            Optional[ListNode]: Head of the merged sorted linked list.
        """
        dummy = ListNode(0)    # Dummy node to simplify result list construction
        current = dummy        # Pointer to the last node in the merged list

        # Traverse both lists while neither is exhausted
        while list1 and list2:
            if list1.val < list2.val:
                current.next = list1  # Attach the smaller node
                list1 = list1.next    # Move pointer forward in list1
            else:
                current.next = list2
                list2 = list2.next    # Move pointer forward in list2
            
            current = current.next    # Advance merged list pointer

        # At least one of the lists is exhausted; append the remaining list
        current.next = list1 or list2

        return dummy.next  # Return the real head (next of dummy)
        

    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        """
        Merge k sorted linked lists into one sorted linked list using
        a divide-and-conquer approach.

        The function recursively splits the list of linked lists into halves,
        merges each half, and then merges the results together. This reduces
        the k-way merge problem into multiple two-way merges.

        Args:
            lists (List[Optional[ListNode]]): A list containing the heads of k sorted linked lists.

        Returns:
            Optional[ListNode]: Head of the final merged sorted linked list.
        """
        if not lists:
            return None
        if len(lists) == 1:
            return lists[0]
        
        # Split the list of lists into two halves
        mid = len(lists) // 2
        left = self.mergeKLists(lists[:mid])   # Recursively merge the left half
        right = self.mergeKLists(lists[mid:])  # Recursively merge the right half

        # Merge the two sorted halves into one
        return self.mergeTwoLists(left, right)
    

# Example usage
l1 = ListNode(1, ListNode(4, ListNode(5)))
l2 = ListNode(1, ListNode(3, ListNode(4)))
l3 = ListNode(2, ListNode(6))

lists: List[Optional[ListNode]] = [l1, l2, l3]
result = Solution().mergeKLists(lists)

# Print merged result
print("[", end="")
while result:
    print(result.val, end=",")
    result = result.next
print("\b]")