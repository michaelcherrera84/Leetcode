/// Given a string containing digits from `2-9`` inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
///
/// 
/// **Example 1:**
/// - **Input:** digits = "23"
/// - **Output:** ["ad","ae","af","bd","be","bf","cd","ce","cf"]
///
/// **Example 2:**
/// - **Input:** digits = ""
/// - **Output:** []
/// 
/// **Example 3:**
/// - **Input:** digits = "2"
/// - **Output:** ["a","b","c"]
/// 
/// **Constraints:**
/// - `0 <= digits.length <= 4`
/// - `digits[i]` is a digit in the range `['2', '9']`.
enum _16_3Sum_Closest {
    
    /// A solution class that generates all possible letter combinations 
    /// corresponding to a given string of digits (like on a phone keypad).
    class Solution {
    
        /// Given a string of digits (2–9), returns all possible letter 
        /// combinations that the number could represent, based on the mapping 
        /// of digits to letters on a phone keypad.
        ///
        /// - Parameter digits: A string containing digits from '2' to '9'.
        /// - Returns: An array of all possible letter combinations. 
        ///   Returns an empty array if the input string is empty.
        func letterCombinations(_ digits: String) -> [String] {
            // Edge case: if no digits are provided, return an empty array.
            if digits.isEmpty {
                return []
            }

            // Mapping of digit -> corresponding letters, like a phone keypad.
            let phoneButtons: [Character: String] = [
                "2": "abc", 
                "3": "def",
                "4": "ghi",
                "5": "jkl",
                "6": "mno",
                "7": "pqrs",
                "8": "tuv",
                "9": "wxyz"
            ]

            // Storage for all generated combinations.
            var result: [String] = []

            /// Recursive helper function to generate combinations.
            ///
            /// - Parameters:
            ///   - resElement: The current partial combination being built.
            ///   - digitsSub: The remaining digits to process.
            func letterCombinations(_ resElement: String, _ digitsSub: String) {
                // Base case: if no digits remain, save the completed combination.
                if digitsSub.isEmpty {
                    result.append(resElement)
                    return
                }

                // Get the set of letters for the first digit in digitsSub.
                let letters = phoneButtons[digitsSub.first!]!

                // For each letter, extend the current combination and recurse.
                for c in letters {
                    letterCombinations(resElement + String(c), String(digitsSub.dropFirst()))
                }
            }

            // Start recursion with an empty prefix and the full digits string.
            letterCombinations("", digits)

            return result
        }
    }
}

// Example usage:
var sol = _16_3Sum_Closest.Solution()
print(sol.letterCombinations("23"))
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]