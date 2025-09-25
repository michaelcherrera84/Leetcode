using System;

public class _14_Longest_Common_Prefix
{
    /// <summary>
    /// Problem: Find the longest common prefix string among an array of strings.
    /// If no common prefix exists, return an empty string.
    /// Example 1:
    ///
    /// Input: strs = ["flower","flow","flight"]
    /// Output: "fl"
    /// Example 2:
    ///
    /// Input: strs = ["dog","racecar","car"]
    /// Output: ""
    /// Explanation: There is no common prefix among the input strings.
    /// 
    /// Constraints:
    ///
    /// 1 <= strs.length <= 200
    /// 0 <= strs[i].length <= 200
    /// strs[i] consists of only lowercase English letters if it is non-empty.
    /// </summary>
    public class Solution
    {
        /// <summary>
        /// First approach:
        /// Start with the first string as the initial "prefix candidate."
        /// Gradually shorten it while comparing with each subsequent string,
        /// trimming down the candidate until it matches all strings checked so far.
        /// </summary>
        public string LongestCommonPrefix(string[] strs)
        {
            // Start with the first string as the initial result
            string result = strs[0];

            // If the first string is empty, no common prefix is possible
            if (string.IsNullOrEmpty(result)) return "";

            // Compare result with each subsequent string
            for (int i = 1; i < strs.Length; i++)
            {
                // If the current string is empty, no common prefix can exist
                if (string.IsNullOrEmpty(strs[i])) return "";

                // If the current string is shorter than result,
                // trim result down to that length before comparing
                if (result.Length > strs[i].Length)
                    result = result.Substring(0, strs[i].Length);

                // Compare characters of result with current string
                for (int j = 0; j < strs[i].Length && j < result.Length; j++)
                {
                    // If characters mismatch, cut result down to the matched part
                    if (strs[i][j] != result[j])
                        result = result.Substring(0, j);
                }
            }

            // After processing all strings, result holds the common prefix
            return result;
        }
    }

    /// <summary>
    /// Alternative approach:
    /// Keep reducing the prefix (by chopping off its end) 
    /// until every string in the array starts with it.
    /// </summary>
    public class AnotherSolution
    {
        public string LongestCommonPrefix(string[] strs)
        {
            // Start with the first string as the candidate prefix
            string result = strs[0];

            // Compare with each subsequent string
            for (int i = 1; i < strs.Length; i++)
            {
                // While the current string does not start with result,
                // chop one character off the end of result
                while (!strs[i].StartsWith(result))
                {
                    result = result.Substring(0, result.Length - 1);

                    // If result becomes empty, no common prefix exists
                    if (result == "") return "";
                }
            }

            // At the end, result contains the longest common prefix
            return result;
        }
    }
}