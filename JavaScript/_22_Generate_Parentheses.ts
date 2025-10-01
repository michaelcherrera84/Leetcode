/**
 * Given `n` pairs of parentheses, write a function to generate all
 * combinations of well-formed parentheses.
 * 
 * #### Example 1:
 * &nbsp;&nbsp;**Input:** n = 3 \
 * &nbsp;&nbsp;**Output:** ["((()))","(()())","(())()","()(())","()()()"]
 *
 * #### Example 2:
 * &nbsp;&nbsp;**Input:** n = 1 \
 * &nbsp;&nbsp;**Output:** ["()"]
 *
 * #### Constraints:
 * &nbsp;&nbsp;`1 <= n <= 8`
 * 
 * @param {number} n the number of pairs of parentheses
 * @returns {string[]} a list of all possible valid parentheses combinations
 */
function generateParenthesis(n: number): string[] {
    let result: string[] = [];
    buildList(n, 0, 0, "", result);
    return result;
}

/**
 * Recursive helper method to build valid parentheses strings.
 *
 * The method works by ensuring that:
 * - The number of open parentheses used never exceeds `n`.
 * - The number of close parentheses never exceeds the number of open
 * parentheses, which guarantees well-formedness.
 *
 * @param {number} n the total number of parentheses pairs
 * @param {number} opens the current count of open parentheses used
 * @param {number} closes the current count of close parentheses used
 * @param {string} current the string built so far
 * @param {string[]} result the list to collect valid combinations
 */
function buildList(n: number, opens: number, closes: number, current: string, result: string[]) {
    // Base case: when the string reaches length 2*n, it's complete
    if (current.length === n * 2) {
        result.push(current);
        return;
    }

    // If there are still open parentheses to add, add one and recurse.
    if (opens < n) buildList(n, opens + 1, closes, current + "(", result);

    // If a closing parenthesis can be added without exceeding the number of already open
    // parentheses, add one and recurse.
    if (closes < opens) buildList(n, opens, closes + 1, current + ")", result);
}

console.log(generateParenthesis(3));
