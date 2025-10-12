package dev.rizaldi.leetcode.problem.p3710;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    @Test
    public void example1() {
        final var solution = new Solution();
        final var output = solution.maxPartitionFactor(
                new int[][]{
                        new int[]{0, 0},
                        new int[]{0, 2},
                        new int[]{2, 0},
                        new int[]{2, 2},
                }
        );
        Assertions.assertEquals(4, output);
    }

    @Test
    public void example2() {
        final var solution = new Solution();
        final var output = solution.maxPartitionFactor(
                new int[][]{
                        new int[]{0, 1},
                        new int[]{10, 0},
                        new int[]{0, 0},
                }
        );
        Assertions.assertEquals(11, output);
    }
}
