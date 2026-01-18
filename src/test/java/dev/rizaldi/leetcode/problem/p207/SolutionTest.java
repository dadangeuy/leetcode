package dev.rizaldi.leetcode.problem.p207;

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

    @Test
    public void wa1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "wa1");
    }

    private Output process(final Input input) {
        final Output output = new Output();
        output.canFinish = solution.canFinish(input.numCourses, input.prerequisites);
        return output;
    }
}

class Input {
    public int numCourses;
    public int[][] prerequisites;
}

class Output {
    public boolean canFinish;
}
