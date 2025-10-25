package dev.rizaldi.leetcode.contest.bc168.q4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void example1() {
        final var output = solution.countCoprime(new int[][]{new int[]{1,2}, new int[]{3, 4}});
        Assertions.assertEquals(3, output);
    }

    @Test
    public void example2() {
        final var output = solution.countCoprime(new int[][]{new int[]{2,2}, new int[]{2, 2}});
        Assertions.assertEquals(0, output);
    }

    @Test
    public void wa1() {
        final var output = solution.countCoprime(new int[][]{new int[]{150}});
        Assertions.assertEquals(0, output);
    }

    @Test
    public void wa2() {
        final var output = solution.countCoprime(new int[][]{new int[]{1}, new int[]{2}, new int[]{2}});
        Assertions.assertEquals(1, output);
    }

    @Test
    public void wa3() {
        final var output = solution.countCoprime(new int[][]{new int[]{2}, new int[]{2}, new int[]{1}});
        Assertions.assertEquals(1, output);
    }

    @Test
    public void wa4() {
        final var output = solution.countCoprime(new int[][]{new int[]{94,130,106,105,14,52,8,44,136,141,19,107,80,36,7,123,140,93,150,21,137,67,42,114,81,98,30,71,27,38,65,114,132,95,82,52,52,10,10,99,116,108,23,133,40,85,123,137,119,50,142,122,13,79,7,131,114,139,39,19,18,25,32,37,21,84,28,112,98,111,124,48,88,35,108,71,44,94,21,40,120,147,101,20,108,96,133,9,39,6,30,31,16,107,58,136,113,116,142,123,18,38,12,23,115,33,137,131,43,78,29,130,52,28,136,115,55,55,92,71,135,22,64,60,142,58,83,68,17,31,13,141,81,55,27,45,140,101,15,140,120,8,28,15,56,94,69,93,80,62}});
        Assertions.assertEquals(0, output);
    }

    @Test
    public void tle1() {
        final var output = solution.countCoprime(new int[][]{
            new int[]{46,54,97,75,3,36,39,70,86,87},
            new int[]{95,24,87,10,11,70,42,39,150,75},
            new int[]{93,102,141,34,76,30,123,62,13,79},
            new int[]{46,134,19,78,104,85,77,107,28,26},
            new int[]{144,124,122,87,88,32,123,30,128,110},
            new int[]{10,78,86,40,43,145,97,23,17,22},
            new int[]{51,57,16,99,3,26,101,143,133,75},
        });
        Assertions.assertEquals(9936340, output);
    }
}
