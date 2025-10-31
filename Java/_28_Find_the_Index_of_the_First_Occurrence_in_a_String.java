
public class _28_Find_the_Index_of_the_First_Occurrence_in_a_String {

    /**
     * Given two strings {@code needle} and {@code haystack}, return the index
     * of the first occurrence of {@code needle} in {@code haystack}, or
     * {@code -1} if {@code needle} is not part of {@code haystack}
     * <pre>
     *   <b>Example 1:</b>
     *   <b>Input:</b> haystack = "sadbutsad", needle = "sad"
     *   <b>Output:</b> 0
     *   <b>Explanation:</b> "sad" occurs at index 0 and 6. The first occurrence is at index 0, so we return 0.
     * 
     *   <b>Example 2:</b>
     *   <b>Input:</b> haystack = "leetcode", needle = "leeto"
     *   <b>Output:</b> -1
     *   <b>Explanation:</b> "leeto" did not occur in "leetcode", so we return -1.
     * </pre>
     * <b>Constraints:</b>
     * <ul>
     * <li>{@code 1 <= haystack.length, needle.length <= 10^4}</li>
     * <li>{@code haystack} and {@code needle} consist of only lowercase English
     * characters.</li>
     * </ul>
     */
    static class Solution {

        /**
         * Returns the index within this string of the first occurrence of the specified substring.
         * 
         * @param haystack the string
         * @param needle the substing
         * @return the index of the first occurrence of the specified substring, or -1 if there is no such occurrence
         */
        public int strStr(String haystack, String needle) {
            return haystack.indexOf(needle);
        }
    }

    public static void main(String[] args) {
        var sol = new Solution();
        System.out.println(sol.strStr("stringTesting", "Testing"));
    }
}
