/**
 * Given a string containing digits from `2-9`` inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 *
 * @param digits a string containing digits from '2' to '9'
 * @returns an array of all possible letter combinations
 */
function letterCombinations(digits: string): string[] {
    // If no digits are provided, return an empty array.
    if (digits.length < 1) return [];

    // Storage for all generated combinations.
    let result: string[] = [];

    getResult(digits, "", result);

    return result;
}

// Mapping of digit -> corresponding letters, like a phone keypad.
const buttons = new Map([
    ["2", "abc"],
    ["3", "def"],
    ["4", "ghi"],
    ["5", "jkl"],
    ["6", "mno"],
    ["7", "pqrs"],
    ["8", "tuv"],
    ["9", "wxyz"],
]);

/**
 * Recursive helper funtion to generate combinations.
 *
 * @param digits the remaining digits to process
 * @param element the current partial combination being built
 * @param result storage for all generated combinations
 */
function getResult(digits: string, element: string, result: string[]) {
    // Base case: if no digits remain, save the completed combination.
    if (digits.length < 1) {
        result.push(element);
        return;
    }

    // Get the set of letters for the first digit.
    const letters = buttons.get(digits[0]);

    // For each letter, extend the current combination and recurse.
    for (let i = 0; i < letters!.length; i++) {
        getResult(digits.substring(1), element + letters![i], result);
    }
}

console.log(letterCombinations("2"));
