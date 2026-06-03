from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        """
        You are given an integer array `coins` representing coins of different
        denominations and an integer `amount` representing a total amount of
        money.

        Return *the fewest number of coins that you need to make up that amount*.
        If that amount of money cannot be made up by any combination of the
        coins, return `-1`.

        You may assume that you have an infinite number of each kind of coin.

        Args:
            coins (List[int]): list of denominations
            amount (int): amount of money

        Returns:
            int: the fewest number of coins needed to make up the amount of money
        """

        max_coin = 2**31 - 1
        dp = [max_coin] * (amount + 1)
        dp[0] = 0
        coins.sort()

        # Calculate the minimum number of coins required to acheive each money 
        # quantity up to `amount`.
        for i in range(1, amount + 1):
            for c in coins:
                # With the coins sorted, if the current coin is too large to be
                # used to acheive the current quantity, we do not need check any
                # more coins.
                if i - c < 0:
                    break
                # We already know how many coins it takes to achieve the current
                # quantity minus the current coin.
                if dp[i - c] != max_coin:
                    dp[i] = min(dp[i], 1 + dp[i - c])

        return -1 if dp[amount] == max_coin else dp[amount]


import unittest


class TestSolution(unittest.TestCase):
    def setUp(self) -> None:
        self.sol = Solution()

    def test_example1(self):
        coins = [1, 2, 5]
        amount = 11
        expected = 3
        actual = self.sol.coinChange(coins, amount)
        self.assertEqual(expected, actual)

    def test_example2(self):
        coins = [2]
        amount = 3
        expected = -1
        actual = self.sol.coinChange(coins, amount)
        self.assertEqual(expected, actual)

    def test_example3(self):
        coins = [1]
        amount = 0
        expected = 0
        actual = self.sol.coinChange(coins, amount)
        self.assertEqual(expected, actual)

    def test_max(self):
        max_coin = 2**31 - 1
        max_amount = 10**4
        coins = [max_coin, max_amount]
        amount = max_amount 
        expected = 1
        actual = self.sol.coinChange(coins, amount)
        self.assertEqual(expected, actual)


if __name__ == "__main__":
    unittest.main()
