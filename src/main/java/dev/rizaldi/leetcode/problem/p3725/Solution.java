package dev.rizaldi.leetcode.problem.p3725;

/**
 * <p>3725. Count Ways to Choose Coprime Integers from Rows</p>
 * <p>Difficulty: Hard</p>
 * <p>Verdict: Accepted</p>
 * <p>Link: <a href="https://leetcode.com/problems/count-ways-to-choose-coprime-integers-from-rows/description/">LeetCode</a></p>
 */
public class Solution {
    private static final int MODULO = 1_000_000_000 + 7;
    private static final int MAX_VALUE = 150;
    private static final Integer[][] CACHE_GCD = new Integer[MAX_VALUE + 1][MAX_VALUE + 1];

    public int countCoprime(int[][] mat) {
        final int[][] countPerRowAndGCD = new int[mat.length + 1][MAX_VALUE + 1];

        for (int column = 0; column < mat[0].length; column++) {
            final int value = mat[0][column];
            countPerRowAndGCD[0][value]++;
        }

        for (int i = 1; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                for (int k = 0; k <= MAX_VALUE; k++) {
                    final int value = mat[i][j];
                    final int gcd = gcd(value, k);
                    final int count = countPerRowAndGCD[i - 1][k];

                    countPerRowAndGCD[i][gcd] += count;
                    countPerRowAndGCD[i][gcd] %= MODULO;
                }
            }
        }

        return countPerRowAndGCD[mat.length - 1][1];
    }

    /**
     * <p>Euclidean algorithm for computing the greatest common divisor</p>
     * <a href="https://cp-algorithms.com/algebra/euclid-algorithm.html">Algorithms for Competitive Programming</a>
     */
    private int gcd(final int first, final int second) {
        if (second == 0) return first;

        if (CACHE_GCD[first][second] == null) {
            final int remainder = first % second;
            final int gcd = gcd(second, remainder);
            CACHE_GCD[first][second] = gcd;
        }
        return CACHE_GCD[first][second];
    }
}
