class Solution:
    """
    Given two strings `needle` and `haystack`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

    #### Example 1:
    **Input:** haystack = "sadbutsad", needle = "sad" \\
    **Output:** 0 \\
    **Explanation:** "sad" occurs at index 0 and 6. The first occurrence is at index 0, so we return 0. \
    
    #### Example 2:
    **Input:** haystack = "leetcode", needle = "leeto" \\
    **Output:** -1 \\
    **Explanation:** "leeto" did not occur in "leetcode", so we return -1.

    #### Constraints:
    - `1 <= haystack.length, needle.length <= 10^4`
    - `haystack` and `needle` consist of only lowercase English characters.
    """

    def strStr(self, haystack: str, needle: str) -> int:
        """
        Return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

        Args:
            haystack (str): the string
            needle (str): the substring for which to search

        Returns:
            int: the index of the first occurence of the substring
        """
        return haystack.find(needle)


def main():
    sol = Solution()
    print(sol.strStr("leetcode", "leeto"))

if __name__ == "__main__":
    main()
