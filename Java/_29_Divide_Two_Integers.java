
/**
 * Given two integers {@code dividend} and {@code divisor}, divide two integers
 * <b>without</b> using multiplication, division, and mod operator.
 * <p>
 * The integer division should truncate toward zero, which means losing its
 * fractional part. For example, {@code 8.345} would be truncated to {@code 8},
 * and {@code -2.7335} would be truncated to {@code -2}.
 * <p>
 * Return the <i><b>quotient</b> after dividing</i> {@code dividend} <i>by</i>
 * {@code divisor}.
 * <p>
 * <b>Note:</b> Assume we are dealing with an environment that could only store
 * integers within the <b>32-bit</b> signed integer range:
 * {@code [−2^31, 2^31 − 1]}. For this problem, if the quotient is <b>strictly
 * greater than</b> {@code 2^31 - 1}, then return {@code 2^31 - 1}, and if the
 * quotient is <b>strictly less than</b> {@code -2^31}, then return {@code -2^31}.
 * <pre>
 * <b>Example 1:</b>
 *   <b>Input:</b> dividend = 10, divisor = 3 
 *   <b>Output:</b> 3 
 *   <b>Explanation:</b> 10/3 = 3.33333.. which is truncated to 3. 
 * 
 * <b>Example 2:</b>
 *   <b>Input:</b> dividend = 7, divisor = -3 
 *   <b>Output:</b> -2 
 *   <b>Explanation:</b> 7/-3 = -2.33333.. which is truncated to -2.
 * </pre>
 * <b>Constraints:</b>
 * <ul>
 * <li>{@code -231 <= dividend, divisor <= 231 - 1}</li>
 * <li>{@code divisor != 0}</li>
 * </ul>
 */
public class _29_Divide_Two_Integers {

    /**
     * Solution class implementing integer division without using the division
     * operator.
     *
     * The method Divide(int dividend, int divisor) returns the quotient after
     * dividing dividend by divisor, following truncation toward zero (as in
     * integer division).
     *
     * Edge cases: - Division by zero is not handled here (LeetCode usually
     * guarantees divisor ≠ 0). - Overflow case: when dividend ==
     * Integer.MIN_VALUE and divisor == -1, the result would exceed
     * Integer.MAX_VALUE, so it must be capped.
     */
    public class Solution {

        /**
         * Divides two integers without using multiplication, division, or mod
         * operators.
         *
         * @param dividend the integer to be divided
         * @param divisor the integer to divide by
         * @return the quotient after division, truncated toward zero
         */
        public int Divide(int dividend, int divisor) {

            // Case 1: identical values yield quotient 1 (since x/x = 1)
            if (dividend == divisor) {
                return 1;
            }

            // Case 2: 0 divided by any non-zero number is 0
            // Also, if divisor == Integer.MIN_VALUE, any dividend except the same
            // will yield 0 since |divisor| is too large to fit in range.
            if (dividend == 0 || divisor == Integer.MIN_VALUE) {
                return 0;
            }

            // Case 3: dividing by 1 or -1 has simple, direct results
            if (divisor == 1) {
                return dividend;
            }
            if (divisor == -1) {
                // Prevent overflow for Integer.MIN_VALUE / -1
                return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
            }

            // quot will hold the running quotient value
            int quot = 0;
            int d1, d2;
            d1 = d2 = divisor; // working copies of divisor

            // --- Handle negative dividend ---
            if (dividend < 0) {
                quot = -1;

                // If divisor is positive, make it negative for uniform comparison
                if (divisor > 0) {
                    d1 = d2 = -divisor;
                    // If dividend is greater than -divisor (closer to zero), quotient is 0
                    if (dividend > -divisor) {
                        return 0;
                    }
                }

                // If dividend > divisor (e.g., -3 > -2), quotient is 0
                if (dividend > divisor) {
                    return 0;
                }

                // Accumulate quotient while repeatedly subtracting divisor
                // until we approach the dividend
                while (d1 >= dividend - d2) {
                    d1 += d2;
                    quot--;
                }

                // If divisor is negative, the result should flip sign
                if (divisor < 0) {
                    quot = quot == Integer.MIN_VALUE ? Integer.MAX_VALUE : -quot;
                }

            } else {
                // --- Handle positive dividend ---
                quot = 1;

                // If divisor is negative, invert it for easier looping
                if (divisor < 0) {
                    d1 = d2 = -divisor;
                    // If dividend is smaller than -divisor, quotient is 0
                    if (dividend < -divisor) {
                        return 0;
                    }
                }

                // If dividend < divisor, quotient is 0 (integer truncation)
                if (dividend < divisor) {
                    return 0;
                }

                // Accumulate quotient while adding divisor until exceeding dividend
                while (d1 <= dividend - d2) {
                    d1 += d2;
                    quot++;
                }

                // Adjust sign if divisor is negative
                if (divisor < 0) {
                    quot = -quot;
                }
            }

            return quot;
        }
    }

