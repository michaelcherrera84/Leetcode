# Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
#
# You may assume that each input would have exactly one solution, and you may not use the same element twice.
#
# You can return the answer in any order.
#
# Example 1:
#
# Input: nums = [2,7,11,15], target = 9
# Output: [0,1]
# Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
#
# Example 2:
#
# Input: nums = [3,2,4], target = 6
# Output: [1,2]
#
# Example 3:
#
# Input: nums = [3,3], target = 6
# Output: [0,1]
#
# Constraints:
#
# 2 <= nums.length <= 104
# -109 <= nums[i] <= 109
# -109 <= target <= 109
# Only one valid answer exists.

class Solution:
    def twoSum(self, nums, target):
        map = {}  # Dictionary to store number -> index

        for i in range(len(nums)):
            complement = target - nums[i]  # What number do we need?

            if complement in map:
                # If we've seen the complement before, return the indices
                return [map[complement], i]
            
            # Otherwise, store this number and its index
            map[nums[i]] = i

        # If no pair found, return placehoder
        return [-1, -1]
    
solution = Solution()
result = solution.twoSum([1, 2, 3, 12, 5], 7)
print(result)
