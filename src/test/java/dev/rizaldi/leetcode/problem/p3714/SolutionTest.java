package dev.rizaldi.leetcode.problem.p3714;

import dev.rizaldi.leetcode.helper.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.File;

public class SolutionTest {
    private static final int TIMEOUT = 1;
    private final File directory = TestHelper.getDirectory(getClass());
    private final Solution solution = new Solution();


    @Test
    @Timeout(TIMEOUT)
    public void example() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, directory, "example1", "example2", "example3");
    }

    @Test
    @Timeout(TIMEOUT)
    public void wa1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, directory, "wa1");
    }

    @Test
    @Timeout(TIMEOUT)
    public void tle1() throws Exception {
        TestHelper.run(this::process, Input.class, Output.class, directory, "tle1");
    }

    private Output process(final Input input) {
        final var result = new Output();
        result.longestBalanced = solution.longestBalanced(input.s);
        return result;
    }
}

class Input {
    String s;
}

class Output {
    int longestBalanced;
}
