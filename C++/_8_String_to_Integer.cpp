// Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.

// The algorithm for myAtoi(string s) is as follows:

// Whitespace: Ignore any leading whitespace (" ").
// Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
// Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
// Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
// Return the integer as the final result.

// Example 1:

// Input: s = "42"

// Output: 42

// Explanation:

// The underlined characters are what is read in and the caret is the current reader position.
// Step 1: "42" (no characters read because there is no leading whitespace)
//          ^
// Step 2: "42" (no characters read because there is neither a '-' nor '+')
//          ^
// Step 3: "42" ("42" is read in)
//            ^
// Example 2:

// Input: s = " -042"

// Output: -42

// Explanation:

// Step 1: "   -042" (leading whitespace is read and ignored)
//             ^
// Step 2: "   -042" ('-' is read, so the result should be negative)
//              ^
// Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
//                ^
// Example 3:

// Input: s = "1337c0d3"

// Output: 1337

// Explanation:

// Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
//          ^
// Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
//          ^
// Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
//              ^
// Example 4:

// Input: s = "0-1"

// Output: 0

// Explanation:

// Step 1: "0-1" (no characters read because there is no leading whitespace)
//          ^
// Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
//          ^
// Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
//           ^
// Example 5:

// Input: s = "words and 987"

// Output: 0

// Explanation:

// Reading stops at the first non-digit character 'w'.

// Constraints:

// 0 <= s.length <= 200
// s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.

#include <iostream>

// First implementation of myAtoi, more verbose and step-based
class Solution
{
public:
    int myAtoi(std::string s)
    {
        std::string final_string = ""; // holds the numeric digits we extract
        int sign = 0;                  // 0 means not set, -1 means negative, 1 means positive
        bool first_zero = false;       // track if leading zero has been seen
        bool first_digit = false;      // track if a nonzero digit has been seen

        for (size_t i = 0; i < s.length(); i++)
        {
            // Case: first non-space character is invalid (not sign, not digit)
            if (!sign && !first_digit && s[i] != ' ' && s[i] != '-' && s[i] != '+' && !isdigit(s[i]))
                return 0;

            // Skip whitespace before anything meaningful is seen
            if (!sign && !first_digit && !first_zero && s[i] == ' ')
                continue;

            // Skip leading zeros before first digit/sign
            if (!sign && !first_digit && s[i] == '0')
            {
                first_zero = true;
                continue;
            }

            // If a non-digit appears right after zeros, stop parsing
            if (!isdigit(s[i]) && first_zero)
                break;

            // Handle negative sign
            if (!sign && !first_digit && s[i] == '-')
            {
                sign = -1;
                continue;
            }

            // Handle positive sign
            if (!sign && !first_digit && s[i] == '+')
            {
                sign = 1;
                continue;
            }

            // Another leading zero case
            if (!first_digit && s[i] == '0')
            {
                first_zero = true;
                continue;
            }

            // First actual nonzero digit, default sign to + if not set
            if (!sign && !first_digit && isdigit(s[i]) && s[i] != '0')
            {
                sign = 1;
                first_digit = true;
                final_string += s[i];
                continue;
            }

            // First digit after possible zeros
            if (!first_digit && isdigit(s[i]))
            {
                first_digit = true;
                final_string += s[i];
                continue;
            }

            // Stop if we encounter a non-digit after digits/sign
            if (!isdigit(s[i]) && (first_digit || sign))
                break;

            // Normal digit accumulation
            final_string += s[i];
        }

        // If number is too long (>10 digits) -> will overflow
        if (final_string.length() > 10)
        {
            return (sign == 1) ? INT_MAX : INT_MIN;
        }

        long result = 0;
        // Convert string of digits to integer manually
        for (size_t i = 0; i < final_string.length(); i++)
        {
            result += (final_string[i] - '0') * pow(10, final_string.length() - 1 - i);

            // Check bounds during accumulation
            if (result > pow(2, 31) - 1 && sign == 1)
                return std::numeric_limits<int>::max();
            if (result > pow(2, 31) && sign == -1)
                return std::numeric_limits<int>::min();
        }

        return int(result * sign);
    }
};

// A much cleaner, conventional implementation
class BetterSolution
{
public:
    int myAtoi(std::string s)
    {
        int i = 0, n = s.size();
        long result = 0;
        int sign = 1; // assume positive unless '-' is seen

        // 1. Skip leading whitespace
        while (i < n && s[i] == ' ')
            i++;

        // 2. Handle sign
        if (s[i] == '+' || s[i] == '-')
        {
            sign = (s[i] == '+') ? 1 : -1;
            i++;
        }

        // 3. Parse digits
        while (i < n && isdigit(s[i]))
        {
            result = result * 10 + (s[i] - '0'); // build number incrementally

            // Clamp immediately if out of range
            if (sign * result <= INT_MIN)
                return INT_MIN;
            if (sign * result >= INT_MAX)
                return INT_MAX;

            i++;
        }

        // 4. Return signed result
        return (int)(sign * result);
    }
};

int main()
{
    auto solution = Solution();
    std::cout << solution.myAtoi("   -042") << std::endl; // test first implementation

    auto bSol = BetterSolution();
    std::cout << bSol.myAtoi("   -042") << std::endl; // test better implementation

    return 0;
}