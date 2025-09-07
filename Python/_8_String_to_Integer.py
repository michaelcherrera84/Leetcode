class Solution:
    def myAtoi(self, s: str) -> int:
        """
        Convert a string to a 32-bit signed integer.

        The function follows the rules of the C/C++ `atoi` function:
        1. Whitespace: Leading whitespace is ignored.
        2. Sign: A leading '+' or '-' sets the sign. If neither is present, the number is positive.
        3. Conversion: Consecutive digits are read and converted into an integer.
           Reading stops at the first non-digit character.
        4. Clamping: If the value is outside the 32-bit signed integer range
           [-2^31, 2^31 - 1], it is clamped to the nearest bound.

        Args:
            s (str): Input string containing the number representation.

        Returns:
            int: The parsed 32-bit signed integer, clamped within
                 [-2^31, 2^31 - 1].

        Examples:
            >>> Solution().myAtoi("42")
            42
            >>> Solution().myAtoi("   -42")
            -42
            >>> Solution().myAtoi("4193 with words")
            4193
            >>> Solution().myAtoi("words and 987")
            0
            >>> Solution().myAtoi("-91283472332")
            -2147483648
        """

        s = s.strip()  # remove leading whitespace
        if len(s) < 1:
            return 0

        result = 0
        sign = 1  # default to positive

        i = 0
        # Handle sign
        if s[i] == "+" or s[i] == "-":
            sign = -1 if s[i] == "-" else 1
            i += 1

        # Parse digits
        while i < len(s) and s[i].isdigit():
            result = result * 10 + ord(s[i]) - ord("0")  # build number incrementally

            # Clamp to 32-bit signed integer bounds
            if result * sign > pow(2, 31) - 1:
                return pow(2, 31) - 1
            if result * sign < -pow(2, 31):
                return -pow(2, 31)

            i += 1

        return result * sign


def main():
    sol = Solution()
    print(sol.myAtoi(" "))


if __name__ == "__main__":
    main()
