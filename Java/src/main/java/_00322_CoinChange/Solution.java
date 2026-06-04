package _00322_CoinChange;

import java.util.Arrays;

public class Solution {

    /**
     * You are given an integer array {@code coins} representing coins of different
     * denominations and an integer {@code amount} representing a total amount of
     * money.
     * 
     * Return <i>the fewest number of coins that you need to make up that
     * amount.</i> If that amount of money cannot be made up by any combination of
     * the coins, return {@code -1}.
     * 
     * You may assume that you have an infinite number of each kind of coin.
     * 
     * @param coins  array of coin denominations to use
     * @param amount the amount to make up from the coins
     * @return the fewest number of coins needed to make the amount
     */
    public int coinChange(int[] coins, int amount) {
        // array of coins needed to build to each quantity from zero to amount
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        Arrays.sort(coins);

        // Calculate the number of coins needed to build to each quantity from
        // zero to amount.
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = Integer.MAX_VALUE;

            // Check each coin to see if fewer coins are possible.
            for (int coin : coins) {
                // If the current quantity (`i`) minus the current coin value is
                // less than zero, then no more coins need to be checked.
                if (i - coin < 0)
                    break;
                // If dp[i - coin] is not equal to MAX_VALUE, then we know how
                // many coins are needed to make that quantity. The number of
                // coins needed to make the current quantity is the lesser of
                // that number of coins plus one more and the current number of
                // coins already determined.
                if (dp[i - coin] != Integer.MAX_VALUE)
                    dp[i] = Integer.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
