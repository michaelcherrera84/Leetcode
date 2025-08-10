// Given a string s, return the longest palindromic substring in s.

// Example 1:
// Input: s = "babad"
// Output: "bab"
// Explanation: "aba" is also a valid answer.

// Example 2:
// Input: s = "cbbd"
// Output: "bb"

// Constraints:
// 1 <= s.length <= 1000
// s consist of only digits and English letters.

/**
 * Class for Manacher's algorithm to find all palindromic substrings in linear time.
 */
class Manacher {
    ms = "@"; // modified string with sentinels and separators to handle even/odd palindromes uniformly
    p; // array to store the radius of the palindrome centered at each position in ms

    /**
     * Initializes the Manacher object and preprocesses the string.
     *
     * @param {string} s the input string to process
     */
    constructor(s) {
        // Preprocess the string.
        for (const c of s) this.ms += "#" + c;
        this.ms += "$";
        this.p = new Array(this.ms.length).fill(0);
        this.runManacher();
    }

    /**
     * Executes Manacher's algorithm to compute palindrome radii for each center in ms.
     */
    runManacher() {
        const n = this.ms.length;
        let l = 0, r = 0; // left and right boundary of palindrome

        for (let i = 1; i < n - 1; i++) {
            // Calculate this mirror position of i with respect to the current palindrome center
            let mirror = l + r - i;
            // Assign p using the mirror value if within the current palindrome
            if (mirror >= 0 && mirror < n) this.p[i] = Math.max(0, Math.min(r - i, this.p[mirror]));
            else this.p[i] = 0;

            // Attempt to expand palindrome centered at i
            while (
                i + 1 + this.p[i] < n &&
                i - 1 - this.p[i] >= 0 &&
                this.ms[i + 1 + this.p[i]] == this.ms[i - 1 - this.p[i]]
            )
                this.p[i] += 1;

            // If palindrome centered at i expands past r, update l and r
            if (i + this.p[i] > r) {
                l = i - this.p[i];
                r = i + this.p[i];
            }
        }
    }

    /**
     * Returns the length of the longest palindrome centered at index cen in the original string.
     *
     * @param {int} cen center index in the original string
     * @param {int} odd 0 for odd-length palindrome, 1 for even-length palindrome
     * @returns the length of the longest palindrome centered at cen
     */
    getLongest(cen, odd) {
        // Map the original index and odd/even to the corresponding position in ms
        const pos = 2 * cen + 2 + odd;
        return this.p[pos];
    }
}

/**
 * Returns the longest palindromic substring in s.
 *
 * @param {string} s the string
 * @return {string} longest palindromic substing
 */
var longestPalindrome = function (s) {
    const n = s.length;
    let maxLen = 1; // the maximum palindrome length found
    let start = 0; // the starting index of the longest palindrome
    const M = new Manacher(s);

    for (let i = 0; i < n; i++) {
        // Check for odd-length palindrome centered at i.
        const oddLen = M.getLongest(i, 0);
        // Update start index if longer palindrome is found.
        if (oddLen > maxLen) start = i - Math.floor((oddLen - 1) / 2);
        // Check for even-length palindrome centered at i.
        const evenLen = M.getLongest(i, 1);
        // Update start index if longer palindrome is found.
        if (evenLen > maxLen) start = i - Math.floor((evenLen - 1) / 2);

        // Update the maximum palindrome length found so far
        maxLen = Math.max(maxLen, Math.max(oddLen, evenLen));
    }

    // Return the longest palindromic substring
    return s.slice(start, start + maxLen);
};

console.log(longestPalindrome("qwerasdfhhhhfdsarewt"));
