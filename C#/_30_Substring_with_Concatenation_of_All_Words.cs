using System;

var sol = new _30_Substring_with_Concatenation_of_All_Words.Solution();
IList<int> indexes = sol.FindSubstring("barfoofoobarthefoobarman", ["bar", "foo", "the"]);

Console.Write("[");
foreach (var item in indexes)
{
    Console.Write(item + ", ");
}
Console.Write("\b\b]");

public class _30_Substring_with_Concatenation_of_All_Words
{
    /// <summary>
    /// You are given a string <c>s</c> and an array of strings <c>words</c>. All the strings of <c>words</c> are of <b>the same length</b>.
    /// <para>
    /// A <b>concatenated string</b> is a string that exactly contains all the strings of any permutation of <c>words</c> concatenated.
    /// <list type="bullet">
    /// For example, if <c>words = ["ab","cd","ef"]</c>, then <c>"abcdef"</c>, <c>"abefcd"</c>, <c>"cdabef"</c>, <c>"cdefab"</c>, <c>"efabcd"</c>, and <c>"efcdab"</c> are all concatenated strings. <c>"acdbef"</c> is not a concatenated string because it is not the concatenation of any permutation of <c>words</c>.
    /// </list>
    /// </para>
    /// <para>
    /// Return an array of <em>the starting indices</em> of all the concatenated substrings in <c>s</c>. You can return the answer in <b>any order</b>.
    /// </para>
    /// </summary>
    /// <remarks>
    /// <example>
    /// <para><b>Example 1:</b></para>
    /// <para><b>Input:</b> s = "barfoothefoobarman", words = ["foo","bar"]</para>
    /// <para><b>Output:</b> [0,9]</para>
    /// <para><b>Explanation:</b></para>
    /// <para>The substring starting at 0 is <c>"barfoo"</c>. It is the concatenation of <c>["bar","foo"]</c> which is a permutation of <c>words</c>.</para>
    /// <para>The substring starting at 9 is <c>"foobar"</c>. It is the concatenation of <c>["foo","bar"]</c> which is a permutation of <c>words</c>.</para>
    /// </example>
    /// <example>
    /// <para><b>Example 2:</b></para>
    /// <para><b>Input:</b> "wordgoodgoodgoodbestword", words = ["word","good","best","word"]</para>
    /// <para><b>Output:</b> []</para>
    /// <para><b>Explanation:</b></para>
    /// <para>There is no concatenated substring.</para>
    /// </example>
    /// <example>
    /// <para><b>Example 3:</b></para>
    /// <para><b>Input:</b> "barfoofoobarthefoobarman", words = ["bar","foo","the"]</para>
    /// <para><b>Output:</b> [6,9,12]</para>
    /// <para><b>Explanation:</b></para>
    /// <para>The substring starting at 6 is <c>"foobarthe"</c>. It is the concatenation of <c>["foo","bar","the"]</c>.</para>
    /// <para>The substring starting at 9 is <c>"barthefoo"</c>. It is the concatenation of <c>["bar","the","foo"]</c>.</para>
    /// <para>The substring starting at 12 is <c>"thefoobar"</c>. It is the concatenation of <c>["the","foo","bar"]</c>.</para>
    /// </example>
    /// <para><b>Constraints:</b></para>
    /// <list type="bullet">
    /// <item><c>1 &lt;= s.length &lt;= 104</c></item>
    /// <item><c>1 &lt;= words.length &lt;= 5000</c></item>
    /// <item><c>1 &lt;= words[i].length &lt;= 30</c></item>
    /// <item><c>s</c> and <c>words[i]</c> consist of lowercase English letters.</item>
    /// </list>
    /// </remarks>
    public class Solution
    {
        /// <summary>
        /// You are given a string <c>s</c> and an array of strings <c>words</c>. All the strings of <c>words</c> are of <b>the same length</b>.
        /// <para>A <b>concatenated string</b> is a string that exactly contains all the strings of any permutation of <c>words</c> concatenated.</para>
        /// </summary>
        /// <param name="s">the string</param>
        /// <param name="words">the array of strings</param>
        /// <returns>an array of <em>the starting indices</em> of all the concatenated substrings in <c>s</c>. You can return the answer in <b>any order</b>.</returns>
        public IList<int> FindSubstring(string s, string[] words)
        {
            var sLen = s.Length;
            var wordLen = words[0].Length;
            var wordCount = words.Length;
            var winLen = wordLen * wordCount;

            // Count expected occurrences of each word.
            var wordFreq = new Dictionary<string, int>();
            foreach (string word in words)
            {
                wordFreq[word] = wordFreq.GetValueOrDefault(word) + 1;
            }

            var res = new List<int>();

            // We run the sliding window for each possible alignment within one word length.
            // Example: if words are length 3, we consider starting at indices 0, 1, and 2.
            for (int offset = 0; offset < wordLen; offset++)
            {
                var left = offset;                              // Start of the current window
                var count = 0;                                  // Words matched in the window
                var currFreq = new Dictionary<string, int>();   // Frequency of words currently in the window

                // Move the right pointer in steps of word_len.
                for (int right = offset; right < sLen - wordLen + 1; right += wordLen)
                {
                    var word = s.Substring(right, wordLen);

                    // If the word is part of the expected set, process it.
                    if (wordFreq.ContainsKey(word))
                    {
                        currFreq[word] = currFreq.GetValueOrDefault(word) + 1;
                        count++;

                        // If this word occurs too many times, shrink the window.
                        while (currFreq[word] > wordFreq[word])
                        {
                            var leftWord = s.Substring(left, wordLen);
                            currFreq[leftWord] -= 1;
                            left += wordLen;
                            count -= 1;
                        }

                        // When we have matched exactly `word_count` words, we found a valid start.
                        if (count == wordCount)
                        {
                            res.Add(left);

                            // Move left to look for new windows.
                            var leftWord = s.Substring(left, wordLen);
                            currFreq[leftWord]--;
                            left += wordLen;
                            count -= 1;
                        }
                    }
                    else
                    {
                        // Reset if the word does not belong in any valid window.
                        currFreq.Clear();
                        count = 0;
                        left = right + wordLen;
                    }
                }
            }

            return res;
        }
    }
}