package dev.rizaldi.leetcode.contest.bc167.q2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() {
        final var output = solution.longestSubarray(new int[]{1, 1, 1, 1, 2, 3, 5, 1});
        Assertions.assertEquals(5, output);
    }

    @Test
    public void example2() {
        final var output = solution.longestSubarray(new int[]{5, 2, 7, 9, 16});
        Assertions.assertEquals(5, output);
    }

    @Test
    public void example3() {
        final var output = solution.longestSubarray(new int[]{1000000000, 1000000000, 1000000000});
        Assertions.assertEquals(2, output);
    }
}
