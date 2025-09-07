
/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1: Input: s = "babad" Output: "bab" Explanation: "aba" is also a
 * valid answer.
 *
 * Example 2: Input: s = "cbbd" Output: "bb"
 *
 * Constraints: 1 <= s.length <= 1000 s consist of only digits and English
 * letters.
 */
public class _5_Longest_Palindromic_Substring {

    public static class Solution {

        /**
         * Return the longest palindromic substing in s.
         *
         * @param s the string
         * @return the longest palindromic substing
         */
        public static String longestPalindrome(String s) {

            int n = s.length();
            int maxLen = 1; // the maximum palindrome length found
            int start = 0;  // the starting index of the longest palindrome
            var M = new Manacher(s);

            for (int i = 0; i < n; i++) {
                // Check for odd-length palindrome centered at i.
                int oddLen = M.getLongest(i, 0);
                // Update start index if longer palindrome is found.
                if (oddLen > maxLen) {
                    start = i - (oddLen - 1) / 2;
                }
                // Check for even-length palindrome centered at i.
                int evenLen = M.getLongest(i, 1);
                // Update start index if longer palindrome is found.
                if (evenLen > maxLen) {
                    start = i - (evenLen - 1) / 2;
                }
                // Update the maximum palindrome length found so far.
                maxLen = Math.max(maxLen, Math.max(oddLen, evenLen));
            }
            // Return the longest palindromic substing.
            return s.substring(start, start + maxLen);
        }
    }

    /**
     * Class for Manacher's algorithm to find all palindromic substrings in
     * linear time.
     */
    public static class Manacher {

        private String ms;      // modified string with sentinels and separators to handle even/odd palindromes uniformly
        private final int[] P;  // array to store the radius of the palindrome centered at each position in ms

        /**
         * Initializes the Manacher object and preprocesses the string.
         *
         * @param s the input string to process
         */
        public Manacher(String s) {
            // Preprocess the string.
            ms = "@";
            for (char c : s.toCharArray()) {
                ms += "#" + c;
            }
            ms += "#";
            P = new int[ms.length()];
            runManacher();
        }

        /**
         * Executes Manacher's algorithm to compute palindrome radii for each
         * center in ms.
         */
        private void runManacher() {

            int n = ms.length();
            int l = 0, r = 0;   // left and right boundary of palindrome

            for (int i = 1; i < n - 1; i++) {
                // Calculate this mirror position of i with respect to the current palindrome center.
                int mirror = l + r - i;
                // Assign p using the mirror value if withing the current palindrome.
                if (mirror >= 0 && mirror < n) {
                    P[i] = Math.max(0, Math.min(r - i, P[mirror]));
                } else {
                    P[i] = 0;
                }

                // Attempt to expand the palindrome centered at i.
                while (i + 1 + P[i] < n
                        && i - 1 - P[i] >= 0
                        && ms.charAt(i + 1 + P[i]) == ms.charAt(i - 1 - P[i])) {
                    P[i]++;
                }

                // If palindrome centered at i expands past r, update l and r.
                if (i + P[i] > r) {
                    l = i - P[i];
                    r = i + P[i];
                }
            }
        }

        /**
         * Return the length of the longest palindrome centered at index cen in
         * the original string.
         *
         * @param cen center index in the original string
         * @param odd 0 for odd-length palindrome, 1 for even-length palindrome
         * @return the length of the longest palindrome centered at cen
         */
        public int getLongest(int cen, int odd) {
            // Map the original index and odd/even to the corresponding position in ms.
            int pos = 2 * cen + 2 + odd;
            return P[pos];
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.longestPalindrome("qwerasdfdsazxcv"));
    }
}
