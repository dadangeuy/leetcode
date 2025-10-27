package dev.rizaldi.leetcode.contest.bc168.q4;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * <p>Verdict: Wrong Answer</p>
 * <p>Refer to {@link dev.rizaldi.leetcode.problem.p3725.Solution} for Accepted solution</p>
 */
public class Solution {
    private static final long MODULO = 1000000007;
    private static final int[] PRIMES = getPrimes(150);

    private static int[] getPrimes(final int max) {
        final boolean[] isPrimes = new boolean[max + 1];
        Arrays.fill(isPrimes, true);
        isPrimes[0] = false;
        isPrimes[1] = true;

        for (int i = 2; i <= max; i++) {
            if (!isPrimes[i]) continue;
            for (int j = i + i; j <= max; j = j + i) {
                isPrimes[j] = false;
            }
        }

        return IntStream.rangeClosed(1, max).filter(i -> isPrimes[i]).toArray();
    }

    public int countCoprime(int[][] mat) {
        final Map<Integer, Map<Long, Integer>> countPerFactorsPerRow = new HashMap<>();

        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[row].length; col++) {
                final long factors = bitmaskFactors(mat[row][col]);
                countPerFactorsPerRow
                    .computeIfAbsent(row, k -> new HashMap<>())
                    .compute(factors, (k, v) -> v == null? 1 : v + 1);
            }
        }

        final LinkedList<Long> factorsq = new LinkedList<>();
        final LinkedList<Long> gcdq = new LinkedList<>();
        final LinkedList<Integer> rowq = new LinkedList<>();
        final LinkedList<Long> permutationq = new LinkedList<>();

        for (final Map.Entry<Long, Integer> entry : countPerFactorsPerRow.getOrDefault(0, Collections.emptyMap()).entrySet()) {
            final long factors = entry.getKey();
            final int count = entry.getValue();

            factorsq.addLast(factors);
            gcdq.addLast(factors);
            rowq.addLast(0);
            permutationq.addLast((long) count % MODULO);
        }

        long totalPermutation = 0;

        while (!factorsq.isEmpty()) {
            final long factors = factorsq.removeFirst();
            final long gcd = gcdq.removeFirst();
            final int row = rowq.removeFirst();
            final long permutation = permutationq.removeFirst();

            final int nextRow = row + 1;

            if (nextRow == mat.length) {
                if (gcd > 1L) {
                    continue;
                } else {
                    totalPermutation += permutation;
                    totalPermutation %= MODULO;
                    continue;
                }
            }

            for (final Map.Entry<Long, Integer> entry : countPerFactorsPerRow.getOrDefault(nextRow, Collections.emptyMap()).entrySet()) {
                final long otherFactors = entry.getKey();
                final int otherCount = entry.getValue();

                final long nextGCD = gcd & otherFactors;
                final long nextFactors = factors | otherFactors;
                factorsq.addLast(nextFactors);
                gcdq.addLast(nextGCD);
                rowq.addLast(nextRow);
                permutationq.addLast(permutation * otherCount);
            }
        }



        return (int) totalPermutation;
    }

    private long bitmaskFactors(final int number) {
        long bitmask = 0;
        for (int i = 0; i < PRIMES.length; i++) {
            if (number % PRIMES[i] == 0) {
                final long bit = 1L << i;
                bitmask |= bit;
            }
        }
        return bitmask;
    }
}
