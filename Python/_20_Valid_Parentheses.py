class Solution:
    '''
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

    1. Open brackets must be closed by the same type of brackets.
    2. Open brackets must be closed in the correct order.
    3. Every close bracket has a corresponding open bracket of the same type.

    Example 1:
    Input: s = "()"
    Output: true

    Example 2:
    Input: s = "()[]{}"
    Output: true

    Example 3:
    Input: s = "(]"
    Output: false

    Example 4:
    Input: s = "([])"
    Output: true

    Example 5:
    Input: s = "([)]"
    Output: false

    Constraints:
    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.
    '''
    def isValid(self, s: str) -> bool:
        '''
        Checks whether a given string of parentheses/brackets/braces is valid.

        Args:
            s (str): The input string consisting only of '(', ')', '{', '}', '[' and ']'.

        Returns:
            bool: True if the string is valid according to the bracket rules,
                  False otherwise.
        '''
        # Mapping of opening brackets to their corresponding closing brackets
        opposites = {'(': ')', '[': ']', '{': '}'}

        # Stack to keep track of currently open brackets
        openings = []

        # Iterate through each character in the string
        for c in s:
            if c in "([{":
                # If it's an opening bracket, push it onto the stack
                openings.append(c)
            elif openings and c == opposites.get(openings[-1]):
                # If it's a closing bracket and matches the last opening, pop the stack
                openings.pop()
            else:
                # If it's a mismatch or there's no opening to match, it's invalid
                return False
        
        # If there are leftover openings, the string is invalid
        if len(openings) > 0:
            return False

        # All brackets matched correctly
        return True


# Example usage
sol = Solution()
print(sol.isValid("([])[()]"))  # Expected output: True