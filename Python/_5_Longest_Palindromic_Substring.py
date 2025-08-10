# Given a string s, return the longest palindromic substring in s.

# Example 1:
# Input: s = "babad"
# Output: "bab"
# Explanation: "aba" is also a valid answer.

# Example 2:
# Input: s = "cbbd"
# Output: "bb"

# Constraints:
# 1 <= s.length <= 1000
# s consist of only digits and English letters.


class Solution(object):
    """Solution class for finding the longest palindromic substring in a string.

    Methods:
        longestPalindrome(s): Returns the longest palindromic substring in s.
    """

    def longest_palindrome(self, s):
        """Finds the longest palindromic substring in the given string.

        Args:
            s (str): The input string.

        Returns:
            str: The longest palindromic substring found in s.
        """
        n = len(s)
        max_len = 1  # Initialize the maximum palindrome length found
        start = 0  # Initialize the starting index of the longest palindrome
        M = Manacher(s)  # Preprocess and run Manacher's algorithm on the string

        for i in range(n):
            # Check for odd-length palindrome centered at i
            odd_len = M.get_longest(i, 1)
            if odd_len > max_len:
                start = (
                    i - (odd_len - 1) // 2
                )  # Update start index if longer palindrome is found

            # Check for even-length palindrome centered at i
            even_len = M.get_longest(i, 0)
            if even_len > max_len:
                start = (
                    i - (even_len - 1) // 2
                )  # Update start index if longer palindrome is found

            # Update the maximum palindrome length found so far
            max_len = max(max_len, max(odd_len, even_len))

        # Return the longest palindromic substring
        return s[start : start + max_len]


class Manacher:
    """Class for Manacher's algorithm to find all palindromic substrings in linear time.

    Attributes:
        ms (str): The modified string with sentinels and separators.
        p (List[int]): Array storing the palindrome radius at each position in ms.
    """

    def __init__(self, s):
        """Initializes the Manacher object and preprocesses the string.

        Args:
            s (str): The input string to process.
        """
        # Preprocess the string: add sentinels and separators to handle even/odd palindromes uniformly
        self.ms = "@"
        for c in s:
            self.ms += "#" + c
        self.ms += "#$"
        # Array to store the radius of the palindrome centered at each position in ms
        self.p = [0] * len(self.ms)
        # Run Manacher's algorithm to fill self.p
        self.run_manacher()

    def run_manacher(self):
        """Executes Manacher's algorithm to compute palindrome radii for each center in ms."""
        n = len(self.ms)
        l = r = 0  # (l, r) is the rightmost palindrome's left and right boundary

        for i in range(1, n - 1):
            # Calculate the mirror position of i with respect to the current palindrome center
            mirror = l + r - i
            # Initialize self.p[i] using the mirror value if within the current palindrome
            if 0 <= mirror < n:
                self.p[i] = max(0, min(r - i, self.p[mirror]))
            else:
                self.p[i] = 0

            # Attempt to expand palindrome centered at i
            while (
                i + 1 + self.p[i] < n
                and i - 1 - self.p[i] >= 0
                and self.ms[i + 1 + self.p[i]] == self.ms[i - 1 - self.p[i]]
            ):
                self.p[i] += 1

            # If palindrome centered at i expands past r, update l and r
            if i + self.p[i] > r:
                l = i - self.p[i]
                r = i + self.p[i]

    def get_longest(self, cen, odd):
        """Returns the length of the longest palindrome centered at index cen in the original string.

        Args:
            cen (int): Center index in the original string.
            odd (int): 1 for odd-length palindrome, 0 for even-length palindrome.

        Returns:
            int: The length of the longest palindrome centered at cen.
        """
        # Map the original index and odd/even to the corresponding position in ms
        pos = 2 * cen + 2 + (0 if odd else 1)
        return self.p[pos]

def main():
    # Example usage of the Solution class
    sol = Solution()
    longestPalindrome = sol.longest_palindrome("zxcvabbaqwer")
    print(longestPalindrome)


if __name__ == "__main__":
    main()
