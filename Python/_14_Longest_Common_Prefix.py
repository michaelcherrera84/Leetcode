from typing import List

class Solution:
    """
    Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".

    Example 1:
    Input: strs = ["flower","flow","flight"]
    Output: "fl"
    
    Example 2:

    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
    
    Constraints:

    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters if it is non-empty.
    """
    def longestCommonPrefix(self, strs: List[str]) -> str:
        """
        Find the longest common prefix among a list of strings.
        If there is no common prefix, return an empty string.

        Approach:
        - Start with the first string as the candidate prefix.
        - For each subsequent string, check if it starts with the current prefix.
        - If not, repeatedly shorten the prefix (by removing the last character)
          until it matches or becomes empty.
        - Return the final prefix after checking all strings.

        Args:
            strs (List): the list of strings to compare

        Returns:
            str: the longest common prefix
        """

        # Start with the first string as the initial candidate
        result = strs[0]

        i = 1
        # Iterate over the rest of the strings
        while i < len(strs):
            # While the current string does not start with the candidate prefix...
            while not strs[i].startswith(result):
                # Shorten the prefix by chopping off the last character
                result = result[0:len(result) - 1]

                # If result becomes empty, there is no common prefix
                if not result:
                    return ""
            # Move on to the next string
            i += 1

        # At this point, result is the longest common prefix
        return result
    
def main():
    sol = Solution()
    # Example usage: should print "fl"
    print(sol.longestCommonPrefix(["flower", "flow", "flight"]))


if __name__ == "__main__":
    main()