import Cocoa

extension String {

    /// Safely extracts a substring using integer offsets.
    ///
    /// Swift `String` doesn't allow direct integer indexing, because its characters
    /// are variable-width Unicode. This helper converts the provided integer
    /// positions into proper `String.Index` values before slicing.
    ///
    /// - Parameters:
    ///   - start: The starting character offset (0-based) from the beginning of the string.
    ///   - length: The number of characters to include in the slice.
    /// - Returns: A new `String` containing the requested substring.
    ///
    /// - Note: This counts in *characters*, not bytes. If you're working with
    ///   raw ASCII data or need byte-based indexing, consider using `utf8` instead.
    func slice(from start: Int, length: Int) -> String {
    
        // Convert the starting offset into a valid String.Index.
        let startIndex = index(self.startIndex, offsetBy: start)
        
        // Move forward by `length` characters to determine the end boundary.
        let endIndex = index(startIndex, offsetBy: length)
        
        // Create and return a new String from the character range.
        return String(self[startIndex..<endIndex])
    }
}


/// You are given a string `s` and an array of strings `words`. All the strings of `words` are of **the same length**.
///
/// A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated.
///
///  - For example, if `words = ["ab","cd","ef"]`, then `"abcdef"`, `"abefcd"`, `"cdabef"`, `"cdefab"`, `"efabcd"`, and `"efcdab"` 
/// are all concatenated strings. `"acdbef"` is not a concatenated string because it is not the concatenation of any permutation of `words`.
///
/// Return an array of *the starting indices* of all the concatenated substrings in `s`. You can return the answer in **any order**.
/// 
/// #### Example 1:
/// > **Input:** s = "barfoothefoobarman", words = ["foo","bar"] \n
/// > **Output:** [0,9] \n
/// > **Explanation:** \n
/// > The substring starting at 0 is `"barfoo"`. It is the concatenation of `["bar","foo"]` which is a permutation of `words`. \n
/// > The substring starting at 9 is `"foobar"`. It is the concatenation of `["foo","bar"]` which is a permutation of `words`.
///
/// #### Example 2:
/// > **Input:** s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] \n
/// > **Output:** [] \n
/// > **Explanation:** \n
/// > There is no concatenated substring. \n
///
/// #### Example 3:
/// > **Input:** s = "barfoofoobarthefoobarman", words = ["bar","foo","the"] \n
/// > **Output:** [6,9,12] \n
/// > **Explanation:** \n
/// > The substring starting at 6 is `"foobarthe"`. It is the concatenation of `["foo","bar","the"]`.
/// > The substring starting at 9 is `"barthefoo"`. It is the concatenation of `["bar","the","foo"]`.
/// > The substring starting at 12 is `"thefoobar"`. It is the concatenation of `["the","foo","bar"]`.
///
/// #### Constraints:
/// - `1 <= s.length <= 104`
/// - `1 <= words.length <= 5000`
/// - `1 <= words[i].length <= 30`
/// - `s` and `words[i]` consist of lowercase English letters.
class Solution {

    /// You are given a string `s` and an array of strings `words`. All the strings of `words` are of **the same length**.
    ///    
    /// A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated.
    /// 
    /// - Parameters:
    ///   - s: the string
    ///   - words: the array of strings
    /// - Returns: an array of *the starting indices* of all the concatenated substrings in `s`. You can return the answer in **any order**.
    func findSubstring(_ s: String, _ words: [String]) -> [Int] {
        var res: [Int] = []
        var wordFreq: [String: Int] = [:]
        let wordLength = words[0].count
        let sLength = s.count
        
        // Count expected occurrences of each word.
        for word in words {
            wordFreq[word, default: 0] += 1
        }
        
        // We run the sliding window for each possible alignment within one word length.
        // Example: if words are length 3, we consider starting at indices 0, 1, and 2.
        for offset in 0..<wordLength {
            var left = offset                       // Start of the current window
            var count = 0                           // Words matched in the window
            var currentFreq: [String: Int] = [:]    // Frequency of words currently in the window
            
            // Move the right pointer in steps of wordLength.
            for right in stride(from: offset, to: sLength - wordLength + 1, by: wordLength) {
                let word = s.slice(from: right, length: wordLength)
                
                // If the word is part of the expected set, process it.
                if wordFreq[word] != nil {
                    currentFreq[word, default: 0] += 1
                    count += 1
                    
                    // If this word occurs too many times, shrink the window.
                    while currentFreq[word]! > wordFreq[word]! {
                        let leftWord = s.slice(from: left, length: wordLength)
                        currentFreq[leftWord]! -= 1
                        count -= 1
                    }
                    
                    // When we have matched exactly `word_count` words, we found a valid start.
                    if count == words.count {
                        res.append(left)

                        // Move left to look for new windows.
                        let leftWord = s.slice(from: left, length: wordLength)
                        currentFreq[leftWord]! -= 1
                        left += wordLength
                        count -= 1
                    }
                } else {
                    // Reset if the word does not belong in any valid window.
                    left = right + wordLength
                    count = 0
                    currentFreq.removeAll()
                }
            }
        }
        
        return res
    }
}

let sol = Solution()
print(sol.findSubstring("barfoothefoobarman", ["foo", "bar"]))