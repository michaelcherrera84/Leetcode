
import java.util.PriorityQueue;

/**
 * You are given an array of {@code k} linked-lists {@code lists}, each
 * linked-list is sorted in ascending order.
 * <br>
 * <i>Merge all the linked-lists into one sorted linked-list and return it.</i>
 * <br>
 * <pre>
 * <b>Example 1:</b><br>
 *   <b>Input:</b> lists = [[1,4,5],[1,3,4],[2,6]]
 *   <b>Output:</b> [1,1,2,3,4,4,5,6]
 *   <b>Explanation:</b> The linked-lists are:
 *   [
 *     1->4->5,
 *     1->3->4,
 *     2->6
 *   ]
 *   merging them into one sorted linked list:
 *   1->1->2->3->4->4->5->6
 *
 * <b>Example 2:</b>
 *   <b>Input:</b> lists = []
 *   <b>Output:</b> []
 *
 * <b>Example 3:</b>
 *   <b>Input:</b> lists = [[]]
 *   <b>Output:</b> []
 *
 * <b>Constraints:</b>
 * </pre>
 * <ul>
 * <li>{@code k == lists.length}</li>
 * <li>{@code 0 <= k <= 104}</li>
 * <li>{@code 0 <= lists[i].length <= 500}</li>
 * <li>{@code -104 <= lists[i][j] <= 104}</li>
 * <li>{@code lists[i]} is sorted in <b>ascending order</b>.</li>
 * <li>The sum of {@code lists[i].length} will not exceed 104.</li>
 * </ul>
 */
@SuppressWarnings("unused")
public class _23_Merge_k_Sorted_Lists {

    /**
     * Definition of a singly-linked list.
     */
    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {

        /**
         * Merges k sorted linked lists into a single sorted linked list. Uses a
         * priority queue to collect and sort all node values.
         *
         * @param lists an array of {@link ListNode} heads representing k sorted
         * linked lists
         * @return the head of the merged sorted linked list, or null if input
         * is empty
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null; // no lists to merge
            }

            // Min-heap to hold all node values from every list
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // Collect all values from all lists
            for (ListNode node : lists) {
                while (node != null) {
                    pq.add(node.val);
                    node = node.next;
                }
            }

            // If all lists were empty, return null
            if (pq.isEmpty()) {
                return null;
            }

            // Dummy head simplifies list building
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            // Build the merged sorted list from priority queue
            while (!pq.isEmpty()) {
                tail.next = new ListNode(pq.poll());
                tail = tail.next;
            }

            return dummy.next; // skip dummy node
        }
    }
}
