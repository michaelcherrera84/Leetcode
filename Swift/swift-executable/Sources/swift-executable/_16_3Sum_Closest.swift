/// Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
///
/// Return the sum of the three integers.
///
/// You may assume that each input would have exactly one solution.
///
/// Example 1:
/// Input: nums = [-1,2,1,-4], target = 1
/// Output: 2
///
/// Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
///
/// Example 2:
/// Input: nums = [0,0,0], target = 1
/// Output: 0
///
/// Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
/// 
/// Constraints:
/// 3 <= nums.length <= 500
/// -1000 <= nums[i] <= 1000
/// -104 <= target <= 104
enum _16_3Sum_Closest {
    class Solution {

        /// Finds the sum of three integers in the input array `nums` such that the sum is closest to the given `target`.
        ///
        /// The algorithm:
        /// - Sorts the array first.
        /// - Iterates through each element, fixing one number at a time.
        /// - Uses a two-pointer technique (`l`, `r`) to find the closest sum to the target.
        ///
        /// - Parameters:
        ///   - nums: An array of integers (can be unsorted).
        ///   - target: The integer value to approach as closely as possible with the sum of three numbers.
        /// - Returns: An integer representing the sum of three numbers from `nums` that is closest to `target`.
        ///
        func threeSumClosest(_ nums: [Int], _ target: Int) -> Int {
            // Sort the array for two-pointer traversal
            let sorted = nums.sorted()

            // Initialize result with the sum of the first two and the last element
            var result = sorted[0] + sorted[1] + sorted[sorted.count - 1]

            // Iterate through each index as the "fixed" element
            for i in 0..<sorted.count - 2 {
                var l = i + 1  // left pointer
                var r = sorted.count - 1  // right pointer

                // Move pointers inward to find closest sum
                while l < r {
                    let sum = sorted[i] + sorted[l] + sorted[r]

                    // If exact match found, return immediately
                    if sum == target {
                        return target
                    }

                    // Adjust pointers based on comparison with target
                    if sum < target {
                        l += 1
                    } else {
                        r -= 1
                    }

                    // Update result if this sum is closer than the previous best
                    if abs(sum - target) < abs(result - target) {
                        result = sum
                    }
                }
            }

            return result
        }
    }
}
