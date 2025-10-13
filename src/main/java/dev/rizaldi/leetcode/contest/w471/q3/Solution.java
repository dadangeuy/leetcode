package dev.rizaldi.leetcode.contest.w471.q3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>Verdict: Wrong Answer</p>
 * <p>Refer to {@link dev.rizaldi.leetcode.problem.p3714.Solution} for Accepted solution</p>
 */
public class Solution {
    public int longestBalanced(String s) {
        // case 1: single char (a, b, c)
        int maxLengthSingle = 0;
        for (char letter = 'a'; letter <= 'c'; letter++) {
            int length = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == letter) length++;
                else length = 0;
                maxLengthSingle = Math.max(maxLengthSingle, length);
            }
        }

        // case 2: double char (ab, ac, bc)
        int maxLengthDouble = 0;
        for (String letters : Arrays.asList("ab", "ac", "bc")) {
            final int[] lengths = new int[]{0, 0};
            for (int i = 0; i < s.length(); i++) {
                final char letter = s.charAt(i);
                if (letter == letters.charAt(0)) {
                    lengths[0]++;
                } else if (letter == letters.charAt(1)) {
                    lengths[1]++;
                } else {
                    lengths[0] = lengths[1] = 0;
                }

                if (lengths[0] == lengths[1]) {
                    final int length = lengths[0] + lengths[1];
                    maxLengthDouble = Math.max(maxLengthDouble, length);
                }
            }
        }

        // case 3: triple char (abc)
        int maxLengthTriple = 0;
        {
            final String letters = "abc";
            int[] lengths = new int[]{0, 0, 0};
            for (int i = 0; i < s.length(); i++) {
                final char letter = s.charAt(i);
                if (letter == letters.charAt(0)) {
                    lengths[0]++;
                } else if (letter == letters.charAt(1)) {
                    lengths[1]++;
                } else if (letter == letters.charAt(2)) {
                    lengths[2]++;
                } else {
                    lengths[0] = lengths[1] = lengths[2] = 0;
                }

                if (lengths[0] == lengths[1] && lengths[1] == lengths[2]) {
                    final int length = lengths[0] + lengths[1];
                    maxLengthDouble = Math.max(maxLengthDouble, length);
                }
            }
        }

        return Math.max(maxLengthSingle, Math.max(maxLengthDouble, maxLengthTriple));
    }
}

class Count {
    public static final Comparator<Count> ORDER_BY_A_B_C = Comparator
            .comparingInt((Count c) -> c.countA)
            .thenComparingInt((Count c) -> c.countB)
            .thenComparingInt((Count c) -> c.countC);
    public static final Count ZERO = new Count(0, 0, 0);

    public final int countA;
    public final int countB;
    public final int countC;

    public Count(final int countA, final int countB, final int countC) {
        this.countA = countA;
        this.countB = countB;
        this.countC = countC;
    }

    public Count increment(final char letter) {
        return switch (letter) {
            case 'a' -> incrementA();
            case 'b' -> incrementB();
            default -> incrementC();
        };
    }

    public Count incrementA() {
        return new Count(countA + 1, countB, countC);
    }

    public Count incrementB() {
        return new Count(countA, countB + 1, countC);
    }

    public Count incrementC() {
        return new Count(countA, countB, countC + 1);
    }

    public Count minify() {
        final int min = Math.min(countA, Math.min(countB, countC));
        return new Count(countA - min, countB - min, countC - min);
    }

    public Count reverse() {
        final int max = Math.max(countA, Math.max(countB, countC));
        return new Count(max - countA, max - countB, max - countC);
    }
}
