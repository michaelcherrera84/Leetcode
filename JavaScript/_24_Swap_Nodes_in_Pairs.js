// Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

// Example 1:
// Input: head = [1,2,3,4]
// Output: [2,1,4,3]

// Example 2:
// Input: head = []
// Output: []

// Example 3:
// Input: head = [1]
// Output: [1]

// Example 4:
// Input: head = [1,2,3]
// Output: [2,1,3]

// Constraints:

// The number of nodes in the list is in the range [0, 100].
// 0 <= Node.val <= 100

/**
 * Definition for singly-linked list.
 * 
 * @param {number} val - The value of the node.
 * @param {ListNode|null} next - The reference to the next node.
 */
function ListNode(val, next) {
    this.val = val === undefined ? 0 : val;
    this.next = next === undefined ? null : next;
}

/**
 * Swaps every two adjacent nodes in a singly-linked list.
 * Does not modify node values; only rearranges the node links.
 *
 * @param {ListNode|null} head - The head of the original linked list.
 * @return {ListNode|null} - The new head of the modified list.
 */
var swapPairs = function(head) {
    // Dummy node to simplify head swapping
    const dummy = new ListNode(0);
    dummy.next = head;

    // 'prev' is the node before the current pair
    let prev = dummy;

    // Loop while there are at least two nodes to swap
    while (prev.next && prev.next.next) {
        // Identify the two nodes to swap
        const first = prev.next;
        const second = first.next;

        // Swapping nodes by reassigning pointers
        first.next = second.next;  // Step 1: First node points to the node after second
        second.next = first;       // Step 2: Second node points to first
        prev.next = second;        // Step 3: Previous node points to second (new front)

        // Move to the next pair
        prev = first;
    }

    // Return the new head (next of dummy)
    return dummy.next;
};

/**
 * Recursively swaps every two adjacent nodes in a singly-linked list.
 * Does not modify node values; only rearranges the node links.
 *
 * @param {ListNode|null} head - The head of the original linked list.
 * @return {ListNode|null} - The new head of the modified list.
 */
var swapPairsRecursive = function(head) {
    // Base case: if the list is empty or has only one node, return it as-is
    if (!head || !head.next) {
        return head;
    }

    // Identify the first two nodes
    const first = head;
    const second = head.next;

    // Recursively call swapPairs on the rest of the list (starting after the second node)
    const restSwapped = swapPairsRecursive(second.next);

    // Perform the actual swap
    second.next = first;      // Second now points to first
    first.next = restSwapped; // First now points to the head of the swapped rest

    // Return new head (second becomes the new head of this segment)
    return second;
};

//////////////////////
// Test Setup Below //
//////////////////////

let head = null;

/**
 * Helper to append a node to the linked list.
 * @param {number} val - Value of the new node.
 */
function addNode(val) {
    if (!head) {
        head = new ListNode(val, null);
    } else {
        let p = head;
        while (p.next) {
            p = p.next;
        }
        p.next = new ListNode(val, null);
    }
}

/**
 * Helper to print the linked list in [a, b, c, ...] format.
 * @param {ListNode|null} head - The head of the linked list.
 */
function printList(head) {
    const values = [];
    let p = head;
    while (p) {
        values.push(p.val);
        p = p.next;
    }
    console.log("[" + values.join(", ") + "]");
}

// Example: [1, 2, 3, 4] -> [2, 1, 4, 3]
for (let i = 1; i <= 4; i++) {
    addNode(i);
}

console.log("Original list:");
printList(head);

head = swapPairs(head);

console.log("After swapping pairs:");
printList(head);

head = swapPairsRecursive(head);

console.log("After swapping pairs back:");
printList(head);