class Solution:
    """
    A solution that converts a Roman numeral string into an integer.
    This version explicitly checks each numeral and its subtractive cases 
    (like IV = 4, IX = 9, etc.) using conditional statements.
    """

    def romanToInt(self, s: str) -> int:
        """
        Convert a Roman numeral string to an integer.

        Args:
            s (str): Roman numeral string (e.g., "MCMXCIV")

        Returns:
            int: The integer value of the Roman numeral
        """
        result = 0  # Final accumulated integer value
        n = len(s)
        skip = False  # Used to skip the next character if we've processed a subtractive pair

        for i in range(n):
            # Skip if previous iteration already consumed this numeral in a pair
            if skip:
                skip = False
                continue

            # Direct matches and subtractive cases
            if s[i] == "M":
                result += 1000

            if s[i] == "C" and i < n - 1 and s[i + 1] == "M":
                result += 900
                skip = True
                continue

            if s[i] == "D":
                result += 500

            if s[i] == "C" and i < n - 1 and s[i + 1] == "D":
                result += 400
                skip = True
                continue

            if s[i] == "C":
                result += 100

            if s[i] == "X" and i < n - 1 and s[i + 1] == "C":
                result += 90
                skip = True
                continue

            if s[i] == "L":
                result += 50

            if s[i] == "X" and i < n - 1 and s[i + 1] == "L":
                result += 40
                skip = True
                continue

            if s[i] == "X":
                result += 10

            if s[i] == "I" and i < n - 1 and s[i + 1] == "X":
                result += 9
                skip = True
                continue

            if s[i] == "V":
                result += 5

            if s[i] == "I" and i < n - 1 and s[i + 1] == "V":
                result += 4
                skip = True
                continue

            if s[i] == "I":
                result += 1

        return result


class BetterSolution:
    """
    A more elegant and concise solution that uses a dictionary lookup
    and a subtraction rule: if a smaller numeral appears before a larger one, subtract it.
    """

    def romanToInt(self, s: str) -> int:
        """
        Convert a Roman numeral string to an integer.

        Args:
            s (str): Roman numeral string (e.g., "MCMXCIV")

        Returns:
            int: The integer value of the Roman numeral
        """
        result = 0

        # Map of Roman numerals to integer values
        nums = {"I": 1, "V": 5, "X": 10, "L": 50,
                "C": 100, "D": 500, "M": 1000}

        for i in range(len(s)):
            # If the current numeral is smaller than the next one, subtract it
            if i + 1 < len(s) and nums[s[i]] < nums[s[i + 1]]:
                result -= nums[s[i]]
            else:
                result += nums[s[i]]

        return result


def main():
    """
    Test both solutions with the Roman numeral "MCMXCIV" (1994).
    """
    sol = Solution()
    sol1 = BetterSolution()

    # Using the explicit, verbose solution
    print(sol.romanToInt("MCMXCIV"))  # Expected output: 1994

    # Using the concise dictionary-based solution
    print(sol1.romanToInt("MCMXCIV"))  # Expected output: 1994


if __name__ == "__main__":
    main()
