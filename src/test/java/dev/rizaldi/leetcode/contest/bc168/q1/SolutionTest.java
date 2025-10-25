package dev.rizaldi.leetcode.contest.bc168.q1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void sample1() {
        final var output = solution.lexSmallest("dcab");
        Assertions.assertEquals("acdb", output);
    }

    @Test
    public void sample2() {
        final var output = solution.lexSmallest("abba");
        Assertions.assertEquals("aabb", output);
    }

    @Test
    public void sample3() {
        final var output = solution.lexSmallest("zxy");
        Assertions.assertEquals("xzy", output);
    }
}
