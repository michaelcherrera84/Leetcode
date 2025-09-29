/// Definition for a singly-linked list node.
public class ListNode {
    /// Value stored in the node
    public var val: Int
    /// Reference to the next node in the list (or `nil` if this is the last node)
    public var next: ListNode?
    
    /// Initializes a node with value `0` and no next node.
    public init() {
        self.val = 0
        self.next = nil
    }
    
    /// Initializes a node with the given value and no next node.
    /// - Parameter val: Integer value to store in this node.
    public init(_ val: Int) {
        self.val = val
        self.next = nil
    }
    
    /// Initializes a node with the given value and a reference to the next node.
    /// - Parameters:
    ///   - val: Integer value to store in this node.
    ///   - next: The next node in the list (or `nil` if none).
    public init(_ val: Int, _ next: ListNode?) {
        self.val = val
        self.next = next
    }
}

/// Given the head of a linked list, remove the nth node from the end of the list and return its head.
///
/// **Example 1:**
/// - **Input:** head = [1,2,3,4,5], n = 2
/// - **Output:** [1,2,3,5]
///
/// **Example 2:**
/// - **Input:** head = [1], n = 1
/// - **Output:** [] 
///
/// **Example 3:**
/// - **Input:** head = [1,2], n = 1
/// - **Output:** [1]
///
/// **Constraints:**
/// - The number of nodes in the list is `sz`.
/// - `1 <= sz <= 30`
/// - `0 <= Node.val <= 100`
/// - `1 <= n <= sz`
class Solution {
    
    /// Removes the n-th node from the end of a singly linked list.
    ///
    /// - Parameters:
    ///   - head: The head of the linked list.
    ///   - n: The position from the end (1-based index) of the node to remove.
    /// - Returns: The head of the modified linked list.
    func removeNthFromEnd(_ head: ListNode?, _ n: Int) -> ListNode? {
        var fast: ListNode? = head
        var slow: ListNode? = head
        
        // Move `fast` pointer n steps ahead.
        for _ in 0..<n {
            fast = fast?.next
        }
        
        // If fast is nil, that means the head itself should be removed.
        if fast == nil {
            return head?.next
        }
        
        // Move both `fast` and `slow` until `fast` reaches the end.
        // At that point, `slow` will be just before the node to remove.
        while fast?.next != nil {
            fast = fast?.next
            slow = slow?.next
        }
        
        // If slow.next is nil, it means there was no node to remove.
        if slow?.next === nil {
            return nil
        }
        
        // Skip the target node by linking slow to the node after slow.next.
        slow?.next = slow?.next?.next
        
        return head
    }
}

// Example usage:
let sol = Solution()
let l1 = ListNode(1)
l1.next = ListNode(2)
l1.next?.next = ListNode(3)
l1.next?.next?.next = ListNode(4)
l1.next?.next?.next?.next = ListNode(5)

var list = sol.removeNthFromEnd(l1, 2)

print("[", terminator: "")
while list != nil {
    print(list!.val, terminator: ",")
    list = list!.next
}
print("\u{8}]") // overwrite last comma and close bracket