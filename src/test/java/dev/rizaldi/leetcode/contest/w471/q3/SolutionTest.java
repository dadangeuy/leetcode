package dev.rizaldi.leetcode.contest.w471.q3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void example1() {
        final int output = solution.longestBalanced("abbac");
        Assertions.assertEquals(4, output);
    }

    @Test
    public void example2() {
        final int output = solution.longestBalanced("aabcc");
        Assertions.assertEquals(3, output);
    }

    @Test
    public void example3() {
        final int output = solution.longestBalanced("aba");
        Assertions.assertEquals(2, output);
    }
}
