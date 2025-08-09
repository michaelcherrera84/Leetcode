# Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

# Example 1:
# Input: head = [1,2,3,4]
# Output: [2,1,4,3]

# Example 2:
# Input: head = []
# Output: []

# Example 3:
# Input: head = [1]
# Output: [1]

# Example 4:
# Input: head = [1,2,3]
# Output: [2,1,3]

# Constraints:

# The number of nodes in the list is in the range [0, 100].
# 0 <= Node.val <= 100


class ListNode:
    """A node in a singly linked list.

    Attributes:
        val (int): The value stored in the node.
        next (ListNode|None): Pointer to the next node in the list.
    """
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution(object):
    """Solution class containing linked list operations."""

    def add_node(self, val):
        """Appends a new node to the end of the linked list.

        Args:
            val (int): Value of the new node.
        """
        if not hasattr(self, 'head') or self.head is None:
            # If head does not exist or is None, create the first node
            self.head = ListNode(val, None)
        else:
            # Traverse until the last node
            p = self.head
            while p.next:
                p = p.next
            # Attach the new node at the end
            p.next = ListNode(val, None)

    def print_list(self, head):
        """Prints the linked list in [a, b, c, ...] format.

        Args:
            head (ListNode|None): The head node of the list to print.
        """
        values = []
        p = head
        while p:
            # Collect values from each node
            values.append(p.val)
            p = p.next
        print("[" + ", ".join(map(str, values)) + "]")

    def swap_pairs(self, head):
        """Swaps every two adjacent nodes in the linked list (iterative approach).

        Args:
            head (ListNode|None): The head of the linked list.

        Returns:
            ListNode|None: The new head after swapping pairs.
        """
        dummy = ListNode(0)  # Temporary starting node to simplify edge cases
        dummy.next = head
        prev = dummy

        # Loop while there are at least two nodes left to swap
        while prev.next and prev.next.next:
            first = prev.next
            second = first.next

            # Swap nodes
            prev.next, first.next, second.next = second, second.next, first

            # Move prev two nodes forward
            prev = first

        return dummy.next

    def swap_pairs_recursive(self, head):
        """Swaps every two adjacent nodes in the linked list (recursive approach).

        Args:
            head (ListNode|None): The head of the linked list.

        Returns:
            ListNode|None: The new head after swapping pairs.
        """
        # Base case: if less than 2 nodes remain, return head as is
        if not head or not head.next:
            return head

        first = head
        second = head.next

        # Swap: second becomes the head, and first is linked to the result of the next swap
        first.next = self.swap_pairs_recursive(second.next)
        second.next = first

        return second


def main():
    """Main function to demonstrate swapping pairs in a linked list."""
    sol = Solution()
    sol.head = None  # Initialize empty list

    # Example: Create list [1, 2, 3, 4]
    for i in range(1, 5):
        sol.add_node(i)

    print("Original list:")
    sol.print_list(sol.head)

    # Iterative swap
    sol.head = sol.swap_pairs(sol.head)
    print("After swapping pairs (iterative):")
    sol.print_list(sol.head)

    # Recursive swap (swap back to original)
    sol.head = sol.swap_pairs_recursive(sol.head)
    print("After swapping pairs back (recursive):")
    sol.print_list(sol.head)


if __name__ == "__main__":
    main()