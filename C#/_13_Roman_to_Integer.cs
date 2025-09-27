using System;

// _13_Roman_to_Integer.Solution sol = new _13_Roman_to_Integer.Solution();
// _13_Roman_to_Integer.AnotherSolution aSol = new _13_Roman_to_Integer.AnotherSolution();
// Console.WriteLine(sol.romanToInt("MMMXLVIII"));
// Console.WriteLine(aSol.romanToInt("MMMXLVIII"));

/// <summary>
/// Problem 13: Roman to Integer
/// Contains two different implementations for converting a Roman numeral string into its integer value.
/// </summary>
public class _13_Roman_to_Integer
{
    /// <summary>
    /// First approach using a Dictionary for Roman numeral values.
    /// </summary>
    public class Solution
    {
        /// <summary>
        /// Converts a Roman numeral string to an integer.
        /// </summary>
        /// <param name="s">Roman numeral string (e.g., "XIV")</param>
        /// <returns>Integer value of the Roman numeral</returns>
        public int romanToInt(string s)
        {
            int result = 0;

            // Dictionary mapping Roman numeral characters to their integer values
            Dictionary<char, int> nums = new Dictionary<char, int>();
            nums.Add('I', 1); nums.Add('V', 5); nums.Add('X', 10); nums.Add('L', 50);
            nums.Add('C', 100); nums.Add('D', 500); nums.Add('M', 1000);

            // Loop through each character in the string
            for (int i = 0; i < s.Length; i++)
            {
                int num1; // Current numeral value
                int num2 = 0; // Next numeral value (default 0 if none)

                // Look up the value for the current character
                nums.TryGetValue(s[i], out num1);

                // If not at the end, also get the next character’s value
                if (i + 1 < s.Length)
                {
                    nums.TryGetValue(s[i + 1], out num2);
                }

                // If current is smaller than next, subtract (e.g., IV = 5 - 1 = 4)
                if (i + 1 < s.Length && num1 < num2)
                {
                    result -= num1;
                }
                else // Otherwise, just add it
                {
                    result += num1;
                }
            }

            return result;
        }
    }

    /// <summary>
    /// Second approach using an integer array indexed by character codes
    /// for faster lookup (avoids Dictionary overhead).
    /// </summary>
    public class AnotherSolution
    {
        // Precomputed values for ASCII character codes
        private static readonly int[] nums;

        // Static constructor initializes the lookup table
        static AnotherSolution()
        {
            nums = new int[89]; // Large enough to cover ASCII up to 'X'/'M'
            nums['I'] = 1; nums['V'] = 5; nums['X'] = 10; nums['L'] = 50;
            nums['C'] = 100; nums['D'] = 500; nums['M'] = 1000;
        }

        /// <summary>
        /// Converts a Roman numeral string to an integer.
        /// </summary>
        /// <param name="s">Roman numeral string (e.g., "MCMXCIV")</param>
        /// <returns>Integer value of the Roman numeral</returns>
        public int romanToInt(string s)
        {
            int result = 0;

            // Loop through each character
            for (int i = 0; i < s.Length; i++)
            {
                // If the current numeral is less than the next, subtract it
                if (i + 1 < s.Length && nums[s[i]] < nums[s[i + 1]])
                {
                    result -= nums[s[i]];
                }
                else // Otherwise, add it
                {
                    result += nums[s[i]];
                }
            }

            return result;
        }
    }
}