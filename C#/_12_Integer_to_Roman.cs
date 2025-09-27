using System;

// _12_Integer_to_Roman.Solution sol = new _12_Integer_to_Roman.Solution();
// Console.WriteLine(sol.IntToRoman(55));


/// <summary>
/// Converts integers to Roman numerals (constraint: 1 <= num <= 3999).
/// Implements digit-by-digit conversion by processing ones, tens,
/// hundreds, and thousands places separately.
/// </summary>
public class _12_Integer_to_Roman
{
    public class Solution
    {
        /// <summary>
        /// Converts an integer into its Roman numeral representation.
        /// </summary>
        /// <param name="num">The integer to convert (1 <= num <= 3999).</param>
        /// <returns>A Roman numeral string.</returns>
        public string IntToRoman(int num)
        {
            string result = "";   // Final Roman numeral result

            int place = 1;        // Tracks which decimal place we're at (1, 10, 100, 1000)
            while (num > 0)
            {
                int digit = num % 10;   // Extract the last digit (ones, tens, etc.)

                // Handle conversion based on the current place
                switch (place)
                {
                    case 1: // Ones place
                        if (digit < 4)
                            // Repeat "I" digit times (1-3)
                            for (int i = 1; i <= digit; i++)
                                result = "I" + result;
                        else if (digit == 4)
                            result = "IV";   // Special case: 4
                        else if (digit < 9)
                        {
                            // For 5-8: start with "V" and add extra "I"s
                            result = "V";
                            for (int i = 6; i <= digit; i++)
                                result += "I";
                        }
                        else result = "IX";  // Special case: 9
                        break;

                    case 10: // Tens place
                        if (digit < 4)
                            for (int i = 1; i <= digit; i++)
                                result = "X" + result;
                        else if (digit == 4)
                            result = "XL" + result;  // 40
                        else if (digit < 9)
                        {
                            // For 50-80
                            string temp = "L";
                            for (int i = 6; i <= digit; i++)
                                temp += "X";
                            result = temp + result;
                        }
                        else result = "XC" + result;  // 90
                        break;

                    case 100: // Hundreds place
                        if (digit < 4)
                            for (int i = 1; i <= digit; i++)
                                result = "C" + result;
                        else if (digit == 4)
                            result = "CD" + result;   // 400
                        else if (digit < 9)
                        {
                            // For 500-800
                            string temp = "D";
                            for (int i = 6; i <= digit; i++)
                                temp += "C";
                            result = temp + result;
                        }
                        else result = "CM" + result;  // 900
                        break;

                    case 1000: // Thousands place
                        // Repeat "M" digit times (max 3 since num <= 3999)
                        for (int i = 1; i <= digit; i++)
                            result = "M" + result;
                        break;
                }

                // Move on to the next higher decimal place
                num /= 10;
                place *= 10;
            }

            return result;
        }
    }

    public class AnotherSolution
    {
        /// <summary>
        /// Converts an integer into a Roman numeral string.
        /// </summary>
        /// <param name="num">The integer to convert (1 <= num <= 3999).</param>
        /// <returns>The Roman numeral representation.</returns>
        public string IntToRoman(int num)
        {
            // Predefined Roman numeral representations for each digit place
            string[] thousands = { "", "M", "MM", "MMM" }; // 0-3
            string[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // 0-9
            string[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }; // 0-9
            string[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }; // 0-9

            // Breakdown the number by place value:
            // - Thousands digit: num / 1000
            // - Hundreds digit: (num % 1000) / 100
            // - Tens digit: (num % 100) / 10
            // - Ones digit: num % 10
            string result =
                thousands[num / 1000] +
                hundreds[(num % 1000) / 100] +
                tens[(num % 100) / 10] +
                ones[num % 10];

            return result;
        }
    }
}