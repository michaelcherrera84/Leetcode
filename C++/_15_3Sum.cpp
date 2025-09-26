#include <iostream>

/// @brief Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
///
/// Notice that the solution set must not contain duplicate triplets.
///
/// Example 1:
/// Input: nums = [-1,0,1,2,-1,-4]
/// Output: [[-1,-1,2],[-1,0,1]]
/// Explanation:
/// nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
/// nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
/// nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
/// The distinct triplets are [-1,0,1] and [-1,-1,2].
/// Notice that the order of the output and the order of the triplets does not matter.
///
/// Example 2:
/// Input: nums = [0,1,1]
/// Output: []
/// Explanation: The only possible triplet does not sum up to 0.
///
/// Example 3:
/// Input: nums = [0,0,0]
/// Output: [[0,0,0]]
/// Explanation: The only possible triplet sums up to 0.
///
/// Constraints:
/// 3 <= nums.length <= 3000
/// -105 <= nums[i] <= 105
class Solution
{
public:
    /// @brief Naive O(n^3) solution to the 3Sum problem.
    /// Given an integer array nums, returns all unique triplets [nums[i], nums[j], nums[k]]
    /// such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    ///
    /// This implementation checks every possible triplet and then manually filters duplicates.
    /// It is correct but inefficient for large input sizes.
    /// @param nums array of nums
    /// @return array of triplets
    std::vector<std::vector<int>> threeSum(std::vector<int> &nums)
    {
        std::vector<std::vector<int>> result;

        // Iterate over every pair (i, j)
        for (size_t i = 0; i < nums.size() - 1; i++)
        {
            int x = nums[i];
            for (size_t j = i + 1; j < nums.size(); j++)
            {
                int y = nums[j];
                int find = -(x + y); // The number we want to complete the triplet

                // Search for that number in the rest of the array
                for (size_t k = j + 1; k < nums.size(); k++)
                {
                    if (nums[k] == find)
                    {
                        std::vector<int> triplet = {nums[i], nums[j], nums[k]};
                        bool isDuplicate = false;

                        // Sort the triplet to make duplicate detection easier
                        std::vector<int> sort = triplet;
                        for (size_t l = 0; l < sort.size() - 1; l++)
                        {
                            for (size_t m = l + 1; m < sort.size(); m++)
                            {
                                if (sort[l] > sort[m])
                                {
                                    std::swap(sort[l], sort[m]);
                                }
                            }
                        }

                        // Compare against all previous triplets in the result
                        for (std::vector<int> temp : result)
                        {
                            // Ensure each stored triplet is sorted before comparing
                            for (size_t l = 0; l < temp.size() - 1; l++)
                            {
                                for (size_t m = l + 1; m < temp.size(); m++)
                                {
                                    if (temp[l] > temp[m])
                                    {
                                        std::swap(temp[l], temp[m]);
                                    }
                                }
                            }

                            if (temp == sort)
                                isDuplicate = true;
                        }

                        // Only add if not duplicate
                        if (!isDuplicate)
                        {
                            result.push_back(triplet);
                        }
                    }
                }
            }
        }

        return result;
    };
};

class BetterSolution
{
public:
    /// @brief Optimized O(n^2) solution to the 3Sum problem using sorting and two pointers.
    /// This approach sorts the array and then fixes one number at a time, using a left and right
    /// pointer to find pairs that sum to the negative of that fixed number.
    /// Duplicate triplets are avoided by skipping repeated numbers during iteration.
    /// @param nums array of nums
    /// @return array of triplets
    std::vector<std::vector<int>> threeSum(std::vector<int> &nums)
    {
        std::vector<std::vector<int>> result;

        // Sort to simplify duplicate handling and enable two-pointer scanning
        std::sort(nums.begin(), nums.end());

        // Fix one number nums[i] at a time
        for (size_t i = 0; i < nums.size(); i++)
        {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            size_t l = i + 1;           // left pointer
            size_t r = nums.size() - 1; // right pointer

            // Move l and r inward to find valid triplets
            while (l < r)
            {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0)
                {
                    result.push_back({nums[i], nums[l], nums[r]});

                    // Skip duplicates
                    while (l < r && nums[l] == nums[l + 1])
                        l++;
                    while (l < r && nums[r] == nums[r - 1])
                        r--;

                    l++;
                    r--;
                }
                else if (sum < 0)
                {
                    l++;
                }
                else
                {
                    r--;
                }
            }
        }

        return result;
    }
};

void print(std::vector<std::vector<int>>);

int main()
{
    auto sol = Solution();
    auto sol1 = BetterSolution();
    std::vector<int> nums = {-1, 0, 1, 2, -1, -4};
    std::vector<std::vector<int>> result = sol.threeSum(nums);
    std::vector<std::vector<int>> result1 = sol1.threeSum(nums);

    print(result);
    std::cout << std::endl;
    print(result1);

    return 0;
}

/// @brief Helper function to print a 2D vector of integers.
/// @param tripletArray array of triplet arrays to print
void print(std::vector<std::vector<int>> tripletArray)
{
    std::cout << "[";
    for (std::vector<int> triplet : tripletArray)
    {
        std::cout << "[";
        for (int i = 0; i < triplet.size(); i++)
        {
            std::cout << triplet[i];
            if (i != triplet.size() - 1)
            {
                std::cout << ", ";
            }
        }
        std::cout << "]";
    }
    std::cout << "]";
}