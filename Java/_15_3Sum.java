
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j],
 * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
 * nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1: Input: nums = [-1,0,1,2,-1,-4] Input: nums = [-1,0,1,2,-1,-4]
 *
 * Explanation: nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0. nums[1] +
 * nums[2] + nums[4] = 0 + 1 + (-1) = 0. nums[0] + nums[3] + nums[4] = (-1) + 2
 * + (-1) = 0. The distinct triplets are [-1,0,1] and [-1,-1,2]. Notice that the
 * order of the output and the order of the triplets does not matter.
 *
 * Example 2: Input: nums = [0,1,1] Output: []
 *
 * Explanation: The only possible triplet sums up to 0.
 *
 * Example 3: Input: nums = [0,0,0] Output: [[0,0,0]]
 *
 * Explanation: The only possible triplet sums up to 0.
 *
 * Constraints: 3 <= nums.length <= 3000 -105 <= nums[i] <= 105
 */
public class _15_3Sum {

    public static class Solution {

        /**
         * O(n^2) solution to the 3Sum problem using sorting and two pointers.
         * This approach sorts the array and then fixes one number at a time,
         * using a left and right pointer to find pairs that sum to the negative
         * of that fixed number. Duplicate triplets are avoided by skipping
         * repeated numbers during iteration.
         *
         * @param nums array of numbers
         * @return list of triplets
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();

            // Sort to simplify duplicate handling and enable two-pointer scanning
            Arrays.sort(nums);

            // Fix one number nums[i] at a time
            for (int i = 0; i < nums.length; i++) {
                // Skip duplicates for the first number
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int l = i + 1;              // left pointer
                int r = nums.length - 1;    // right pointer

                // Move l and r inward to find valid triplets.
                while (l < r) {
                    int sum = nums[i] + nums[l] + nums[r];

                    if (sum == 0) {
                        result.add(new ArrayList<>());
                        result.getLast().addAll(List.of(nums[i], nums[l], nums[r]));

                        // Skip duplicates
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }

                        l++;
                        r--;
                    } else if (sum < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
