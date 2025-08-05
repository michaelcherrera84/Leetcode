/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * @param {ListNode} l1 linked list 1
 * @param {ListNode} l2 linked list 2
 * @returns {ListNode} linked list representing the sum of the two numbers
 */
var addTwoNumbers = function (l1, l2) {

    // Initialize a dummy head to simplify the code
    let dummyHead = new ListNode(0);

    // Pointers for l1, l2, and the current node in the result list
    let p = l1, q = l2, current = dummyHead;

    // Variable to store carry-over value
    let carry = 0;

    // Loop through both lists until both are fully traversed
    // Add corresponding digits along with carry
    // If one list is shorter, treat missing digits as 0
    // Create new nodes for the result list
    // Update carry for next iteration
    while (p !== null || q !== null) {
        let x = (p !== null) ? p.val : 0;
        let y = (q !== null) ? q.val : 0;
        let sum = carry + x + y;
        carry = Math.floor(sum / 10);
        current.next = new ListNode(sum % 10);
        current = current.next;

        if (p !== null) p = p.next;
        if (q !== null) q = q.next;
    }

    // If there's a carry left after the last addition, add a new node
    if (carry > 0) {
        current.next = new ListNode(carry);
    }

    return dummyHead.next;
};

function ListNode(val, next) {
    this.val = (val === undefined ? 0 : val)
    this.next = (next === undefined ? null : next)
}


// Example usage:
let l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
let l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
let result = addTwoNumbers(l1, l2);
let output = [];
while (result !== null) {
    output.push(result.val);
    result = result.next;
}
console.log(output); // [7, 0, 8]