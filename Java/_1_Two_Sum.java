import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9 
 * Output: [0,1] 
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1]. 
 * 
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6 
 * Output: [1,2] 
 * 
 * Example 3:
 * Input: nums = [3,3], target = 6 
 * Output: [0,1]
 *
 * Constraints:
 * 2 <= nums.length <= 104 
 * -109 <= nums[i] <= 109 
 * -109 <= target <= 109 
 * Only one valid answer exists.
 */
public class _1_Two_Sum {

    /**
     * Finds two indices in the array such that the numbers at those indices add
     * up to the target.
     *
     * @param nums an array of integers
     * @param target the target sum
     * @return an array containing the two indices
     */
    public int[] twoSum(int[] nums, int target) {

        // Using a HashMap to store the numbers and their indices
        Map<Integer, Integer> map = new java.util.HashMap<>();

        // Iterating through the array
        for (int i = 0; i < nums.length; i++) {

            // Calculating the complement of the current number
            // The complement is the number that, when added to nums[i], equals the target
            // If the complement exists in the map, we have found our solution
            // Otherwise, we store the current number and its index in the map
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // Store the current number and its index in the map
            map.put(nums[i], i);
        }

        // If no solution is found, throw an exception
        // This is a safeguard; the problem guarantees that there is exactly one solution
        throw new IllegalArgumentException("No two sum solution");
    }
}
