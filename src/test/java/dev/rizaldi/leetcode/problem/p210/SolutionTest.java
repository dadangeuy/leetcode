package dev.rizaldi.leetcode.problem.p210;

import dev.rizaldi.leetcode.helper.TestHelper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example1");
    }

    @Test
    @Disabled("non-deterministic output")
    public void example2() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example2");
    }

    @Test
    public void example3() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example3");
    }

    private Output process(final Input input) {
        final Output output = new Output();
        output.findOrder = solution.findOrder(input.numCourses, input.prerequisites);
        return output;
    }
}

class Input {
    public int numCourses;
    public int[][] prerequisites;
}

class Output {
    public int[] findOrder;
}
