#include <iostream>

// -----------------------------------------------------------------------------
// String-based solution: convert integer to string, then check symmetry.
// -----------------------------------------------------------------------------
class Solution
{
public:
    /**
     * Check whether an integer is a palindrome.
     *
     * A palindrome number reads the same forward and backward (e.g., 121, 1331).
     * Negative numbers are never palindromes (because of the '-' sign).
     * Single-digit numbers are always palindromes.
     *
     * @param x Input integer to check.
     * @return true if x is a palindrome, false otherwise.
     */
    bool isPalindrome(int x)
    {
        // Single-digit numbers are always palindromes.
        if (x >= 0 && x < 10)
            return true;

        // Negative numbers cannot be palindromes.
        if (x < 0)
            return false;

        // Convert the number to a string for easy comparison.
        std::string num = std::to_string(x);

        // Use two-pointer technique: one from start, one from end.
        for (size_t i = 0, j = num.length() - 1; i <= j; i++, j--)
        {
            if (num[i] != num[j])
                return false; // mismatch found
        }

        return true; // no mismatches, it's a palindrome
    }
};

// -----------------------------------------------------------------------------
// Math-based solution: compare digits without string conversion.
// -----------------------------------------------------------------------------
class AnotherSolution
{
public:
    /**
     * Check whether an integer is a palindrome using math operations only.
     *
     * This avoids converting the number to a string. Instead, it compares the
     * most significant digit with the least significant digit, moving inward.
     *
     * @param x Input integer to check.
     * @return true if x is a palindrome, false otherwise.
     */
    bool isPalindrome(int x)
    {
        // Single-digit numbers are always palindromes.
        if (x >= 0 && x < 10)
            return true;

        // Negative numbers cannot be palindromes.
        if (x < 0)
            return false;

        int y = x; // copy of the number to peel off digits from the right

        // i: index from left, j: index from right (log10 gives number of digits-1)
        for (int i = 0, j = int(log10(x)); i <= j; i++, j--)
        {
            // Compare digit at position j (from left) with last digit of y
            if (x / int(pow(10, j)) % 10 != y % 10)
                return false;

            // Remove the last digit from y for the next iteration
            y /= 10;
        }

        return true; // all digits matched
    }
};

// -----------------------------------------------------------------------------
// Reverse-half solution: reverse only half of the digits and compare.
// -----------------------------------------------------------------------------
class BetterSolution
{
public:
    /**
     * Check whether an integer is a palindrome by reversing half of it.
     *
     * The idea:
     * - Negative numbers are not palindromes.
     * - Numbers ending with 0 are not palindromes unless the number is 0
     *   (e.g., 10 -> not a palindrome).
     * - Reverse the last half of the digits and compare it to the first half.
     *   Once reversed_half >= x, we've reversed enough digits.
     *
     * This avoids string conversion and avoids full reversal,
     * reducing the chance of integer overflow.
     *
     * @param x Input integer to check.
     * @return true if x is a palindrome, false otherwise.
     */
    bool isPalindrome(int x)
    {
        // Negative numbers and multiples of 10 (except 0) can't be palindromes.
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int reversed_half = 0;

        // Reverse digits until we've processed at least half the number.
        while (x > reversed_half)
        {
            reversed_half = reversed_half * 10 + x % 10; // add last digit
            x /= 10;                                     // drop last digit
        }

        // For even digit counts: x == reversed_half
        // For odd digit counts: ignore middle digit by reversed_half/10
        return (x == reversed_half || x == reversed_half / 10);
    }
};

int main()
{
    auto sol = Solution();
    std::cout << sol.isPalindrome(0) << std::endl;

    auto sol1 = AnotherSolution();
    std::cout << sol1.isPalindrome(100030001) << std::endl;

    auto sol2 = BetterSolution();
    std::cout << sol2.isPalindrome(1000030001);

    return 0;
}