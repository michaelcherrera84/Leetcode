#include <vector>
#include <iostream>

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
class Solution
{
public:
    std::vector<int> twoSum(std::vector<int> &nums, int target)
    {
        std::vector<int> result;

        // Loop through each element
        for (size_t i = 0; i < nums.size() - 1; i++)
        {
            // Only check elements *after* the current one to avoid duplicates and self-pairs
            for (size_t j = i + 1; j < nums.size(); j++)
            {
                // Check if this pair adds up to the target
                if (nums[i] + nums[j] == target)
                {
                    result.push_back(i); // Store index of the first number
                    result.push_back(j); // Store index of the second number
                    return result;       // Return early after finding the first valid pair
                }
            }
        }

        // If no pair found, return a placeholder
        return {-1, -1};
    }
};

class BetterSolution
{
public:
    std::vector<int> twoSum(std::vector<int> &nums, int target)
    {
        std::unordered_map<int, int> map; // maps number → index

        for (int i = 0; i < nums.size(); i++)
        {
            int complement = target - nums[i]; // What number do we need to reach target?

            // Check if that number has already been seen
            if (map.find(complement) != map.end())
            {
                // Found the pair: return indices
                return {map[complement], i};
            }

            // Otherwise, store the current number and its index
            map[nums[i]] = i;
        }

        // If no solution found, return placeholder
        return {-1, -1};
    }
};

int main()
{

    std::vector<int> nums = {1, 2, 3, 12, 5};

    auto solution = Solution();
    auto result = solution.twoSum(nums, 7);
    std::cout << "[" << result[0] << ", " << result[1] << "]";

    auto betterSolution = BetterSolution();
    result = betterSolution.twoSum(nums, 7);
    std::cout << "[" << result[0] << ", " << result[1] << "]";

    return 0;
}