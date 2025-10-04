using System;

// int[] nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4];
// int[] expectedNums = [0, 1, 2, 3, 4];

// int k = new _26_Remove_Duplicates_from_Sorted_Array.Solution().RemoveDuplicates(nums);
// Console.WriteLine(k == expectedNums.Length);
// for (int i = 0; i < k; i++)
// {
//     Console.Write((nums[i] == expectedNums[i]) + " ");
// }

/// <summary>
/// Given an integer array <c>nums</c> sorted in <b>non-decreasing order</b>, remove the duplicates in-place such that each unique element appears only <b>once</b>. The <b>relative order</b> of the elements should be kept the <b>same</b>. Then return <i>the number of unique elements in</i> <c>nums</c>.
/// </summery>
/// <remarks>
/// Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
/// - Change the array <c>nums</b> such that the first <c>k</c> elements of <c>nums</b> contain the unique elements in the order they were present in <c>nums</b> initially. The remaining elements of <c>nums</b> are not important as well as the size of <c>nums</b>.
/// - Return <c>k</c>.
/// #### Custom Judge:
/// <code>
/// The judge will test your solution with the following code:
/// int[] nums = [...]; // Input array
/// int[] expectedNums = [...]; // The expected answer with correct length
/// int k = removeDuplicates(nums); // Calls your implementation 
/// assert k == expectedNums.length;
/// for (int i = 0; i < k; i++) {
///     assert nums[i] == expectedNums[i];
/// }
/// </code>
/// If all assertions pass, then your solution will be accepted.
/// 
/// #### Example 1:
/// <b>Input:</b> nums = [1,1,2]
/// <b>Output:</b> 2, nums = [1,2,_]
/// <b>Explanation:</b> Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
/// It does not matter what you leave beyond the returned k (hence they are underscores).
///
/// #### Example 2:
/// <b>Input:</b> nums = [0,0,1,1,1,2,2,3,3,4]
/// <b>Output:</b> 5, nums = [0,1,2,3,4,_,_,_,_,_]
/// <b>Explanation:</b> Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
/// It does not matter what you leave beyond the returned k (hence they are underscores).
///
/// #### Constraints:
/// <c>1 <= nums.length <= 3 * 104</c>
/// <c>-100 <= nums[i] <= 100</c>
/// <c>nums</c> is sorted in <b>non-decreasing</b> order.
/// </remarks>
public class _26_Remove_Duplicates_from_Sorted_Array
{
    public class Solution
    {
        /// <summary>
        /// Removes duplicates from a sorted integer array in-place,
        /// ensuring that each element appears only once.
        /// The relative order of the elements is preserved.
        /// </summary>
        /// <param name="nums">The input array of integers (sorted in non-decreasing order).</param>
        /// <returns>The number of unique elements in the array.</returns>
        public int RemoveDuplicates(int[] nums)
        {
            // Index j tracks the position where the next unique element should be placed.
            int j = 1;

            // Start from the second element (index 1), compare each element to the previous one.
            for (int i = 1; i < nums.Length; i++)
            {
                // If the current element is different from the previous one, it's unique.
                if (nums[i] != nums[i - 1])
                {
                    // Place the unique element at position j and increment j.
                    nums[j++] = nums[i];
                }
            }

            // j now represents the count of unique elements.
            return j;
        }
    }
}