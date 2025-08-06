 # Given two sorted arrays nums1 and nums2 of size m and n respectively, return
 # the median of the two sorted arrays.
 #
 # The overall run time complexity should be O(log (m+n)).
 #
 # Example 1: Input: nums1 = [1,3], nums2 = [2] Output: 2.00000 Explanation:
 # merged array = [1,2,3] and median is 2.
 #
 # Example 2: Input: nums1 = [1,2], nums2 = [3,4] Output: 2.50000 Explanation:
 # merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 #
 # Constraints: nums1.length == m nums2.length == n 0 <= m <= 1000 0 <= n <=
 # 1000 1 <= m + n <= 2000 -106 <= nums1[i], nums2[i] <= 106
 
class Solution:
    """
    An optimal solution using recursive binary search, achieving O(log(min(m, n))) complexity.
    """
    
    def findMedianSortedArrays(self, nums1, nums2):
        """
        Finds the median of two sorted arrays.
        """
        length = len(nums1) + len(nums2)

        # If total lenght of arrays is even, find the average of the two middle elements.
        if length % 2 == 0:
            return (self.__findKth(nums1, nums2, length // 2 - 1, 0, len(nums1) - 1, 0, len(nums2) - 1) + 
                self.__findKth(nums1, nums2, length // 2, 0, len(nums1) - 1, 0, len(nums2) - 1)) / 2
        # Otherwise, return the middle element.
        return self.__findKth(nums1, nums2, length // 2, 0, len(nums1) - 1, 0, len(nums2) - 1)
    

    def __findKth(self, nums1, nums2, k, nums1Start, nums1End, nums2Start, nums2End):
        """
        Recursive helper function to find the k-th smallest element between two sorted arrays.
        """

        # If nums1 is exhausted, return from nums2 the element at the difference of the target index
        # and the quantity of passed indexes from nums1
        if nums1End < nums1Start:
            return nums2[k - nums1Start]
        
        # If nums2 is exhausted, return from nums1 the element at the difference of the target index
        # and the quantity of passed indexes from nums2
        if nums2End < nums2Start:
            return nums1[k - nums2Start]
        
        nums1Mid = (nums1Start + nums1End) // 2; nums2Mid = (nums2Start + nums2End) // 2
        nums1MidValue = nums1[nums1Mid]; nums2MidValue = nums2[nums2Mid]

        # If k is in the right half of the combined indexes, remove the left of the smaller range.
        # Otherwise, remove the right of the larger range.
        if nums1Mid + nums2Mid < k:
            if nums1MidValue > nums2MidValue:
                return self.__findKth(nums1, nums2, k, nums1Start, nums1End, nums2Mid + 1, nums2End)
            else:
                return self.__findKth(nums1, nums2, k, nums1Mid + 1, nums1End, nums2Start, nums2End)
        else:
            if nums1MidValue > nums2MidValue:
                return self.__findKth(nums1, nums2, k, nums1Start, nums1Mid - 1, nums2Start, nums2End)
            else:
                return self.__findKth(nums1, nums2, k, nums1Start, nums1End, nums2Start, nums2Mid - 1)


def main():
    """
    Demonstates the solution with simple inputs
    """
    solution = Solution()
    print(solution.findMedianSortedArrays([1, 3, 5, 7, 9, 10], [0, 2, 4, 6, 8]))

if __name__ == "__main__":
    main()