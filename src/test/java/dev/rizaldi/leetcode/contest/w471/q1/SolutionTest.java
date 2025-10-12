package dev.rizaldi.leetcode.contest.w471.q1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() {
        final int output = solution.sumDivisibleByK(new int[]{1, 2, 2, 3, 3, 3, 3, 4}, 2);
        Assertions.assertEquals(16, output);
    }

    @Test
    public void example2() {
        final int output = solution.sumDivisibleByK(new int[]{1, 2, 3, 4, 5}, 2);
        Assertions.assertEquals(0, output);
    }

    @Test
    public void example3() {
        final int output = solution.sumDivisibleByK(new int[]{4, 4, 4, 1, 2, 3}, 3);
        Assertions.assertEquals(12, output);
    }
}
