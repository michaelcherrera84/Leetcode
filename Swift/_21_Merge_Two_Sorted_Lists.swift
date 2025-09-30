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

/// You are given the heads of two sorted linked lists `list1` and `list2`.
/// Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
/// Return the head of the merged linked list.
///
/// **Example 1:**
///  - **Input:** list1 = [1,2,4], list2 = [1,3,4]
///  - **Output:** [1,1,2,3,4,4]
///
/// **Example 2:**
///  - **Input:** list1 = [], list2 = []
///  - **Output:** []
///
/// **Example 3:**
///  - **Input:** list1 = [], list2 = [0]
///  - **Output:** [0]
///
/// **Constraints:**
///  - The number of nodes in both lists is in the range `[0, 50]`.
///  - `-100 <= Node.val <= 100`
///  - Both `list1` and `list2` are sorted in non-decreasing order.
class Solution {

    /// Merges two sorted singly linked lists into one sorted list.
    ///
    /// Given the heads of two sorted linked lists (`list1` and `list2`, this method merges
    /// them by splicing together nodes into a single sorted list.
    /// - Parameters:
    ///   - list1: Pointer to the head of the first sorted list
    ///   - list2: Pointer to the head of the second sorted list
    /// - Returns: Pointer to the head of the newly merged sorted list
    func mergeTwoLists(_ list1: ListNode?, _ list2: ListNode?) -> ListNode? {

        // Dummy node acts as a placehoder for the new head.
        let dummyHead = ListNode(0)
        var current: ListNode? = dummyHead
        var l1 = list1
        var l2 = list2

        // Merge while both lists will have nodes
        while l1 != nil && l2 != nil {
            if l1!.val < l2!.val {
                current?.next = l1  // attach list1 node
                l1 = l1?.next
            } else {
                current?.next = l2  // attach list2 node
                l2 = l2?.next
            }
            current = current?.next  // advance current
        }

        // Attach the remainder of whichever list is not empty
        current?.next = l1 ?? l2

        // the merged list starts after the dummy
        return dummyHead.next
    }

    /// Print the values of a liked list
    /// - Parameter head: the list to print
    func printList(_ head: ListNode?) {
        var current = head
        print("[", terminator: "")
        while current != nil {
            print(current!.val, terminator: ",")
            current = current!.next
        }
        print("\u{8}]")
    }
}

// Example usage:
var list1: ListNode? = ListNode(1, ListNode(3, ListNode(5)))
var list2: ListNode? = ListNode(2, ListNode(4, ListNode(6)))
let mergedList = Solution().mergeTwoLists(list1, list2)
Solution().printList(mergedList)
