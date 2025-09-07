/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 * the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1: Input: nums1 = [1,3], nums2 = [2] Output: 2.00000 Explanation:
 * merged array = [1,2,3] and median is 2.
 *
 * Example 2: Input: nums1 = [1,2], nums2 = [3,4] Output: 2.50000 Explanation:
 * merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Constraints: nums1.length == m nums2.length == n 0 <= m <= 1000 0 <= n <=
 * 1000 1 <= m + n <= 2000 -106 <= nums1[i], nums2[i] <= 106
 */
public class _4_Median_of_Two_Sorted_Arrays {

    /**
     * A straightforward merge-based solution that runs in O(m + n) time. Only
     * merges enough elements to determine the median.
     */
    public static class Solution {

        /**
         * Finds the median of two sorted arrays by partially merging them up to
         * the middle.
         *
         * @param nums1 First sorted array.
         * @param nums2 Second sorted array.
         * @return The median value as a double.
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalLength = nums1.length + nums2.length;
            int mid = totalLength / 2;
            boolean even = totalLength % 2 == 0;

            // Array to hold just enough elements to compute the median
            int[] combined = new int[mid + 1];

            // Three pointers: i for combined, j for nums1, k for nums2
            for (int i = 0, j = 0, k = 0; i < mid + 1; i++) {
                if (j < nums1.length && k < nums2.length) {
                    // Choose the smaller element from the front of nums1 or nums2
                    if (nums1[j] > nums2[k]) {
                        combined[i] = nums2[k++];
                    } else {
                        combined[i] = nums1[j++];
                    }
                } else if (j < nums1.length) {
                    combined[i] = nums1[j++];
                } else {
                    combined[i] = nums2[k++];
                }
            }

            // Return average of middle two if even, else the middle one
            if (even) {
                return (combined[mid - 1] + combined[mid]) / 2.0;
            } else {
                return combined[mid];
            }
        }
    }

    /**
     * A more optimal solution using recursive binary search, achieving
     * O(log(min(m, n))) complexity.
     */
    public static class BetterSolution {

        /**
         * Public method to find the median of two sorted arrays.
         *
         * @param nums1 First sorted array.
         * @param nums2 Second sorted array.
         * @return The median value as a double.
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalLength = nums1.length + nums2.length;
            int mid = totalLength / 2;
            boolean even = totalLength % 2 == 0;

            // If even, find the average of two middle elements
            if (even) {
                return (findKth(nums1, nums2, mid - 1, 0, nums1.length - 1, 0, nums2.length - 1)
                        + findKth(nums1, nums2, mid, 0, nums1.length - 1, 0, nums2.length - 1)) / 2;
            }

            // If odd, return the middle element directly
            return findKth(nums1, nums2, mid, 0, nums1.length - 1, 0, nums2.length - 1);
        }

        /**
         * Recursive helper function to find the k-th smallest element between
         * two sorted arrays.
         *
         * @param nums1 First sorted array.
         * @param nums2 Second sorted array.
         * @param k Index of the element to find in the merged array.
         * @param nums1Start Start index in nums1.
         * @param nums1End End index in nums1.
         * @param nums2Start Start index in nums2.
         * @param nums2End End index in nums2.
         * @return The k-th smallest element in the merged array.
         */
        private double findKth(int[] nums1, int[] nums2, int k,
                int nums1Start, int nums1End,
                int nums2Start, int nums2End) {

            // If nums1 is exhausted, return from nums2 the element at the difference of the target index
            // and the quantity of passed indexes from nums1
            if (nums1End < nums1Start) {
                return nums2[k - nums1Start];
            }

            // If nums2 is exhausted, return from nums1 the element at the difference of the target index
            // and the quantity of passed indexes from nums2
            if (nums2End < nums2Start) {
                return nums1[k - nums2Start];
            }

            int nums1Mid = (nums1Start + nums1End) / 2;
            int nums2Mid = (nums2Start + nums2End) / 2;

            int nums1MidValue = nums1[nums1Mid];
            int nums2MidValue = nums2[nums2Mid];

            // If k is in the right half of the combined indexes, remove the left of the smaller range.
            // Otherwise, remove the right of the larger range.
            if (nums1Mid + nums2Mid < k) {
                if (nums1MidValue > nums2MidValue) {
                    return findKth(nums1, nums2, k, nums1Start, nums1End, nums2Mid + 1, nums2End);
                } else {
                    return findKth(nums1, nums2, k, nums1Mid + 1, nums1End, nums2Start, nums2End);
                }
            } else {
                if (nums1MidValue > nums2MidValue) {
                    return findKth(nums1, nums2, k, nums1Start, nums1Mid - 1, nums2Start, nums2End);
                } else {
                    return findKth(nums1, nums2, k, nums1Start, nums1End, nums2Start, nums2Mid - 1);
                }
            }
        }
    }

    /**
     * Demonstrates both solutions with sample inputs.
     */
    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 7, 8}));
        System.out.println(new BetterSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 7, 8}));
    }
}