    /**
     * Divides two integers without using multiplication, division, or modulo
     * operators. This version focuses on clarity and correctness, not speed.
     */
    public class RefactoredSolution {

        /**
         * Divides two integers without using multiplication, division, or mod
         * operators.
         *
         * @param dividend the integer to be divided
         * @param divisor the integer to divide by
         * @return the quotient after division, truncated toward zero
         */
        public int Divide(int dividend, int divisor) {
            // Handle overflow (special case)
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            // Simple cases
            if (divisor == 1) {
                return dividend;
            }
            if (divisor == -1) {
                return -dividend;
            }
            if (dividend == 0) {
                return 0;
            }
            if (dividend == divisor) {
                return 1;
            }

            // Convert both numbers to negatives for easier overflow-safe subtraction
            // (Integer.MIN_VALUE cannot be made positive safely)
            boolean negative = (dividend > 0) ^ (divisor > 0);
            int negDividend = dividend > 0 ? -dividend : dividend;
            int negDivisor = divisor > 0 ? -divisor : divisor;

            int quotient = 0;

            // Subtract divisor repeatedly from dividend
            // until the dividend is "less" (more negative) than divisor
            while (negDividend <= negDivisor) {
                negDividend -= negDivisor;
                quotient++;
                // Prevent overflow of quotient
                if (quotient == Integer.MAX_VALUE) {
                    break;
                }
            }

            // Apply sign
            return negative ? -quotient : quotient;
        }
    }

    /**
     * Optimized division using bitwise doubling (similar to long division).
     * Avoids multiplication, division, and modulo operators.
     */
    public class BetterSolution {

        /**
         * Divides two integers without using multiplication, division, or mod
         * operators.
         *
         * @param dividend the integer to be divided
         * @param divisor the integer to divide by
         * @return the quotient after division, truncated toward zero
         */
        public int Divide(int dividend, int divisor) {
            // Handle overflow
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            // Determine sign of result
            boolean negative = (dividend > 0) ^ (divisor > 0);

            // Work with negatives to avoid overflow
            int negDividend = dividend > 0 ? -dividend : dividend;
            int negDivisor = divisor > 0 ? -divisor : divisor;

            int quotient = 0;

            // Repeat until dividend is smaller (more negative) than divisor
            while (negDividend <= negDivisor) {
                int currentDivisor = negDivisor;
                int currentQuotient = 1;

                // Double the divisor as long as it fits
                // (i.e., doesn’t overflow and still <= dividend)
                while (currentDivisor >= (Integer.MIN_VALUE >> 1)
                        && negDividend <= currentDivisor + currentDivisor) {
                    currentDivisor += currentDivisor; // Double
                    currentQuotient += currentQuotient; // Double quotient too
                }

                // Subtract the largest doubled divisor
                negDividend -= currentDivisor;
                quotient += currentQuotient;
            }

            return negative ? -quotient : quotient;
        }
    }

    public static void main(String[] args) {
        _29_Divide_Two_Integers p29 = new _29_Divide_Two_Integers();
        var sol1 = p29.new Solution();
        var sol2 = p29.new RefactoredSolution();
        var sol3 = p29.new BetterSolution();

        System.out.println(sol1.Divide(Integer.MIN_VALUE, 2));
        System.out.println(sol2.Divide(Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(sol3.Divide(Integer.MIN_VALUE, -1));
    }
}
