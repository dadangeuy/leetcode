package dev.rizaldi.leetcode.contest.bc168.q3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() {
        final var output = solution.minOperations(new int[]{2, 8}, new int[]{1, 7, 3});
        Assertions.assertEquals(4L, output);
    }

    @Test
    public void example2() {
        final var output = solution.minOperations(new int[]{1, 3, 6}, new int[]{2,4,5,3});
        Assertions.assertEquals(4L, output);
    }

    @Test
    public void example3() {
        final var output = solution.minOperations(new int[]{2}, new int[]{3, 4});
        Assertions.assertEquals(3L, output);
    }
}
