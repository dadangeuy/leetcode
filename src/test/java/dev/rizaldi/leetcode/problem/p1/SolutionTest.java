package dev.rizaldi.leetcode.problem.p1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class SolutionTest {
    private final Solution solution = new Solution();
    private final TestCase[] exampleTestCases = new TestCase[]{
            new TestCase(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
            new TestCase(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
            new TestCase(new int[]{3, 3}, 6, new int[]{0, 1}),
    };

    @Test
    @Timeout(1)
    public void example() {
        for (final TestCase testCase : exampleTestCases) {
            testCase.validate(solution);
        }
    }
}

class TestCase {
    final int[] nums;
    final int target;
    final int[] indices;

    TestCase(
            final int[] nums,
            final int target,
            final int[] indices
    ) {
        this.nums = nums;
        this.target = target;
        this.indices = indices;
    }

    void validate(final Solution solution) {
        final var indices = solution.twoSum(nums, target);
        Assertions.assertArrayEquals(this.indices, indices);
    }
}
