class Solution:
    def reverse(self, x: int) -> int:
        """
        Reverse digits of a 32-bit signed integer.

        If the reversed integer overflows beyond the 32-bit signed integer range
        [-2^31, 2^31 - 1], return 0.

        Example:
            Input: 123
            Output: 321

            Input: -120
            Output: -21
        """
        # Edge case: if the number is zero, reversed result is still zero
        if x == 0:
            return 0

        # Work with the absolute value first to simplify digit extraction
        abs_val = abs(x)

        # Build reversed number as a string
        reversed_str = ""
        while abs_val > 0:
            # Take the last digit and append to reversed string
            reversed_str += str(abs_val % 10)
            # Remove the last digit from abs_val
            abs_val //= 10

        # Add negative sign if original number was negative
        if x < 0:
            reversed_str = "-" + reversed_str

        # Convert string back to integer
        reversed_num = int(reversed_str)

        # Check for 32-bit signed integer overflow
        if -(2**31) <= reversed_num <= 2**31 - 1:
            return reversed_num
        return 0


class BetterSolution:
    def reverse(self, x: int) -> int:
        """
        Optimized version of reverse using arithmetic instead of string manipulation.

        Extracts digits one by one and builds the reversed number mathematically.
        Still enforces 32-bit signed integer overflow rules.
        """
        # Determine the sign (+1 for positive, -1 for negative)
        sign = -1 if x < 0 else 1

        # Work with absolute value for simplicity
        abs_val = abs(x)
        reversed_num = 0

        # Extract digits until no digits remain
        while abs_val > 0:
            # Get last digit
            digit = abs_val % 10
            # Append digit to reversed number
            reversed_num = reversed_num * 10 + digit
            # Remove the last digit
            abs_val //= 10

        # Restore original sign
        reversed_num *= sign

        # Enforce 32-bit signed integer boundaries
        if -(2**31) <= reversed_num <= 2**31 - 1:
            return reversed_num
        return 0


def main():
    sol = Solution()
    print(sol.reverse(9999999999999999999999999))

    sol1 = BetterSolution()
    print(sol1.reverse(-54321))


if __name__ == "__main__":
    main()
