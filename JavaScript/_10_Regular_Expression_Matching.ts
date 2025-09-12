/**
 * Determines if a string `s` matches a pattern `p`.
 * The pattern supports:
 *  - '.' (dot) which matches any single character
 *  - '*' which means zero or more occurrences of the preceding character
 *
 * Example:
 *   isMatch("aab", "c*a*b") → true
 *
 * @param s - Input string
 * @param p - Pattern string (may contain '.' and '*')
 * @returns true if the pattern matches the entire string, false otherwise
 */
function isMatch(s: string, p: string): boolean {
    const n = s.length;

    // Dynamic programming (DP) arrays:
    // prev[i] → whether the prefix of pattern processed so far matches the first i characters of `s`
    // curr[i] → same as above, but for the current pattern character(s) being processed
    let prev = new Array<boolean>(n + 1).fill(false);
    let curr = new Array<boolean>(n + 1).fill(false);

    // Empty pattern matches empty string
    prev[0] = true;

    // Process the pattern string one character (or one char + '*') at a time
    for (let j = 0; j < p.length; ) {
        const c = p[j]; // current pattern character
        const asterisk = j + 1 < p.length && p[j + 1] === "*"; // check if it’s followed by '*'
        curr.fill(false); // reset DP row

        if (!asterisk) {
            // Case 1: single character (no '*')
            // Match s[i-1] with c if:
            // - previous substring matched up to i-1, and
            // - current char matches (direct match or '.')
            for (let i = 1; i <= n; i++) {
                if (prev[i - 1] && (c === "." || s[i - 1] === c)) {
                    curr[i] = true;
                }
            }
            j += 1; // move to next pattern char
        } else {
            // Case 2: character followed by '*'
            // This means zero or more repetitions of `c`
            
            // Zero repetitions: pattern up to here still matches empty prefix
            curr[0] = prev[0];

            for (let i = 1; i <= n; i++) {
                const match = c === "." || s[i - 1] === c;

                // Two possibilities:
                // (1) curr[i - 1] && match → extend current repetition of c
                // (2) prev[i] → zero repetitions, just skip "c*"
                if ((curr[i - 1] && match) || prev[i]) {
                    curr[i] = true;
                }
            }
            j += 2; // skip both c and '*'
        }

        // Swap DP arrays for next iteration (reuse memory instead of reallocating)
        const tmp = prev;
        prev = curr;
        curr = tmp;
    }

    // Final result: does full pattern match the full string?
    return prev[n];
}

console.log(isMatch("a", "ab*a"));
