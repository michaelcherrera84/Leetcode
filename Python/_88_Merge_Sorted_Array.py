from typing import List


class Solution:
    """You are given two integer arrays `nums1` and `nums2`, sorted in **non-decreasing order**, and two integers `m` and `n`,
    representing the number of elements in nums1 and nums2 respectively.

    Merge `nums1` and `nums2` into a single array sorted in **non-decreasing order**.

    The final sorted array should not be returned by the function, but instead be *stored inside the array* `nums1`. To accommodate
    this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n`
    elements are set to `0` and should be ignored. `nums2` has a length of `n`.

    #### Example 1:
    > **Input:** nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    > **Output:** [1,2,2,3,5,6]
    > **Explanation:** The arrays we are merging are [1,2,3] and [2,5,6].
    > The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

    #### Example 2:
    > **Input:** nums1 = [1], m = 1, nums2 = [], n = 0
    > **Output:** [1]
    > **Explanation:** The arrays we are merging are [1] and [].
    > The result of the merge is [1].

    #### Example 3:
    > **Input:** nums1 = [0], m = 0, nums2 = [1], n = 1
    > **Output:** [1]
    > **Explanation:** The arrays we are merging are [] and [1].
    > The result of the merge is [1].
    > Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.

    #### Constraints:
    - `nums1.length == m + n}`
    - `nums2.length == n}`
    - `0 <= m, n <= 200}`
    - `1 <= m + n <= 200}`
    - `-10^9 <= nums1[i], nums2[j] <= 10^9}`

    Follow up: Can you come up with an algorithm that runs in `O(m + n)` time?
    """

    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """Optimal LeetCode solution:
        - Runs in `O(m + n)` time
        - Users `O(1)` extra space

        ***Key Idea:** Nums 1 already has extra space at the END, so we merge
        from the back to avoid overwriting values we still need.*

        Args:
            nums1 (List[int]): first list
            m (int): lenth of nums1 (not including space for nums2)
            nums2 (List[int]): second list
            n (int): lenth of nums2
        """
     
        i = m - 1           # last number in nums1
        j = n - 1           # last number in nums2
        k = m + n - 1       # last position in nums1

        # While both lists still have elements to compare...
        while i >= 0 and j >= 0:

            # Place the larger element at the end.
            if nums1[i] > nums2[j]:
                nums1[k] = nums1[i]
                i -= 1
            else:
                nums1[k] = nums2[j]
                j -= 1

            # Move the insertion pointer left.
            k -= 1

        # If nums2 still has numbers place them in nums1
        while j >= 0:
            nums1[k] = nums2[j]
            j -= 1
            k -= 1

sol = Solution()
nums1 = [6, 7, 8, 9, 0, 0, 0]
nums2 = [1, 2, 3]
sol.merge(nums1, 4, nums2, 3)
print(nums1)