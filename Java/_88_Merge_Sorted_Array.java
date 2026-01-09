
import java.util.Arrays;

/**
 * You are given two integer arrays {@code nums1} and {@code nums2}, sorted in <b>non-decreasing order</b>, and two integers {@code m} and {@code n},
 * representing the number of elements in nums1 and nums2 respectively.
 * <p>
 * Merge {@code nums1} and {@code nums2} into a single array sorted in <b>non-decreasing order</b>.
 * <p>
 * The final sorted array should not be returned by the function, but instead be <i>stored inside the array</i> {@code nums1}. To accommodate
 * this, {@code nums1} has a length of {@code m + n}, where the first {@code m} elements denote the elements that should be merged, and the last {@code n}
 * elements are set to {@code 0} and should be ignored. {@code nums2} has a length of n.
 * <p>
 * <pre> </pre>
 * <h4>Example 1:</h4>
 * <p><b>Input:</b> nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * <p><b>Output:</b> [1,2,2,3,5,6]
 * <p><b>Explanation:</b> The arrays we are merging are [1,2,3] and [2,5,6].
 * <p>The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * <pre> </pre>
 * <h4>Example 2:</h4>
 * <p><b>Input:</b> nums1 = [1], m = 1, nums2 = [], n = 0
 * <p><b>Output:</b> [1]
 * <p><b>Explanation:</b> The arrays we are merging are [1] and [].
 * <p>The result of the merge is [1].
 * <pre> </pre>
 * <h4>Example 3:</h4>
 * <p><b>Input:</b> nums1 = [0], m = 0, nums2 = [1], n = 1
 * <p><b>Output:</b> [1]
 * <p><b>Explanation:</b> The arrays we are merging are [] and [1].
 * <p>The result of the merge is [1].
 * <p>Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 * <pre> </pre>
 * <h4>Constraints:</h4>
 * <ul>
 * <li>{@code nums1.length == m + n}</li>
 * <li>{@code nums2.length == n}</li>
 * <li>{@code 0 <= m, n <= 200}</li>
 * <li>{@code 1 <= m + n <= 200}</li>
 * <li>{@code -10^9 <= nums1[i], nums2[j] <= 10^9}</li>
 * </ul>
 * Follow up: Can you come up with an algorithm that runs in {@code O(m + n)} time?
 */
public class _88_Merge_Sorted_Array {

    /**
     * Straightforward solution:
     * - Copy the meaningful parts of both arrays into a new array
     * - Sort the combined array
     * - Copy the result back into nums1
     *
     * This works, but it uses extra memory and sorting.
     */
    class Solution {

        public void merge(int[] nums1, int m, int[] nums2, int n) {

            // Create a new array large enough to hold both arrays' valid elements
            int[] allNums = new int[m + n];

            // Copy the first m elements from nums1 (ignore trailing zeros)
            System.arraycopy(nums1, 0, allNums, 0, m);

            // Copy all n elements from nums2 immediately after nums1's elements
            System.arraycopy(nums2, 0, allNums, m, n);

            // Sort the combined array
            Arrays.sort(allNums);

            // Copy the sorted result back into nums1 (required by the problem)
            for (int i = 0; i < nums1.length; i++) {
                nums1[i] = allNums[i];
            }
        }
    }

    /**
     * Optimal LeetCode solution:
     * - Runs in O(m + n) time
     * - Uses O(1) extra space
     *
     * Key idea:
     * nums1 already has extra space at the END.
     * So we merge from the back to avoid overwriting values we still need.
     */
    class BetterSolution {

        public void merge(int[] nums1, int m, int[] nums2, int n) {

            // Pointer to the last valid element in nums1
            int i = m - 1;

            // Pointer to the last element in nums2
            int j = n - 1;

            // Pointer to the last position in nums1 (including empty space)
            int k = m + n - 1;

            // While both arrays still have elements to compare
            while (i >= 0 && j >= 0) {

                // Place the larger of the two elements at the end
                if (nums1[i] > nums2[j]) {
                    nums1[k] = nums1[i--];
                } else {
                    nums1[k] = nums2[j--];
                }

                // Move the insertion pointer left
                k--;
            }

            /*
             * If nums2 still has remaining elements, copy them.
             *
             * No need to copy nums1's remaining elements:
             * they are already in the correct position.
             */
            while (j >= 0) {
                nums1[k--] = nums2[j--];
            }
        }
    }


    public static void main(String[] args) {
        var sol = new _88_Merge_Sorted_Array().new BetterSolution();

        // Tests
        // var nums1 = new int[] {-1, 0, 0, 3, 3, 3, 0, 0, 0};
        // var nums2 = new int[] {1, 2, 2};
        // var nums1 = new int[] {1, 2, 3, 0, 0, 0};
        // var nums2 = new int[] {2, 5, 6};

        /*
         * nums1 has extra space (zeros) at the end to hold nums2.
         * m = number of valid elements in nums1
         * n = number of elements in nums2
         */
        var nums1 = new int[]{-1, 3, 0, 0, 0, 0, 0};
        var nums2 = new int[]{0, 0, 1, 2, 3};

        sol.merge(nums1, 2, nums2, 5);
        Arrays.stream(nums1).forEach(e -> System.out.print(e + " "));
    }
}
