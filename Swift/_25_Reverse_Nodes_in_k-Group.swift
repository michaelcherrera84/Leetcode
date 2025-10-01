/// Definition for singly-linked list.
public class ListNode {
    public var val: Int
    public var next: ListNode?

    public init() {
        self.val = 0
        self.next = nil
    }
    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }
    public init(_ val: Int, _ next: ListNode?) {
        self.val = val
        self.next = next
    }
}

/// Given the `head` of a linked list, reverse the nodes of the list `k` at a time, and return _the modified list_.
///
/// `k` is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of `k` then left-out nodes, in the end, should remain as it is.
///
/// You may not alter the values in the list's nodes, only nodes themselves may be changed.
///
/// #### Example 1:
///   **Input:** head = [1,2,3,4,5], k = 2
///   **Output:** [2,1,4,3,5]
///
/// #### Example 2:
///   **Input:** head = [1,2,3,4,5], k = 3
///   **Output:** [3,2,1,4,5]
///
/// #### Constraints:
/// - The number of nodes in the list is `n`.
/// - `1 <= k <= n <= 5000`
/// - `0 <= Node.val <= 1000`
class Solution {

    /// Reverses the nodes of a list `k` at a time, and returns the modified list.
    /// - Parameters:
    ///   - head: head of the singly-linked list
    ///   - k: number of nodes to reverse at a time
    /// - Returns: the modified list
    func reverseKGroup(_ head: ListNode?, _ k: Int) -> ListNode? {
        let groupTail: ListNode? = head     // Will become the tail of the reversed group
        var prev: ListNode? = nil           // Tracks the previous node during reversal
        var current: ListNode? = head       // Tracks the current node being processed
        var next: ListNode? = nil           // Temporarily stores the next node

        // First, check if there are at least k nodes to reverse
        var test = head
        for _ in 0..<k {
            if test != nil {
                test = test?.next
            } else {
                // Not enough nodes: return the head unchanged
                return head
            }
        }

        // Reverse exactly k nodes
        for _ in 0..<k {
            next = current?.next  // Store next node
            current?.next = prev  // Reverse pointer
            prev = current  // Move prev forward
            current = next  // Move current forward
        }

        // After reversing k nodes, recurse on the rest of the list
        if next != nil {
            groupTail?.next = reverseKGroup(next, k)
        }

        // prev now points to the new head of the reversed group
        return prev
    }
}

var list = ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5)))))
var result = Solution().reverseKGroup(list, 3)
while result != nil {
    print(result!.val, terminator: " ")
    result = result?.next
}