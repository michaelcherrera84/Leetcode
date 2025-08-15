# The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

# P   A   H   N
# A P L S I I G
# Y   I   R
# And then read line by line: "PAHNAPLSIIGYIR"

# Write the code that will take a string and make this conversion given a number of rows:

# string convert(string s, int numRows);
 

# Example 1:

# Input: s = "PAYPALISHIRING", numRows = 3
# Output: "PAHNAPLSIIGYIR"
# Example 2:

# Input: s = "PAYPALISHIRING", numRows = 4
# Output: "PINALSIGYAHRPI"
# Explanation:
# P     I    N
# A   L S  I G
# Y A   H R
# P     I
# Example 3:

# Input: s = "A", numRows = 1
# Output: "A"
 

# Constraints:

# 1 <= s.length <= 1000
# s consists of English letters (lower-case and upper-case), ',' and '.'.
# 1 <= numRows <= 1000

class Solution:
    def convert(self, s: str, numRows: int) -> str:
        """Converts a string into a zigzag pattern across `numRows` rows and then
        reads it line-by-line to produce a new string.

        Args:
            s (str): the input string to convert
            numRows (int): the number of rows to use for the zigzag pattern

        Returns:
            str: the zigzag-converted string read row by row
        """
        if numRows == 1 or numRows >= len(s):
            return s

        # Create a list for each row
        rows = [''] * numRows
        index, step = 0, 1  # index: current row, step: direction (down or up)

        for char in s:
            rows[index] += char
            # Change direction at the first or last row
            if index == 0:
                step = 1
            elif index == numRows - 1:
                step = -1
            index += step

        # Join all rows to get the final string
        return ''.join(rows)
    
class AnotherSolution:
    def convert(self, s: str, numRows: int) -> str:
        """Converts a string into a zigzag pattern across `numRows` rows and then
        reads it line-by-line to produce a new string.

        Args:
            s (str): the input string to convert
            numRows (int): the number of rows to use for the zigzag pattern

        Returns:
            str: the zigzag-converted string read row by row
        """
        if numRows == 1:
            return s
        
         # Create a list for each row.
        rows = [''] * numRows

        # Initialize variables to track the current row and the direction.
        current_row = 0
        going_down = False

        #Iterate through each character of the input string.
        for char in s:
            # Append the character to the current row.
            rows[current_row] += char

            # Check if we've reached the top or bottom row.
            # If so, reverse the direction.
            if current_row == 0 or current_row == numRows - 1:
                going_down = not going_down

            # Move to the next row on the current direction.
            if going_down:
                current_row += 1
            else:
                current_row -= 1

        # Join the rows to form the final string.
        return "".join(rows)
    
def main():
    sol = Solution()
    sol1 = AnotherSolution()
    print(sol.convert("PAYPALISHIRING", 3))
    print(sol1.convert("PAYPALISHIRING", 4))

if __name__ == "__main__":
    main()