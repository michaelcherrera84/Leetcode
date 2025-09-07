import java.util.ArrayList;
import java.util.HashMap;

public class _3_Longest_Substring_Without_Repeating_Characters {

    public static class Solution {

        public int lengthOfLongestSubstring(String s) {
            
            // Longest length found and length of current substring without repeating characters
            int finalCount = 0, count = 0;
            // List to keep track of characters in the current substring
            var letters = new ArrayList<Character>();

            for (int i = 0, j = 0; j < s.length(); j++)
                if (!letters.contains(s.charAt(j))) {
                    // If the current character is not in the list, add it.
                    letters.add(s.charAt(j));
                    count++;
                    // Update max length if substring length is longer.
                    if (count > finalCount)
                        finalCount = count;
                } else {  // If a duplicate is found...
                    // Shift the start of the substing by 1, clear the list, and reset the substring length. 
                    i++;
                    letters.clear();
                    count = 0;
                    j = i;
                }

            return finalCount;
        }
    }

    public static class BetterSolution {

        public int lengthOfLongestSubstring(String s) {

            // Left boundary of the substring, and longest substring length
            int left = 0, maxLength = 0;
            // Map of each character and its last seen index
            var lastSeen = new HashMap<Character, Integer>();

            for (int right = 0; right < s.length(); right++) {  // Right boundary of substring
                Character character = s.charAt(right);

                if (lastSeen.containsKey(character) && lastSeen.get(character) >= left)
                    // Move the left boundary to the right of the duplicate.
                    left = lastSeen.get(character) + 1;
                // Update the longest substring if the current is longer.
                maxLength = Math.max(maxLength, right - left + 1);
                // Update the last seen position of the current character.
                lastSeen.put(character, right);
            }

            return maxLength;
        }
    }


    public static void main(String[] args) {
        var solution = new Solution();
        int result = solution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);

        var betterSolution = new BetterSolution();
        result = betterSolution.lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);
    }
}
