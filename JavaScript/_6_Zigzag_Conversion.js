// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"

// Write the code that will take a string and make this conversion given a number of rows:

// string convert(string s, int numRows);

// Example 1:

// Input: s = "PAYPALISHIRING", numRows = 3
// Output: "PAHNAPLSIIGYIR"
// Example 2:

// Input: s = "PAYPALISHIRING", numRows = 4
// Output: "PINALSIGYAHRPI"
// Explanation:
// P     I    N
// A   L S  I G
// Y A   H R
// P     I
// Example 3:

// Input: s = "A", numRows = 1
// Output: "A"

// Constraints:

// 1 <= s.length <= 1000
// s consists of English letters (lower-case and upper-case), ',' and '.'.
// 1 <= numRows <= 1000

/**
 * Converts a string into a zigzag pattern across `numRows` rows
 * and then reads it line-by-line to produce a new string.
 *
 * Example for s = "PAYPALISHIRING", numRows = 3:
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * Returns: "PAHNAPLSIIGYIR"
 *
 * @param {string} s - The input string to convert.
 * @param {number} numRows - The number of rows to use for the zigzag pattern.
 * @return {string} - The zigzag-converted string read row by row.
 */
var convert = function (s, numRows) {
    // 2D array representing each row of the zigzag
    const zigzag = [];

    // Initialize `numRows` empty arrays (rows) in zigzag
    for (let i = 0; i < numRows; i++) zigzag[i] = [];

    // i: index in input string
    // j: current row index
    // k: current column index (zigzag "vertical" slices)
    let i = 0,
        j = 0,
        k = 0;

    // Process characters until we exhaust the string
    while (s[i]) {
        // Move downward through the rows (vertical part of zigzag)
        while (j < numRows && s[i]) {
            zigzag[j++][k] = s[i++]; // Place char into the current row/col, then go down
        }
        // Step back up diagonally: start one row above bottom
        j = numRows - 2;
        while (j >= 0 && s[i]) {
            zigzag[j--][++k] = s[i++]; // Move up and right in zigzag
            if (j < 0) {               // If we overshoot the top row, reset for next cycle
                j = 1;
                break;
            }
        }
        // Safety check: if diagonal step overshot, fix indices
        if (j < 0) {
            j = 0;
            k++;
        }
    }

    // Read the zigzag rows in order and join into final result
    let result = "";
    for (i = 0; i < zigzag.length; i++) {
        for (j = 0; j < zigzag[i].length; j++) {
            if (zigzag[i][j]) result += zigzag[i][j]; // Skip empty cells
        }
    }

    return result;
};

console.log(convert("AB", 1));

/**
 * Optimized zigzag conversion without creating a full 2D grid.
 * Uses math to jump directly to characters in zigzag order.
 *
 * @param {string} s - The input string to convert.
 * @param {number} numRows - The number of rows to use for the zigzag pattern.
 * @return {string} - The zigzag-converted string read row by row.
 */
var betterConvert = function (s, numRows) {
    // If only one row, zigzag is just the original string
    if (numRows === 1) return s;

    // The full cycle length for one zigzag repetition
    // Example: numRows = 4 → divider = 6
    // Pattern repeats every divider chars
    const divider = 2 + (numRows - 2) * 2;

    let result = "";
    // Outer loop: iterate through each row of zigzag
    for (let i = 0; i <= divider / 2; i++) {
        // Inner loop: jump in steps of `divider` through the string
        for (let j = 0; j < s.length; j += divider) {
            // Vertical column character in this row
            if (j + i < s.length) result += s[j + i];

            // Diagonal character (if applicable):
            // - skip for first and last row
            // - skip if computed index is outside string bounds
            if (i !== 0 && i !== divider - i && j + divider - i < s.length) {
                result += s[j + divider - i];
            }
        }
    }

    return result;
};

console.log(betterConvert("PAYPALISHIRING", 3));