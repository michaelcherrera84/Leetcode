# 3. Longest Substring Without Repeating Characters
# Given a string s, find the length of the longest substring without duplicate characters.
#
# Example 1:
# Input: s = "abcabcbb"
# Output: 3
# Explanation: The answer is "abc", with the length of 3.
#
# Example 2:
# Input: s = "bbbbb"
# Output: 1
# Explanation: The answer is "b", with the length of 1.
#
# Example 3:
# Input: s = "pwwkew"
# Output: 3
# Explanation: The answer is "wke", with the length of 3.
# Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
#
# Constraints:
# 0 <= s.length <= 5 * 10^4
# s consists of English letters, digits, symbols and spaces.


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        """Sliding Window with Reset on Repeat"""

        letters = []    # List to keep track of characters in the current substring window
        count = 0       # Length of current substring without repeating characters
        final = 0       # Longest length found so far

        i = 0           # Start index of current window
        j = 0           # End index (inclusive) of current window
        while j < len(s):
            if not s[j] in letters:
                # If current character is not in our list, add it
                letters.append(s[j])
                count += 1
                # Update max length if needed
                if count > final:
                    final = count
                j += 1
            else:
                # If a duplicate is found:
                # Shift start of window by 1, clear list, reset count
                i += 1
                letters.clear()
                count = 0
                j = i   # Start window again from new position

        return final

solution = Solution()
result = solution.lengthOfLongestSubstring("abcabcbb")
print(result)

class BetterSolution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        """Optimized Sliding Window using a dictionary (like Java's HashMap)"""

        last_seen = {}          # Maps each character to its last seen index
        left = 0                # Left boundary of the sliding window
        max_length = 0          # Longest substring length found so far

        for right in range(len(s)):  # Right boundary of the window
            char = s[right]

            if char in last_seen and last_seen[char] >= left:
                # Move the left boundary to the right of the duplicate
                left = last_seen[char] + 1

            # Update max_length if current window is longer
            max_length = max(max_length, right - left + 1)

            # Update the last seen position of the current character
            last_seen[char] = right

        return max_length
    
solution = BetterSolution()
result = solution.lengthOfLongestSubstring("abcabcbb")
print(result)
