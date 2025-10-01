
import java.util.ArrayList;
import java.util.List;

/**
 * Given {@code n} pairs of parentheses, write a function to generate all
 * combinations of well-formed parentheses.
 * <pre>
 * <b>Example 1:</b>
 *   <b>Input:</b> n = 3
 *   <b>Output:</b> ["((()))","(()())","(())()","()(())","()()()"]
 *
 * <b>Example 2:</b>
 *   <b>Input:</b> n = 1
 *   <b>Output:</b> ["()"]
 *
 * <b>Constraints:</b>
 *   {@code 1 <= n <= 8}
 * </pre>
 */
public class _22_Generate_Parentheses {

    class Solution {

        /**
         * Generates all combinations of well-formed parentheses.
         *
         * @param n the number of pairs of parentheses
         * @return a list of all possible valid parentheses combinations
         */
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            // Start recursive generation with 0 opens, 0 closes, and an empty string
            generateParenthesis(n, 0, 0, "", result);
            return result;
        }

        /**
         * Recursive helper method to build valid parentheses strings.
         *
         * <p>
         * The method works by ensuring that:
         * <ul>
         * <li>The number of open parentheses used never exceeds {@code n}.</li>
         * <li>The number of close parentheses never exceeds the number of open
         * parentheses, which guarantees well-formedness.</li>
         * </ul>
         *
         * @param n the total number of parentheses pairs
         * @param opens the current count of open parentheses used
         * @param closes the current count of close parentheses used
         * @param current the string built so far
         * @param result the list to collect valid combinations
         */
        public void generateParenthesis(int n, int opens, int closes, String current, List<String> result) {
            // Base case: when the string reaches length 2 * n, it's complete
            if (current.length() == n * 2) {
                result.add(current);
                return;
            }

            // If we have open parentheses to add, add one and recurse.
            if (opens < n) {
                generateParenthesis(n, opens + 1, closes, current + '(', result);
            }

            // If a closing parenthesis can be added without exceeding the number of already open
            // parentheses, add one and recurse.
            if (closes < opens) {
                generateParenthesis(n, opens, closes + 1, current + ")", result);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new _22_Generate_Parentheses().new Solution().generateParenthesis(2));
    }
}
