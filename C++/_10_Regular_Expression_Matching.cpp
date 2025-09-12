#include <iostream>

class Solution
{
public:
    /**
     * Determines if a string `s` matches a pattern `p`.
     * The pattern supports:
     *  - '.' (dot) which matches any single character
     *  - '*' which means zero or more occurrences of the preceding character
     *
     * Dynamic Programming Approach:
     * We maintain two boolean vectors:
     *   - prev[j] → whether the prefix of the pattern processed so far
     *               matches the first j characters of `s`
     *   - curr[j] → same as above, but for the current pattern character(s) being processed
     *
     * @param s Input string
     * @param p Pattern string (may contain '.' and '*')
     * @return true if the pattern matches the entire string, false otherwise
     */
    bool isMatch(std::string s, std::string p)
    {
        int n = s.length();

        // Initialize DP state vectors of size (n + 1), all false initially
        std::vector<bool> prev(n + 1, false);
        std::vector<bool> curr(n + 1, false);

        // Base case: empty pattern matches empty string
        prev[0] = true;

        // Traverse the pattern one unit at a time.
        // A "unit" is either:
        //   1. A single character (like 'a' or '.')
        //   2. A character followed by '*' (like 'a*' or '.*')
        size_t i = 0;
        while (i < p.length())
        {
            char c = p[i]; // current pattern character
            bool asterisk = i + 1 < p.length() && p[i + 1] == '*'; // check if followed by '*'

            // Reset current DP row before processing this unit
            std::fill(curr.begin(), curr.end(), false);

            if (asterisk)
            {
                // Case 1: Character followed by '*'
                // This means we can match zero or more repetitions of `c`

                // Zero repetitions: carry over match from prev[0]
                curr[0] = prev[0];

                // Check all positions in `s`
                for (size_t j = 0; j < n; j++)
                {
                    bool match = (c == '.' || c == s[j]); // does current char match s[j]?

                    // Two possibilities:
                    // (1) curr[j] && match → extend repetition of c
                    // (2) prev[j + 1]      → treat "c*" as empty (skip it)
                    if ((curr[j] && match) || prev[j + 1])
                    {
                        curr[j + 1] = true;
                    }
                }

                i += 2; // skip both character and '*'
            }
            else
            {
                // Case 2: Single character (no '*')

                for (size_t j = 0; j < n; j++)
                {
                    // If substring matched up to j, and current char matches s[j],
                    // then substring matches up to j+1
                    if (prev[j] && (c == '.' || s[j] == c))
                    {
                        curr[j + 1] = true;
                    }
                }

                i++; // move to next character in pattern
            }

            // Move current results into prev for next iteration
            auto temp = prev;
            prev = curr;
            curr = temp;
        }

        // Final result: does full pattern match the full string?
        return prev[n];
    }
};

int main()
{
    auto sol = Solution();

    std::cout << (sol.isMatch("a", "ab*a") ? "true" : "false");

    return 0;
}