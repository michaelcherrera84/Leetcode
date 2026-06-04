package _00322_CoinChange;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSolution {
    Solution sol;

    @BeforeEach
    void setUp() {
        sol = new Solution();
    }

    @Test
    void example1() {
        int[] coins = {1, 2, 5};
        int amount = 11;
        int expected = 3;
        int actual = sol.coinChange(coins, amount);
        assertEquals(expected, actual);
    }

    @Test
    void example2() {
        int[] coins = {2};
        int amount = 3;
        int expected = -1;
        int actual = sol.coinChange(coins, amount);
        assertEquals(expected, actual);
    }

    @Test
    void example3() {
        int[] coins = {1};
        int amount = 0;
        int expected = 0;
        int actual = sol.coinChange(coins, amount);
        assertEquals(expected, actual);
    }
}
