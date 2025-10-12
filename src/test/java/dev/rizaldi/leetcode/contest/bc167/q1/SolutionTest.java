package dev.rizaldi.leetcode.contest.bc167.q1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    public void failed1() {
        final var solution = new Solution();
        final var output = solution.scoreBalance("edb");
        Assertions.assertEquals(false, output);
    }

    @Test
    public void example1() {
        final var solution = new Solution();
        final var output = solution.scoreBalance("adcb");
        Assertions.assertEquals(true, output);
    }
}
