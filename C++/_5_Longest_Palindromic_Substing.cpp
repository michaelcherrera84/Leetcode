#include <iostream>
#include <vector>

// Given a string s, return the longest palindromic substring in s.

// Example 1:
// Input: s = "babad"
// Output: "bab"
// Explanation: "aba" is also a valid answer.

// Example 2:
// Input: s = "cbbd"
// Output: "bb"

// Constraints:
// 1 <= s.length <= 1000
// s consist of only digits and English letters.

/// @brief Class for Manacher's algorithm to find all palindromic substrings in linear time.
class Manacher
{
    std::string ms;
    std::vector<int> p;

public:
    /// @brief Initialize the Manacher object and preprocesses the string.
    /// @param s the input string to process
    Manacher(std::string s)
    {
        // Preprocess the string.
        ms = "@";
        for (char c : s)
        {
            ms.push_back(c);
            ms.push_back('#');
        }
        ms.push_back('$');
        p.assign(ms.length(), 0);
        runManacher();
    }

    /// @brief Executes Manacher's algorithm to compute palindrome radii for each center in ms.
    void runManacher()
    {
        int n = ms.length();
        int l = 0, r = 0; // left and right boundary of palindrome

        for (int i = 1; i < n - 1; i++)
        {
            // Calculate this mirror position of i with respect to the current palindrom center.
            int mirror = l + r - i;

            // Update p using the mirror value if within the current palindrome.
            if (mirror >= 0 && mirror < n)
                p[i] = std::max(0, std::min(r - i, p[mirror]));
            else
                p[i] = 0;

            // Attempt to expand palindrome centered at i.
            while (i + 1 + p[i] < n &&
                   i - 1 - p[i] >= 0 &&
                   ms[i + 1 + p[i]] == ms[i - 1 - p[i]])
                p[i]++;

            // If palindrom centered at i expands past r, update l and r.
            if (i + p[i] > r)
            {
                l = i - p[i];
                r = i + p[i];
            }
        }
    }

    /// @brief Returns the length of the longest palindrome centered at index cen in the original string.
    /// @param cen center index in the original string
    /// @param odd 0 for odd-length palindrome, 1 for even-length palindrome
    /// @return the length of the longest palindrome centered at cen
    int getLongest(int cen, int odd)
    {
        // Map the original index and odd/even to the corresponding position in ms
        int pos = 2 * cen + 2 + odd;
        return p[pos];
    }
};

class Solution
{

public:
    /// @brief Returns the longest palindromic substing in s.
    /// @param s the string
    /// @return the longest palindromic substing
    std::string longestPalindrome(std::string s)
    {
        int n = s.length();
        int maxLen = 1; // the maximum palindrome length found
        int start = 0;  // the starting index of the longest palindrome
        auto M = Manacher(s);

        for (int i = 0; i < n; i++) {
            // Check for odd-length palindrome centered at i.
            int oddLen = M.getLongest(i, 0);
            // Update start index if longer palindrome is found.
            if (oddLen > maxLen)
                start = i - (oddLen - 1) / 2;
            // Check for even-length palindrome centered at i.
            int evenLen = M.getLongest(i, 1);
            // Update start index if longer palindrome is found.
            if (evenLen > maxLen)
                start = i - (evenLen - 1) / 2;

            // Update the maximum palindrome length found so far.
            maxLen = std::max(maxLen, std::max(oddLen, evenLen));
        }

        // Return the longest palindromic substring.
        return s.substr(start, maxLen);
    }
};

int main() {
    auto sol = Solution();
    std::cout << sol.longestPalindrome("qwerasdfhhhhfdsarewt");

    return 0;
}