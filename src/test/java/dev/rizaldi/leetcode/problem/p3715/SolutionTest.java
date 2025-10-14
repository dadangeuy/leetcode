package dev.rizaldi.leetcode.problem.p3715;

import dev.rizaldi.leetcode.helper.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    @Timeout(1)
    public void example1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example1");
    }

    @Test
    @Timeout(1)
    public void example2() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example2");
    }

    @Test
    @Timeout(1)
    public void example3() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "example3");
    }

    @Test
    @Timeout(1)
    public void tle1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "tle1");
    }

    @Test
    @Timeout(1)
    public void tle2() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, "tle2");
    }

    private Output process(final Input input) {
        final Output output = new Output();
        output.sumOfAncestors = solution.sumOfAncestors(input.n, input.edges, input.nums);
        return output;
    }
}

class Input {
    public int n;
    public int[][] edges;
    public int[] nums;
}

class Output {
    public long sumOfAncestors;
}
