from typing import List

class Solution:
    """
    You are given `a` string s and an array of strings `words`. All the strings of `words` are of **the same length**.

    A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated.

    - For example, if `words = ["ab","cd","ef"]`, then `"abcdef"`, `"abefcd"`, `"cdabef"`, `"cdefab"`, `"efabcd"`, and `"efcdab"` 
    are all concatenated strings. `"acdbef"` is not a concatenated string because it is not the concatenation of any 
    permutation of `words`.

    Return an array of *the starting indices* of all the concatenated substrings in `s`. You can return the answer in **any order**.

    #### Example 1:
    > **Input:** s = "barfoothefoobarman", words = ["foo","bar"] \n
    > **Output:** [0,9] \n
    > **Explanation:** \n
    > The substring starting at 0 is `"barfoo"`. It is the concatenation of `["bar","foo"]` which is a permutation of `words`. \n
    > The substring starting at 9 is `"foobar"`. It is the concatenation of `["foo","bar"]` which is a permutation of `words`.

    #### Example 2:
    > **Input:** s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] \n
    > **Output:** [] \n
    > **Explanation:** \n
    > There is no concatenated substring. \n

    #### Example 3:
    > **Input:** s = "barfoofoobarthefoobarman", words = ["bar","foo","the"] \n
    > **Output:** [6,9,12] \n
    > **Explanation:** \n
    > The substring starting at 6 is `"foobarthe"`. It is the concatenation of `["foo","bar","the"]`.
    > The substring starting at 9 is `"barthefoo"`. It is the concatenation of `["bar","the","foo"]`.
    > The substring starting at 12 is `"thefoobar"`. It is the concatenation of `["the","foo","bar"]`.

    #### Constraints:
    - `1 <= s.length <= 104`
    - `1 <= words.length <= 5000`
    - `1 <= words[i].length <= 30`
    - `s` and `words[i]` consist of lowercase English letters.
    """

    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        """
        You are given `a` string s and an array of strings `words`. All the strings of `words` are of **the same length**.
        
        A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated.

        Args:
            s (str): the string
            words (List[str]): the array of strings

        Returns:
            List[int]: an array of *the starting indices* of all the concatenated substrings in `s`. You can return the answer in **any order**.
        """

        if not s or not words:
            return []

        word_len = len(words[0])
        word_count = len(words)
        window_len = word_len * word_count

        # Count expected occurrences of each word.
        word_freq = {}
        for w in words:
            word_freq[w] = 1 + word_freq.get(w, 0)

        res = []
        n = len(s)

        # We run the sliding window for each possible alignment within one word length.
        # Example: if words are length 3, we consider starting at indices 0, 1, and 2.
        for offset in range(word_len):
            left = offset              # Start of the current window
            count = 0                  # Words matched in the window
            cur_freq = {}              # Frequency of words currently in the window

            # Move the right pointer in steps of word_len.
            for right in range(offset, n - word_len + 1, word_len):

                word = s[right : right + word_len]

                # If the word is part of the expected set, process it.
                if word in word_freq:
                    cur_freq[word] = cur_freq.get(word, 0) + 1
                    count += 1

                    # If this word occurs too many times, shrink the window.
                    while cur_freq[word] > word_freq[word]:
                        left_word = s[left : left + word_len]
                        cur_freq[left_word] -= 1
                        left += word_len
                        count -= 1

                    # When we have matched exactly `word_count` words, we found a valid start.
                    if count == word_count:
                        res.append(left)

                        # Move left to look for new windows.
                        left_word = s[left : left + word_len]
                        cur_freq[left_word] -= 1
                        left += word_len
                        count -= 1

                else:
                    # Reset if the word does not belong in any valid window.
                    cur_freq.clear()
                    count = 0
                    left = right + word_len

        return res


def main() :
    sol = Solution()
    print(sol.findSubstring("wordgoodgoodgoodbestword", ["word","good","best","good"]))

if __name__ == "__main__":
    main()