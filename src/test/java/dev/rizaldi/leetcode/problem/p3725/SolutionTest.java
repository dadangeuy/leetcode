package dev.rizaldi.leetcode.problem.p3725;

import dev.rizaldi.leetcode.helper.TestHelper;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example1");
    }

    @Test
    public void example2() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example2");
    }

    private Output process(final Input input) {
        final Output output = new Output();
        output.countCoprime = solution.countCoprime(input.mat);
        return output;
    }
}

class Input {
    public int[][] mat;
}

class Output {
    public int countCoprime;
}
