package dev.rizaldi.leetcode.contest.bc168.q2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() {
        final var output = solution.maxSumOfSquares(2, 3);
        Assertions.assertEquals("30", output);
    }

    @Test
    public void example2() {
        final var output = solution.maxSumOfSquares(2, 17);
        Assertions.assertEquals("98", output);
    }

    @Test
    public void example3() {
        final var output = solution.maxSumOfSquares(1, 10);
        Assertions.assertEquals("", output);
    }
}
